package me.guillaume.duel.weapons;

public class GreatSword extends Weapon {
    private int cooldown;
    private boolean active;

    public GreatSword() {
        super(12);
        cooldown = 2;
        active = true;
    }

    public int getCooldown() {
        return cooldown;
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
        if(active){
            cooldown = 2;
        }
        this.active = active;
    }
}
