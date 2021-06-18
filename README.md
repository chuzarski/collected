# Collected

---

## Build/Run

### Database:
For current development purposes an H2 server should be running:
- [Get H2 here](https://www.h2database.com/html/download.html)

Create a new H2 database at `~/db/collected` with the credentials:
- Username: `sa`
- Password: `1234`

The REST framework will run a database migration at start, which will populate an empty database

### Building with Gradle
Java 11+ is required and JAVA_HOME must be correctly configured.

On Mac/Linux, run these commands in the project root`/`:

- `./gradlew build` - to build
- `./gradlew run` - to build/run
- `./gradlew clean` - clean project

On Windows, run these commds in the project root `/`:

- `gradlew.bat build` - to build
- `gradlew.bat run` - to build/run
- `./gradlew.bat clean` - clean project


---

## Micronaut 2.5.5 Documentation

- [User Guide](https://docs.micronaut.io/2.5.5/guide/index.html)
- [API Reference](https://docs.micronaut.io/2.5.5/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/2.5.5/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

## Feature flyway documentation

- [Micronaut Flyway Database Migration documentation](https://micronaut-projects.github.io/micronaut-flyway/latest/guide/index.html)

- [https://flywaydb.org/](https://flywaydb.org/)

## Feature sql-jdbi documentation

- [Micronaut Jdbi documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jdbi)

## Feature jdbc-hikari documentation

- [Micronaut Hikari JDBC Connection Pool documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jdbc)

## Feature security-jwt documentation

- [Micronaut Security JWT documentation](https://micronaut-projects.github.io/micronaut-security/latest/guide/index.html)

