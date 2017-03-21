__author__ = "Patrick Lehmann"

import re


def main():
    # Make sure we have 3 vowels
    rule1 = re.compile('(.*[aeiou].*){3,}')
    # Make sure there are double letters somewhere
    rule2 = re.compile(r'.*(\w)\1.*')
    # Can't have any of these
    rule3 = re.compile('(.*(ab|cd|pq|xy).*)')
    # 2 non overlapping pair of letters
    rule4 = re.compile(r'.*([a-z][a-z]).*\1')
    # something like xyx
    rule5 = re.compile(r'.*([a-z])[a-z]\1.*')

    count_nice_strings = 0
    count_nicer_strings = 0
    with open("../../input/day05.txt") as f:
        data = f.read()
        for line in data.splitlines():
            if rule1.match(line) and rule2.match(r'' + line) and not rule3.match(line):
                count_nice_strings += 1
            if rule4.match(r'' + line) and rule5.match(r'' + line):
                count_nicer_strings += 1
    print(str(count_nice_strings))
    print(str(count_nicer_strings))


if __name__ == '__main__':
    main()
