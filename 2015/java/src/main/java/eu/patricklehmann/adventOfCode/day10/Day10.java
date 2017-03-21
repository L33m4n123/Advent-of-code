package eu.patricklehmann.adventOfCode.day10;

/**
 * --- Day 10: Elves Look, Elves Say ---
 * <p>
 * Today, the Elves are playing a game called look-and-say. They take turns making sequences by reading aloud the previous sequence and using that reading as the next sequence. For example, 211 is read as "one two, two ones", which becomes 1221 (1 2, 2 1s).
 * <p>
 * Look-and-say sequences are generated iteratively, using the previous value as input for the next step. For each step, take the previous value, and replace each run of digits (like 111) with the number of digits (3) followed by the digit itself (1).
 * <p>
 * For example:
 * <p>
 * 1 becomes 11 (1 copy of digit 1).
 * 11 becomes 21 (2 copies of digit 1).
 * 21 becomes 1211 (one 2 followed by one 1).
 * 1211 becomes 111221 (one 1, one 2, and two 1s).
 * 111221 becomes 312211 (three 1s, two 2s, and one 1).
 * Starting with the digits in your puzzle input, apply this process 40 times. What is the length of the result?
 * <p>
 * Your puzzle answer was 360154.
 * <p>
 * --- Part Two ---
 * <p>
 * Neat, right? You might also enjoy hearing John Conway talking about this sequence (that's Conway of Conway's Game of Life fame).
 * <p>
 * Now, starting again with the digits in your puzzle input, apply this process 50 times. What is the length of the new result?
 * <p>
 * Your puzzle answer was 5103798.
 */
public class Day10 {

    public Day10() {
        String input = "1113122113";
        /*
         * String checkIfAlgorithmWorks = "311311222113";
         * System.out.println(checkIfAlgorithmWorks.equals(lookAndSay(input.toCharArray())));
         * worked. so now
        */
        System.out.println("Length of result after 40 runs: " + lookAndSay(input, 40).length());
        System.out.println("Length of result after 50 runs: " + lookAndSay(input, 50).length());
    }

    private String lookAndSay(String input, int i) {
        String result = input;
        for (int j = 0; j < i; j++) {
            result = lookAndSay(result.toCharArray());
        }
        return result;
    }

    private String lookAndSay(char[] input) {
        StringBuilder sb = new StringBuilder();
        char lastChar = 'ü';
        int counter = 0;
        for (int i = 0; i < input.length; i++) {
            if (lastChar == 'ü') {
                //not set
                lastChar = input[i];
                counter++;
            } else if (lastChar == input[i]) {
                counter++;
            } else if (lastChar != input[i]) {
                sb.append(String.valueOf(counter)).append(lastChar);
                lastChar = input[i];
                counter = 1;
            }
            if (i == input.length - 1) {
                sb.append(String.valueOf(counter)).append(lastChar);
            }
        }
        return sb.toString();
    }
}
