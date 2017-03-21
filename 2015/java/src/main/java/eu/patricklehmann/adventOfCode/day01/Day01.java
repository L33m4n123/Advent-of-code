package eu.patricklehmann.adventOfCode.day01;

import eu.patricklehmann.adventOfCode.utils.AdventInputReader;

import java.io.InputStreamReader;

/**
 * Santa was hoping for a white Christmas, but his weather machine's "snow" function is powered by stars,
 * and he's fresh out! To save Christmas, he needs you to collect fifty stars by December 25th.
 * <p>
 * Collect stars by helping Santa solve puzzles. Two puzzles will be made available on each day in the advent calendar;
 * the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
 * <p>
 * Here's an easy puzzle to warm you up.
 * <p>
 * Santa is trying to deliver presents in a large apartment building, but he can't find the right floor -
 * the directions he got are a little confusing. He starts on the ground floor (floor 0) and then follows the instructions one character at a time.
 * <p>
 * An opening parenthesis, (, means he should go up one floor,
 * and a closing parenthesis, ), means he should go down one floor.
 * <p>
 * The apartment building is very tall, and the basement is very deep; he will never find the top or bottom floors.
 * <p>
 * For example:
 * <p>
 * (()) and ()() both result in floor 0.
 * ((( and (()(()( both result in floor 3.
 * ))((((( also results in floor 3.
 * ()) and ))( both result in floor -1 (the first basement level).
 * ))) and )())()) both result in floor -3.
 * To what floor do the instructions take Santa?
 * <p>
 * To what floor do the instructions take Santa?
 * <p>
 * Your puzzle answer was 138.
 * <p>
 * --- Part Two ---
 * <p>
 * Now, given the same instructions, find the position of the first character that causes him to enter the basement (floor -1). The first character in the instructions has position 1, the second character has position 2, and so on.
 * <p>
 * For example:
 * <p>
 * ) causes him to enter the basement at character position 1.
 * ()()) causes him to enter the basement at character position 5.
 * What is the position of the character that causes Santa to first enter the basement?
 * <p>
 * Your puzzle answer was 1771.
 */
public class Day01 {

    String input;
    int currFloor;
    int totalFloorChanges;
    int targetFloor;
    boolean reachedBasement = false;

    public Day01() {
        this.input = AdventInputReader.getStringInput(new InputStreamReader(getClass()
                .getResourceAsStream("/inputs/day01_input.txt")));

        runElevator(false);
        System.out.println("BING! You reached Floor: " + currFloor);

        // Part 2:
        totalFloorChanges = 0;
        currFloor = 0;
        targetFloor = -1;
        runElevator(true);
        System.out.println("BING! You reached Floor: " + currFloor + " after " + totalFloorChanges + " instructions");
    }

    private void runElevator(boolean stop) {
        for (char charakter : input.toCharArray()) {
            changeFloor(charakter, stop);
        }
    }

    private void changeFloor(char direction, boolean stop) {
        if (!reachedBasement) {
            switch (direction) {
                case '(':
                    totalFloorChanges++;
                    currFloor++;
                    if (currFloor == targetFloor && stop)
                        reachedBasement = true;
                    break;
                case ')':
                    totalFloorChanges++;
                    currFloor--;
                    if (currFloor == targetFloor && stop)
                        reachedBasement = true;
                    break;
            }
        }
    }


}
