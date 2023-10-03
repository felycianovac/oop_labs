package lab_1.behavior;
import lab_1.models.Faculty;
import lab_1.models.University;

import java.util.Scanner;

public class AppLoop {
    private University university = new University();
    private Scanner scanner = new Scanner(System.in);

    public AppLoop(University university, Scanner scanner){
        this.university = university;
        this.scanner = scanner;
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
                    // TODO move to its own method
                    System.out.println("Please enter the input in the format: <faculty name>/<faculty abbreviation>/<field>");
                    String facultyInput = scanner.nextLine();
                    university.createFaculty(facultyInput);
                    break;
                case "ssf":
                    handleSearchStudent();
                    break;
                case "uf":
                    university.displayUniversityFaculties();
                    break;
                case "df":
                    System.out.println("Please enter the field name to filter faculties");
                    String field = scanner.nextLine();
                    university.displayFacultiesByField(field);
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

    private void handleSearchStudent() {
        System.out.println("Please enter the student email to search for his faculty");
        String studentEmail = scanner.nextLine();
        Faculty faculty = university.searchStudentFaculty(studentEmail);
        if(faculty != null){
            System.out.println("Faculty " + faculty.getName());
        } else {System.out.println("Faculty not found for student with the email: " + studentEmail);}
    }

    private void facultyOperationsMenu() {
        while (true) {
            displayFacultyMenu();

            String choice = getUserChoice();
            switch (choice) {
                case "cs":
                    System.out.println("Please enter student details in the format: <faculty abbreviation>/<firstname>/<lastname>/<email>/<enrollmentDate>/<dateOfBirth>");
                    String studentInput = scanner.nextLine();
                    university.createAndAssignStudent(studentInput);
                    break;
                case "gs":
                    System.out.println("Please enter student data in the format <first name>/<last name>/<faculty>");
                    String studentData = scanner.nextLine();
                    university.graduateStudent(studentData);
                    break;
                case "des":
                    university.displayEnrolledStudents();
                    break;
                case "dg":
                    university.displayGraduates();
                    break;
                case "bf":
                    System.out.println("Please enter the student details in the format <first name>/<last name>/<faculty abbreviation>");
                    String studentFaculty = scanner.nextLine();
                    university.checkStudentBelongsToFaculty(studentFaculty);
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