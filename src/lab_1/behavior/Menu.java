package lab_1.behavior;
import lab_1.models.University;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private University university = new University();
    private Scanner scanner = new Scanner(System.in);

    public Menu(University university, Scanner scanner){
        this.university = university;
        this.scanner = scanner;
    }
    public void start(){
        System.out.println("WELCOME TO THE STUDENT MANAGEMENT SYSTEM !");
        while(true){
            displayMainMenu();
        }
    }

    private void displayMainMenu() {
        System.out.println("What do you want to do?:");
        System.out.println("g - General operations");
        System.out.println("f - Faculty operations");
        System.out.println("s - Student operations");
    }

    private void displayGeneralMenu(){
        System.out.println("----------------------");
        System.out.println("You are currently in the General menu. What do you want to do next?");
        System.out.println("nf - Create a new faculty");
        System.out.println("ssf - Search what a faculty a student belongs to");
        System.out.println("uf - Display university faculties");
        System.out.println("df - Diplay all faculties belonging to a specific field");
        System.out.println("b - Back to main menu");
        System.out.println("q -Quit program");
    }

    private void displayFacultyMenu(){
        System.out.println("----------------------");
        System.out.println("You are currently in the Faculty menu. What do you want to do next?");
        System.out.println("cs - Create and assign student to a faculty");
        System.out.println("gs - Graduate student from a faculty");
        System.out.println("des - Display current enrolled students");
        System.out.println("dg - Display graduates");
        System.out.println("bf - Tell if a student belongs to a faculty");
        System.out.println("");
        System.out.println("b - Back to main menu");
        System.out.println("q -Quit program");
    }

    private String getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextLine().trim().toLowerCase(); // Convert to lowercase for case-insensitive comparison
    }

    private void generalOperationsMenu() {
        while (true) {
            displayGeneralMenu();

            String choice = getUserChoice();
            switch (choice) {
                case "nf":
                    System.out.println("Please enter the input in the format: <faculty name>/<faculty abbreviation>/<field>");
                    String facultyInput = scanner.nextLine();
                    createFaculty(facultyInput);
                    break;
                case "ssf":
                    System.out.println("Please enter the student email to search for his faculty");
                    String studentEmail = scanner.nextLine();
                    searchStudentFaculty(studentEmail);
                    break;
                case "uf":
                    displayUniversityFaculties();
                    break;
                case "df":
                    System.out.println("Please enter the field name to filter faculties");
                    String field = scanner.nextLine();
                    displayFacultiesByField(field);
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

    private void facultyOperationsMenu() {
        while (true) {
            displayFacultyMenu();

            String choice = getUserChoice();
            switch (choice) {
                case "cs":
                    System.out.println("Please enter student details in the format: <firstname>/<lastname>/<email>/<enrollmentDate>/<dateOfBirth>");
                    String studentInput = scanner.nextLine();
                    createAndAssignStudent(studentInput);
                    break;
                case "gs":
                    System.out.println("Please enter student data in the format <first name>/<last name>/<faculty>");
                    String studentData = scanner.nextLine();
                    graduateStudent(studentData);
                    break;
                case "des":
                    displayEnrolledStudents();
                    break;
                case "dg":
                    displayGraduates();
                    break;
                case "bf":
                    System.out.println("Please enter the student details in the format <first name>/<last name>/<faculty>");
                    String studentFaculty = scanner.nextLine();
                    checkStudentBelongsToFaculty(studentFaculty);
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


    private void quitProgram() {
        System.out.println("Exiting the program. Goodbye!");
        System.exit(0);
    }
}