### **JdbcIntro_1_EntwicklungsumgebungEinrichten**

-   **Session-Ziel**: Einführung in die grundlegende Einrichtung einer Java-Entwicklungsumgebung für JDBC-Projekte.
-   **Inhalte**:
    -   **Installation der notwendigen Software**:
        -   Download und Installation des Java Development Kit (JDK).
        -   Einrichtung einer geeigneten IDE (z. B. IntelliJ IDEA oder Eclipse).
    -   **Projekt- und Klassenerstellung**:
        -   Anlage eines neuen Projekts in der IDE.
        -   Strukturierung und Organisation von Java-Dateien und -Paketen.
    -   **Grundlegende IDE-Funktionen**:
        -   Überblick über zentrale Funktionen und Tools der IDE.
        -   Schreiben und Ausführen einfacher Java-Programme.

* * * * *

### **JdbcIntro_2_DatenbankErstellungUndVerbindung**

-   **Session-Ziel**: Einführung in die Erstellung einer Datenbank und die Anbindung über Java JDBC.
-   **Inhalte**:
    -   **Grundlagen zu Datenbanken und DBMS**:
        -   Überblick über Datenbankmanagementsysteme wie MySQL, SQLite und andere.
        -   Erstellen und Konfigurieren einer neuen Datenbank mit einer DBMS-Oberfläche.
    -   **JDBC-Verbindung herstellen**:
        -   Einführung in JDBC (Java Database Connectivity) und JDBC-Treiber.
        -   Download und Einbindung des passenden JDBC-Treibers für die gewählte Datenbank.
        -   Aufbau einer Verbindung über `DriverManager.getConnection()`.
    -   **Erste Datenbankoperationen**:
        -   Testen der Verbindung zur Datenbank.
        -   Grundlagen der Fehlerbehandlung bei der Verbindungsherstellung.

* * * * *

### **JdbcIntro_3_VertiefteVerbindungUndFehlerbehandlung**

-   **Session-Ziel**: Erweiterung und Optimierung der Arbeit mit JDBC-Verbindungen.
-   **Inhalte**:
    -   **SQL-Operationen in Java**:
        -   Erstellen und Ausführen von SQL-Befehlen mit Java (`Statement` und `PreparedStatement`).
        -   Einführung in einfache SQL-Befehle.
    -   **Prepared Statements**:
        -   Erklärung der Sicherheits- und Performancevorteile von prepared Statements.
        -   Beispiel für das Einfügen von Daten mit Parameterbindung.
    -   **Fehlerbehandlung und Debugging**:
        -   Erkennen und Beheben häufiger Fehler beim Arbeiten mit JDBC.
        -   Logging und effizientes Verbindungsmanagement.

* * * * *

### **JdbcIntro_4_DatenabfrageUndManipulation**

-   **Session-Ziel**: Datenabfragen und -manipulation mit JDBC.
-   **Inhalte**:
    -   **CRUD-Operationen (Create, Read, Update, Delete)**:
        -   Einführung in CRUD-Befehle und deren Umsetzung.
        -   Erstellen, Lesen, Aktualisieren und Löschen von Datensätzen.
    -   **Datenabfragen mit `SELECT`-Statements**:
        -   Ausführung von Abfragen und Verarbeitung der Ergebnisse (`ResultSet`-Objekt).
        -   Iteration durch ResultSets und Datenextraktion.

        ### **JDBC_DAO_5_Domänenklassen**
        - 