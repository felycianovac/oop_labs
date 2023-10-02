package lab_1;
import lab_1.behavior.AppLoop;
import lab_1.models.University;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        University university = new University();
        AppLoop appLoop = new AppLoop(university, scanner);
        appLoop.run(); // Call the "run" method to start the application loop
    }

}
