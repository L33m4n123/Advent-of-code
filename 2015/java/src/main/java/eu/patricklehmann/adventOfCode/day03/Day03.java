package eu.patricklehmann.adventOfCode.day03;

import eu.patricklehmann.adventOfCode.utils.AdventInputReader;

import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * --- Day 3: Perfectly Spherical House in a Vacuum ---
 * <p>
 * Santa is delivering presents to an infinite two-dimensional grid of houses.
 * <p>
 * He begins by delivering a present to the house at his starting location, and then an elf at the North Pole calls him via radio and tells him where to move next. Moves are always exactly one house to the north (^), south (v), east (>), or west (<). After each move, he delivers another present to the house at his new location.
 * <p>
 * However, the elf back at the north pole has had a little too much eggnog, and so his directions are a little off, and Santa ends up visiting some houses more than once. How many houses receive at least one present?
 * <p>
 * For example:
 * <p>
 * > delivers presents to 2 houses: one at the starting location, and one to the east.
 * ^>v< delivers presents to 4 houses in a square, including twice to the house at his starting/ending location.
 * ^v^v^v^v^v delivers a bunch of presents to some very lucky children at only 2 houses.
 * Your puzzle answer was 2592.
 * <p>
 * --- Part Two ---
 * <p>
 * The next year, to speed up the process, Santa creates a robot version of himself, Robo-Santa, to deliver presents with him.
 * <p>
 * Santa and Robo-Santa start at the same location (delivering two presents to the same starting house), then take turns moving based on instructions from the elf, who is eggnoggedly reading from the same script as the previous year.
 * <p>
 * This year, how many houses receive at least one present?
 * <p>
 * For example:
 * <p>
 * ^v delivers presents to 3 houses, because Santa goes north, and then Robo-Santa goes south.
 * ^>v< now delivers presents to 3 houses, and Santa and Robo-Santa end up back where they started.
 * ^v^v^v^v^v now delivers presents to 11 houses, with Santa going one direction and Robo-Santa going the other.
 * Your puzzle answer was 2360.
 */
public class Day03 {

    String input;
    int currPosX;
    int currPosY;
    Set<House> visitedHouses;

    public Day03() {
        input = AdventInputReader.getStringInput(new InputStreamReader(getClass()
                .getResourceAsStream("/inputs/day03_input.txt")));

        visitedHouses = new HashSet<>();
        // --- Part One ---
        moveSanta(false);
        System.out.println("Santa visited " + visitedHouses.size() + " different Houses!");

        // -- Part Two ---
        // This time Santa moves only every x%2==1 step and
        // on x%2==0 the Robot Version of him moves

        // First. Let's reset houses
        visitedHouses.clear();

        moveSanta(true);
        moveRobot();
        System.out.println("Santa and his Robo companion visited " + visitedHouses.size() + " different Houses this year!");

    }

    private void moveSanta(boolean partTwo) {
        // He starts at 0,0 at visits the house
        currPosX = 0;
        currPosY = 0;
        visitHouse();
        int counter = 0;
        for (char dir : input.toCharArray()) {
            // Now he moves according to the input by the Elf
            if (partTwo) {
                counter++;
                if (counter % 2 == 0)
                    continue;
            }
            move(dir);
            // After he moved he visits the house
            visitHouse();
        }
    }

    private void moveRobot() {
        // He starts at 0,0 at visits the house
        currPosX = 0;
        currPosY = 0;
        visitHouse();
        int counter = 0;
        for (char dir : input.toCharArray()) {
            // Now he moves according to the input by the Elf
            counter++;
            if (counter % 2 == 1)
                continue;
            move(dir);
            // After he moved he visits the house
            visitHouse();
        }
    }

    private void move(char dir) {
        switch (dir) {
            case '^':
                currPosY++;
                break;
            case 'v':
                currPosY--;
                break;
            case '>':
                currPosX++;
                break;
            case '<':
                currPosX--;
                break;
        }
    }

    private void visitHouse() {
        visitedHouses.add(new House(currPosX, currPosY));
    }
}
