package lab_1.models;


import lab_1.models.Faculty;
import lab_1.models.Student;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Faculty> faculties;

    public University() {
        faculties = new ArrayList<>();
    }

    private String[] parseInput(String input) {
        return input.split("/");
    }
    public void createFaculty(String facultyInput) {
        String [] facultyDetails = parseInput(facultyInput);
        if (facultyDetails.length == 3) {
            String facultyName = facultyDetails[0];
            String facultyAbbreviation = facultyDetails[1];
            String field = facultyDetails[2];

            // Create a new Faculty instance and add it to the faculties list
            Faculty newFaculty = new Faculty(facultyName, facultyAbbreviation, new ArrayList<>(), field);
            faculties.add(newFaculty);
            System.out.println("Faculty created successfully!");
        } else {
            System.out.println("Invalid input format. Please use <faculty name>/<faculty abbreviation>/<field>.");
        }
    }

    public List<Faculty> getAllFaculties() {
        // Return a list of all faculties
        return faculties;
    }

    public List<Faculty> getFacultiesByField(String field) {
        // Placeholder for displaying faculties by field
        List<Faculty> facultiesByField = new ArrayList<>();
        for (Faculty faculty : faculties) {
            if (faculty.getStudyField().toString().equals(field)) {
                facultiesByField.add(faculty);
            }
        }
        return facultiesByField;
    }

    public Faculty findFacultyByStudentEmail(String studentEmail) {
        for (Faculty faculty : faculties) {
            for (Student student : faculty.getStudents()) {
                if (student.getEmail().equalsIgnoreCase(studentEmail)) {
                    return faculty;
                }
            }
        }
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

}

