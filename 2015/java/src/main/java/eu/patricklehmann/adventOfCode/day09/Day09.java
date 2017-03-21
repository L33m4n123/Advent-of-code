package eu.patricklehmann.adventOfCode.day09;

import com.google.common.collect.Collections2;
import eu.patricklehmann.adventOfCode.utils.AdventInputReader;

import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * --- Day 9: All in a Single Night ---
 * <p>
 * Every year, Santa manages to deliver all of his presents in a single night.
 * <p>
 * This year, however, he has some new locations to visit; his elves have provided him the distances between every pair of locations. He can start and end at any two (different) locations he wants, but he must visit each location exactly once. What is the shortest distance he can travel to achieve this?
 * <p>
 * For example, given the following distances:
 * <p>
 * London to Dublin = 464
 * London to Belfast = 518
 * Dublin to Belfast = 141
 * The possible routes are therefore:
 * <p>
 * Dublin -> London -> Belfast = 982
 * London -> Dublin -> Belfast = 605
 * London -> Belfast -> Dublin = 659
 * Dublin -> Belfast -> London = 659
 * Belfast -> Dublin -> London = 605
 * Belfast -> London -> Dublin = 982
 * The shortest of these is London -> Dublin -> Belfast = 605, and so the answer is 605 in this example.
 * <p>
 * What is the distance of the shortest route?
 * <p>
 * Your puzzle answer was 141.
 * <p>
 * --- Part Two ---
 * <p>
 * The next year, just to show off, Santa decides to take the route with the longest distance instead.
 * <p>
 * He can still start and end at any two (different) locations he wants, and he still must visit each location exactly once.
 * <p>
 * For example, given the distances above, the longest route would be 982 via (for example) Dublin -> London -> Belfast.
 * <p>
 * What is the distance of the longest route?
 * <p>
 * Your puzzle answer was 736.
 */
public class Day09 {

    private Set<String> destinations;
    private List<Route> routes;

    public Day09() {
        List<String> input = AdventInputReader.getStringListInput(new InputStreamReader(getClass()
                .getResourceAsStream("/inputs/day09_input.txt")));

        // Disclaimer.. This is not! my code. I got this from here
        // https://github.com/markwryan/advent-of-code/blob/master/src/main/java/com/markwryan/adventofcode/day9/TravelingSanta.java
        // As I couldn't wrap my head arround it how to solve it however I wanted the solution. I will get my own, working
        // code up and running today hopefully

        routes = new ArrayList<>();
        destinations = new HashSet<>();


        List<Integer> result = getShortestRoute(input);

        System.out.println(result.get(0));
        System.out.println(result.get(result.size() - 1));

    }

    List<Integer> getShortestRoute(List<String> reader) {
        List<Integer> result = new ArrayList<>();

        reader.forEach(this::readRoute);

        Collection<List<String>> permutations = Collections2.permutations(destinations);

        for (List<String> places : permutations) {
            int total = 0;
            String route = "";

            for (int i = 1; i < places.size(); i++) {
                String from = places.get(i - 1);
                String to = places.get(i);
                route += from;
                if (i < places.size() - 1) {
                    route += " -> ";
                }
                total += getDistanceBetweenCities(from, to);
            }
            result.add(total);
        }

        return result.stream().sorted().collect(Collectors.toList());
    }

    int getDistanceBetweenCities(String c1, String c2) {
        for (Route r : routes) {
            if (r.cities[0].equals(c1) || r.cities[0].equals(c2)) {
                if (r.cities[1].equals(c1) || r.cities[1].equals(c2)) {
                    return r.distance;
                }
            }
        }
        throw new IllegalArgumentException("Distance is not found: " + c1 + " to " + c2);
    }


    void readRoute(String input) {
        String[] locationAndDist = input.split("=");
        int distance = Integer.parseInt(locationAndDist[1].trim());
        String[] splitLoc = locationAndDist[0].trim().split(" to ");

        Route route = new Route(splitLoc, distance);
        routes.add(route);


        if (!destinations.contains(route.cities[0])) {
            destinations.add(route.cities[0]);
        }
        if (!destinations.contains(route.cities[1])) {
            destinations.add(route.cities[1]);
        }
    }


    private class Route {
        String[] cities;
        int distance;

        public Route(String[] c, int d) {
            cities = c;
            distance = d;
        }
    }
}
