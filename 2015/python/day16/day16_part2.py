#  -*- coding: utf-8 -*-
__author__ = "Patrick Lehmann"

import re

number = re.compile("\d")
signature = {
    'children': 3, 'cats': 7, 'samoyeds': 2, 'pomeranians': 3, 'akitas': 0,
    'vizslas': 0, 'goldfish': 5, 'trees': 3, 'cars': 2, 'perfumes': 1
}

remove = set()


def main():
    global signature, number
    """
     There is most likey a better way to do it. However I will just simply "bruteforce" it and
     iterate over all Aunts and check who fits
    """
    with open('../../input/day16.txt') as f:
        data = f.read()
    lines = data.splitlines()
    for line in lines:
        for key in signature.keys():
            if key in line:
                if key == "trees" or key == "cats":
                    line_split = line.split(key + ": ")
                    value = number.findall(line_split[1])[0]
                    if not signature.get(key) < int(value):
                        remove.add(line)
                elif key == "pomeranians" or key == "goldfish":
                    line_split = line.split(key + ": ")
                    value = number.findall(line_split[1])[0]
                    if not signature.get(key) > int(value):
                        remove.add(line)
                else:
                    line_split = line.split(key + ": ")
                    value = number.findall(line_split[1])[0]
                    if not signature.get(key) == int(value):
                        remove.add(line)

    for entry in remove:
        lines.remove(entry)
    for line in lines:
        print(line)


if __name__ == "__main__":
    main()
