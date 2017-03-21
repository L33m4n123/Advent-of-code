#  -*- coding: utf-8 -*-
__author__ = "Patrick Lehmann"

import re


def main():
    total_one, total_two = 0, 0
    with open('../../input/day08.txt', encoding='utf-8') as f:
        data = f.read()
        for line in data.splitlines():
            total_one += (len(line) - len(eval(line)))
            total_two += len(re.escape(line)) - (len(line)) + 2
        print("Part1", "Part2")
        print(total_one, total_two)


if __name__ == '__main__':
    main()
