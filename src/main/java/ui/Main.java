package ui;

import dataaccess.MySqlCourseRepository;
import dataaccess.MysqlDatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Cli mycli = new Cli(new MySqlCourseRepository());
            mycli.start();
        } catch (SQLException e) {
            System.out.println("Datenbankfehler: " + e.getMessage() + "SQL State" + e.getSQLState());
        } catch (ClassNotFoundException e) {
            System.out.println("Datenbankfehler: " + e.getMessage());
        }


    }
}