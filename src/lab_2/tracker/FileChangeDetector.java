package lab_2.tracker;

import lab_2.files.Document;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileChangeDetector {
    private final Repository repository;
    private final ScheduledExecutorService scheduler;
    private SnapshotSys snapshotSys;

    public FileChangeDetector(Repository repository) {
        this.repository = repository;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.snapshotSys = repository.getSnapshotSys();

        // Initialize lastKnownSnapshot with the current state
        snapshotSys.setLastKnownSnapshot();
    }

    public void start() {
        repository.getSnapshotSys().loadSnapshots();
        repository.getSnapshotSys().setLastKnownSnapshot();
        scheduler.scheduleAtFixedRate(this::detectChanges, 0, 5, TimeUnit.SECONDS);
    }

    public void stop() {
        scheduler.shutdownNow();
    }

    public void detectChanges() {
        snapshotSys.loadSnapshots();
        Map<String, Document> lastKnownSnapshot = snapshotSys.getLastKnownSnapshot();
        Map<String, Document> currentSnapshot = snapshotSys.getCurrentSnapshot();

        boolean changesDetected = false;

        for (Map.Entry<String, Document> entry : currentSnapshot.entrySet()) {
            String filename = entry.getKey();
            Document document = entry.getValue();
            if (!lastKnownSnapshot.containsKey(filename)) {
                System.out.println("New file detected: " + filename);
                changesDetected = true;
            } else if (!document.getLastModified().equals(lastKnownSnapshot.get(filename).getLastModified())) {
                System.out.println("Modification detected in: " + filename);
                changesDetected = true;
            }
        }
        for (String fileName : lastKnownSnapshot.keySet()) {
            if (!currentSnapshot.containsKey(fileName)) {
                System.out.println("File deleted: " + fileName);
                changesDetected = true;
            }
        }
        if (changesDetected) {
            snapshotSys.setLastKnownSnapshot();
        }
    }
}
