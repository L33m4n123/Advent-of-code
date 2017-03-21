#  -*- coding: utf-8 -*-
__author__ = "Patrick Lehmann"

from itertools import permutations

persons = set()
happiness = dict()


def main():
    global persons, happiness

    biggest_happiness = 0
    seat_placement = "None"
    with open('../../input/day13.txt', 'r', encoding='utf-8') as f:
        data = f.read()

    for line in data.splitlines():
        line = line.replace('would ', '').replace('gain ', '+').replace('lose ', '-')
        line = line.replace('happiness units by sitting next to ', '')
        split = line.split()
        split[0] = split[0].replace(" ", "").replace(".", "")
        split[2] = split[2].replace(" ", "").replace(".", "")
        split[1] = split[1].replace(" ", "")
        key = split[0] + " " + split[2]
        persons.add(split[0])
        persons.add(split[2])
        happiness[key] = int(split[1])
    # Add myself to the person list. Give
    # me no Happiness factor. so that when I get it
    # I just return a default value of 0 for me
    persons.add("You")

    for neighbour in permutations(persons):
        curr = happy_neighbour(neighbour)
        if curr > biggest_happiness:
            biggest_happiness = curr
            seat_placement = neighbour

    print(seat_placement, biggest_happiness)


def happy_neighbour(data):
    total = 0
    for i in range(len(data)):
        if i != (len(data) - 1):
            total += get_happiness(data[i], data[i + 1])
        else:
            total += get_happiness(data[i], data[0])
    return total


def get_happiness(person1, person2):
    key = person1 + " " + person2
    key2 = person2 + " " + person1
    total = happiness.get(key, 0)
    total += happiness.get(key2, 0)
    return total


if __name__ == "__main__":
    main()
