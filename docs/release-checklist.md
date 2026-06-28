# Release Checklist

## Versioning Strategy

FlexiScale follows [Semantic Versioning](https://semver.org/):

- **Major** — Breaking API changes
- **Minor** — New features, backward-compatible
- **Patch** — Bug fixes, backward-compatible

Current version: `0.1.0-SNAPSHOT` (pre-release)

## Pre-Release Checklist

- [ ] All tests pass: `./gradlew test`
- [ ] CHANGELOG.md updated with all changes
- [ ] Version updated in root `build.gradle.kts`
- [ ] README dependency versions updated
- [ ] Documentation reviewed and up-to-date
- [ ] Sample app tested (if applicable)

## Release Steps

1. Update version in `build.gradle.kts` (remove `-SNAPSHOT`)
2. Update `CHANGELOG.md` with release date
3. Commit: `git commit -m "Prepare release v0.1.0"`
4. Tag: `git tag v0.1.0`
5. Push: `git push --tags`
6. GitHub Actions publishes to Maven Central automatically
7. Create GitHub Release from tag
8. Verify artifacts on [Maven Central](https://central.sonatype.com/)

## Post-Release

- [ ] Bump version to next `-SNAPSHOT`
- [ ] Update CHANGELOG for unreleased section
- [ ] Announce release
