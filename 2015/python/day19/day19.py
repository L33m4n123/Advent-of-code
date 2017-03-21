#  -*- coding: utf-8 -*-
__author__ = "Patrick Lehmann"

import re
from collections import defaultdict

instructions = defaultdict(list)
sorted_instructions = dict()
molecule = ""
atoms = list()
result = set()


def main():
    global instructions, molecule, atoms, result, sorted_instructions
    with open("../../input/day19.txt") as f:
        data = f.read()
    for line in data.splitlines():
        if "=>" in line:
            split = line.split(" => ")
            instructions[split[0]].append(split[1])
        else:
            molecule = line
    # A Atom starts with a Capitol Letter followed by lowercase letters
    atom_pattern = re.compile(r"[A-Z][a-z]*")

    atoms = atom_pattern.findall(molecule)

    for i, atom in enumerate(atoms):
        for replacement in instructions[atom]:
            temp = atoms[:]
            temp[i] = replacement
            result.add(''.join(temp))

    print(len(result))

    # --- Part Two ---
    instructions = defaultdict(str)
    for line in data.splitlines():
        if "=>" in line:
            split = line.split(" => ")
            instructions[split[1]] = split[0]
        else:
            molecule = line

    sorted_instructions = sorted(instructions.keys(), key=len, reverse=True)
    print(search_and_replace(molecule))


def search_and_replace(s):
    if s == 'e':
        return 0
    return 1 + next(search_and_replace(s.replace(t, instructions[t], 1))
                    for t in sorted_instructions if t in s)


if __name__ == "__main__":
    main()
