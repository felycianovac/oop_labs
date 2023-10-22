package lab_2;

import java.util.Scanner;

public class AppLoop {
    private Scanner scanner;

    public AppLoop() {
        this.scanner = new Scanner(System.in);
    }

    public void run(){
        while(true){
            displayMenu();
            String choice = getUserChoice();

            switch (choice){
                case "commit":
                    //TODO: implement commit
                    break;
                case "info":
                    //TODO: implement info
                    break;
                case "status":
                    //TODO: implement status
                    break;
                case "exit":
                    quitProgram();
                    break;
                default:
                    System.out.println("Invalid choice, please enter a valid one!");
            }
        }
    }
    public void displayMenu(){
        System.out.print("""
                Menu of actions:
                1. commit
                2. info <filename>
                3. status
                4. exit
                """);
    }
private String getUserChoice(){
        System.out.println("Please enter your choice: ");
        return scanner.nextLine().trim().toLowerCase();
}

private void quitProgram(){
        System.out.println("Exiting the program. Goodbye!");
        System.exit(0);
}
}
