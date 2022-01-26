package dev.wcs.tutoring.littlehelpers.filemonitor;

import dev.wcs.tutoring.littlehelpers.csv.CsvReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

@Slf4j
public class FileMonitor {

    public static void main(String[] args) throws Exception {
        File folder = new File("src/test/resources");
        int interval = 5000;
        log.info("Observing folder '{}' at interval {}ms", folder.getName(), interval);
        startFileMonitor(folder, interval);
    }

    public static void startFileMonitor(File folder, int interval) throws Exception {
        // Configure Folder
        FileAlterationObserver observer = new FileAlterationObserver(folder);
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