
# JDBC DAO - Dokumentation

## Video 1: Einführung in JDBC und DAO
In diesem Video lernen wir:
- JDBC ist ein Tool, um Java mit Datenbanken wie MySQL zu verbinden.
- Das DAO-Pattern organisiert den Datenbankzugriff besser.
    - Vorteil: Code bleibt sauber und Datenbanken können leicht ausgetauscht werden.

## Video 2: Projektsetup
Hier haben wir:
1. Die Datenbank `KursSystem` erstellt.
2. Tabelle `Courses` hinzugefügt mit:
    - `ID` (Auto-Increment, Primary Key)
    - `Name` (max. 200 Zeichen)
    - `Description` (Text)
    - `Hours` (Integer)
    - `BeginDate` und `EndDate` (Date)
    - `CourseType` (z. B. "ZA" oder "OE").
3. Ein Maven-Projekt in IntelliJ IDEA eingerichtet und die MySQL-Dependency hinzugefügt.

## Video 3: Datenbankverbindung mit Singleton
In diesem Video haben wir eine Singleton-Klasse geschrieben, die sicherstellt, dass es nur eine Datenbankverbindung gibt.
# Datenbankverbindung mit JDBC und Singleton-Pattern

In dieser Anleitung zeige ich, wie man eine Verbindung zu einer MySQL-Datenbank mit JDBC erstellt und dabei das Singleton-Pattern nutzt. Das sorgt dafür, dass wir nur eine Verbindung während der Laufzeit haben.

---

## Was ist das Singleton-Pattern?

Das Singleton-Pattern stellt sicher, dass eine Klasse nur eine Instanz hat. Bei Datenbankverbindungen ist das praktisch, weil wir nicht bei jeder Anfrage eine neue Verbindung öffnen müssen.

---

## Schritte

### 1. Neue Klasse erstellen

Erstellt eine Klasse `MySQLDatabaseConnection` in einem Package, z. B. `dataaccess`.

Codeauszug:

```package dataaccess;

````import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyS`QLDatabaseCo`nnection {
    private static Connection conn = null; 

    private MySQLDatabaseConnection() {} 

    public static Connection getConnection(String url, String user, String password) throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(url, user, password); 
        }
        return conn; 
    }
}

````

## Fazit
Ich habe gelernt:
1. Wie JDBC funktioniert und wie das DAO-Pattern hilft.
2. Wie man eine Datenbank und ein Maven-Projekt einrichtet.
3. Wie man mit Singleton effizient eine Verbindung zur Datenbank aufbaut.


