package me.guillaume.duel.styles;

import me.guillaume.duel.Contender;
import me.guillaume.duel.equipements.weapons.Axe;

public class Viking extends Contender {
    public Viking(){
        super(120, new Axe());
    }

    public Viking equip(String equipement){
        return (Viking) super.equip(equipement);
    }
}
