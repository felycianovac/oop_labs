package lab_2.tracker;

import lab_2.files.Document;
import lab_2.utils.FileUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Repository {
    private SnapshotSys snapshotSys;
    private List<Document> documents;
    private String directoryPath;
    private FileUtils directoryAnalyzer;
    private DetectorScheduler detectorScheduler;


    public Repository(String directoryPath, List<Document> documents, SnapshotSys snapshotSys){
        this.documents = documents;
        this.snapshotSys = snapshotSys;
        this.directoryAnalyzer = new FileUtils(directoryPath);
        this.detectorScheduler = new DetectorScheduler(this);
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
        snapshotSys.loadSnapshots();
        long lastSnapshotTime = snapshotSys.getLastSnapshotTime();

        System.out.println("Last Snapshot Time: " + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date(lastSnapshotTime)));

        Map<String, Document> currentSnapshot = snapshotSys.getCurrentSnapshot();
        Map<String, Document> previousSnapshot = snapshotSys.getPreviousSnapshot();

        ChangeDetector.compareSnapshots(previousSnapshot, currentSnapshot, (filename, changeType) -> {
            switch (changeType) {
                case NEW:
                    System.out.println(filename + " - New File");
                    break;
                case MODIFIED:
                    System.out.println(filename + " - Changed");
                    break;
                case DELETED:
                    System.out.println(filename + " - Deleted");
                    break;
                case UNCHANGED:
                    System.out.println(filename + " - Unchanged");
                    break;
            }
        });
    }

    public SnapshotSys getSnapshotSys() {
        return snapshotSys;
    }
}
