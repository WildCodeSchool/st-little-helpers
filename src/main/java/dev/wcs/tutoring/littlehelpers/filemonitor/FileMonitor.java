package dev.wcs.tutoring.littlehelpers.filemonitor;

import dev.wcs.tutoring.littlehelpers.csv.CsvReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Slf4j
@Component
public class FileMonitor {

    private final String watchedFolder;
    private final int interval;

    public FileMonitor(@Value("${filemonitor.folder}") String watchedFolder, @Value("${filemonitor.interval}")  int interval) {
        this.watchedFolder = watchedFolder;
        this.interval = interval;
    }

    public static void main(String[] args) throws Exception {
        String folderName = "src/test/resources";
        int interval = 5000;
        log.info("Observing folder '{}' at interval {}ms", folderName, interval);
        new FileMonitor(folderName, interval).startFileMonitor();
    }

    public void startFileMonitor() throws Exception {
        // Configure Folder
        FileAlterationObserver observer = new FileAlterationObserver(new File(watchedFolder));
        // Configure Interval
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval);
        // Configure Actions
        FileAlterationListener fal = new FileAlterationListenerAdaptor() {

            @Override
            public void onFileCreate(File file) {
                log.info("File was created: {}", file.getName());
                importCsv(file);
            }

            @Override
            public void onFileChange(File file) {
                log.info("File was changed: {}", file.getName());
                importCsv(file);
            }

            private void importCsv(File file) {
                try {
                    new CsvReader().readFileAsCsv(file);
                } catch (Exception ex) {
                    log.error("Cannot import file {}", file.getName());
                }
            }
        };

        observer.addListener(fal);
        monitor.addObserver(observer);
        monitor.start();
    }
}