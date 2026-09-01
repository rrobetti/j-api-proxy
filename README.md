# j-api-proxy (J API Proxy)

A small, general-purpose filter-chain core built on JDK dynamic proxies, plus recursive adapters
that automatically apply it across a JDBC or Jakarta JMS object graph — including XA resources.

## What J API Proxy is

- One consistent, dependency-free filter model (`InvocationFilter` / `InvocationChain` /
  `InterfaceProxy`) for intercepting calls on standard Java interfaces via JDK dynamic proxies.
- A recursive JDBC adapter that wraps the whole `DataSource → Connection → Statement → ResultSet /
  DatabaseMetaData` graph (and the XA equivalent) with one call.
- A recursive Jakarta JMS adapter that wraps the whole `ConnectionFactory → Connection → Session →
  MessageProducer/MessageConsumer/...` graph, `JMSContext`, and JMS XA resources, including
  asynchronous callback arguments (`MessageListener`, `CompletionListener`, `ExceptionListener`).
- A clean SPI (`ReturnValueAdapter`, `ArgumentAdapter`) that monitoring, tracing, fault-injection,
  and testing libraries can build on.

## What J API Proxy is not

- **Not a general AOP framework.** The core stays intentionally small: no annotations, no
  bytecode instrumentation, no class proxies — JDK dynamic proxies over standard interfaces only.
