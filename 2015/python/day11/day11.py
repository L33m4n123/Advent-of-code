#  -*- coding: utf-8 -*-
__author__ = "Patrick Lehmann"

import re


def main():
    with open('../../input/day11.txt') as f:
        data = f.read()
    found = False
    while not found:
        data = get_next_valid_password(data)
        found = is_valid(data)
    print(data)
    found = False
    while not found:
        data = get_next_valid_password(data)
        found = is_valid(data)
    print(data)


def get_next_valid_password(passw, i=0):
    size = len(passw)
    if passw[size - 1 - i] is 'z':
        passw = passw[:size - 1 - i] + 'a' + passw[size - i:]
        i += 1
        passw = get_next_valid_password(passw, i)
    else:
        passw = passw[:size - 1 - i] + chr(ord(passw[size - 1 - i]) + 1) + passw[size - i:]
    return passw


"""
    if not is_valid(pwd):
    else:
        return pwd
"""


def is_valid(password):
    return has_straight(password) and not has_forbidden(password) and has_pair(password)


def has_straight(password):
    # Password cannot be longer than 8 chars. And as I need a straight of 3.. I don't need to check
    # the last two characters in the password
    for i in range(6):
        if password[i + 1] == chr(ord(password[i]) + 1) and password[i + 2] == chr(ord(password[i]) + 2):
            return True
    return False


def has_forbidden(password):
    forbidden = {'i', 'o', 'l'}
    for c in forbidden:
        if c in password:
            return True
    return False


def has_pair(password):
    return len(re.findall(r"(.)\1", password)) >= 2


if __name__ == "__main__":
    main()
