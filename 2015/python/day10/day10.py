#  -*- coding: utf-8 -*-
__author__ = "Patrick Lehmann"

from itertools import groupby


def main():
    with open("../../input/day10.txt", "r", encoding="utf-8") as f:
        part_one = look_and_say(f.read(), 40)
        part_two = look_and_say(part_one, 10)
        print(len(part_one), len(part_two))


def look_and_say(data, runs=0):
    for i in range(runs):
        tmp = ''
        for k, g in groupby(data):
            tmp += str(len(list(g))) + str(k)
        data = tmp
    return data


if __name__ == "__main__":
    main()