- **Not a connection pool, SQL parser, or query logger.** See
  [`docs/adr/0001-relationship-with-datasource-proxy.md`](docs/adr/0001-relationship-with-datasource-proxy.md)
  for what is deliberately left to [datasource-proxy](https://github.com/jdbc-observations/datasource-proxy).
- **Not a transaction manager or XA fault-injection engine.** XA interception is provided so that
  consumers (e.g. a hypothetical "J XA Tester") can build fault scenarios on top of it; this
  library does not implement scenario engines, recovery orchestration, or Toxiproxy integration.
- **Not a legacy `javax.jms` adapter.** Only Jakarta JMS (`jakarta.jms.*`) is supported today; the
  module layout allows a separate legacy adapter to be added later without touching the core.

## Modules

| Module | Purpose |
|---|---|
| `j-api-proxy-parent` | Parent POM: Java 17, JUnit 5, Apache-2.0, shared plugin/version management. |
| `j-api-proxy-core` | The generic filter-chain core. No JDBC/JMS/XA-testing/framework dependencies. |
| `j-api-proxy-jdbc` | Recursive JDBC + XA adapter (`JdbcProxy`, `JdbcProxyOptions`). |
| `j-api-proxy-jms-jakarta` | Recursive Jakarta JMS + XA adapter (`JmsProxy`, `JmsProxyOptions`). |
| `j-api-proxy` | Convenience module depending on the JDBC and Jakarta JMS adapters. |
| `j-api-proxy-bom` | Bill of materials for consistent version alignment across modules. |

## Maven dependency examples

Core only (build your own adapter/SPI usage):

```xml
<dependency>
  <groupId>io.github.rrobetti</groupId>
  <artifactId>j-api-proxy-core</artifactId>
  <version>0.1.0-SNAPSHOT</version>
</dependency>
```

JDBC adapter:

```xml
<dependency>
  <groupId>io.github.rrobetti</groupId>
  <artifactId>j-api-proxy-jdbc</artifactId>
  <version>0.1.0-SNAPSHOT</version>
</dependency>
```

Jakarta JMS adapter:

```xml
<dependency>
  <groupId>io.github.rrobetti</groupId>
  <artifactId>j-api-proxy-jms-jakarta</artifactId>
  <version>0.1.0-SNAPSHOT</version>
</dependency>
```

Convenience module (both adapters) or the BOM:

```xml
<dependency>
  <groupId>io.github.rrobetti</groupId>
  <artifactId>j-api-proxy</artifactId>
  <version>0.1.0-SNAPSHOT</version>
</dependency>
```

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>io.github.rrobetti</groupId>
      <artifactId>j-api-proxy-bom</artifactId>
      <version>0.1.0-SNAPSHOT</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>
```

## Filter-chain example

```java
InvocationFilter timingFilter = (invocation, chain) -> {
    long started = System.nanoTime();
    try {
        return chain.proceed(invocation);
    } finally {
        metrics.record(invocation.method(), System.nanoTime() - started);
    }
};

MyService proxy = InterfaceProxy.builder(MyService.class, delegate)
    .resourceName("orders-db")
    .filter(timingFilter)
    .build();
```

`InvocationContext` exposes the proxy, the original delegate, the invoked `Method`, mutable
arguments, the declared return type, a per-invocation attribute map (for sharing state between
before/after logic), an optional resource name, the parent proxy/object, and the logical interface
being intercepted. Filters can inspect/replace arguments, run logic before delegation, call
`chain.proceed()` (or skip it to short-circuit), replace the result, replace or rethrow exceptions,
and run logic after success or failure (typically in a `finally` block).

## JDBC example

```java
DataSource wrapped = JdbcProxy.wrap(vendorDataSource, "orders-db", timingFilter);

try (Connection connection = wrapped.getConnection();
     Statement statement = connection.createStatement();
     ResultSet resultSet = statement.executeQuery("select 1")) {
    resultSet.next(); // transparently proxied, same timingFilter observes this call too
}
```

```java
XADataSource xaWrapped = JdbcProxy.wrapXa(vendorXaDataSource, "orders-db", timingFilter);
```

## Jakarta JMS example

```java
ConnectionFactory wrapped = JmsProxy.wrap(vendorConnectionFactory, "payments-mq", timingFilter);

try (Connection connection = wrapped.createConnection()) {
    Session session = connection.createSession();
    MessageConsumer consumer = session.createConsumer(destination);
    consumer.setMessageListener(message -> handle(message)); // the listener itself is wrapped too
}
```

```java
XAConnectionFactory xaWrapped = JmsProxy.wrapXa(vendorXaConnectionFactory, "payments-mq", timingFilter);
```

## XA interception example

```java
InvocationFilter xaAuditFilter = (invocation, chain) -> {
    if (invocation.interfaceType() == XAResource.class) {
        auditLog.record(invocation.method().getName());
    }
    return chain.proceed(invocation);
};

XAResource xaResource = someWrappedXAConnection.getXAResource(); // already carries xaAuditFilter
```

`isSameRM(XAResource)` automatically unwraps a J API Proxy argument before delegating to the
vendor resource, so resource-manager identity semantics are unaffected by wrapping.
`XAException.errorCode` and the original `Xid` instance passed by callers are preserved unchanged;
XIDs are never logged or retained by the library itself.

## Object-tree diagrams

JDBC:

```
DataSource
  └─ Connection
       ├─ Statement
       ├─ PreparedStatement
       ├─ CallableStatement
       │     └─ ResultSet (from executeQuery/getResultSet/getGeneratedKeys)
       └─ DatabaseMetaData

XADataSource
  └─ XAConnection
       ├─ Connection (as above)
       └─ XAResource
```

Jakarta JMS:

```
ConnectionFactory
  └─ Connection
       └─ Session
            ├─ MessageProducer
            ├─ MessageConsumer
            ├─ QueueBrowser
            ├─ TemporaryQueue
            └─ TemporaryTopic

JMSContext
  ├─ JMSProducer
  └─ JMSConsumer

XAConnectionFactory
  ├─ XAConnection ─ XASession ─ XAResource
  └─ XAJMSContext ─ XAResource
```

## Relationship with datasource-proxy

[datasource-proxy](https://github.com/jdbc-observations/datasource-proxy) already provides mature
JDBC query logging, SQL formatting, parameter capture, execution metrics, repeatable result sets,
and generated-key handling. J API Proxy does not duplicate any of that. Instead, it contributes a
single filter model that is reused, unmodified, across JDBC, Jakarta JMS, and XA. See
[`docs/adr/0001-relationship-with-datasource-proxy.md`](docs/adr/0001-relationship-with-datasource-proxy.md)
for the full architecture decision record, including which JDBC features are deliberately not
duplicated and why composition (rather than forking or reimplementing) was chosen.

## Vendor-cast limitation

Only standard `java.sql`, `javax.sql`, `jakarta.jms`, and `javax.transaction.xa` interfaces are
proxied. **Casting a wrapped object to a vendor-specific implementation class is not supported and
will fail** (e.g. `ClassCastException`) because the returned object is a JDK dynamic proxy, not an
instance of the vendor's class. If you need vendor-specific behavior, use `unwrap(...)` (JDBC) to
obtain the real delegate first — see the warning below.

## `unwrap()` bypass warning

Calling `unwrap()` (JDBC `Wrapper.unwrap(Class)`, or `InterfaceProxy.unwrap(Object)` /
`ProxyContext.unwrap(Object)` directly) returns the original vendor delegate. **Any calls made
directly on that unwrapped object bypass all J API Proxy filters** — monitoring, fault injection,
and any other cross-cutting behavior will not observe those calls. This is intentional (it is the
only way to reach vendor-specific functionality) but must be used deliberately.

## Performance controls

Interception depth can be limited to avoid unnecessary proxying/allocation on hot paths such as
`ResultSet.next()`, result-set getters, or message property getters:

```java
JdbcProxyOptions options = JdbcProxyOptions.builder()
    .connections(true)
    .statements(true)
    .resultSets(false)
    .build();

DataSource wrapped = JdbcProxy.wrap(vendorDataSource, "orders-db", options, timingFilter);
```

```java
JmsProxyOptions options = JmsProxyOptions.builder()
    .connections(true)
    .sessions(true)
    .consumersProducers(false)
    .build();

ConnectionFactory wrapped = JmsProxy.wrap(vendorConnectionFactory, "payments-mq", options, timingFilter);
```

A simple repeatable performance smoke test
(`j-api-proxy-core/src/test/java/.../PerformanceSmokeTest.java`) reports the approximate
per-call overhead of a proxy with no filters, one no-op filter, and several filters, compared to a
direct call. On a typical development machine this shows single-digit to low-hundreds of
nanoseconds of overhead per call — enough to be negligible for typical JDBC/JMS call latencies, but
this is a smoke test, not a rigorous JMH benchmark, and no production-suitability claim should be
inferred from it.

## Thread-safety guarantees

- `InterfaceProxy` proxies are safe for concurrent invocation from multiple threads; each
  invocation gets its own `InvocationContext` and argument array copy.
- `ProxyContext`'s identity-based proxy cache is internally synchronized and safe for concurrent
  `wrap()` calls.
- Filters themselves are responsible for their own thread-safety if they hold shared mutable state
  (the example `MonitoringFilter` uses concurrent collections for this reason).

## Security and privacy behaviour

- J API Proxy does not log SQL text, bind parameters, result values, credentials, message bodies,
  or XID data by default. Any such recording is entirely opt-in, via a user-installed
  `InvocationFilter`.
- The library preserves original exceptions (including `SQLException`/`XAException.errorCode`)
  without wrapping them in `InvocationTargetException`.

## One filter used across JDBC, JMS, and XA

```java
MonitoringFilter monitoring = new MonitoringFilter(); // io.github.rrobetti.japiproxy.core.examples

DataSource jdbc = JdbcProxy.wrap(vendorDataSource, "orders-db", monitoring);
ConnectionFactory jms = JmsProxy.wrap(vendorConnectionFactory, "payments-mq", monitoring);
XADataSource jdbcXa = JdbcProxy.wrapXa(vendorXaDataSource, "orders-db", monitoring);

// the same `monitoring` instance now observes calls across all three technologies
```

See `MonitoringFilter`, and the inline `readOnly`/`replacement` filter examples below, for
demonstrations of common patterns:

```java
InvocationFilter readOnly = (invocation, chain) -> {
    if (invocation.method().getName().equals("commit")) {
        throw new SQLException("Commit disabled by test filter");
    }
    return chain.proceed(invocation);
};

InvocationFilter replacement = (invocation, chain) -> {
    Object result = chain.proceed(invocation);
    if (invocation.method().getName().equals("getTransactionTimeout")) {
        return 30;
    }
    return result;
};
```

## Building and testing

```
mvn test
```

runs the full test suite (core, JDBC, Jakarta JMS) using hand-written fakes/test doubles only — no
database, broker, or Docker daemon is required.

## CI/CD and releases

Continuous integration builds and tests every push to `main` and every approved pull request.
Releases are published to Maven Central on demand, after approval by @rrobetti.
See [docs/ci-cd.md](docs/ci-cd.md) for details.

## License

Apache License 2.0. See [LICENSE](LICENSE).
