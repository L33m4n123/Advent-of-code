__author__ = "Patrick Lehmann"

santaPosX = 0
santaPosY = 0
robotSantaPosX = 0
robotSantaPosY = 0

routeList = ['0,0']


def main():
    global santaPosX, santaPosY, routeList
    """
    Entry point of the program
    """
    with open('../../input/day03.txt') as f:
        data = f.read()

    present_delivery(data)

    santaPosX = 0
    santaPosY = 0
    routeList = ['0,0']

    present_delivery_part2(data)


def present_delivery(data):
    global santaPosY, santaPosX
    for c in data:
        if c == '^':
            santaPosY += 1
        elif c == 'v':
            santaPosY -= 1
        elif c == '>':
            santaPosX += 1
        elif c == '<':
            santaPosX -= 1
        curr_pos = str(santaPosX) + "," + str(santaPosY)
        if curr_pos not in routeList:
            routeList.append(curr_pos)
    print("Santa visited " + str(len(routeList)) + " individual Houses")


def present_delivery_part2(data):
    global santaPosY, santaPosX, robotSantaPosX, robotSantaPosY
    counter = 0
    for c in data:
        counter += 1
        if c == '^':
            if counter % 2 == 0:
                robotSantaPosY += 1
            else:
                santaPosY += 1
        elif c == 'v':
            if counter % 2 == 0:
                robotSantaPosY -= 1
            else:
                santaPosY -= 1
        elif c == '>':
            if counter % 2 == 0:
                robotSantaPosX += 1
            else:
                santaPosX += 1
        elif c == '<':
            if counter % 2 == 0:
                robotSantaPosX -= 1
            else:
                santaPosX -= 1
        curr_pos = "0,0"
        if counter % 2 == 0:
            curr_pos = str(robotSantaPosX) + "," + str(robotSantaPosY)
        else:
            curr_pos = str(santaPosX) + "," + str(santaPosY)
        if curr_pos not in routeList:
            routeList.append(curr_pos)
    print("Santa and Robot Santa visited " + str(len(routeList)) + " individual Houses")


if __name__ == '__main__':
    main()
