package dataaccess;

import domain.Course;
import domain.CourseType;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MySqlCourseRepository implements MyCourseRepository{
    @Override
    public List<Course> findByCourseName(String name) {
        return List.of();
    }

    @Override
    public List<Course> findAllCoursesByDescription(String description) {
        return List.of();
    }

    @Override
    public List<Course> findAllCoursesByCourseNameOrDescription(String searchText) {
        return List.of();
    }

    @Override
    public List<Course> findAllCoursesByCourseType(CourseType courseType) {
        return List.of();
    }

    @Override
    public List<Course> findAllCoursesByStartDate(Date startDate) {
        return List.of();
    }

    @Override
    public List<Course> findAllRunningCourses() {
        return List.of();
    }

    @Override
    public Optional<Course> insert(Course entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Course> getbyId(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Course> getAll() {
        return List.of();
    }

    @Override
    public Optional<Course> update(Course entity) {
        return Optional.empty();
    }

    @Override
    public void deletebyId(Long id) {

    }
}
