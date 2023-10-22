package lab_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AppLoop appLoop = new AppLoop();
        appLoop.run();
        scanner.close();
    }
}
