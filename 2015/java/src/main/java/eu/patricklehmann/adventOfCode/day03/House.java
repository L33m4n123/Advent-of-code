package eu.patricklehmann.adventOfCode.day03;

import java.util.Arrays;

/**
 * A little Object representing visited houses and its coordinates
 */
public class House {

    private int[] coordinates;

    public House(int... coordinates) {
        this.coordinates = coordinates;
    }

    public boolean equals(Object o) {
        if (o instanceof House) {
            House h = (House) o;
            return Arrays.equals(this.coordinates, h.coordinates);
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(coordinates);
    }
}
