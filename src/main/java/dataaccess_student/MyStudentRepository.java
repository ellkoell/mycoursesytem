package dataaccess_student;

import dataaccess.BaseRepository;
import domain_student.Student;

import java.util.List;
import java.util.Optional;

public interface MyStudentRepository extends BaseRepository<Student,Long> {

    List <Student> findStudentByName(String searchText);


    List <Student> findStudentByGeburtsjahr(int year);

}