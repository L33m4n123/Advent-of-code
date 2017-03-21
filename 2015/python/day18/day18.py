#  -*- coding: utf-8 -*-
__author__ = "Patrick Lehmann"

from collections import defaultdict

lights = dict()
neighbours = defaultdict(int)


def main():
    global lights, neighbours
    with open("../../input/day18.txt") as f:
        data = f.read()

    """
    Due to the way enumerate works top left is 0|0 and bottom right is 99|99
    """
    for y, line in enumerate(data.splitlines()):
        for x, instruction in enumerate(line):
            lights[(x, y)] = instruction

    step = 100
    for i in range(step):
        get_neighbours()
        toggle_lights()

    print(turned_on_lights())

    # Step 2
    lights = dict()
    with open("../../input/day18.txt") as f:
        data = f.read()
    for y, line in enumerate(data.splitlines()):
        for x, instruction in enumerate(line):
            lights[(x, y)] = instruction

    lights[0, 0] = "#"
    lights[99, 0] = "#"
    lights[99, 99] = "#"
    lights[0, 99] = "#"

    step = 100
    for i in range(step):
        get_neighbours()
        toggle_lights(True)

    print(turned_on_lights())


def turned_on_lights():
    global lights
    count = 0
    for value in lights.values():
        if value == "#":
            count += 1
    return count


def toggle_lights(second_part=False):
    global lights, neighbours
    for key in lights.keys():
        if lights[key] == "#" and not (neighbours[key] == 2 or neighbours[key] == 3):
            lights[key] = "."
        elif lights[key] == "." and neighbours[key] == 3:
            lights[key] = "#"
    if second_part:
        lights[0, 0] = "#"
        lights[99, 0] = "#"
        lights[99, 99] = "#"
        lights[0, 99] = "#"


def get_neighbours():
    global lights, neighbours
    neighbours = defaultdict(int)
    for key in lights.keys():
        x, y = key
        for _x in (x - 1, x, x + 1):
            if 0 <= _x <= 99:
                for _y in (y - 1, y, y + 1):
                    if 0 <= _y <= 99:
                        if (_x, _y) != key and lights[_x, _y] == "#":
                            neighbours[key] += 1


if __name__ == "__main__":
    main()
