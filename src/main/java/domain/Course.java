package domain;

import java.sql.Date;

public class Course extends BaseEntity {

    private String name;
    private String description;
    private int hours;
    private Date beginDate;
    private Date endDate;
    private CourseType courseType;

    public String getName() {
        return name;
    }

    public Course(Long id, String name, String description, int hours, Date beginDate, Date endDate, CourseType courseType)  throws InvalidValueException{
        super(id);
        this.setName(name);
        this.setDescription(description);
        this.setHours(hours);
        this.setBeginDate(beginDate);
        this.setEndDate(endDate);
        this.setBeginDate(beginDate);
        this.setCourseType(courseType);
    }

    public Course(String name, String description, int hours, Date beginDate, Date endDate, CourseType courseType) throws  InvalidValueException{
        super(null);
        this.setName(name);
        this.setDescription(description);
        this.setHours(hours);
        this.setBeginDate(beginDate);
        this.setEndDate(endDate);
        this.setBeginDate(beginDate);
        this.setCourseType(courseType);
    }

    public void setName(String name) throws InvalidValueException {
        if(name!=null&&name.length()>1){
            this.name = name;
        }else{
         throw new IllegalArgumentException("Kursname muss mindestens 2 Zeichen lang sein");
        }

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description)throws InvalidValueException  {
        if (description != null && description.length()>10) {
            this.description = description;
        }else{
            throw new InvalidValueException("Kursbeschreibung muss mindestens 10 Zeichen lang sein");
        }

    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) throws InvalidValueException {
        if (hours >= 0 && hours <= 10) {
            this.hours = hours;
        } else {
throw new InvalidValueException("Anzahl der Kursstunden pro Kurs darf zwischen 1 und 10 liegen");
        }
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) throws InvalidValueException {
        if (beginDate != null) {
            if (this.endDate != null) {
                if(beginDate.before(this.endDate)){
                    this.beginDate = beginDate;
                }else {
                    throw new InvalidValueException("Kursbeginn muss VOR Kursende sein!");
                }
            }else{
                this.beginDate = beginDate;
            }
        }else {
            throw new InvalidValueException("Startdatum darf nicht null / leer sein!");
        }
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate)throws InvalidValueException  {
        if (endDate != null) {
            if (this.beginDate != null) {
                if (endDate.after(this.beginDate)) {
                    this.endDate = endDate;
                } else {
                    throw new InvalidValueException("Kursende muss NACH Kursbeginn sein!");
                }

            }else{
                this.endDate = endDate;
            }
        }else {
            throw new InvalidValueException("Enddatum darf nicht null / leer sein!");
        }
        this.endDate = endDate;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) throws InvalidValueException {
        if (courseType != null) {
            this.courseType = courseType;
        }else {
            throw new InvalidValueException("Kurstyp darf nicht null/lerr sein!");
        }

    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + this.getId() + '\'' +
        ", courseType=" + courseType +'\''+
                ", endDate=" + endDate +
                ", beginDate=" + beginDate +
                ", hours=" + hours +
                ", description='" + description + '\'' +
                ", name='" + name +  +
                '}';
    }
}
