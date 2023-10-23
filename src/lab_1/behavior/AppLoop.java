package lab_1.behavior;
import lab_1.models.Faculty;
import lab_1.models.University;
import lab_1.util.BatchOperations;

import java.util.Scanner;

public class AppLoop {
    private University university;
    private BatchOperations batchOperations;
    private Scanner scanner;

    public AppLoop(){
        this.scanner = new Scanner(System.in);
        this.university = new University();
        this.batchOperations = new BatchOperations(university);
    }
    public void run() {

        System.out.println("WELCOME TO THE STUDENT MANAGEMENT SYSTEM !");
        while (true) {
            displayMainMenu();
            String choice = getUserChoice();

            switch (choice) {
                case "g":
                    generalOperationsMenu();
                    break;
                case "f":
                    facultyOperationsMenu();
                    break;
                case "s":
                    studentMenu();
                    break;
                case "q":
                    quitProgram();
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }

        }
    }
    private void displayMainMenu() {
        System.out.println("""
                What do you want to do?
                g - General operations
                f - Faculty operations
                s - Student operations
                
                q - Quit program
                """);
    }

    private void displayGeneralMenu(){
        System.out.println("""
                -------------------------------------------------------------------
                You are currently in the General menu. What do you want to do next?
                nf - Create a new faculty
                ssf - Search what a faculty a student belongs to
                uf - Display university faculties
                df - Diplay all faculties belonging to a specific field
                
                b - Back to main menu
                q -Quit program
                """);
    }

    private void displayFacultyMenu() {

        System.out.println("""
                You are currently in the Faculty menu. What do you want to do next?
                cs - Create and assign student to a faculty
                gs - Graduate student from a faculty
                des - Display current enrolled students
                dg - Display graduates
                bf - Tell if a student belongs to a faculty
                                
                b - Back to main menu
                q -Quit program
                """);
    }

    private void displayStudentMenu(){
        System.out.println("""
                You are currently in the Student menu. What do you want to do next?
                 be - Perform students' batch enrollment via enrollment_file.txt
                 bg - Perform students batch graduation via graduation_file.txt
                 
                 b - Back to main menu
                 q - Quit program' """);
    }
    private String getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextLine().trim().toLowerCase();
    }

    private void generalOperationsMenu() {
        while (true) {
            displayGeneralMenu();

            String choice = getUserChoice();
            switch (choice) {
                case "nf":
                    university.createFaculty();
                    break;
                case "ssf":
                    university.searchStudentFaculty();
                    break;
                case "uf":
                    university.displayUniversityFaculties();
                    break;
                case "df":
                    university.displayFacultiesByField();
                    break;
                case "b":
                    return;
                case "q":
                    quitProgram();
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }


    private void facultyOperationsMenu() {
        while (true) {
            displayFacultyMenu();

            String choice = getUserChoice();
            switch (choice) {
                case "cs":
                    university.createAndAssignStudent();
                    break;
                case "gs":
                    university.graduateStudent();
                    break;
                case "des":
                    university.displayStudents(false);
                    break;
                case "dg":
                    university.displayStudents(true);
                    break;
                case "bf":
                    university.checkStudentBelongsToFaculty();
                    break;
                case "b":
                    return; // Return to Main Menu
                case "q":
                    quitProgram();
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private void studentMenu() {
        while (true) {
            displayStudentMenu();
            String choice = getUserChoice();
            switch (choice){
                case "be":
                    batchOperations.performBatchEnrollment();
                    break;
                case "bg":
                    batchOperations.performBatchGraduation();
                    break;
                case "b":
                    return;
                case "q":
                    quitProgram();
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");

            }
        }
    }

    private void quitProgram() {
        System.out.println("Exiting the program. Goodbye!");
        System.exit(0);
    }
}