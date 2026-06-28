# Publishing

## Prerequisites

1. A [Sonatype account](https://central.sonatype.com/) with namespace verification
2. A GPG key pair for signing
3. Gradle properties configured

## Configuration

Set these in `gradle.properties` or pass as environment variables / CI secrets:

```properties
# Sonatype credentials
sonatypeUser=your-token-user
sonatypePassword=your-token-password

# GPG signing (in-memory key - preferred for CI)
signingKey=base64-encoded-pgp-private-key
signingPassword=your-key-password

# OR keyring file (for local publishing)
signingKeyId=last-8-chars-of-key-id
signingSecretKeyRingFile=/path/to/secring.gpg
```

## Publishing to Maven Local

```bash
./gradlew publishToMavenLocal
```

## Publishing to Maven Central (Snapshot)

```bash
./gradlew publish
```

## Publishing to Maven Central (Release)

1. Update version in `build.gradle.kts` (remove `-SNAPSHOT`)
2. Commit and tag: `git tag v0.1.0`
3. Push: `git push --tags`
4. Run: `./gradlew publish`
5. Release the staging repository on Sonatype

## Module-Specific Commands

```bash
# Publish all modules
./gradlew publishToMavenLocal

# Publish specific module
./gradlew :flexiscale-runtime:publishToMavenLocal

# Skip flexiscale-resources (if AAPT2 memory issues)
./gradlew :flexiscale-runtime:publishToMavenLocal :flexiscale-tokens:publishToMavenLocal :flexiscale-compose:publishToMavenLocal
```
