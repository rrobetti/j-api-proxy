# Contributing to j-api-proxy

Thanks for your interest in improving j-api-proxy.

## Before you start

- Read the project README to understand the scope and design goals.
- Keep changes aligned with the project's intentionally small API surface.
- For behavior, bug fixes, or new features, prefer opening an issue or starting a discussion before investing in a large change.

## Development setup

### Requirements

- Java 17
- Maven 3.9+ recommended

### Build and test

From the repository root:

```bash
mvn test
```

The project is a Maven multi-module build and its tests rely on in-repository fakes, so no external database, message broker, or Docker setup is required for normal test runs.

## Contribution workflow

1. Fork the repository and create a focused branch.
2. Make the smallest change that fully addresses the issue.
3. Add or update tests when production behavior changes.
4. Run the relevant Maven tests locally before opening a pull request.
5. Update documentation when behavior, public APIs, or contributor workflows change.

## Pull request expectations

Please keep pull requests easy to review:

- Use a clear title and summary.
- Describe the problem being solved and the approach taken.
- Call out any trade-offs, limitations, or follow-up work.
- Keep unrelated refactors out of the same pull request.

## Coding guidelines

- Preserve backward compatibility unless the change is explicitly intended to break it.
- Prefer clear, dependency-light solutions that fit the project's current architecture.
- Avoid introducing framework-style complexity into the core module.
- Follow existing naming, package, and test patterns in the module you are changing.

## Testing guidance

- Add regression tests for bug fixes whenever practical.
- Keep tests deterministic and self-contained.
- Prefer the same fake-based approach already used in the test suite over requiring external infrastructure.

## Documentation guidance

Update the relevant documentation when you change:

- public APIs
- supported behaviors or limitations
- build, release, or contribution workflows

## Code of conduct

By participating in this project, you agree to follow the guidelines in [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md).
