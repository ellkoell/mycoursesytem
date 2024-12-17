package ui;

import dataaccess.DatabaseException;
import dataaccess.MyCourseRepository;
import dataaccess_student.MySqlStudentRepository;
import dataaccess_student.MyStudentRepository;
import domain.Course;
import domain.CourseType;
import domain.InvalidValueException;
import domain_student.Student;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Cli {
    private Scanner scan;
    private MyCourseRepository courseRepo;
    private MyStudentRepository studentRepo;

    public Cli(MyCourseRepository courseRepo, MyStudentRepository studentRepo) {
        this.scan = new Scanner(System.in);
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }

    public void start() {
        String input = "-"; //variable für benutzereingaben, wird je nach eingabe aktualisiert
        while (!input.equals("x")) {
            showMenue();
            input = scan.nextLine();
            switch (input) {

                case "1": addCourse(); break;
                case "2": showAllCourses(); break;
                case "3": showCourseDetails(); break;
                case "4": updateCourseDetails(); break;
                case "5": deleteCourse(); break;
                case "6": courseSearch(); break;
                case "7": runningCourses(); break;


                case "8": addStudent(); break;
                case "9": findStudentById(); break;
                case "10": findStudentByName(); break;
                case "11": findStudentByYear(); break;


                case "x": System.out.println("Auf Wiedersehen"); break;
                default: inputError(); break; //wenn keiner der case variablen zutrifft, wird inputError ausgeführt
            }
        }
        scan.close();
    }

    private void showMenue() {
        System.out.println("\n---------------------------------- VERWALTUNG ------------------------------------");

        System.out.println("(1) Kurs eingeben \t (2) Alle Kurse anzeigen \t (3) Kursdetails anzeigen");
        System.out.println("(4) Kursdetails ändern \t (5) Kurs löschen \t (6) Kurssuche");
        System.out.println("(7) Laufende Kurse");


        System.out.println("(8) Student hinzufügen \t (9) Student laut ID suchen");
        System.out.println("(10) Student nach Name suchen \t (11) Student laut Geburtsjahr suchen" );

        System.out.println("(x) Beenden");

    }

    private void inputError() {
        System.out.println("Bitte nur die Zahlen der Menüauswahl eingeben!");
    }

    private void addCourse() {
        String name, description;
        int hours;
        Date dateFrom, dateTo;
        CourseType courseType;

        try {
            System.out.println("Bitte alle Kursdaten angeben:");
            System.out.println("Name: ");
            name = scan.nextLine();
            if (name.equals("")) throw new IllegalArgumentException("Eingabe darf nicht leer sein!");
            System.out.println("Beschreibung: ");
            description = scan.nextLine();
            if (description.equals("")) throw new IllegalArgumentException("Eingabe darf nicht leer sein!");
            System.out.println("Stundenanzahl");
            hours = Integer.parseInt(scan.nextLine());
            System.out.println("Startdatum (YYYY-MM-DD): ");
            dateFrom = Date.valueOf(scan.nextLine());

            System.out.println("Enddatum (YYYY-MM-DD): ");
            dateTo = Date.valueOf(scan.nextLine());
            System.out.println("Kurstyp: (ZA/BF/FF/OE): ");
            courseType = CourseType.valueOf(scan.nextLine());

            Optional<Course> optionalCourse = courseRepo.insert(
                    new Course(name, description, hours, dateFrom, dateTo, courseType)
            );
            if (optionalCourse.isPresent()) {
                System.out.println("Kurs angelegt: " + optionalCourse.get());
            } else {
                System.out.println("kurs konnte nicht angelegt werden!");
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("Eingabefehler: " + illegalArgumentException.getMessage());
        } catch (InvalidValueException invalidValueException) {
            System.out.println("Kursdaten nicht korrekt angegeben:" + invalidValueException.getMessage());
        } catch (DatabaseException databaseException) {
            System.out.println("Datenbankfehler beim Einfügen: " + databaseException.getMessage());
        } catch (Exception exception) {
            System.out.println("Unbekannter Fehler beim Einfügen: " + exception.getMessage());
        }
    }

    private void addStudent() {
        String vorname, nachname;
        Date geburtsdatum;

        try {
            System.out.println("Bitte alle Studentendaten angeben:");
            System.out.println("Vorname: ");
            vorname = scan.nextLine();
            if (vorname.equals("")) throw new IllegalArgumentException("Eingabe darf nicht leer sein!");
            System.out.println("Nachname: ");
            nachname = scan.nextLine();
            if (nachname.equals("")) throw new IllegalArgumentException("Eingabe darf nicht leer sein!");
            System.out.println("Geburtsdatum (YYYY-MM-DD): ");
            geburtsdatum = Date.valueOf(scan.nextLine());

            Optional<Student> optionalStudent = studentRepo.insert(
                    new Student(vorname, nachname, geburtsdatum));

            if (optionalStudent.isPresent()) {
                System.out.println("Student angelegt: " + optionalStudent.get());
            } else {
                System.out.println("Student konnte nicht angelegt werden!");
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("Eingabefehler: " + illegalArgumentException.getMessage());
        } catch (InvalidValueException invalidValueException) {
            System.out.println("Studentendaten nicht korrekt angegeben:" + invalidValueException.getMessage());
        } catch (DatabaseException databaseException) {
            System.out.println("Datenbankfehler beim Einfügen: " + databaseException.getMessage());
        } catch (Exception exception) {
            System.out.println("Unbekannter Fehler beim Einfügen: " + exception.getMessage());
        }
    }




    private void runningCourses() {
        System.out.println("Aktuell laufende Kurse: ");
        List<Course> list;
        try {
            list = courseRepo.findAllRunningCourses();
            for (Course course : list) {
                System.out.println(course);
            }
        } catch (DatabaseException databaseException) {
            System.out.println("Datenbankfehler bei Kursanzeige für laufende  Kurse: " + databaseException.getMessage());

        } catch (Exception exception) {
            System.out.println("Unbekannter Fehler bei Kursanzeige für laufende Kurse: " + exception.getMessage());
        }
    }
    private void findStudentByName() {
        System.out.print("Vonrame oder Nachname? ");
        String searchName = scan.nextLine();
        List<Student> studentList;
        try {
            studentList = studentRepo.findStudentByName(searchName);
            for (Student student : studentList) {
                System.out.println(student);
            }

        } catch (DatabaseException databaseException) {
            System.out.println("Datenbankfehler bei der Studentensuche: " + databaseException.getMessage());

        } catch (Exception exception) {

            System.out.println("Unbekannter Fehler bei der Studentensuche: " + exception.getMessage());
        }
    }


    private void findStudentByYear() {
        System.out.print("Geburtsjahr? ");
        String year = scan.nextLine();
        List<Student> studentList;
        try {
            studentList = studentRepo.findStudentByGeburtsjahr(Integer.parseInt(year));
            for (Student student : studentList) {
                System.out.println(student);
            }

        } catch (DatabaseException databaseException) {
            System.out.println("Datenbankfehler bei der Studentensuche: " + databaseException.getMessage());

        } catch (Exception exception) {

            System.out.println("Unbekannter Fehler bei der Studentensuche: " + exception.getMessage());
        }
    }

    private void courseSearch() {
        System.out.println("Geben Sie einen Suchbegriff an!");
        String searchString = scan.nextLine();
        List<Course> courseList;
        try {
            courseList = courseRepo.findAllCoursesByNameOrDescription(searchString);
            for (Course course : courseList) {
                System.out.println(course);
            }

        } catch (DatabaseException databaseException) {
            System.out.println("Datenbankfehler bei der Kurssuche: " + databaseException.getMessage());

        } catch (Exception exception) {

            System.out.println("Unbekannter Fehler bei der Kurssuche: " + exception.getMessage());
        }
    }
    private void showAllCourses() {
        try {
            List<Course> list = courseRepo.getAll();
            if (list.size() > 0) {
                for (Course course : list) {
                    System.out.println(course);
                }
            } else {
                System.out.println("Kursliste leer");
            }
        } catch (DatabaseException databaseException) {
            System.out.println("Datenbankfehler bei Anzeige aller Kurse:" + databaseException.getMessage());
        } catch (Exception exception) {
            System.out.println("Unbekannter Fehler bei Anzeige aller Kurse:" + exception.getMessage());
        }
    }

    private void showCourseDetails() {
        System.out.println("Für welchen Kurs möchten Sie die Kursdetails anzeigen?");
        Long courseId = Long.parseLong(scan.nextLine());
        try {
            Optional<Course> courseOptional = courseRepo.getbyId(courseId);
            if (courseOptional.isPresent()) {
                System.out.println(courseOptional.get());
            } else {
                System.out.println("Kurs mit der ID " + courseId + " nicht gefunden!");
            }
        } catch (DatabaseException databaseException) {
            System.out.println("Datenbankfehler bei Kurs-Detailanzeige: " + databaseException.getMessage());
        } catch (Exception exception) {
            System.out.println("Unbekannter Fehler bei Kurs-Detailanzeige: " + exception.getMessage());
        }
    }

    private void updateCourseDetails() {
        System.out.println("Für welche Kurs-ID möchten Sie die Kursdetails ändern?");
        Long courseId = Long.parseLong(scan.nextLine());

        try {
            Optional<Course> courseOptional = courseRepo.getbyId(courseId);
            if (courseOptional.isEmpty()) {
                System.out.println("Kurs mit der gegebenen ID nicht in der Datenbank!");
            } else {
                Course course = courseOptional.get();
                System.out.println("Änderungen für folgenden Kurs: ");
                System.out.println(course);

                String name, description, hours, dateFrom, dateTo, courseType;

                System.out.println("Bitte neue Kursdaten angeben (Enter, falls keine Änderung gewünscht ist):");
                System.out.println("Name: ");
                name = scan.nextLine();
                System.out.println("Beschreibung: ");
                description = scan.nextLine();
                System.out.println("Stundenanzahl: ");
                hours = scan.nextLine();
                System.out.println("Startdatum (YYYY-MM-DD): ");
                dateFrom = scan.nextLine();
                System.out.println("Enddatum (YYYY-MM-DD): ");
                dateTo = scan.nextLine();
                System.out.println("Kurstyp (ZA/BF/FF/OE): ");
                courseType = scan.nextLine();

                Optional<Course> optionalCourseUpdated = courseRepo.update(
                        new Course(
                                course.getId(),
                                name.equals("") ? course.getName() : name,
                                description.equals("") ? course.getDescription() : description,
                                hours.equals("") ? course.getHours() : Integer.parseInt(hours),
                                dateFrom.equals("") ? course.getBeginDate() : Date.valueOf(dateFrom),
                                dateTo.equals("") ? course.getEndDate() : Date.valueOf(dateTo),
                                courseType.equals("") ? course.getCourseType() : CourseType.valueOf(courseType)

                        )
                );

                optionalCourseUpdated.ifPresentOrElse(
                        (c) -> System.out.println("Kurs aktualisiert: " + c),
                        () -> System.out.println("Kurs konnte nicht aktualisiert werden!")
                );
            }

        } catch (Exception exception) {
            System.out.println("Unbekannter Fehler bei Kursupdate:" + exception.getMessage());
        }
    }

        private void deleteCourse() {
            System.out.println("Welchen Kurs möchten sie löschen? Bitte ID eingeben: ");
            Long courseIdToDelete = Long.parseLong(scan.nextLine());

            try {
                courseRepo.deletebyId(courseIdToDelete);
                System.out.println("Kurs mit ID " + courseIdToDelete + " gelöscht!");
            } catch (DatabaseException databaseException) {
                System.out.println("Datenbankfehler beim löschen: " + databaseException.getMessage());
            } catch (Exception e) {
                System.out.println("Unbekannter Fehler beim Löschen: " + e.getMessage());
            }
        }


    private void findStudentById() {
        System.out.println("Welchen Studenten möchten sie finden (laut ID) ");
        Long studentId = Long.parseLong(scan.nextLine());
        try {
            Optional<Student> studentOptional = studentRepo.getbyId(studentId);
            if (studentOptional.isPresent()) {
                System.out.println(studentOptional.get());
            } else {
                System.out.println("Student mit der ID " + studentId + " nicht gefunden!");
            }
        } catch (DatabaseException databaseException) {
            System.out.println("Datenbankfehler bei Studentensuche: " + databaseException.getMessage());
        } catch (Exception exception) {
            System.out.println("Unbekannter Fehler bei Studentensuche: " + exception.getMessage());
        }
    }
}


