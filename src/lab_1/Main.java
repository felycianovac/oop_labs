package lab_1;
import lab_1.behavior.AppLoop;
import lab_1.models.Faculty;
import lab_1.models.University;
import lab_1.util.FileManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Faculty> savedFaculties = FileManager.loadUniversityState();
        University university = new University();

        // Add the loaded faculties to the university instance
        if (savedFaculties != null) {
            university.getAllFaculties().addAll(savedFaculties);
            // }

            AppLoop appLoop = new AppLoop();
            appLoop.run();

            scanner.close();

        }


    }
}
