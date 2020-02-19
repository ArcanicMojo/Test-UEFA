package me.guillaume.duel.equipements.weapons;

import me.guillaume.duel.Contender;

public class GreatSword extends Weapon {
    private int cooldown;
    private boolean active;

    public GreatSword() {
        super(12, 2);
        cooldown = 2;
        active = true;
    }

    public boolean isActive() {
        return active;
    }

    public void setCooldown(int cooldown) {
        if (cooldown == 0){
            this.active = false;
        }
        this.cooldown = cooldown;
    }

    public void setActive(boolean active) {
        if (active) {
            cooldown = 2;
        }
        this.active = active;
    }

    @Override
    public int hit(Contender adversary) {
        if (isActive()) {
            setCooldown(cooldown - 1);
            return super.hit(adversary);
        } else {
            setActive(true);
            return 0;
        }
    }
}
