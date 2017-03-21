#  -*- coding: utf-8 -*-
__author__ = "Patrick Lehmann"

import json


def main():
    with open('../../input/day12.json') as f:
        data = json.load(f)
    print(sum(all_numbers(data)))

    print(sum(all_numbers_without_red(data)))


def all_numbers(data):
    if isinstance(data, int):
        yield data
    if isinstance(data, list):
        for value in data:
            yield from all_numbers(value)
    if isinstance(data, dict):
        for value in data.values():
            yield from all_numbers(value)


def all_numbers_without_red(data):
    if isinstance(data, int):
        yield data

    if isinstance(data, list):
        for value in data:
            yield from all_numbers_without_red(value)

    if isinstance(data, dict):
        if 'red' in data.values():
            return
        for value in data.values():
            yield from all_numbers_without_red(value)


if __name__ == "__main__":
    main()