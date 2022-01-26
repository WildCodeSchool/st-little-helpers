package dev.wcs.tutoring.littlehelpers;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

public class CommonsRandomStringTests {

    @Test
    public void testRandomStrings() {
        System.out.println(RandomStringUtils.random(6));
        System.out.println(RandomStringUtils.random(6, false, true)); //579125
        System.out.println(RandomStringUtils.random(6, true, false)); //RSZKAb
        System.out.println(RandomStringUtils.random(6, 0, 4, true, true, 'a', 'b', '0', '1', '2')); //10ab1a
        System.out.println(RandomStringUtils.random(6, 0, 4, false, true, 'a', 'b', '0', '1', '2')); //000110
        System.out.println(RandomStringUtils.random(6, 0, 4, true, false, 'a', 'b', '0', '1', '2')); //babbaa
        System.out.println(RandomStringUtils.random(6, "abcd")); //dbcadd
        System.out.println(RandomStringUtils.randomAlphabetic(4, 11));
        System.out.println(RandomStringUtils.randomNumeric(6)); //035980
        System.out.println(RandomStringUtils.randomPrint(6)); //%Z4\jj
        System.out.println(RandomStringUtils.randomPrint(4, 11)); //:' =P
        System.out.println(RandomStringUtils.randomGraph(6)); ///0#2Wq
        System.out.println(RandomStringUtils.randomGraph(4, 11)); //K|QGq
    }
}
