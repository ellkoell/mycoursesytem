package dataaccess_student;

import dataaccess.DatabaseException;
import dataaccess.MysqlDatabaseConnection;
import domain.Course;
import domain.CourseType;
import domain_student.Student;
import util.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MySqlStudentRepository implements MyStudentRepository {
    private Connection con;

    public MySqlStudentRepository() throws ClassNotFoundException, SQLException {
        this.con = MysqlDatabaseConnection.getConnection("jdbc:mysql://localhost:3306/kurssystem", "root", "");
    }


    @Override
    public Optional<Student> insert(Student entity) { //muss implementiert werden, weil MyStudentrepository von baserepository erbt
        Assert.notNull(entity);

        try {
            //sql code für das Einfügen von Students
            String sql = "INSERT INTO `studenten` (`vorname`, `nachname`, `geburtsdatum`) VALUES ( ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getVorname()); //? Platzhalter werden mit Werten aus dem entity objekt ersetzt
            preparedStatement.setString(2, entity.getNachname());
            preparedStatement.setDate(3, entity.getGeburtsdatum());

            int affectedRows = preparedStatement.executeUpdate(); //führt prepared statement aus und gibt anzahl der betroffenen zeilen zurück

            if (affectedRows == 0) {
                return Optional.empty();
            }

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys(); //result set mit neu generierten primärschlüsseln
            if (generatedKeys.next()) { //wenn ein primärschlüssel gefunden wird, wird er zurückgegeben
                return this.getbyId(generatedKeys.getLong(1));
            } else {
                return Optional.empty();
            }
        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        }


    }



    private int countStudentsInDbWithId(Long id) {
        try {
            String countSql = "SELECT COUNT(*) FROM `studenten` WHERE `student_id` = ?";
            PreparedStatement preparedStatementCount = con.prepareStatement(countSql);
            preparedStatementCount.setLong(1, id);
            ResultSet resultSetCount = preparedStatementCount.executeQuery();
            resultSetCount.next();
            int StudentCount = resultSetCount.getInt(1);
            return StudentCount;
        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        }
    }


    @Override
    public Optional<Student> getbyId(Long id) {
        Assert.notNull(id);
        if (countStudentsInDbWithId(id) == 0) {
            return Optional.empty();
        } else {
            try {
                String sql = "SELECT * FROM `studenten` WHERE `student_id` = ?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                resultSet.next();
                Student student = new Student(
                        resultSet.getLong("student_id"),
                        resultSet.getString("vorname"),
                        resultSet.getString("nachname"),
                        resultSet.getDate("geburtsdatum")
                );
                return Optional.of(student);


            } catch (SQLException sqlException) {
                throw new DatabaseException(sqlException.getMessage());

            }


        }
    }

    @Override
    public List<Student> getAll() {
        String sql = "SELECT * FROM `studenten`";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Student> studentList = new ArrayList<>();
            while (resultSet.next()) {
                studentList.add(new Student(
                        resultSet.getLong("student_id"),
                        resultSet.getString("vorname"),
                        resultSet.getString("nachname"),
                        resultSet.getDate("geburtsdatum")
                        )


                );


            }
            return studentList;
        } catch (SQLException e) {
            throw new DatabaseException("Database error occured!");
        }

    }

    @Override
    public Optional<Student> update(Student entity) {

        Assert.notNull(entity);

        String sql = "UPDATE `studenten` SET `vorname` = ?, `nachname` = ?, `geburtsdatum` = ? WHERE `studenten`.`student_id` = ? ";

        if (countStudentsInDbWithId(entity.getId()) == 0) {
            return Optional.empty();

        } else {
            try {
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, entity.getVorname()); //? Platzhalter werden mit Werten aus dem entity objekt ersetzt
                preparedStatement.setString(2, entity.getNachname());
                preparedStatement.setDate(3, entity.getGeburtsdatum());
                preparedStatement.setLong(4, entity.getId());

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    return Optional.empty();
                } else {
                    return this.getbyId(entity.getId());
                }
            } catch (SQLException sqlException) {
                throw new DatabaseException(sqlException.getMessage());
            }
        }

    }

    @Override
    public void deletebyId(Long id) {
        Assert.notNull(id);
        String sql = "DELETE from studenten WHERE student_id = ?";
        if(countStudentsInDbWithId(id) == 1) {

            try {
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }catch (SQLException sqlException) {
                throw new DatabaseException(sqlException.getMessage());
            }
        }


    }





    @Override
    public List<Student> findStudentByName(String searchText) {
        try{
            String sql = "SELECT * FROM studenten WHERE LOWER(vorname) LIKE LOWER(?) OR LOWER(nachname) LIKE LOWER(?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, "%"+searchText+"%");
            preparedStatement.setString(2, "%"+searchText+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Student> studentList = new ArrayList<>();
            while(resultSet.next()){
                studentList.add(new Student(
                                resultSet.getLong("student_id"),
                                resultSet.getString("vorname"),
                                resultSet.getString("nachname"),
                                resultSet.getDate("geburtsdatum")
                        )


                );
            }
            return studentList;
        }catch (SQLException sqlException){
            throw new DatabaseException(sqlException.getMessage());
        }

    }






    @Override
    public List<Student> findStudentByGeburtsjahr(int year) {
        String sql = "SELECT * FROM studenten WHERE YEAR(geburtsdatum) = ?";


        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, year);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Student> studentList = new ArrayList<>();
            while (resultSet.next()) {
                studentList.add(new Student(
                        resultSet.getLong("student_id"),
                        resultSet.getString("vorname"),
                        resultSet.getString("nachname"),
                        resultSet.getDate("geburtsdatum")

                        ));
            }
            return studentList;
        }
        catch (SQLException sqlException){
            throw new DatabaseException("Datenbankfehler: "+sqlException.getMessage());

    }
}

}