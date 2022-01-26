package dev.wcs.tutoring.littlehelpers;

import org.apache.commons.lang3.compare.ComparableUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class BiggerThanFive implements Predicate<Integer> {
    @Override
    public boolean test(Integer v) {
        return v > 5;
    }
}

public class CommonsComparableUtilsTests {

    @Test
    public void testComparables() {
        Predicate<Integer> biggerThanFiveGuava = ComparableUtils.gt(5);
        Predicate<Integer> biggerThanFiveJava = biggerThanFive(5);
        List<Integer> allNumbers = List.of(2,3,4,5,6,7,3,9,2,10);

        System.out.println(allNumbers.stream().filter(biggerThanFiveGuava).collect(Collectors.toList()));
        System.out.println(allNumbers.stream().filter(biggerThanFiveJava).collect(Collectors.toList()));
    }

    private Predicate<Integer> biggerThanFive(int comp) {
        return i -> i > comp;
    }

}
