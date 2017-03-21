#  -*- coding: utf-8 -*-
__author__ = "Patrick Lehmann"


def get_code_count(row, column):
    return sum(range(row + column - 1)) + column


class Main:
    def __init__(self):
        self.base = 252533
        self.mod = 33554393
        self.row = 2947
        self.column = 3029
        self.count = get_code_count(self.row, self.column)
        self.answer = 20151125
        for i in range(self.count - 1):
            self.answer = self.next_code(self.answer)
        print(self.answer)

    def next_code(self, cur_code):
        remainder = (cur_code * self.base) % self.mod
        return remainder


if __name__ == "__main__":
    Main()
