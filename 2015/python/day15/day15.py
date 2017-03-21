#  -*- coding: utf-8 -*-
__author__ = "Patrick Lehmann"

from collections import namedtuple
from operator import mul
import re
import functools


numbers = re.compile(r'-?\d+')
Ingredient = namedtuple('Ingredient', ['capacity', 'durability', 'flavor', 'texture'])
ingredients = []
calorie_list = []
recipies = []


def main():
    global recipies
    with open('../../input/day15.txt') as f:
        for line in f:
            line = line.split("calories")
            line[1] = line[1].replace("\n", "").replace(" ", "")
            calorie_list.append(int(line[1]))
            line = line[0]
            ingredients.append(Ingredient(*map(int, numbers.findall(line))))
    calc_score()
    print("=== Part One ===")
    print(max(recipies))
    recipies = []
    calc_score(True)
    print("=== Part Two ===")
    print(max(recipies))


def get_score(amounts, ings, part_two):
    global calorie_list
    totals = []
    by_ing = zip(*ings)
    for vals in by_ing:
        totals.append(map(lambda x: x[0] * x[1], zip(vals, amounts)))
    score = map(sum, totals)
    if part_two:
        calories = 0
        for i in range(len(calorie_list)):
            calories += calorie_list[i] * amounts[i]
        if calories != 500:
            return 0
    score = map(lambda x: max(x, 0), score)
    return functools.reduce(mul, score)


def calc_score(part_two=False):
    global recipies
    # Go through all combinations
    for v in range(101):
        for w in range(101 - v):
            for x in range(101 - v - w):
                y = 100 - v - w - x
                recipies.append(get_score([v, w, x, y], ingredients, part_two))


if __name__ == "__main__":
    main()
