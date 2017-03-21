package eu.patricklehmann.adventOfCode.day01;

import eu.patricklehmann.adventOfCode.utils.AdventInputReader;

import java.io.InputStreamReader;

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
