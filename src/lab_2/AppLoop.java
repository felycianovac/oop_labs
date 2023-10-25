package lab_2;

import lab_2.files.Document;
import lab_2.utils.DirectoryAnalyzer;

import java.util.List;
import java.util.Scanner;

public class AppLoop {
    private List<Document> documents;
    private Repository vcsRepository;
    private String folderPath;
    private Scanner scanner;

    public AppLoop(String folderPath) {
        this.folderPath = folderPath;
        this.scanner = new Scanner(System.in);
        DirectoryAnalyzer directoryAnalyzer = new DirectoryAnalyzer(folderPath);
        documents = directoryAnalyzer.extractDirectoryFiles();
        this.vcsRepository = new Repository(folderPath,documents);
    }

    public void run() {
        while (true) {
            displayMenu();
            String choice = getUserChoice();
            if(choice.startsWith("info")){
                String filename = choice.substring(5);
                vcsRepository.info(filename);



            }
        }
    }

    public void displayMenu() {
        System.out.print("""
                Menu of actions:
                1. commit
                2. info <filename>
                3. status
                4. exit
                """);
    }

    private String getUserChoice() {
        System.out.println("Please enter your choice: ");
        return scanner.nextLine().trim();
    }


    private void quitProgram() {
        System.out.println("Exiting the program. Goodbye!");
        System.exit(0);
    }
}


