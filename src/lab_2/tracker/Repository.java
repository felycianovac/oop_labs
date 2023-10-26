package lab_2.tracker;

import lab_2.files.Document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Repository {
    private SnapshotSys snapshotSys;
    private List<Document> documents;
    private String DIRECTORY_PATH;
    private DirectoryAnalyzer directoryAnalyzer;
    private FileChangeDetector fileChangeDetector;


    public Repository(String filePath, List<Document> documents, SnapshotSys snapshotSys){
        this.documents = documents;
        this.DIRECTORY_PATH = DIRECTORY_PATH;
        this.snapshotSys = snapshotSys;
        this.directoryAnalyzer = new DirectoryAnalyzer(filePath);
        this.fileChangeDetector = new FileChangeDetector(this);
    }

    public void info(String filename) {

        documents = directoryAnalyzer.extractDirectoryFiles();
        List<Document> matchingDocs = documents.stream()
                .filter(document -> document.getFilename().equals(filename))
                .collect(Collectors.toList());

        if (matchingDocs.isEmpty()) {
            System.out.println("File not found!");
        } else {
            for (Document document : matchingDocs) {
                document.info();
            }
        }
    }

    public void commit(){
        snapshotSys.saveSnapshot();
        snapshotSys.setLastKnownSnapshot();
        System.out.println("Changes committed!");
    }

    public void status() {
        //TODO: implement a method that print the report of the changes (unify with FileChangeDetector.detectChanges())
        snapshotSys.loadSnapshots();
        long lastSnapshotTime = snapshotSys.getLastSnapshotTime();

        System.out.println("Last Snapshot Time: " + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date(lastSnapshotTime)));

        Map<String, Document> lastKnownSnapshot = snapshotSys.getLastKnownSnapshot();
        Map<String, Document> currentSnapshot = snapshotSys.getCurrentSnapshot();
        Map<String, Document> previousSnapshot = snapshotSys.getPreviousSnapshot();

        for (Map.Entry<String, Document> entry : currentSnapshot.entrySet()) {
            String fileName = entry.getKey();
            Document document = entry.getValue();
            if (!previousSnapshot.containsKey(fileName)) {
                System.out.println(fileName + " - New File");
            } else if (!document.getLastModified().equals(previousSnapshot.get(fileName).getLastModified())) {
                System.out.println(fileName + " - Changed");
            } else {
                System.out.println(fileName + " - Unchanged");
            }
        }

        for (String fileName : previousSnapshot.keySet()) {
            if (!currentSnapshot.containsKey(fileName)) {
                System.out.println(fileName + " - Deleted");
            }
        }
    }

    public SnapshotSys getSnapshotSys() {
        return snapshotSys;
    }
}
