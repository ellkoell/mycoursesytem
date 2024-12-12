
# JDBC DAO - Dokumentation

## Video 1: Einführung in JDBC und DAO + 1. Aufgabe
# Anleitung zur Umsetzung der Aufgaben

## 1. Datenbankserver einrichten
- Installiere MySQL auf deinem Computer oder nutze einen Cloud-Service wie Amazon RDS oder Azure Database.
- Starte den MySQL-Server und richte eine neue Datenbank ein:
  ```sql
  CREATE DATABASE kurs_db;



In diesem Video lernen wir:
- JDBC ist ein Tool, um Java mit Datenbanken wie MySQL zu verbinden.
- Das DAO-Pattern organisiert den Datenbankzugriff besser.
    - Vorteil: Code bleibt sauber und Datenbanken können leicht ausgetauscht werden.

## DB-Server Adminkonsole einrichten und verwenden
INSERT INTO kurse (name, beschreibung) VALUES ('Java Grundlagen', 'Einführung in Java');
SELECT * FROM kurse;

## 3. Java-Maven-Projekt erstellen
   Erstelle  neues Maven-Projekt:
   mvn archetype:generate -DgroupId=com.example -DartifactId=kursverwaltung -DarchetypeArtifactId=mave


## 4. Dependency für MySQL in der pom.xml hinzufügen
   Füge die MySQL-Connector-Java-Dependency hinzu:
   
   <dependency>
   <groupId>mysql</groupId>
   <artifactId>mysql-connector-java</artifactId>
   <version>8.0.34</version>
   </dependency>

## 5. Verbindung zur Datenbank aufbauen
   Codeauszug:
  
   import java.sql.Connection;
   import java.sql.DriverManager;

public class DBConnection {
public static Connection connect() {
String url = "jdbc:mysql://localhost:3306/kurs_db";
String user = "root";
String password = "deinPasswort";

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

## 6. Prepared Statement für Abfragen verwenden

Codeauszug:  
try {
String sql = "SELECT * FROM `courses` WHERE `id` = ?";
PreparedStatement preparedStatement = con.prepareStatement(sql);
preparedStatement.setLong(1, id);
ResultSet resultSet = preparedStatement.executeQuery();

## 7. Prepared Statement für Änderungen verwenden
Codeauszug:
public Optional<Course> insert(Course entity) {
Assert.notNull(entity);

        try {
            String sql = "INSERT INTO `courses` (`name`, `description`, `hours`, `begindate`, `enddate`, `coursetype`) VALUES ( ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setInt(3, entity.getHours());
            preparedStatement.setDate(4, entity.getBeginDate());
            preparedStatement.setDate(5, entity.getEndDate());
            preparedStatement.setString(6, entity.getCourseType().toString());

## 8. Abgefragte Daten mit ResultSet verarbeiten
ResultSet resultSet = preparedStatement.executeQuery();

                resultSet.next();
                Course course = new Course(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("hours"),
                        resultSet.getDate("beginDate"),
                        resultSet.getDate("enddate"),
                        CourseType.valueOf(resultSet.getString("coursetype"))
                );

## 9. Exceptions verwenden und verarbeiten
   Verarbeite SQLExceptions mit sinnvollen Nachrichten:
   java
   Copy code
   catch (SQLException e) {
   System.err.println("Datenbankfehler: " + e.getMessage());
   }


## 10. Debugging von JDBC-Applikationen
erledigt


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

# Aufgabe 3

## 1. DAO-Pattern zum objektrelationalen Zugriff auf Datenbanken verstehen und anwenden
- **DAO-Pattern (Data Access Object)** ist ein Entwurfsmuster, das den Zugriff auf eine Datenbank abstrahiert.
- Es bietet eine klare Schnittstelle für CRUD-Operationen (Create, Read, Update, Delete) und isoliert die Datenbanklogik vom Rest der Anwendung.
- Im Transkript wird erklärt, dass das DAO für spezifische Datenbankoperationen genutzt wird, wie die Verbindung zur Datenbank und das Abrufen von Datensätzen.

## 2. Grundkonzept des objektrelationalen Mappings verstehen
- Objektrelationales Mapping (ORM) bezieht sich auf die Konvertierung von Datenbanktabellen in Objekte.
- Ein Transferobjekt wird dabei aus den Datenbankdaten erstellt, um diese in der Anwendung zu verwenden.
- Das Transkript betont, dass das DAO-Pattern in Verbindung mit ORM verwendet wird, um Datenbankoperationen zu kapseln und Daten in eine für die Anwendung verständliche Form zu bringen.

## 3. Singleton-Pattern zum Aufbau der DB-Verbindung verstehen und anwenden
- Das **Singleton-Pattern** stellt sicher, dass nur eine Instanz der Datenbankverbindung während der Laufzeit existiert.
- Im Transkript wird eine `MySQL-Database-Connection`-Klasse beschrieben, die:
   - Eine private statische Instanz (`Connection`) hält.
   - Einen privaten Konstruktor hat, um Mehrfachinstanzen zu verhindern.
   - Eine `getConnection`-Methode bietet, die bei Bedarf eine neue Verbindung aufbaut oder die bestehende zurückgibt.

## 4. Commandline-Interface (Kommandozeilenmenü) sauber programmieren
- Das Transkript gibt hier keine spezifischen Hinweise.
- Allgemein sollte ein CLI klar strukturiert sein, mit Optionen für CRUD-Operationen und einer robusten Fehlerbehandlung.

## 5. Exceptions verstehen und verwenden
- Im Transkript wird betont, dass Exceptions wie `SQLException` in der Methodensignatur deklariert und mit `try-catch`-Blöcken behandelt werden.
- Beispiele sind die Handhabung von Ausnahmen beim Verbindungsaufbau oder beim Zugriff auf fehlende Ressourcen.

## 6. Abstrakte Klassen verstehen und verwenden
- Im Transkript werden keine abstrakten Klassen erwähnt, aber eine solche könnte genutzt werden, um allgemeine Funktionen wie das Öffnen/Schließen von Verbindungen oder die Definition von Schnittstellen für CRUD-Operationen zu kapseln.

## 7. Interfaces (auch mit Erben für Interfaces) verstehen und verwenden
- Interfaces könnten für DAO-Klassen definiert werden, um die Methoden für CRUD-Operationen zu standardisieren:
  ```java
  public interface DAO<T> {
      void create(T entity);
      T read(int id);
      void update(T entity);
      void delete(int id);
  }


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