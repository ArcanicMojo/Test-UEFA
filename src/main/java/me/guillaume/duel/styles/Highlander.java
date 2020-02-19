package me.guillaume.duel.styles;

import me.guillaume.duel.Contender;
import me.guillaume.duel.equipements.weapons.GreatSword;

public class Highlander extends Contender {
    public Highlander() {
        super(150, new GreatSword());
    }

    public Highlander(String trait) {
        super(120, new GreatSword(), trait);
    }
}
