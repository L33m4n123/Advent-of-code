package eu.patricklehmann.adventOfCode.day06;

import eu.patricklehmann.adventOfCode.utils.AdventInputReader;

import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

/**
 * --- Day 6: Probably a Fire Hazard ---
 * <p>
 * Because your neighbors keep defeating you in the holiday house decorating contest year after year, you've decided to deploy one million lights in a 1000x1000 grid.
 * <p>
 * Furthermore, because you've been especially nice this year, Santa has mailed you instructions on how to display the ideal lighting configuration.
 * <p>
 * Lights in your grid are numbered from 0 to 999 in each direction; the lights at each corner are at 0,0, 0,999, 999,999, and 999,0. The instructions include whether to turn on, turn off, or toggle various inclusive ranges given as coordinate pairs. Each coordinate pair represents opposite corners of a rectangle, inclusive; a coordinate pair like 0,0 through 2,2 therefore refers to 9 lights in a 3x3 square. The lights all start turned off.
 * <p>
 * To defeat your neighbors this year, all you have to do is set up your lights by doing the instructions Santa sent you in order.
 * <p>
 * For example:
 * <p>
 * turn on 0,0 through 999,999 would turn on (or leave on) every light.
 * toggle 0,0 through 999,0 would toggle the first line of 1000 lights, turning off the ones that were on, and turning on the ones that were off.
 * turn off 499,499 through 500,500 would turn off (or leave off) the middle four lights.
 * After following the instructions, how many lights are lit?
 * <p>
 * Your puzzle answer was 543903.
 * <p>
 * --- Part Two ---
 * <p>
 * You just finish implementing your winning light pattern when you realize you mistranslated Santa's message from Ancient Nordic Elvish.
 * <p>
 * The light grid you bought actually has individual brightness controls; each light can have a brightness of zero or more. The lights all start at zero.
 * <p>
 * The phrase turn on actually means that you should increase the brightness of those lights by 1.
 * <p>
 * The phrase turn off actually means that you should decrease the brightness of those lights by 1, to a minimum of zero.
 * <p>
 * The phrase toggle actually means that you should increase the brightness of those lights by 2.
 * <p>
 * What is the total brightness of all lights combined after following Santa's instructions?
 * <p>
 * For example:
 * <p>
 * turn on 0,0 through 0,0 would increase the total brightness by 1.
 * toggle 0,0 through 999,999 would increase the total brightness by 2000000.
 * Your puzzle answer was 14687245.
 */
public class Day06 {
    private final List<String> input;
    private int totalLightsOn;
    //long, because it seems to pass Integer.MAX_VALUE somewhere in between.
    // As it deliveres a wrong result with ints
    private long totalBrightness;

    private int[][] grid;

    public Day06() {
        input = AdventInputReader.getStringListInput(new InputStreamReader(getClass()
                .getResourceAsStream("/inputs/day06_input.txt")));

        grid = new int[1000][1000];
        totalLightsOn = 0;
        long then = new Date().getTime();
        doInstructions(false);
        long now = new Date().getTime();
        System.out.println("Part1: " + totalLightsOn);
        System.out.println(now-then);

        // --- Part 2 ---

        grid = null;
        grid = new int[1000][1000];
        totalLightsOn = 0;
        totalBrightness = 0;
        then = new Date().getTime();
        doInstructions(true);
        now = new Date().getTime();
        System.out.println("Part2: " + totalBrightness);
        System.out.println(now-then);

    }

    private void doInstructions(boolean secondPart) {
        for (String str : input) {
            // Let's Interprete the input in a way the code understands it
            String[] split = str.split(" ");
            String cmd = "";
            int startX, startY, endX, endY;
            if (str.startsWith("toggle")) {
                /*
                split[0] = "toggle"
                split[1]  = "x,y"
                split[3] = "x,y"
                split[2] is irrelevant for us
                 */
                cmd = split[0];
                startX = Integer.parseInt(split[1].split(",")[0]);
                startY = Integer.parseInt(split[1].split(",")[1]);
                endX = Integer.parseInt(split[3].split(",")[0]);
                endY = Integer.parseInt(split[3].split(",")[1]);

            } else {
                /*
                split[0] is irrelevant for us
                split[1]  = "on/off"
                split[2] = "x,y"
                split[3] is irrelevant for us
                split[4] = "x,y"
                 */
                cmd = split[1];
                startX = Integer.parseInt(split[2].split(",")[0]);
                startY = Integer.parseInt(split[2].split(",")[1]);
                endX = Integer.parseInt(split[4].split(",")[0]);
                endY = Integer.parseInt(split[4].split(",")[1]);
            }

            for (int x = startX; x <= endX; x++) {
                for (int y = startY; y <= endY; y++) {
                    switch (cmd) {
                        case "toggle":
                            toggle(x, y, secondPart);
                            break;
                        case "off":
                            off(x, y, secondPart);
                            break;
                        case "on":
                            on(x, y, secondPart);
                            break;
                        default:
                            System.out.println("Oops?");
                            System.out.println(cmd);
                    }
                }
            }
        }
    }

    private void toggle(int x, int y, boolean secondPart) {
        if (secondPart) {
            totalBrightness += 2;
            grid[x][y] += 2;
        } else {
            if (grid[x][y] == 1)
                off(x, y, false);
            else if (grid[x][y] == 0)
                on(x, y, false);
        }
    }

    private void on(int x, int y, boolean secondPart) {
        if (secondPart) {
            grid[x][y] += 1;
            totalBrightness++;
        } else {
            if (grid[x][y] == 0)
                totalLightsOn++;
            grid[x][y] = 1;
        }
    }

    private void off(int x, int y, boolean secondPart) {
        if (secondPart && grid[x][y] > 0) {
            grid[x][y] -= 1;
            totalBrightness--;
        } else {
            if (grid[x][y] == 1)
                totalLightsOn--;
            grid[x][y] = 0;
        }
    }
}
