# RWU Softwareengineering - Spring Boot Demo

Demoprojekt für den Kurs Softwareengineering an der RWU - Softwareentwicklung mit Spring & Spring Boot.

**Voraussetzungen:**

* Java 21
* Spring Boot 3
* Maven 3
* Docker

**Bauen und starten**

Alle Befehle sind im Projekt-Wurzelverzeichnis auszuführen.

Anwendung bauen:

```.\mvnw.cmd clean install```

PostgreSQL in Docker starten:

```docker run --name postgres-rwu -p 5432:5432 -e POSTGRES_PASSWORD=pgsqlpw -d postgres:17```

Anwendung starten (Projektverzeichnis):

```java -jar target\rwu-spring-boot-demo-1.0.jar```
