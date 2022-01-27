package dev.wcs.tutoring.littlehelpers;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuavaStringTests {

    @Test
    public void testStringUtilities() {
        String inputString = "some String 78 9";
        System.out.println("replaceWhitespaces: " + CharMatcher.whitespace().replaceFrom(inputString, '#'));

        // Replacing
        System.out.println("anyOf/collapse    : " + CharMatcher.anyOf("eko").replaceFrom("bookkeeper", '-'));

        // Padding
        System.out.println("Padding padStart  : " + Strings.padStart("7001", 6, '0')); //007001
        System.out.println("Padding padEnd    : " + Strings.padStart("123456", 6, '0')); //123456

        // Repeating
        System.out.println("3x repeat         : " + Strings.repeat("abc", 3));//abcabcabc

        // Check for null
        String toCheck = "";
        if (Strings.isNullOrEmpty(toCheck)) {
            System.out.println("there we are!");
        }
        /*
        if ((toCheck != null) && (!toCheck.equals("")) {
            ....
        }
         */
    }

    @Test
    public void countCharsInStrings() {
        String input = "a, c, z, 1, 2";

        int result = CharMatcher.is(',').countIn(input);
        assertEquals(4, result);

        result = CharMatcher.inRange('a', 'h').countIn(input);
        assertEquals(2, result);
    }

    @Test
    public void testSplitter() {
        Splitter commaSplitter = Splitter.on(',');
        Splitter.MapSplitter keyValueSplitter = commaSplitter.withKeyValueSeparator('=');
        Map<String, String> map = keyValueSplitter.split("a=b,c=d");
        System.out.println(map); //{a=b, c=d}
    }

    @Test
    public void testJoiner() {
        List<String> inputWithNulls = Arrays.asList("one", "two", null, "four");
        Joiner commaJoinerSkippingNulls = Joiner.on(", ").skipNulls();
        System.out.println(commaJoinerSkippingNulls.join(inputWithNulls)); //one, two, four
        Joiner commaJoinerHandlingNulls = Joiner.on(", ").useForNull("NULL");
        System.out.println(commaJoinerHandlingNulls.join(inputWithNulls)); //one, two, NULL, four
    }

    @Test
    public void testMapJoiner() {
        Joiner commaJoinerSkippingNulls = Joiner.on(", ").skipNulls();
        Joiner.MapJoiner mapJoiner = commaJoinerSkippingNulls.withKeyValueSeparator("=");
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        System.out.println(mapJoiner.join(map)); //key1=value1, key2=value2
        // Handling nulls
        map.put("key3", null);

        // NULL Joiner
        Joiner.MapJoiner mapJoinerHandlingNulls = commaJoinerSkippingNulls.withKeyValueSeparator("=").useForNull("NULL");
        System.out.println(mapJoinerHandlingNulls.join(map)); //key1=value1, key2=value2, key3=NULL
    }

}
