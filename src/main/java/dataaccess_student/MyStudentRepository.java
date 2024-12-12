package dataaccess_student;

import domain_student.Student;

import java.util.List;

public interface MyStudentRepository extends BaseRepository<Student> {
    Student findByName(String name);
    Student findByGeburtsjahr(int year);
    List<Student> findBetweenGeburtsdatum(String startDate, String endDate);
}