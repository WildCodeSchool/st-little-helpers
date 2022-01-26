package dev.wcs.tutoring.littlehelpers;

import dev.wcs.tutoring.littlehelpers.filemonitor.FileMonitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class LittleHelpersApplication implements CommandLineRunner {

    private final FileMonitor fileMonitor;

    public LittleHelpersApplication(FileMonitor fileMonitor) {
        this.fileMonitor = fileMonitor;
    }

    public static void main(String[] args) {
        SpringApplication.run(LittleHelpersApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        fileMonitor.startFileMonitor();
    }
}
