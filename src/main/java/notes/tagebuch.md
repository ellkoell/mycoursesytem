
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
    - `CourseType` (z. B. "AB" oder "CD").
3. Ein Maven-Projekt in IntelliJ IDEA eingerichtet und die MySQL-Dependency hinzugefügt.

## Video 3: Datenbankverbindung mit Singleton
In diesem Video haben wir eine Singleton-Klasse geschrieben, die sicherstellt, dass es nur eine Datenbankverbindung gibt.



## Fazit
Wir haben gelernt:
1. Wie JDBC funktioniert und wie das DAO-Pattern hilft.
2. Wie man eine Datenbank und ein Maven-Projekt einrichtet.
3. Wie man mit Singleton effizient eine Verbindung zur Datenbank aufbaut.
