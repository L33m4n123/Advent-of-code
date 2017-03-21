package eu.patricklehmann.adventOfCode.day12;

import eu.patricklehmann.adventOfCode.utils.AdventInputReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * --- Day 12: JSAbacusFramework.io ---
 * <p>
 * Santa's Accounting-Elves need help balancing the books after a recent order. Unfortunately, their accounting software uses a peculiar storage format. That's where you come in.
 * <p>
 * They have a JSON document which contains a variety of things: arrays ([1,2,3]), objects ({"a":1, "b":2}), numbers, and strings. Your first job is to simply find all of the numbers throughout the document and add them together.
 * <p>
 * For example:
 * <p>
 * [1,2,3] and {"a":2,"b":4} both have a sum of 6.
 * [[[3]]] and {"a":{"b":4},"c":-1} both have a sum of 3.
 * {"a":[-1,1]} and [-1,{"a":1}] both have a sum of 0.
 * [] and {} both have a sum of 0.
 * You will not encounter any strings containing numbers.
 * <p>
 * What is the sum of all numbers in the document?
 * <p>
 * Your puzzle answer was 111754.
 * <p>
 * --- Part Two ---
 * <p>
 * Uh oh - the Accounting-Elves have realized that they double-counted everything red.
 * <p>
 * Ignore any object (and all of its children) which has any property with the value "red". Do this only for objects ({...}), not arrays ([...]).
 * <p>
 * [1,2,3] still has a sum of 6.
 * [1,{"c":"red","b":2},3] now has a sum of 4, because the middle object is ignored.
 * {"d":"red","e":[1,2,3,4],"f":5} now has a sum of 0, because the entire structure is ignored.
 * [1,"red",5] has a sum of 6, because "red" in an array has no effect.
 * Your puzzle answer was 65402.
 */
public class Day12 {

    /****
     * NOT MY CODE - WILL REDO IT ONCE I CAN BE ARSED TO READ UP ON JSON :P
     *
     * https://github.com/markwryan/advent-of-code/blob/master/src/main/java/com/markwryan/adventofcode/day12/NumberTotal.java
     */
    public Day12() {
        String input = AdventInputReader.getStringInput(new InputStreamReader(getClass()
                .getResourceAsStream("/inputs/day12_input.json")));
        System.out.println(totalValues(input));

        int total = total(input);
        System.out.println("Part 2: " + total);
    }

    int total(String reader) {
        AtomicInteger total = new AtomicInteger(0);
        total.getAndAdd(getTotalWithoutRed(new JSONObject(reader)));
        return total.get();
    }
    int totalValues(String reader) {
        Pattern numbers = Pattern.compile("[-]?[\\d]+");
        AtomicInteger total = new AtomicInteger(0);

            Matcher matcher = numbers.matcher(reader);
            while (matcher.find()) {
                total.getAndAdd(Integer.parseInt(matcher.group()));
            }

        return total.get();
    }

    int getTotalWithoutRed(JSONArray entry) {
        int total = 0;
        for(Object o : entry) {
            total += check(o);
        }

        return total;
    }

    int getTotalWithoutRed(JSONObject entries) {
        int total = 0;
        for(String key : entries.keySet()) {
            Object o = entries.get(key);
            if(o.getClass() == String.class) {
                String value = (String) o;
                if(value.equals("red")) {
                    return 0;
                }
            }
            else {
                total+= check(o);
            }
        }

        return total;
    }

    private int check(Object o) {
        int total = 0;
        if(o.getClass() == Integer.class) {
            Integer value = (Integer) o;
            total += value;
        }
        else if(o.getClass() == JSONArray.class) {
            JSONArray value = (JSONArray) o;
            total += getTotalWithoutRed(value);
        }
        else if(o.getClass() == JSONObject.class) {
            JSONObject value = (JSONObject) o;
            total += getTotalWithoutRed(value);
        }
        return total;
    }
}