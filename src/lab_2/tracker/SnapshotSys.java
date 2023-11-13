package lab_2.tracker;

import lab_2.files.Document;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class SnapshotSys {
    private String snapshotPath;
    private String directoryPath;
    private Map<String, Document> currentSnapshot;
    private Map<String, Document> previousSnapshot;
    private long lastSnapshotTime;
    private Hashtable<String, Document> lastKnownSnapshot;


    public SnapshotSys(String directoryPath, String snapshotPath) {
        this.directoryPath = directoryPath;
        this.snapshotPath = snapshotPath;
        this.currentSnapshot = new HashMap<>();
        this.previousSnapshot = new HashMap<>();
        this.lastSnapshotTime = 0;
        this.lastKnownSnapshot = new Hashtable<>();

    }

    public void loadSnapshots() {
        loadPreviousSnapshot();
        loadCurrentSnapshot();
    }

    private void loadPreviousSnapshot() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(snapshotPath));
            if (!lines.isEmpty()) {
                lastSnapshotTime = Long.parseLong(lines.remove(0));
            }
            previousSnapshot = lines.stream()
                    .map(line -> line.split("\\|"))
                    .filter(parts -> parts.length == 2)
                    .collect(Collectors.toMap(
                            parts -> parts[0],
                            parts -> new Document(directoryPath, parts[0], null, new Date(Long.parseLong(parts[1]))),
                            (document1, document2) -> document1
                    ));
        } catch (IOException e) {
            System.out.println("Failed to load previous snapshot or snapshot doesn't exist");
        }
    }

    private void loadCurrentSnapshot() {
        File directory = new File(directoryPath);
        currentSnapshot.clear();
        if (directory.isDirectory()) {
            for (File file : Optional.ofNullable(directory.listFiles()).orElse(new File[0])) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    Document document = new Document(directoryPath, fileName, null, new Date(file.lastModified()));
                    currentSnapshot.put(fileName, document);
                }
            }
        }
    }


    public void saveSnapshot() {

        try (PrintWriter writer = new PrintWriter(snapshotPath)) {
            writer.println(System.currentTimeMillis());
            loadCurrentSnapshot();
            currentSnapshot.forEach((fileName, document) -> {
                writer.printf("%s|%d%n", fileName, document.getLastModified().getTime());
            });

        } catch (IOException e) {
            System.out.println("Failed to save snapshot: " + e.getMessage());
        }
    }

    public Map<String, Document> getLastKnownSnapshot() {
        return lastKnownSnapshot;
    }

    public void setLastKnownSnapshot() {
        this.lastKnownSnapshot = new Hashtable<>(currentSnapshot);
    }

    public Map<String, Document> getCurrentSnapshot() {
        return currentSnapshot;
    }

    public Map<String, Document> getPreviousSnapshot() {
        return previousSnapshot;
    }

    public long getLastSnapshotTime() {
        return lastSnapshotTime;
    }


}
