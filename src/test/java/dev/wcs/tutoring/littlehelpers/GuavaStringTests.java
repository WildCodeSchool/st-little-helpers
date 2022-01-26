package dev.wcs.tutoring.littlehelpers;

import com.google.common.base.CharMatcher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuavaStringTests {

    public void testStringUtilities() {
        String inputString = "someString789";
        CharMatcher.whitespace().collapseFrom(inputString, 'o');

    }

    @Test
    public void whenReplaceFromString_thenReplaced() {
        String input = "apple-banana.";

        String result = CharMatcher.anyOf("-.").replaceFrom(input, '!');
        assertEquals("apple!banana!", result);

        result = CharMatcher.is('-').replaceFrom(input, " and ");
        assertEquals("apple and banana.", result);
    }


    @Test
    public void whenCountCharInString_thenCorrect() {
        String input = "a, c, z, 1, 2";

        int result = CharMatcher.is(',').countIn(input);
        assertEquals(4, result);

        result = CharMatcher.inRange('a', 'h').countIn(input);
        assertEquals(2, result);
    }
}
