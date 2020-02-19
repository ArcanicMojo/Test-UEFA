package me.guillaume.duel.traits;

public abstract class Trait {
    public boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
