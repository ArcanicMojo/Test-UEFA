package me.guillaume.duel.styles;

import me.guillaume.duel.Contender;
import me.guillaume.duel.equipements.weapons.Sword;

public class Swordsman extends Contender {
    public Swordsman() {
        super(100, new Sword());
    }

    public Swordsman(String trait) {
        super(100, new Sword(), trait);
    }

    public Swordsman equip(String equipement) {
        return (Swordsman) super.equip(equipement);
    }
}
