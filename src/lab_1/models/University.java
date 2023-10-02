package lab_1.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class University {
    private List<Faculty> faculties;

    public University() {
        faculties = new ArrayList<>();
    }

    private String[] parseInput(String input) {
        return input.split("/");
    }
    public Faculty findFacultyByName(String facultyName) {
        for (Faculty faculty : faculties) {
            if (faculty.getName().equals(facultyName)) {
                return faculty;
            }
        }
        return null; // Faculty not found
    }

    public Faculty findFacultyByAbbreviation(String facultyAbbreviation) {
        for (Faculty faculty : faculties) {
            if (faculty.getAbbreviation().equals(facultyAbbreviation)) {
                return faculty;
            }
        }
        return null; // Faculty not found
    }

    public void createFaculty(String facultyInput) {
        String[] facultyDetails = parseInput(facultyInput);
        String facultyName = facultyDetails[0];
        String facultyAbbreviation = facultyDetails[1];
        String field = facultyDetails[2];

        try {
            StudyField studyField = StudyField.valueOf(field.toUpperCase());

            // Create a new Faculty instance and add it to the faculties list
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


    public Student searchStudentFaculty(String studentEmail) {
        // Iterate through all faculties and their students to find the student with the given email
        for (Faculty faculty : faculties) {
            for (Student student : faculty.getStudents()) {
                if (student.getEmail().equalsIgnoreCase(studentEmail)) {
                    return student; // Return the student if found
                }
            }
        }

        return null; // Return null if the student is not found
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
    public List<Faculty> getFacultiesByField(StudyField field) {
        List<Faculty> matchingFaculties = new ArrayList<>();

        for (Faculty faculty : faculties) {
            if (faculty.getStudyField() == field) {
                matchingFaculties.add(faculty);
            }
        }

        return matchingFaculties;
    }

    public void displayFacultiesByField(String fieldInput) {
        StudyField field = StudyField.valueOf(fieldInput.toUpperCase()); // Convert the input to an enum value

        List<Faculty> facultiesByField = getFacultiesByField(field);

        if (facultiesByField.isEmpty()) {
            System.out.println("No faculties found for the field: " + fieldInput);
        } else {
            System.out.println("Faculties in the " + field + " field:");
            for (Faculty faculty : facultiesByField) {
                System.out.println(faculty.getName() + " (" + faculty.getAbbreviation() + ")");
            }
        }
    }
    public void createAndAssignStudent(String studentInput) {
        String[] studentDetails = parseInput(studentInput);

        if (studentDetails.length != 6) {
            System.out.println("Invalid input format. Please use the format: <faculty abbreviation>/<firstname>/<lastname>/<email>/<enrollmentDate>/<dateOfBirth>");
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

        // Iterate through faculties to find the matching faculty by abbreviation
        for (Faculty faculty : faculties) {
            if (faculty.getAbbreviation().equals(facultyAbbreviation)) {
                faculty.getStudents().add(newStudent);
                System.out.println("Student created and assigned to the faculty: " + faculty.getName());
                return;
            }
        }

        // If no matching faculty is found
        System.out.println("Faculty with abbreviation " + facultyAbbreviation + " not found.");
    }

    public void graduateStudent(String studentData) {
        String[] studentDetails = parseInput(studentData);
        if (studentDetails.length != 3) {
            System.out.println("Invalid input format. Please use <first name>/<last name>/<faculty>.");
            return;
        }

        String firstName = studentDetails[0];
        String lastName = studentDetails[1];
        String facultyName = studentDetails[2];

        // Find the faculty by name
        Faculty faculty = findFacultyByName(facultyName);
        if (faculty == null) {
            System.out.println("Faculty with name " + facultyName + " not found.");
            return;
        }

        // Find the student in the faculty by first name and last name
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

        // If the student is not found in the faculty
        System.out.println("Student " + firstName + " " + lastName + " not found in " + facultyName + ".");
    }
    public void displayEnrolledStudents() {
        System.out.println("Enrolled Students:");
        for (Faculty faculty : faculties) {
            List<Student> currentStudents = faculty.getCurrentStudents();
            if (!currentStudents.isEmpty()) {
                System.out.println("Faculty: " + faculty.getName());
                for (Student student : currentStudents) {
                    System.out.println("Student: " + student.getFirstName() + " " + student.getLastName());
                }
            }
        }
    }
    public void displayGraduates() {
        List<Student> graduates = new ArrayList<>();
        for (Faculty faculty : faculties) {
            List<Student> facultyGraduates = faculty.getGraduates();
            graduates.addAll(facultyGraduates);
        }

        if (graduates.isEmpty()) {
            System.out.println("No graduates found.");
        } else {
            System.out.println("Graduates:");
            for (Student graduate : graduates) {
                System.out.println(graduate.getFirstName() + " " + graduate.getLastName());
            }
        }
    }
    public void checkStudentBelongsToFaculty(String studentData) {
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

