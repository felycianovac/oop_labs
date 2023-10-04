package lab_1.util;

import lab_1.enums.StudyField;
import lab_1.models.Faculty;
import lab_1.models.Student;


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class FileManager {
    private static final Logger logger = Logger.getLogger(FileManager.class.getName());
    private static final String SAVE_FILE_PATH = "D:\\UTM\\3rd semester\\OOP\\OOP Labs\\src\\lab_1\\databases\\university.txt";

    public static void saveUniversityState(List<Faculty> faculties) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE_PATH))) {
            for (Faculty faculty : faculties) {
                writer.write("Faculty: " + faculty.getName() + "," + faculty.getAbbreviation() + "," + faculty.getStudyField());
                writer.newLine();
                for (Student student : faculty.getStudents()) {
                    writer.write("Student: ");
                    writer.write(student.getFirstName() + "," + student.getLastName() + ","
                            + student.getEmail() + "," + new SimpleDateFormat("yyyy-MM-dd").format(student.getEnrollmentDate())
                            + "," + new SimpleDateFormat("yyyy-MM-dd").format(student.getDateOfBirth()) + ","
                            + student.getGraduated());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error while saving university state: " + e.getMessage());
        }
    }

    public static List<Faculty> loadUniversityState() {
        List<Faculty> faculties = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE_PATH))) {
            String line;
            Faculty currentFaculty = null;
            while ((line = reader.readLine()) != null) {

                if (line.startsWith("Faculty:")) {
                    String[] parts = line.split(",");
                    if (parts.length >= 3) {
                        String facultyName = parts[0].substring("Faculty:".length()).trim();
                        String facultyAbbreviation = parts[1].trim();
                        StudyField studyField = StudyField.valueOf(parts[2].trim());

                        currentFaculty = new Faculty(facultyName, facultyAbbreviation, new ArrayList<>(), studyField);
                        faculties.add(currentFaculty);
                    }
                } else if (line.startsWith("Student: ") && currentFaculty != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 6) {
                        String firstName = parts[0].substring("Student:".length()).trim();
                        String lastName = parts[1].trim();
                        String email = parts[2].trim();
                        Date enrollmentDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[3].trim());
                        Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(parts[4].trim());
                        boolean graduated = Boolean.parseBoolean(parts[5].trim());

                        Student student = new Student(firstName, lastName, email, enrollmentDate, dateOfBirth, graduated);
                        currentFaculty.getStudents().add(student);
                    }
                }
            }
            System.out.println("University state loaded successfully.");
        } catch (IOException | ParseException e) {
            System.err.println("Error while loading university state: " + e.getMessage());
        }
        return faculties;
    }

}
