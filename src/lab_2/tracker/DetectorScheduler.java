package lab_2.tracker;

import lab_2.files.Document;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DetectorScheduler {
    private final Repository repository;
    private final ScheduledExecutorService scheduler;
    private SnapshotSys snapshotSys;

    public DetectorScheduler(Repository repository) {
        this.repository = repository;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.snapshotSys = repository.getSnapshotSys();
        this.snapshotSys .loadSnapshots();
        this.snapshotSys.setLastKnownSnapshot();
    }

    public void start() {
        scheduler.scheduleAtFixedRate(() -> ChangeDetector.detectChanges(snapshotSys), 0, 5, TimeUnit.SECONDS);
    }

    public void stop() {
        scheduler.shutdownNow();
    }


}
