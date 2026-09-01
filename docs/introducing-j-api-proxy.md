# Meet J API Proxy

When an application talks to a database or a message broker, a lot happens
between the first call and the final result. A connection opens, a statement
runs, a message is delivered, or a transaction is completed. Those are useful
places to collect timing data, add tracing, test failures, or apply a rule that
your application needs. The difficulty is not usually seeing the first call; it
is keeping that behavior in place while the application moves from a data
source to a connection, from a connection to a statement, and then to every
object returned along the way. J API Proxy gives you one simple way to do that.

## Why it exists

It is common to start with a small wrapper around a `DataSource` or a JMS
`ConnectionFactory`. That wrapper soon needs to deal with the objects returned
by the API too. A database request can pass through a connection, statement,
result set, metadata object, and sometimes an XA resource. A message can travel
through a connection, session, producer or consumer, while asynchronous
listeners bring calls back into the application. Writing and maintaining
separate wrappers for each layer is repetitive, and it is easy for one path to
miss the behavior that the first wrapper intended to provide.

The problem becomes more noticeable when an application needs the same idea in
more than one place. A team may want a timing filter for JDBC and Jakarta JMS,
or a test that makes an XA operation fail at a precise moment. Building one
implementation for the database and another for messaging means the rules
drift apart over time. J API Proxy was created to provide one small,
technology-neutral filter model that can follow both object graphs.

At its heart, J API Proxy wraps standard Java interfaces with JDK dynamic
proxies. You provide a small filter that sees a method call, can do work before
or after that call, and then lets the original object continue. The same filter
model works with JDBC, Jakarta JMS, and XA resources, so you do not have to
learn a separate interception approach for every kind of integration.

```mermaid
flowchart LR
    App[Your application] --> Proxy[J API Proxy]
    Proxy --> Filter[Your filter]
    Filter --> Service[Database or message broker]
    Service --> Filter
    Filter --> App
```

The useful part is that the wrapping does not stop at the first object. Wrap a
JDBC `DataSource`, for example, and J API Proxy also follows the objects it
returns. Connections, statements, result sets, metadata, and XA resources keep
the same filter as your code moves through the database API. The Jakarta JMS
adapter does the equivalent work for connection factories, sessions, producers,
consumers, contexts, and asynchronous listeners. You wrap the entry point once
and the rest of the object tree is covered as it is created.

```mermaid
flowchart TD
    DS[DataSource] --> C[Connection]
    C --> S[Statement]
    S --> R[ResultSet]
    C --> M[DatabaseMetaData]
    CF[ConnectionFactory] --> JC[Connection]
    JC --> JS[Session]
    JS --> P[MessageProducer]
    JS --> N[MessageConsumer]
```

## What using it feels like

This makes J API Proxy a good fit when you need behavior that crosses several
technologies. A timing filter can measure both database calls and message
operations. A test filter can simulate a failure at a chosen point. A tracing
filter can carry context through an outbound API call and an incoming JMS
callback. Since filters receive the method, arguments, return type, resource
name, and a per-call attribute map, they have the context they need without
forcing the rest of your application to change.

For example, the application can wrap its existing data source with
`JdbcProxy.wrap(vendorDataSource, "orders-db", timingFilter)`. The returned
`DataSource` is still used in the normal JDBC way, but calls to objects it
creates pass through `timingFilter` as well. The Jakarta JMS adapter follows the
same pattern with `JmsProxy.wrap`, so the filter code remains the same even
though the application is now talking to a message broker. The README includes
complete, copyable examples for both adapters and their XA counterparts.

Filters can simply observe calls, but they are not limited to observation. They
can adjust an argument before the delegate receives it, replace a result, or
choose not to continue a call. That makes the approach useful for carefully
scoped policy enforcement and fault-injection tests as well as metrics. The
original objects still do the real work; the proxy adds a well-defined place
for your application-specific behavior.

## Why not use an existing solution?

There are already excellent tools for parts of this problem. In the JDBC
world, [datasource-proxy](https://github.com/jdbc-observations/datasource-proxy)
is a mature choice for query logging, SQL formatting, parameter capture,
execution metrics, repeatable result sets, and generated-key handling. General
AOP tools can also intercept calls in an application when annotation-based or
class-level interception is the right fit. Vendor libraries may offer their own
wrappers for a particular database or broker.

Those solutions solve valuable problems, but their focus is different. A JDBC
tool naturally concentrates on JDBC and SQL, while a vendor wrapper generally
stays within that vendor's API. A general AOP framework offers a broader model
than is needed when the goal is to wrap standard integration interfaces and
continue wrapping what they return. None of those roles needs to be replaced.

J API Proxy fills the narrower gap between them: one dependency-free filter
model, applied recursively to standard JDBC, Jakarta JMS, and XA interfaces.
It intentionally does not reproduce SQL parsing, query logging, connection
pooling, transaction management, bytecode instrumentation, or class proxies.
For JDBC-focused observability, applications can use datasource-proxy alongside
J API Proxy rather than choosing one instead of the other. This separation
keeps J API Proxy small while allowing the same custom filter to work across
database, messaging, and XA code.

## Getting started

Getting started is straightforward: add the module that matches your use case,
write an `InvocationFilter`, and wrap the JDBC or Jakarta JMS entry point. The
[project README](../README.md) shows the Maven coordinates and small examples
for the core, JDBC, Jakarta JMS, and XA APIs. If your application already uses
standard interfaces, J API Proxy can help you observe and shape those calls
without replacing the objects and libraries you already trust. When vendor-only
behavior is needed, unwrap the delegate deliberately, knowing that direct calls
to it will bypass the filters.
