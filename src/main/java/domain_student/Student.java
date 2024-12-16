package domain_student;

import domain.BaseEntity;
import domain.InvalidValueException;

import java.sql.Date;

public class Student extends BaseEntity {
    private String vorname;
    private String nachname;
    private Date geburtsdatum;


    public Student(Long id, String vorname, String nachname, Date geburtsdatum) throws InvalidValueException {
        super(id);
        setVorname(vorname);
        setNachname(nachname);
        setGeburtsdatum(geburtsdatum);
    }

    public Student(String vorname, String nachname, Date geburtsdatum) throws InvalidValueException {
        super(null);
        setVorname(vorname);
        setNachname(nachname);
        setGeburtsdatum(geburtsdatum);
    }



    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) throws InvalidValueException {
        if(vorname!=null&&vorname.length()>3){
            this.vorname = vorname;
        }else{
            throw new IllegalArgumentException("Vorname muss mindestens 3 Zeichen lang sein");
        }

    }

    public void setNachname(String nachname) throws InvalidValueException {
        if(nachname!=null&&nachname.length()>3){
            this.nachname = nachname;
        }else{
            throw new IllegalArgumentException("Nachname muss mindestens 3 Zeichen lang sein");
        }

    }




    public String getNachname() {
        return nachname;
    }



    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) throws InvalidValueException {
        if (geburtsdatum == null) {
            throw new InvalidValueException("Geburtsdatum darf nicht null oder leer sein!");
        }


        Date referenceDate = Date.valueOf("2011-09-01");

        if (this.geburtsdatum != null) {
            if (geburtsdatum.before(referenceDate)) {
                this.geburtsdatum = Date.valueOf(String.valueOf(geburtsdatum));
            } else {
                throw new InvalidValueException("Geburtsdatum muss vor dem 1. September 2011 liegen!");
            }
        } else {
            this.geburtsdatum = Date.valueOf(String.valueOf(geburtsdatum));
        }
    }



    public static class InvalidNameException extends RuntimeException {
        public InvalidNameException(String message) {
            super(message);
        }
    }

    public static class InvalidDateException extends RuntimeException {
        public InvalidDateException(String message) {
            super(message);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + this.getId() +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", geburtsdatum='" + geburtsdatum + '\'' +
                '}';
    }
}
