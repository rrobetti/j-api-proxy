# ADR 0001: Relationship with datasource-proxy

## Status

Accepted

## Context

[datasource-proxy](https://github.com/jdbc-observations/datasource-proxy) is a mature, widely used
library that wraps JDBC `DataSource`/`Connection`/`Statement` objects to provide query logging, SQL
formatting, parameter capture, query execution metrics, repeatable `ResultSet`s and generated-key
handling. It has no dependencies of its own and is a well-established tool for JDBC observability.

J API Proxy set out to solve a different, narrower problem: provide **one generic, recursive
filter-chain mechanism** (`InvocationFilter` / `InvocationChain` / `InterfaceProxy`) that can be
applied uniformly across JDBC, Jakarta JMS, and XA object graphs, so that a single filter
implementation (e.g. a metrics filter, a fault-injection filter, a read-only guard) can be attached
to *any* of these technologies without technology-specific glue code.

## What datasource-proxy already provides

- Query and parameter logging (SLF4J, JUL, commons-logging, System.out listeners).
- SQL formatting / pretty-printing.
- `QueryExecutionListener` and `MethodExecutionListener` hooks scoped specifically to JDBC.
- Repeatable `ResultSet` proxies that buffer rows for re-iteration in tests.
- Generated-key capture helpers.
- A mature, JDBC-only proxy implementation with years of production hardening.

## What J API Proxy adds

- A small, dependency-free core (`j-api-proxy-core`) with a generic filter chain
  (`InvocationFilter`/`InvocationChain`) and recursive-wrapping SPI
  (`ReturnValueAdapter`/`ArgumentAdapter`) that is **not** JDBC-specific.
- The *same* filter model reused, unmodified, across JDBC (`j-api-proxy-jdbc`) and Jakarta JMS
  (`j-api-proxy-jms-jakarta`) object graphs, including XA resources
  (`javax.transaction.xa.XAResource`) for both technologies.
- First-class support for JMS asynchronous callbacks (`MessageListener`, `CompletionListener`,
  `ExceptionListener`) being wrapped so filters observe inbound async callbacks, not just outbound
  API calls — something outside datasource-proxy's JDBC-only scope.
- Deliberately small surface area: no SQL parsing, no query logging, no repeatable result sets — see
  "Non-duplicated features" below.

## Why composition was selected over reimplementation or forking

1. **Avoid duplicating a mature project.** datasource-proxy already solves JDBC-specific query
   observability well. Reimplementing it would add risk and maintenance burden for no benefit.
2. **Keep the core reusable.** If JDBC-specific concerns (SQL capture, formatting) leaked into
   `j-api-proxy-core`, the core could no longer be shared cleanly with the JMS adapter, violating the
   project's central goal of "one filter model for JDBC + JMS + XA."
3. **Respect licensing and attribution.** datasource-proxy is Apache-2.0 licensed. Copying its
   source without retaining license/attribution would be improper; composing with it (or bridging to
   it) as a separate dependency avoids this entirely.
4. **Optionality.** Users who only need datasource-proxy's JDBC logging can keep using it directly and
   unmodified. Users who need J API Proxy's cross-technology filter model can compose the two via a
   thin, optional bridge module (`j-api-proxy-datasource-proxy-bridge`) without forking either
   project.

## JDBC features deliberately not duplicated

Per the project's non-goals, J API Proxy does **not** implement:

- Query/SQL logging (already provided by datasource-proxy's `QueryExecutionListener` and logging
  listeners).
- SQL transformation or formatting.
- Parameter capture for logging purposes.
- Query execution metrics/histograms (users can implement this themselves with an
  `InvocationFilter`, but J API Proxy does not ship a pre-built implementation).
- Repeatable `ResultSet`s (test-time buffering of result rows).
- Generated-key capture helpers.

## Consequences

- `j-api-proxy-core` remains free of JDBC/JMS/XA-testing/framework dependencies.
- Users wanting SQL logging should compose datasource-proxy *underneath or alongside* J API Proxy
  (e.g. wrap a datasource-proxy `DataSource` with `JdbcProxy.wrap(...)`, or vice versa) rather than
  expecting J API Proxy to reimplement that functionality.
- A dedicated bridge module may be added later if it can remain small; it is out of scope for the
  initial vertical slice delivered by this change.
