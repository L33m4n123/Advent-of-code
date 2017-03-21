package eu.patricklehmann.adventOfCode.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leeman on 13.12.2015
 */
public class AdventInputReader {

    // If the input is just a single line
    public static String getStringInput(InputStreamReader day) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(day);
        try {
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    // If there are mutliple lines in the file get it as a List. Each Line one List Entry
    public static List<String> getStringListInput(InputStreamReader day) {
        List<String> input = new ArrayList<>();
        BufferedReader br = new BufferedReader(day);
        try {
            String line = br.readLine();
            while (line != null) {
                input.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
}