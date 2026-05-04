# RWU Softwareengineering - Spring Boot Demo

Demoprojekt für den Kurs Softwareengineering an der RWU - Softwareentwicklung mit Spring & Spring Boot.

**Voraussetzungen:**

* Java 25
* Spring Boot 4
* Maven 3
* Testcontainers 2
* Docker

**Bauen und starten**

Alle Befehle sind im Projekt-Stammverzeichnis auszuführen.

Anwendung bauen:

```.\mvnw.cmd clean install```

PostgreSQL in Docker starten:

```docker run -d --name postgres-rwu -p 5432:5432 -e POSTGRES_PASSWORD=pgsqlpw postgres:18```

Anwendung starten (Projektverzeichnis):

```java -jar target\rwu-spring-boot-demo-1.0.jar```
