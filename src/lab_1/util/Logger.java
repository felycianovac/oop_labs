package lab_1.util;

import lab_1.models.Faculty;
import lab_1.models.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String LOG_FILE_PATH = "D:\\UTM\\3rd semester\\OOP\\OOP Labs\\src\\lab_1\\databases\\log.txt";
    public static void logStudentCreation(Student student, Faculty faculty) {
        log("Student created: " + student.getFirstName() + " " + student.getLastName() +
                " and assigned to " + faculty.getName());
    }

    public static void logStudentGraduation(Student student) {
        log("Student graduated: " + student.getFirstName() + " " + student.getLastName());
    }

    public static void logFacultyCreation(Faculty faculty) {
        log("Faculty created: " + faculty.getName());
    }

    private static void log(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE_PATH, true))) {
            LocalDateTime now = LocalDateTime.now();
            String formattedTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.println(formattedTime + " - " + message);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}

