__author__ = "Patrick Lehmann"

from hashlib import md5


def main():
    prefix = "000000"
    with open("../../input/day04.txt") as f:
        input_data = f.read()
    i = 1
    while True:
        md = md5()
        to_hash = input_data + str(i)
        md.update(to_hash.encode("utf-8"))
        dig = md.hexdigest()
        if dig[:len(prefix)] == prefix:
            print("Answer: " + str(i))
            break
        i += 1


if __name__ == '__main__':
    main()
