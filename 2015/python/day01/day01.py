__author__ = "Patrick Lehmann"


def main():
    """
    Entry point of the program
    """
    with open('../../input/day01.txt') as f:
        data = f.read()

    print("Part 1: ")
    print(answer_part1(data))
    print("Part 2: ")
    print(answer_part2(data))


def answer_part1(data):
    counter = 0
    for c in data:
        if c == ")":
            counter -= 1
        else:
            counter +=1
    return counter


def answer_part2(data):
    counter = 0
    floor = 0
    for c in data:
        if floor == -1:
            break
        if c == ")":
            floor -= 1
        else:
            floor += 1
        counter += 1
    return counter


if __name__ == '__main__':
    main()
