package dev.wcs.tutoring.littlehelpers;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

public class CommonsRandomStringTests {

    @Test
    public void testRandomStrings() {
        // Six random numbers
        System.out.println(RandomStringUtils.random(6, false, true)); //579125
        // Six random alphabetic chars
        System.out.println(RandomStringUtils.random(6, true, false)); //RSZKAb

        // Set characters to be used
        System.out.println(RandomStringUtils.random(6, "01")); //dbcadd

        // Password with 4 to 11 alphabetic characters
        System.out.println(RandomStringUtils.randomAlphabetic(4, 11));

        // PIN or TAN with 6 digits
        System.out.println(RandomStringUtils.randomNumeric(6)); //035980

        // Printable Characters only
        System.out.println(RandomStringUtils.randomPrint(6)); //%Z4\jj
        System.out.println(RandomStringUtils.randomPrint(4, 11)); //:' =P

        // Printable Characters without Spaces
        System.out.println(RandomStringUtils.randomGraph(6)); ///0#2Wq
        System.out.println(RandomStringUtils.randomGraph(4, 11)); //K|QGq
    }
}
