package eu.patricklehmann.adventOfCode.day04;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * --- Day 4: The Ideal Stocking Stuffer ---
 * <p>
 * Santa needs help mining some AdventCoins (very similar to bitcoins) to use as gifts for all the economically forward-thinking little girls and boys.
 * <p>
 * To do this, he needs to find MD5 hashes which, in hexadecimal, start with at least five zeroes. The input to the MD5 hash is some secret key (your puzzle input, given below) followed by a number in decimal. To mine AdventCoins, you must find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...) that produces such a hash.
 * <p>
 * For example:
 * <p>
 * If your secret key is abcdef, the answer is 609043, because the MD5 hash of abcdef609043 starts with five zeroes (000001dbbfa...), and it is the lowest such number to do so.
 * If your secret key is pqrstuv, the lowest number it combines with to make an MD5 hash starting with five zeroes is 1048970; that is, the MD5 hash of pqrstuv1048970 looks like 000006136ef....
 * Your puzzle answer was 282749.
 * <p>
 * --- Part Two ---
 * <p>
 * Now find one that starts with six zeroes.
 * <p>
 * Your puzzle answer was 9962624.
 */
public class Day04 {

    private MessageDigest md;

    public Day04() {
        // Let's do it I guess?
        String input = "yzbqklnj";
        String part1 = "00000";
        calculateHash(input, part1);
        String part2 = "000000";
        calculateHash(input, part2);
    }

    private void calculateHash(String input, String prefix) {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        long i = 1;
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        while (!found) {
            // Make sure the Stringbuilder is empty at the new "round"
            sb.delete(0, sb.length());

            //add a number at the end to get the hash we are looking for
            md.update((input + i).getBytes());
            byte[] digest = md.digest();

            for (byte b : digest) {
                // Make a Hex-String out of the Byte Array
                sb.append(String.format("%02x", b));
            }
            if (sb.indexOf(prefix) == 0) {
                // if the hash is found
                found = true;
            } else {
                i++;
            }
        }
        System.out.println("The Answer for the hash we are looking for is: " + i);
        System.out.println("The hash we are looking for is: " + sb.toString());
    }
}
