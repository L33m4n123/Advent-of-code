#  -*- coding: utf-8 -*-
__author__ = "Patrick Lehmann"

from itertools import combinations


def main():
    containers = list()
    combination_ammount = set()
    with open("../../input/day17.txt") as f:
        data = f.read()
    for line in data.splitlines():
        containers.append(int(line))
    variations = 0
    for i in range(len(containers)):
        for combination in combinations(containers, i):
            if sum(combination) == 150:
                variations += 1
                combination_ammount.add(len(combination))
    print(variations)
    print("=== PART 2 ===")
    variations_part_two = 0
    for combination in combinations(containers, min(combination_ammount)):
        if sum(combination) == 150:
            variations_part_two += 1
    print(variations_part_two)


if __name__ == "__main__":
    main()
