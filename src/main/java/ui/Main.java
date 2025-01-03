package ui;

import dataaccess.MySqlCourseRepository;
import dataaccess.MysqlDatabaseConnection;
import dataaccess_student.MySqlStudentRepository;


import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Cli myCli = new Cli(new MySqlCourseRepository(), new MySqlStudentRepository());
            myCli.start();
        } catch (SQLException e) {
            System.out.println("Datenbankfehler: " + e.getMessage() + "SQL State" + e.getSQLState());
        } catch (ClassNotFoundException e) {
            System.out.println("Datenbankfehler: " + e.getMessage());
        }



    }
}