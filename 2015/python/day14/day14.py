#  -*- coding: utf-8 -*-
__author__ = "Patrick Lehmann"

import re
import collections
import itertools

history = collections.defaultdict(list)


def main():
    with open('../../input/day14.txt') as f:
        data = f.read()
    calc(data)


def calc(data):
    if 'can fly' in data:
        parse(data)
    by_dist = max(h[-1] for h in history.values())
    scored = [i for a in zip(*history.values()) for i, v in enumerate(a) if v == max(a)]
    by_points = max(collections.Counter(scored).values())
    print(by_dist, by_points)


def parse(data):
    regex = r'(\w+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.'
    for who, speed, duration, rest in re.findall(regex, data):
        steps = itertools.cycle([int(speed)] * int(duration) + [0] * int(rest))
        history[who] = list(itertools.accumulate(next(steps) for _ in range(2503)))


if __name__ == "__main__":
    main()
