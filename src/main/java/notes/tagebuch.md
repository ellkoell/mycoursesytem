
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

Habe eine Klasse namens `MySQLDatabaseConnection` erstellt in dem Package`dataaccess`.

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

## Video 4: Datenbankverbindung mit Kommandozeilen-Interfaces (CLI) 

# Kommandozeilen-Interface (CLI) in Java erstellen



## 1. Klasse und Scanner einrichten

Klasse namens `CLI` erstellen, in dieser Klasse nutzen wir dann den Scanner, um Benutzereingaben von der Konsole zu lesen.
```java
import java.util.Scanner;

public class CLI {
    private Scanner scanner;

    public CLI() {
        this.scanner = new Scanner(System.in); 
    }
}
```
## die Startmethode
```java
public void start() {
String input;
do {
showMenu(); // Menü anzeigen
input = scanner.nextLine(); // Eingabe lesen
handleInput(input); // Eingabe verarbeiten
} while (!input.equalsIgnoreCase("x")); // Schleife beenden bei "x"
scanner.close(); // Scanner schließen
System.out.println("Auf Wiedersehen!");
}
````
## Aufbau des CLI
1. **Scanner verwenden:** 
2. **do-while-Schleife:** 
3. **Switch-Case-Struktur:** 
4. **Fehlerbehandlung:** 

## Video 5: Datenbankverbindung mit Kommandozeilen-Interfaces (CLI) 