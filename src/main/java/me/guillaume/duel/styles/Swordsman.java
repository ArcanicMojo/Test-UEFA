package me.guillaume.duel.styles;

import me.guillaume.duel.Contender;
import me.guillaume.duel.weapons.Sword;

public class Swordsman extends Contender {
    public Swordsman(){
        super(100, new Sword());
    }

    public Swordsman equip(String equipement){
        return (Swordsman) super.equip(equipement);
    }
}
