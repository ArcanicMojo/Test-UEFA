package me.guillaume.duel.equipements.weapons;

import me.guillaume.duel.Contender;

public abstract class Weapon {
    private final int damage;
    private final int nbHands;

    protected Weapon(int damage, int nbHands) {
        this.damage = damage;
        this.nbHands = nbHands;
    }

    public int nbHands() {
        return nbHands;
    }

    public int hit(Contender adversary) {
        if (adversary.getShield().isPresent() && adversary.getShield().get().block(this)) {
            return 0;
        } else {
            return damage;
        }
    }

}
