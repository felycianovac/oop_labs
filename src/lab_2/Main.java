package lab_2;

import lab_2.files.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String folderPath = "D:\\UTM\\3rd semester\\OOP\\OOP Labs\\src\\lab_2\\directory";
        Scanner scanner = new Scanner(System.in);
        List<Document> documents = new ArrayList<>();

        AppLoop appLoop = new AppLoop(folderPath);
        appLoop.run();
        scanner.close();
    }
}
