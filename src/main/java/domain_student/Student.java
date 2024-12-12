package domain_student;

public class Student {
    private int studentId;
    private String vorname;
    private String nachname;
    private String geburtsdatum; // Format: YYYY-MM-DD


    public Student(int studentId, String vorname, String nachname, String geburtsdatum) {
        this.studentId = studentId;
        setVorname(vorname);
        setNachname(nachname);
        setGeburtsdatum(geburtsdatum);
    }



    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        if (studentId <= 0) {
            throw new IllegalArgumentException("Die Student-ID muss positiv sein.");
        }
        this.studentId = studentId;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        if (vorname == null || vorname.trim().isEmpty()) {
            throw new InvalidNameException("Der Vorname darf nicht leer sein.");
        }
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        if (nachname == null || nachname.trim().isEmpty()) {
            throw new InvalidNameException("Der Nachname darf nicht leer sein.");
        }
        this.nachname = nachname;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        if (!geburtsdatum.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new InvalidDateException("Das Geburtsdatum muss im Format YYYY-MM-DD sein.");
        }
        this.geburtsdatum = geburtsdatum;
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
                "studentId=" + studentId +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", geburtsdatum='" + geburtsdatum + '\'' +
                '}';
    }
}
