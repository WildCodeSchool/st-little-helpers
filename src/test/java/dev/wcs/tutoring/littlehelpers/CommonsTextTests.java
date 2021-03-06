package dev.wcs.tutoring.littlehelpers;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.junit.jupiter.api.Test;

public class CommonsTextTests {

    @Test
    public void testCapitalizations() {
        System.out.println(WordUtils.capitalize("we are using apache commons text"));
        System.out.println(WordUtils.capitalize("we are using APACHE COMMONS TEXT"));
        System.out.println(WordUtils.capitalizeFully("we are using APACHE COMMONS TEXT"));
    }

    @Test
    public void testWordAbbrev() {
        String pp1 = "Thanks for the nice Lunch.";
        String pp2 = "Max Muster Invoice No 551662781";
        String pp3 = "Remittance Item #188655";
        String pp4 = "September 2022 Loan Repayment Max Mustermann";
        String pp5 = "Invoice Reference No 8988826/2022/145";

        String pp1abbrev = WordUtils.abbreviate(pp1, 17, 20, "...");
        String pp2abbrev = WordUtils.abbreviate(pp2, 17, 20, "...");
        String pp3abbrev = WordUtils.abbreviate(pp3, 17, 20, "...");
        String pp4abbrev = WordUtils.abbreviate(pp4, 17, 20, "...");
        String pp5abbrev = WordUtils.abbreviate(pp5, 17, 20, "...");

        System.out.println(pp1abbrev);
        System.out.println(pp2abbrev);
        System.out.println(pp3abbrev);
        System.out.println(pp4abbrev);
        System.out.println(pp5abbrev);
        System.out.println("012345678901234567890\n");

        System.out.println(StringUtils.abbreviate(pp1, 20));
        System.out.println(StringUtils.abbreviate(pp2, 20));
        System.out.println(StringUtils.abbreviate(pp3, 20));
        System.out.println(StringUtils.abbreviate(pp4, 20));
        System.out.println(StringUtils.abbreviate(pp5, 20));
        System.out.println("012345678901234567890");

    }

    @Test
    public void testContainsAll() {
        System.out.println(WordUtils.containsAllWords("This is a long sentence with several words.", "long", "is")); //true
        System.out.println(WordUtils.containsAllWords("This is a long sentence with several words.", "long", "all")); //true
    }

    @Test
    public void testInitials() {
        System.out.println(WordUtils.initials("Using Apache Commons Text is so Cool!")); //UACTisC
        System.out.println(WordUtils.initials("Using Apache Commons Text is so cool!")); //UACTisc
    }

}
