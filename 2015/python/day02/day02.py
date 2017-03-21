__author__ = "Patrick Lehmann"

import heapq


def main():
    """
    Entry point of the program
    """
    with open('../../input/day02.txt') as f:
        data = f.read()

    parse_input(data)


def parse_input(data):
    total_wrapping_paper = 0
    total_ribbon = 0
    for line in data.splitlines():
        dimensions = line.split("x")
        total_wrapping_paper += surface(int(dimensions[0]), int(dimensions[1]), int(dimensions[2]))
        total_wrapping_paper += slack(int(dimensions[0]), int(dimensions[1]), int(dimensions[2]))
        total_ribbon += ribbon(int(dimensions[0]), int(dimensions[1]), int(dimensions[2]))
    print("The total ammount of wrapping paper in sqr Feet is:")
    print(total_wrapping_paper)
    print("The total ammount of ribbons in feet is:")
    print(total_ribbon)


def ribbon(l, w, h):
    smallest = heapq.nsmallest(2, [l, w, h])
    return (2 * smallest[0] + 2 * smallest[1]) + l * w * h


def surface(l, w, h):
    return int(2 * l * w + 2 * w * h + 2 * h * l)


def slack(l, w, h):
    return heapq.nsmallest(1, [l * w, w * h, h * l])[0]


if __name__ == '__main__':
    main()
