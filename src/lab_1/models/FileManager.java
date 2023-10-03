package lab_1.models;

import java.io.*;
import java.util.List;

public class FileManager {
    private String dataFilePath;

    public FileManager(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    public boolean saveData(List<University> universities) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFilePath))) {
            oos.writeObject(universities);
            System.out.println("Data saved successfully.");
            return true;
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
            return false;
        }
    }

    public List<University> loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFilePath))) {
            List<University> universities = (List<University>) ois.readObject();
            System.out.println("Data loaded successfully.");
            return universities;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
        return null;
    }
}
