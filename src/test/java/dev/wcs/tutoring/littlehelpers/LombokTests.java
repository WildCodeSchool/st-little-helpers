package dev.wcs.tutoring.littlehelpers;

import dev.wcs.tutoring.littlehelpers.pojo.LombokDataPojo;
import dev.wcs.tutoring.littlehelpers.pojo.NoLombokPojo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class LombokTests {

    @Test
    public void useLombokPojos() {
        LombokDataPojo pojo = new LombokDataPojo();
        pojo.setAttribute("attr");
        pojo.getAttribute();
        NoLombokPojo nopojo = new NoLombokPojo();


        log.info("Hello from Logger!");
    }
}
