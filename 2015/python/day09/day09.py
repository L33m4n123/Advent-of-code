#  -*- coding: utf-8 -*-
__author__ = "Patrick Lehmann"

from itertools import permutations

nodes = set()
distances = dict()


def main():
    global nodes, distances
    shortest_path, longest_path = "None", "None"
    shortest_distance = pow(2, 16)
    longest_distance = 0
    with open("../../input/day09.txt", encoding='utf-8') as f:
        data = f.read()
        for line in data.splitlines():
            line = line.split()
            key = frozenset((line[0], line[2]))
            nodes |= key
            distances[key] = int(line[4])

    for path in permutations(nodes):
        curr = distance(path)
        if curr < shortest_distance:
            shortest_distance = curr
            shortest_path = path
        if curr > longest_distance:
            longest_path = path
            longest_distance = curr

    print(shortest_path, shortest_distance)
    print(longest_path, longest_distance)


def distance(path):
    total = 0
    for i in range(len(path)):
        if i == (len(path) - 1):
            continue
        else:
            total += get_distance(path[i], path[1 + i])
    return total


def get_distance(star1, star2):
    if distances.get(frozenset((star1, star2)), 0) == 0:
        return distances.get(frozenset((star2, star1)))
    else:
        return distances.get(frozenset((star1, star2)))


if __name__ == "__main__":
    main()
