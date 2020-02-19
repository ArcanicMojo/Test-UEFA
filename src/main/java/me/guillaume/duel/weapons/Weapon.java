package me.guillaume.duel.weapons;

public abstract class Weapon {
    private final int damage;

    protected Weapon(int damage){
        this.damage = damage;
    }

    public int damage() {
        return damage;
    }
}
