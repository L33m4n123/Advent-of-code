#  -*- coding: utf-8 -*-
__author__ = "Patrick Lehmann"


class Main:
    def __init__(self):
        with open('../../input/day23.txt') as f:
            self.data = f.read()
        self.instructions = dict()
        # TODO: Change a to 0 for Part 1
        self.a = 1
        self.b = 0
        self.current_step = 0
        self.parse_input()
        run = True
        while run:
            run = self.interpret_input()

    def parse_input(self):
        for i, line in enumerate(self.data.splitlines()):
            self.instructions[i] = line

    def print_input(self):
        for i in range(len(self.instructions)):
            print(self.instructions[i])

    def interpret_input(self):
        instruction = ""
        if self.current_step in self.instructions:
            instruction = self.instructions.get(self.current_step)
            print(instruction)
        else:
            print("A:", self.a, "B:", self.b)
            return False
        if "jio" in instruction:
            if "a" in instruction and self.a == 1:
                steps = 1
                if "," in instruction:
                    steps = instruction.replace("jio a, ", "")
                self.current_step += int(steps)
            elif "b" in instruction and self.b == 1:
                steps = 1
                if "," in instruction:
                    steps = instruction.replace("jio b, ", "")
                self.current_step += int(steps)
            else:
                self.current_step += 1
        elif "inc" in instruction:
            self.current_step += 1
            if "a" in instruction:
                self.a += 1
            else:
                self.b += 1
        elif "tpl" in instruction:
            self.current_step += 1
            if "a" in instruction:
                self.a *= 3
            else:
                self.b *= 3
        elif "jmp" in instruction:
            self.current_step += int(instruction.replace("jmp ", ""))
        elif "jie" in instruction:
            if "a" in instruction and self.a % 2 == 0:
                steps = 1
                if "," in instruction:
                    steps = instruction.replace("jie a, ", "")
                self.current_step += int(steps)
            elif "b" in instruction and self.b % 2 == 0:
                steps = 1
                if "," in instruction:
                    steps = instruction.replace("jie b, ", "")
                self.current_step += int(steps)
            else:
                self.current_step += 1
        elif "hlf" in instruction:
            self.current_step += 1
            if "a" in instruction:
                self.a /= 2
            else:
                self.b /= 2
        return True

if __name__ == "__main__":
    Main()