package me.guillaume.duel.equipements.shields;

import me.guillaume.duel.equipements.weapons.Axe;
import me.guillaume.duel.equipements.weapons.Weapon;

public class Buckler extends Shield {
    public int nbPlates;
    public boolean onCooldown;

    public Buckler() {
        this.nbPlates = 3;
        this.onCooldown = false;
    }

    @Override
    public boolean block(Weapon weapon) {
        if (!onCooldown) {
            if (weapon instanceof Axe) {
                nbPlates--;
            }
        }
        onCooldown = !onCooldown;
        return onCooldown && nbPlates >= 0;
    }
}
