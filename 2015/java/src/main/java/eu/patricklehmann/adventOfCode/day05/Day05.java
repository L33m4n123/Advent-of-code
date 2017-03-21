package eu.patricklehmann.adventOfCode.day05;

import eu.patricklehmann.adventOfCode.utils.AdventInputReader;

import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Pattern;

/**
 * --- Day 5: Doesn't He Have Intern-Elves For This? ---
 * <p>
 * Santa needs help figuring out which strings in his text file are naughty or nice.
 * <p>
 * A nice string is one with all of the following properties:
 * <p>
 * It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
 * It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
 * It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
 * For example:
 * <p>
 * ugknbfddgicrmopn is nice because it has at least three vowels (u...i...o...), a double letter (...dd...), and none of the disallowed substrings.
 * aaa is nice because it has at least three vowels and a double letter, even though the letters used by different rules overlap.
 * jchzalrnumimnmhp is naughty because it has no double letter.
 * haegwjzuvuyypxyu is naughty because it contains the string xy.
 * dvszwmarrgswjxmb is naughty because it contains only one vowel.
 * How many strings are nice?
 * <p>
 * Your puzzle answer was 255.
 * <p>
 * --- Part Two ---
 * <p>
 * Realizing the error of his ways, Santa has switched to a better model of determining whether a string is naughty or nice. None of the old rules apply, as they are all clearly ridiculous.
 * <p>
 * Now, a nice string is one with all of the following properties:
 * <p>
 * It contains a pair of any two letters that appears at least twice in the string without overlapping, like xyxy (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps).
 * It contains at least one letter which repeats with exactly one letter between them, like xyx, abcdefeghi (efe), or even aaa.
 * For example:
 * <p>
 * qjhvhtzxzqqjkmpb is nice because is has a pair that appears twice (qj) and a letter that repeats with exactly one letter between them (zxz).
 * xxyxx is nice because it has a pair that appears twice and a letter that repeats with one between, even though the letters used by each rule overlap.
 * uurcxstgmygtbstg is naughty because it has a pair (tg) but no repeat with a single letter between them.
 * ieodomkazucvgmuy is naughty because it has a repeating letter with one between (odo), but no pair that appears twice.
 * How many strings are nice under these new rules?
 * <p>
 * Your puzzle answer was 55.
 */
public class Day05 {
    private final List<String> input;

    int niceStrings;

    // -- Part 1 --
    private Pattern rule1;
    private Pattern rule2;
    private Pattern rule3;

    // -- Part 2 --
    private Pattern rule4;
    private Pattern rule5;

    public Day05() {
        input = AdventInputReader.getStringListInput(new InputStreamReader(getClass()
                .getResourceAsStream("/inputs/day05_input.txt")));

        setupPatterns();
        checkForNiceStrings(false);
        System.out.println("Ammount of nice Strings according to rule 1, 2, 3: " + niceStrings);
        niceStrings = 0;
        checkForNiceStrings(true);
        System.out.println("Ammount of nice Strings according to rule 4 and 5: " + niceStrings);
    }

    private void checkForNiceStrings(boolean partTwo) {
        for (String str : input) {
            if (checkRules(partTwo, str))
                niceStrings++;
        }
    }


    private boolean checkRules(boolean partTwo, String toTest) {
        boolean nice = false;
        nice = rule1.matcher(toTest).find() && rule2.matcher(toTest).find() && !rule3.matcher(toTest).find();
        if (partTwo)
            nice = rule4.matcher(toTest).find() && rule5.matcher(toTest).find();
        return nice;
    }

    private void setupPatterns() {
        rule1 = Pattern.compile("([aeiou])\\w*([aeiou])\\w*([aeiou])"); // at least 3 vowels
        rule2 = Pattern.compile("(\\w)\\1+"); //at least one double letter.. i.e. gg
        rule3 = Pattern.compile("(ab)|(cd)|(pq)|(xy)"); // These combinations are _NOT_ allowed
        rule4 = Pattern.compile("(\\w)(\\w)(\\w*)\\1\\2+");
        rule5 = Pattern.compile("(\\w)(\\w)\\1+");
    }
}
