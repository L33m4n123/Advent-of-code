#  -*- coding: utf-8 -*-
__author__ = "Patrick Lehmann"

from itertools import product

stats = {
    "Player HP": 100,
    "Player Armor": 0,
    "Player DMG": 0,
    "Boss HP": 109,
    "Boss Armor": 2,
    "Boss DMG": 8
}

weapons = {
    "Dagger": "8|4|0",
    "Shortsword": "10|5|0",
    "Warhammer": "25|6|0",
    "Longsword": "40|7|0",
    "Greataxe": "74|8|0"
}

armors = {
    "None": "0|0|0",
    "Leather": "13|0|1",
    "Chainmail": "31|0|2",
    "Splintmail": "53|0|3",
    "Bandedmail": "75|0|4",
    "Platemail": "102|0|5"
}

rings = {
    "None 1": "0|0|0",
    "None 2": "0|0|0",
    "Damage +1": "25|1|0",
    "Damage +2": "50|2|0",
    "Damage +3": "100|3|0",
    "Defense +1": "20|0|1",
    "Defense +2": "40|0|2",
    "Defense +3": "80|0|3"
}

winning_costs = set()
losing_costs = set()


def main():
    global stats, weapons, armors, rings, winning_costs, losing_costs
    for loadout in product(weapons, armors, rings, rings):
        weapon, armor, ring_l, ring_r = loadout
        if ring_l == ring_r:
            continue
        reset_stats()
        cost = int(weapons.get(weapon).split("|")[0]) + int(armors.get(armor).split("|")[0]) + int(
            rings.get(ring_l).split("|")[0]) + int(rings.get(ring_r).split("|")[0])
        player_dmg = int(weapons.get(weapon).split("|")[1]) + int(rings.get(ring_l).split("|")[1]) + int(
            rings.get(ring_r).split("|")[1])
        player_armor = int(armors.get(armor).split("|")[2]) + int(rings.get(ring_l).split("|")[2]) + int(
            rings.get(ring_r).split("|")[2])
        if player_armor > 8:
            continue
        stats["Player DMG"] = player_dmg
        stats["Player Armor"] = player_armor
        if simulate_rounds():
            if stats["Player HP"] <= 0:
                losing_costs.add(cost)
            elif stats["Boss HP"] <= 0:
                winning_costs.add(cost)

    print("Part 1", min(winning_costs))
    print("Part 2", max(losing_costs))


def reset_stats():
    global stats
    stats = {
        "Player HP": 100,
        "Player Armor": 0,
        "Player DMG": 0,
        "Boss HP": 109,
        "Boss Armor": 2,
        "Boss DMG": 8
    }


def simulate_rounds():
    global stats
    dead = False
    while not dead:
        if stats['Player HP'] > 0:
            simulate_player()
        else:
            dead = True
            return dead
        if stats['Boss HP'] > 0:
            simulate_boss()
        else:
            dead = True
            return dead


def simulate(i):
    if i == 0:
        # Boss Round
        simulate_boss()
    elif i == 1:
        # Player Round
        simulate_player()


def simulate_player():
    global stats
    # print("==== Player ====")
    dmg = max(1, stats["Player DMG"] - stats["Boss Armor"])
    stats["Boss HP"] -= dmg
    # print("Player deals", dmg, "Damage. Boss now has", stats["Boss HP"], "HP left")


def simulate_boss():
    global stats
    # print("=== Boss ===")
    dmg = max(1, stats['Boss DMG'] - stats['Player Armor'])
    stats['Player HP'] -= dmg
    # print("Boss deals", dmg, "Damage. Player now has", stats["Player HP"], "HP left")


if __name__ == "__main__":
    main()
