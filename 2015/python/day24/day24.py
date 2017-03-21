#  -*- coding: utf-8 -*-
__author__ = "Patrick Lehmann"

from itertools import combinations


class Main():
    def __init__(self):
        with open('../../input/day24.txt') as f:
            self.input = f.read()
        self.containers = list()
        self.total_weight = self.calc_total_weight_and_containers()
        self.parts = 3
        self.possible_first_container = list(set())
        self.answer = self.calc_max_quantum()
        self.solution(3)
        self.solution(4)

    def calc_max_quantum(self):
        quantum = 1
        for line in self.containers:
            quantum *= line
        return quantum

    def calc_total_weight_and_containers(self):
        weight = 0
        for line in self.input.splitlines():
            weight += int(line)
            self.containers.append(int(line))
        return weight

    def solution(self, parts):
        for i in range(1, 9):
            for c in combinations(self.containers, i):
                if sum(c) == self.total_weight / parts:
                    quantum = 1
                    for i in range(len(list(c))):
                        quantum *= list(c)[i]
                    self.answer = min(self.answer, quantum)
        if parts == 3:
            print("Part 1", self.answer)
        else:
            print("Part 2", self.answer)


if __name__ == "__main__":
    Main()
