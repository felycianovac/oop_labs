package lab_2;

import lab_2.files.Document;
import lab_2.tracker.Repository;
import lab_2.tracker.SnapshotSys;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String DIRECTORY_PATH = "D:\\UTM\\3rd semester\\OOP\\OOP Labs\\src\\lab_2\\directory";
        String SNAPSHOT_PATH = "D:\\UTM\\3rd semester\\OOP\\OOP Labs\\src\\lab_2\\snapshot.txt";


        Scanner scanner = new Scanner(System.in);
        List<Document> documents = new ArrayList<>();



        SnapshotSys snapshotSys = new SnapshotSys(DIRECTORY_PATH,SNAPSHOT_PATH);
        Repository repository = new Repository(DIRECTORY_PATH,documents,snapshotSys);


        AppLoop appLoop = new AppLoop(DIRECTORY_PATH,repository);

        appLoop.run();
        scanner.close();
    }
}
