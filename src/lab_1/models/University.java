package lab_1.models;

import lab_1.enums.StudyField;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class University {
    private List<Faculty> faculties;
    private Scanner scanner;

    public University(Scanner scanner) {
        faculties = new ArrayList<>();
        this.scanner = scanner;
    }

    private String[] parseInput(String input) {
        return input.split("/");
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
        String[] facultyDetails = parseInput(facultyInput);
        String facultyName = facultyDetails[0];
        String facultyAbbreviation = facultyDetails[1];
        String field = facultyDetails[2];

        try {
            StudyField studyField = StudyField.valueOf(field.toUpperCase());

            Faculty newFaculty = new Faculty(facultyName, facultyAbbreviation, new ArrayList<>(), studyField);
            faculties.add(newFaculty);
            System.out.println("Faculty created successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid study field: " + field);
        }
    }

    public List<Faculty> getAllFaculties() {
        return faculties;
    }


    public Faculty searchStudentFaculty(String studentEmail) {

        for (Faculty faculty : faculties) {
            for (Student student : faculty.getStudents()) {
                if (student.getEmail().equalsIgnoreCase(studentEmail)) {
                    return faculty;
                }
            }
        }
        System.out.println("Person with email " + studentEmail + " not found");
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
        String[] studentDetails = parseInput(studentInput);

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

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date enrollmentDate;
        Date dateOfBirth;

        try {
            enrollmentDate = dateFormat.parse(enrollmentDateInput);
            dateOfBirth = dateFormat.parse(dateOfBirthInput);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use the format: yyyy-MM-dd");
            return;
        }

        Student newStudent = new Student(firstName, lastName, email, enrollmentDate, dateOfBirth, false);

        for (Faculty faculty : faculties) {
            if (faculty.getAbbreviation().equals(facultyAbbreviation)) {
                faculty.getStudents().add(newStudent);
                System.out.println("Student created and assigned to the faculty: " + faculty.getName());
                return;
            }
        }

        System.out.println("Faculty with abbreviation " + facultyAbbreviation + " not found.");
    }

    public void graduateStudent() {
        System.out.println("Please enter student data in the format <first name>/<last name>/<faculty>");
        String studentData = scanner.nextLine();
        String[] studentDetails = parseInput(studentData);
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
                if (!student.getGraduated()) {
                    student.setGraduated(true);
                    System.out.println("Student " + firstName + " " + lastName + " in " + facultyName + " has been graduated.");
                } else {
                    System.out.println("Student " + firstName + " " + lastName + " in " + facultyName + " is already graduated.");
                }
                return;
            }
        }

        System.out.println("Student " + firstName + " " + lastName + " not found in " + facultyName + ".");
    }
    public void displayStudents(boolean check) {
        if (faculties.isEmpty()) {
            System.out.println("No faculties found.");
            return;
        }

        boolean foundGraduates = false;

        for (Faculty faculty : faculties) {
            for (Student student : faculty.getStudents()) {
                if (!check && !student.getGraduated()) {
                    System.out.println("Faculty: " + faculty.getName());
                    System.out.println("Student: " + student.getFirstName() + " " + student.getLastName());
                } else if (check && student.getGraduated()) {
                    foundGraduates = true;
                    System.out.println("Faculty: " + faculty.getName());
                    System.out.println("Student: " + student.getFirstName() + " " + student.getLastName());
                }
            }
        }

        if (!foundGraduates && check) {
            System.out.println("No graduates found.");
        }
    }



    public void checkStudentBelongsToFaculty() {
        System.out.println("Please enter the student details in the format <first name>/<last name>/<faculty abbreviation>");
        String studentData = scanner.nextLine();
        String[] studentDetails = parseInput(studentData);

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
}

