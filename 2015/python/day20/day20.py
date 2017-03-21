#  -*- coding: utf-8 -*-
__author__ = "Patrick Lehmann"

from collections import defaultdict


def main():
    houses = defaultdict(int)
    smallest_houses = set()
    with open('../../input/day20.txt') as f:
        data = f.read()
    max_range = int(int(data) / 10)
    """for i in range(1, max_range):
        for j in range(i, max_range, i):
            houses[j] += i * 10
            if houses[j] >= int(data):
                smallest_houses.add(j)
    print(min(smallest_houses))
    """
    # --- Part Two! ---
    houses = defaultdict(int)
    smallest_houses = set()
    for i in range(1, max_range):
        counter = 0
        for j in range(i, max_range, i):
            counter += 1
            houses[j] += i * 11
            if houses[j] >= int(data):
                smallest_houses.add(j)
            if counter == 50:
                break
    print(min(smallest_houses))


if __name__ == "__main__":
    main()
