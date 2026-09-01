# CI/CD and release process

This project uses two GitHub Actions workflows, modelled on the
[OJP](https://github.com/open-J-Proxy/ojp) setup.

| Workflow | File | Trigger |
| --- | --- | --- |
| Main CI | [`.github/workflows/main.yml`](../.github/workflows/main.yml) | push to `main`, pull request approval, manual |
| Release to Maven Central | [`.github/workflows/release.yml`](../.github/workflows/release.yml) | manual, approval-gated |

## Main CI

`Main CI` builds and tests the whole Maven reactor with JDK 17
(`mvn --batch-mode verify`). No database, message broker or Docker daemon is
needed because the tests use hand-written fakes.

It runs:

* **On every push to `main`** — so anything merged into `main`, including pull
  request merges, is always built and tested.
* **On pull request approval** — the `pull_request_review` trigger fires when a
  review is submitted, and the job only runs when
  `github.event.review.state == 'approved'`. The workflow checks out
  `github.event.pull_request.head.sha`, i.e. the exact commit that was approved.
  Pushing new commits after an approval therefore does not re-run CI: request a
  new review (or use the manual trigger) to validate the updated branch.
* **Manually** — `workflow_dispatch`, from Actions → *Main CI* → *Run workflow*,
  for any branch.

Runs are grouped per branch/pull request and superseded runs are cancelled.
Surefire reports are uploaded as the `surefire-reports` artifact.

## Release to Maven Central

`Release to Maven Central` publishes all modules to
[Sonatype Central](https://central.sonatype.com/) under the
`io.github.rrobetti` group id, on command and only after approval.

### Approval gate

The job declares `environment: release`. The `release` environment must be
configured in *Settings → Environments* with **@rrobetti as a required
reviewer**. When the workflow is dispatched, the run waits in the
*Waiting for review* state and nothing is built or published until @rrobetti
approves it.

### Required repository secrets

| Secret | Description |
| --- | --- |
| `SONATYPE_USERNAME` | Sonatype Central **user token** username (not the account name) |
| `SONATYPE_PASSWORD` | Sonatype Central user token password |
| `GPG_PRIVATE_KEY` | ASCII-armored GPG private key (`gpg --armor --export-secret-keys KEY_ID`) |
| `GPG_PASSPHRASE` | Passphrase of that GPG key |

The public half of the GPG key must be published to a public keyserver
(for example `keys.openpgp.org`) so that Central can verify the signatures.
The workflow validates that all four secrets exist before touching any version.

### Inputs

| Input | Default | Meaning |
| --- | --- | --- |
| `dry_run` | `false` | Build and version only; skip publishing, tagging and pushing |
| `release_version` | *(blank)* | Override the release version, e.g. `0.2.0`. Blank = current pom version without `-SNAPSHOT` |
| `next_development_version` | *(blank)* | Override the next development version, e.g. `0.3.0-SNAPSHOT`. Blank = patch increment |

### Version scheme

```
0.1.0-SNAPSHOT  →  release 0.1.0  →  next development version 0.1.1-SNAPSHOT
```

Versions carrying a qualifier (for example `1.0.0-RC1`) are treated as
pre-releases: they are published and marked as a GitHub pre-release, but the
branch is not bumped or committed to.

### What the workflow does

1. Validates the required secrets (fast-fail).
2. Checks out the repository with full history.
3. Sets up JDK 17, writes a Maven `settings.xml` with the `central` server
   credentials and imports the GPG key.
4. Computes the release version and next development version.
5. Sets the release version in every `pom.xml` (`versions:set`).
6. Builds and tests all modules.
7. Deploys all modules with `mvn deploy -Prelease` (see below).
8. Commits the release, creates the `vX.Y.Z` tag, bumps to the next
   `-SNAPSHOT` version and pushes both back to the triggering branch.
9. Creates a GitHub Release with auto-generated notes.

### The `release` Maven profile

The `release` profile is defined in both the aggregator `pom.xml` and
`j-api-proxy-parent/pom.xml`, so every published module inherits it. It adds:

* `maven-source-plugin` — sources jar (required by Central)
* `maven-javadoc-plugin` — javadoc jar (required by Central)
* `maven-gpg-plugin` — detached GPG signatures, using `--pinentry-mode loopback`
  for non-interactive CI
* `central-publishing-maven-plugin` — uploads the bundle to Sonatype Central
  with `publishingServerId=central`, `autoPublish=true` and
  `waitForPublishing=true`

Central also requires `name`, `description`, `url`, `licenses`, `developers`
and `scm` metadata; these are set on the aggregator and parent poms and each
module declares its own `description`.

### Running a release

1. Make sure `main` is green and the pom version is the intended
   `X.Y.Z-SNAPSHOT`.
2. Actions → *Release to Maven Central* → *Run workflow*, on `main`.
   Optionally set `release_version` / `next_development_version`, or tick
   `dry_run` to rehearse.
3. @rrobetti approves the pending `release` environment deployment.
4. When the run finishes, the artifacts are searchable at
   <https://central.sonatype.com/search?q=io.github.rrobetti>.
