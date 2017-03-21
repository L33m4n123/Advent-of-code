__author__ = "Patrick Lehmann"

import re
from collections import defaultdict

r = re.compile("([0-9]+)")
lights = set()

lights_part2 = defaultdict(int)


def main():
    with open("../../input/day06.txt") as f:
        for line in f:
            x1, y1, x2, y2 = [int(x) for x in r.findall(line)]
            if line.startswith("toggle"):
                for x in range(x1, x2 + 1):
                    for y in range(y1, y2 + 1):
                        # Toggle the lamp
                        lights_part2[(x, y)] += 2
                        if (x, y) in lights:
                            lights.remove((x, y))
                        else:
                            lights.add((x, y))
            elif line.startswith("turn on"):
                for x in range(x1, x2 + 1):
                    for y in range(y1, y2 + 1):
                        # Turn on the lamp
                        lights_part2[(x, y)] += 1
                        if (x, y) not in lights:
                            lights.add((x, y))
            else:
                for x in range(x1, x2 + 1):
                    for y in range(y1, y2 + 1):
                        # Turn off the lamp
                        lights_part2[(x, y)] = max(lights_part2[(x, y)] - 1, 0)
                        if (x, y) in lights:
                            lights.remove((x, y))
    print(len(lights))
    print(count())


def count():
    counter = 0
    for entry in lights_part2:
        counter += lights_part2.get(entry)
    return counter


if __name__ == '__main__':
    main()
