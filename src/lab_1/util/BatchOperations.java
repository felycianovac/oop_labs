package lab_1.util;

import lab_1.models.Faculty;
import lab_1.models.Student;
import lab_1.models.University;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class BatchOperations {
    private University university;

    private static final String ENROLLMENT_FILE_PATH = "D:\\UTM\\3rd semester\\OOP\\OOP Labs\\src\\lab_1\\databases\\enrollment_file.txt";
    private static final String GRADUATION_FILE_PATH = "D:\\UTM\\3rd semester\\OOP\\OOP Labs\\src\\lab_1\\databases\\graduation_file.txt";
    public BatchOperations(University university) {
        this.university = university;
    }

    public void performBatchEnrollment() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ENROLLMENT_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] studentDetails = line.split("/");
                if (studentDetails.length == 6) {
                    String facultyAbbreviation = studentDetails[0];
                    String firstName = studentDetails[1];
                    String lastName = studentDetails[2];
                    String email = studentDetails[3];
                    String enrollmentDateInput = studentDetails[4];
                    String dateOfBirthInput = studentDetails[5];

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date enrollmentDate = dateFormat.parse(enrollmentDateInput);
                    Date dateOfBirth = dateFormat.parse(dateOfBirthInput);

                    Student newStudent = new Student(firstName, lastName, email, enrollmentDate, dateOfBirth, false);

                    Faculty faculty = university.findFacultyByAbbreviation(facultyAbbreviation);
                    if (faculty != null) {
                        faculty.getStudents().add(newStudent);
                        Logger.logStudentCreation(newStudent, faculty);
                    } else {
                        System.out.println("Faculty with abbreviation " + facultyAbbreviation + " not found.");
                    }
                }
            }
            university.saveUniversityState();
            System.out.println("Batch enrollment completed successfully.");
        } catch (IOException | ParseException e) {
            System.err.println("Error during batch enrollment: " + e.getMessage());
        }
    }

    public void performBatchGraduation() {
        try (BufferedReader reader = new BufferedReader(new FileReader(GRADUATION_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] studentDetails = line.split("/");
                if (studentDetails.length == 3) {
                    String firstName = studentDetails[0];
                    String lastName = studentDetails[1];
                    String facultyName = studentDetails[2];

                    Faculty faculty = university.findFacultyByName(facultyName);
                    if (faculty != null) {
                        for (Student student : faculty.getStudents()) {
                            if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)) {
                                if (!student.getGraduated()) {
                                    student.setGraduated(true);
                                    Logger.logStudentGraduation(student);
                                }
                                break;
                            }
                        }
                    } else {
                        System.out.println("Faculty with name " + facultyName + " not found.");
                    }
                }
            }
            university.saveUniversityState();
            System.out.println("Batch graduation completed successfully.");
        } catch (IOException e) {
            System.err.println("Error during batch graduation: " + e.getMessage());
        }
    }
}
