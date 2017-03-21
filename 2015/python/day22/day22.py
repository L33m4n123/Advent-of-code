#  -*- coding: utf-8 -*-
__author__ = "Patrick Lehmann"


def print_info():
    print("\n\n\n\n")
    print("Here you will find - hopefully - all infos you need about Spells")
    print("Effects all work the same way. Effects apply at the start of both the player's turns and the boss'"
          "turns. Effects are created with a timer (the number of turns they last); at the start of each turn, "
          "after they apply any effect they have, their timer is decreased by one. If this decreases the timer to "
          "zero, the effect ends. You cannot cast a spell that would start an effect which is already active. "
          "However, effects can be started on the same turn they end.")
    print("Shield, Poison and Recharge are Effects")
    print("Magic Missile - 53 Mana - 4 DMG")
    print("Drain - 73 Mana - 2 DMG - Heals for 2")
    print("Shield - 113 Mana - Duration: 6 Rounds - Armor +7")
    print("Poison - 173 Mana - Duration: 6 Rounds - 3 DMG")
    print("Recharge - 229 Mana - Duration: 5 Rounds - Mana Regen: 101 per Round\n")
    input("If you finished reading please press the >Enter< Key!")


class Main:
    def __init__(self):
        # I decided this time to use some classes and stuff to get used to that in python aswell.
        self.spells = {
            "Magic Missile": {
                "mana": 53,
                "dmg": 4
            },
            "Drain": {
                "mana": 73,
                "dmg": 2,
                "heal": 2
            },
            "Shield": {
                "mana": 113,
                "effect": 6,
                "armor": 7
            },
            "Poison": {
                "mana": 173,
                "effect": 6,
                "dmg": 3
            },
            "Recharge": {
                "mana": 229,
                "effect": 5,
                "regeneration": 101
            }
        }
        # self.boss = Boss(55, 8)
        self.boss = Boss(51, 9)
        self.player = Player(50, 500, 0, self.spells)
        self.round_counter = 1

        # I'll do it Interactive. I let the user chose what Spell to use next
        # and output the total Mana used when he wins
        self.sim_round()

    def sim_round(self):
        print("Hard Mode enabled. Player took 1 Damage")
        self.player.hp -= 1
        if self.player.hp <= 0:
            print("You have failed your Quest! Try again!")
            p
            return
        self.player_round()
        self.print_instructions()
        if self.boss.hp <= 0:
            print("Congratz! You killed the boss. You used", self.player.used_mana, "Mana. Can you do better?")
            return True
        self.boss_round()
        self.round_counter += 1
        if self.player.hp >= 0 and self.boss.hp >= 0:
            self.sim_round()
        elif self.player.hp <= 0:
            print("You have failed your Quest! Try again!")
            return True
        elif self.boss.hp <= 0:
            print("Congratz! You killed the boss. You used", self.player.used_mana, "Mana. Can you do better?")

    def player_round(self):
        print("--", self.player.name, "Round --")
        self.resolve_effects()
        self.print_info_player()
        self.print_info_boss()

    def boss_round(self):
        print("--", self.boss.name, "Round --")
        self.resolve_effects()
        self.boss_attack()

    def print_info_player(self):
        print("", self.player.name, "HP:", self.player.hp)
        print("", self.player.name, "Mana", self.player.mana)
        print("", self.player.name, "Armor", self.player.armor, "\n")

    def print_info_boss(self):
        print("", self.boss.name, "HP:", self.boss.hp, "\n")

    def print_instructions(self):
        print("Choose one of the Spells to be used!")
        print("(M)agic Missile")
        print("(D)rain")
        print("(S)hield")
        print("(P)oison")
        print("(R)echarge")
        print("Spell (I)nfo")
        test = ""
        while len(test) != 1:
            test = input("\nPlease Select the Option you want to use: ")
        test = test.lower()
        successful = False
        if test == "i":
            print_info()
            self.print_instructions()
        elif test == "m":
            successful = self.cast_spell("Magic Missile")
        elif test == "d":
            successful = self.cast_spell("Drain")
        elif test == "s":
            successful = self.cast_spell("Shield")
        elif test == "p":
            successful = self.cast_spell("Poison")
        elif test == "r":
            successful = self.cast_spell("Recharge")
        else:
            self.print_instructions_error()
        if not successful:
            self.print_instructions_error()

    def print_instructions_error(self):
        print("There was an error selecting the spell. Please try again!")
        self.print_instructions()

    def cast_spell(self, spell):
        mana_cost = self.spells[spell]["mana"]
        # do we have enough mana?
        if self.player.mana >= mana_cost:
            # Yes! :)
            self.player.mana -= mana_cost
            self.player.used_mana += mana_cost
            # Now we need to identify if it is an effect or not
            if spell == "Magic Missile" or spell == "Drain":
                # It is not! an effect. Superb!
                if spell == "Drain":
                    self.player.hp += self.spells[spell]["heal"]
                dmg = self.spells[spell]["dmg"]
                self.boss.hp -= dmg
                print("Player casts", spell, "for", mana_cost, "Mana and deals", dmg, "Damage")
                return True
            else:
                if self.player.active_effects[spell] > 0:
                    # Give Mana Back as we are not allowed to cast it yet!
                    self.player.mana += mana_cost
                    self.player.used_mana -= mana_cost
                    print(spell, "Effect is still active. You cannot use it yet!")
                else:
                    self.player.active_effects[spell] = self.spells[spell]["effect"]
                    print("Player casts", spell)
                    return True
        else:
            print("You do not have enough mana for this :o")
            return False

        return False

    def boss_attack(self):
        dmg = max(1, self.boss.dmg - self.player.armor)
        self.player.hp -= dmg
        print("The Boss attacks the player for", dmg, "Damage")

    def resolve_effects(self):
        # Loop through effects. If they are running.. Run them
        for k, v in self.player.active_effects.items():
            if v > 0:
                if k == "Shield":
                    self.player.armor = self.spells[k]["armor"]
                    print("The Shield protects you from incomming attacks for", v - 1, "more Rounds")
                    if v == 1:
                        print("Shield Effect ran out!")
                if k == "Poison":
                    self.boss.hp -= self.spells[k]["dmg"]
                    print("Poison caused the Boss to take", self.spells[k]["dmg"], "Damage. It "
                                                                                   "lasts another", v - 1, "Rounds")
                    if v == 1:
                        print("Poison Effect ran out!")
                if k == "Recharge":
                    self.player.mana += self.spells[k]["regeneration"]
                    print("Recharge regenerated", self.spells[k]["regeneration"], "Mana and lasts another", v - 1,
                          "Rounds")
                    if v == 1:
                        print("Recharge Effect ran out!")
        self.player.active_effects["Shield"] = max(0, self.player.active_effects["Shield"] - 1)
        self.player.active_effects["Poison"] = max(0, self.player.active_effects["Poison"] - 1)
        self.player.active_effects["Recharge"] = max(0, self.player.active_effects["Recharge"] - 1)
        if self.player.active_effects["Shield"] == 0:
            self.player.armor = 0


class Boss:
    def __init__(self, hp, dmg):
        self.hp = hp
        self.dmg = dmg
        self.name = "Boss"


class Player:
    def __init__(self, hp, mana, armor, spells):
        self.hp = hp
        self.mana = mana
        self.armor = armor
        self.active_effects = {
            "Shield": 0,
            "Poison": 0,
            "Recharge": 0
        }
        self.spells = spells
        self.used_mana = 0
        self.name = "Player"


if __name__ == "__main__":
    Main()
