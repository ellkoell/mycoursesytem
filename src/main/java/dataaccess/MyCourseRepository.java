package dataaccess;

import domain.Course;
import domain.CourseType;

import java.util.Date;
import java.util.List;

public interface MyCourseRepository extends BaseRepository<Course,Long> { //MyCourseRepository erbt von BaseRepository
    List<Course> findByCourseName(String name);

    List<Course> findAllCoursesByDescription(String description);

    List<Course> findAllCoursesByCourseNameOrDescription(String searchText);

    List<Course> findAllCoursesByCourseType(CourseType courseType);

    List<Course> findAllCoursesByStartDate(Date startDate);

    List<Course> findAllRunningCourses();

    List<Course> findAllCoursesByNameOrDescription(String searchText);


}
