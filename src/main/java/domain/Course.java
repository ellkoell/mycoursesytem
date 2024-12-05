package domain;

import java.sql.Date;

public class Course extends BaseEntity{

    private String name;
    private String description;
    private int hours;
    private Date beginDate;
    private Date endDate;
    private CourseType courseType;

    public Course(Long id) {
        super(id);
    }
}
