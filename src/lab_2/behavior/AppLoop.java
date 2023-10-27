package lab_2.behavior;

import lab_2.files.Document;
import lab_2.utils.FileUtils;
import lab_2.tracker.DetectorScheduler;
import lab_2.tracker.Repository;
import lab_2.tracker.SnapshotSys;

import java.util.List;
import java.util.Scanner;

public class AppLoop {
    private List<Document> documents;
    private Repository repository;
    private String directoryPath;
    private Scanner scanner;
    private DetectorScheduler detectorScheduler;

    public AppLoop(String directoryPath, Repository repository) {
        this.detectorScheduler = new DetectorScheduler(repository);
        this.directoryPath = directoryPath;
        this.scanner = new Scanner(System.in);
        FileUtils directoryAnalyzer = new FileUtils(directoryPath);
        documents = directoryAnalyzer.extractDirectoryFiles();
        this.repository = repository;
        detectorScheduler.start();
    }

    public void run() {
        while (true) {
            displayMenu();
            String choice = getUserChoice();
            if(choice.startsWith("info")){
                String filename = choice.substring(5);
                repository.info(filename);
            } else if(choice.equals("commit")){
                repository.commit();
            }
            else if(choice.equals("status")){
                repository.status();
            }
            else if(choice.equals("exit")){
                quitProgram();
            }
            else System.out.println("Invalid choice!");


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
        detectorScheduler.stop();
        System.out.println("Exiting the program. Goodbye!");
        System.exit(0);
    }

    public Repository getRepository() {
        return repository;
    }
}


