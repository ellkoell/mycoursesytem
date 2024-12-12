package dataaccess_student;

import dataaccess_student.MyStudentRepository;
import domain_student.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlStudentRepository implements MyStudentRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/kurs_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public void save(Student student) {
        String sql = "INSERT INTO studenten (vorname, nachname, geburtsdatum) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getVorname());
            stmt.setString(2, student.getNachname());
            stmt.setString(3, student.getGeburtsdatum());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student findById(int id) {
        String sql = "SELECT * FROM studenten WHERE student_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("student_id"),
                            rs.getString("vorname"),
                            rs.getString("nachname"),
                            rs.getString("geburtsdatum")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM studenten WHERE student_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student findByName(String name) {
        String sql = "SELECT * FROM studenten WHERE vorname = ? OR nachname = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("student_id"),
                            rs.getString("vorname"),
                            rs.getString("nachname"),
                            rs.getString("geburtsdatum")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student findByGeburtsjahr(int year) {
        String sql = "SELECT * FROM studenten WHERE YEAR(geburtsdatum) = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, year);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("student_id"),
                            rs.getString("vorname"),
                            rs.getString("nachname"),
                            rs.getString("geburtsdatum")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> findBetweenGeburtsdatum(String startDate, String endDate) {
        String sql = "SELECT * FROM studenten WHERE geburtsdatum BETWEEN ? AND ?";
        List<Student> students = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, startDate);
            stmt.setString(2, endDate);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    students.add(new Student(
                            rs.getInt("student_id"),
                            rs.getString("vorname"),
                            rs.getString("nachname"),
                            rs.getString("geburtsdatum")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}