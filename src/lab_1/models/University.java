package lab_1.models;

import lab_1.enums.StudyField;
import lab_1.tools.Tools;
import lab_1.util.FileManager;
import lab_1.util.Logger;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class University {
    private List<Faculty> faculties;
    private Scanner scanner = new Scanner(System.in);

    public University() {
        faculties = new ArrayList<>();
        List<Faculty> savedFaculties = FileManager.loadUniversityState();
        if (savedFaculties != null) {
            faculties.addAll(savedFaculties);
        }
    }


    public Faculty findFacultyByCriteria(Predicate<Faculty> criteria){
        return faculties.stream().filter(criteria).findFirst().orElse(null);
    }
    public Faculty findFacultyByName(String facultyName) {
        return findFacultyByCriteria(faculty -> faculty.getName().equals(facultyName));
    }

    public Faculty findFacultyByAbbreviation(String facultyAbbreviation) {
        return findFacultyByCriteria((faculty -> faculty.getAbbreviation().equals(facultyAbbreviation)));
    }

    public void createFaculty() {
        System.out.println("Please enter the input in the format: <faculty name>/<faculty abbreviation>/<field>");
        String facultyInput = scanner.nextLine();
        String[] facultyDetails = Tools.parseInput(facultyInput);
        String facultyName = facultyDetails[0];
        String facultyAbbreviation = facultyDetails[1];
        String field = facultyDetails[2];

        try {
            StudyField studyField = StudyField.valueOf(field.toUpperCase());

            Faculty newFaculty = new Faculty(facultyName, facultyAbbreviation, new ArrayList<>(), studyField);
            faculties.add(newFaculty);
            Logger.logFacultyCreation(newFaculty);

            System.out.println("Faculty created successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid study field: " + field);
        }
        saveUniversityState();

    }

    public List<Faculty> getAllFaculties() {
        return faculties;
    }


    public Faculty searchStudentFaculty() {
        System.out.println("Please enter the student email to search for his faculty");
        String studentEmail = scanner.nextLine();

        for (Faculty faculty : faculties) {
            for (Student student : faculty.getStudents()) {
                if (student.getEmail().equalsIgnoreCase(studentEmail)) {
                    System.out.println("Faculty " + faculty.getName());
                    return faculty;
                }
            }
        }

        System.out.println("Faculty not found for student with the email: " + studentEmail);
        return null;
    }



    public void displayUniversityFaculties() {
        if (faculties.isEmpty()) {
            System.out.println("No faculties found in the university.");
        } else {
            System.out.println("University Faculties:");
            for (Faculty faculty : faculties) {
                System.out.println(faculty.getName() + " (" + faculty.getAbbreviation() + ")");
            }
        }
    }
    public void displayFacultiesByField() {
        System.out.println("Please enter the field name to filter faculties");
        String fieldInput = scanner.nextLine();
        StudyField field = StudyField.valueOf(fieldInput.toUpperCase());

        System.out.println("Faculties in the " + field + " field:");

        boolean foundMatchingFaculties = false;

        for (Faculty faculty : faculties) {
            if (faculty.getStudyField() == field) {
                foundMatchingFaculties = true;
                System.out.println(faculty.getName() + " (" + faculty.getAbbreviation() + ")");
            }
        }

        if (!foundMatchingFaculties) {
            System.out.println("No faculties found for the field: " + fieldInput);
        }
    }

    public void createAndAssignStudent() {
        System.out.println("Please enter student details in the format: <faculty abbreviation>" +
                "/<firstname>/<lastname>/<email>/<enrollmentDate>/<dateOfBirth>");
        String studentInput = scanner.nextLine();
        String[] studentDetails = Tools.parseInput(studentInput);

        if (studentDetails.length != 6) {
            System.out.println("Invalid input format. Please use the format: " +
                    "<faculty abbreviation>/<firstname>/<lastname>/<email>/<enrollmentDate>/<dateOfBirth>");
            return;
        }

        String facultyAbbreviation = studentDetails[0];
        String firstName = studentDetails[1];
        String lastName = studentDetails[2];
        String email = studentDetails[3];
        String enrollmentDateInput = studentDetails[4];
        String dateOfBirthInput = studentDetails[5];

        Date enrollmentDate;
        Date dateOfBirth;

        try {
            enrollmentDate = Tools.parseDate(enrollmentDateInput);
            dateOfBirth = Tools.parseDate(dateOfBirthInput);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use the format: yyyy-MM-dd");
            return;
        }

        Student newStudent = new Student(firstName, lastName, email, enrollmentDate, dateOfBirth, false);

        for (Faculty faculty : faculties) {
            if (faculty.getAbbreviation().equals(facultyAbbreviation)) {
                faculty.getStudents().add(newStudent);
                Logger.logStudentCreation(newStudent,faculty);
                System.out.println("Student created and assigned to the faculty: " + faculty.getName());
                saveUniversityState();
                return;
            }
        }

        System.out.println("Faculty with abbreviation " + facultyAbbreviation + " not found.");
    }

    public void graduateStudent() {
        System.out.println("Please enter student data in the format <first name>/<last name>/<faculty>");
        String studentData = scanner.nextLine();
        String[] studentDetails = Tools.parseInput(studentData);

        if (studentDetails.length != 3) {
            System.out.println("Invalid input format. Please use <first name>/<last name>/<faculty>.");
            return;
        }

        String firstName = studentDetails[0];
        String lastName = studentDetails[1];
        String facultyName = studentDetails[2];

        Faculty faculty = findFacultyByName(facultyName);
        if (faculty == null) {
            System.out.println("Faculty with name " + facultyName + " not found.");
            return;
        }

        List<Student> students = faculty.getStudents();
        for (Student student : students) {
            if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)) {
                if (student.getGraduated() == false) {
                    student.setGraduated(true);
                    Logger.logStudentGraduation(student);
                    System.out.println("Student " + firstName + " " + lastName + " in " + facultyName + " has been graduated.");
                } else if(student.getGraduated() == true){
                    System.out.println("Student " + firstName + " " + lastName + " in " + facultyName + " is already graduated.");
                } else{System.out.println("Student " + firstName + " " + lastName + " not found in " + facultyName + ".");}
                break;
            }
        }
        saveUniversityState();
    }
    public void displayStudents(boolean check) {
        boolean foundStudents = false;

        for (Faculty faculty : faculties) {
            for (Student student : faculty.getStudents()) {
                if ((!check && !student.getGraduated()) || (check && student.getGraduated())) {
                    foundStudents = true;
                    System.out.println("Faculty: " + faculty.getName());
                    System.out.println("Student: " + student.getFirstName() + " " + student.getLastName());
                }
            }
        }

        if (!foundStudents) {
            if (check) {
                System.out.println("No graduates found.");
            } else {
                System.out.println("No current enrolled students found.");
            }
        }
    }


    public void checkStudentBelongsToFaculty() {
        System.out.println("Please enter the student details in the format <first name>/<last name>/<faculty abbreviation>");
        String studentData = scanner.nextLine();
        String[] studentDetails = Tools.parseInput(studentData);

        if (studentDetails.length != 3) {
            System.out.println("Invalid input format. Please use the format: <first name>/<last name>/<faculty abbreviation>");
            return;
        }

        String firstName = studentDetails[0];
        String lastName = studentDetails[1];
        String facultyAbbreviation = studentDetails[2];

        Faculty faculty = findFacultyByAbbreviation(facultyAbbreviation);
        if (faculty == null) {
            System.out.println("Faculty with abbreviation " + facultyAbbreviation + " not found.");
        } else {
            boolean studentBelongsToFaculty = faculty.containsStudent(firstName, lastName);
            if (studentBelongsToFaculty) {
                System.out.println(firstName + " " + lastName + " belongs to " + faculty.getName());
            } else {
                System.out.println(firstName + " " + lastName + " does not belong to " + faculty.getName());
            }
        }
    }

    public void saveUniversityState() {
        FileManager.saveUniversityState(getAllFaculties());
    }

}

