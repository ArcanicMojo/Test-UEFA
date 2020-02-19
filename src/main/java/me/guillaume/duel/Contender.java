package me.guillaume.duel;

import me.guillaume.duel.equipements.shields.Buckler;
import me.guillaume.duel.equipements.shields.Shield;
import me.guillaume.duel.equipements.weapons.Axe;
import me.guillaume.duel.equipements.weapons.Weapon;
import me.guillaume.duel.traits.Trait;
import me.guillaume.duel.traits.Veteran;
import me.guillaume.duel.traits.Vicious;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Contender {
    private int hitPoints;
    private final int maxHitPoints;
    private Optional<Weapon> weapon;
    private Optional<Shield> shield;
    protected List<Object> inventory;
    private Optional<Trait> trait;

    protected Contender(int hitPoints, Weapon weapon) {
        this.hitPoints = hitPoints;
        this.maxHitPoints = hitPoints;
        this.weapon = Optional.of(weapon);
        this.shield = Optional.empty();
        this.inventory = new ArrayList<>();
        this.trait = Optional.empty();
    }

    protected Contender(int hitPoints, Weapon weapon, String trait) {
        this.hitPoints = hitPoints;
        this.maxHitPoints = hitPoints;
        this.weapon = Optional.of(weapon);
        this.inventory = new ArrayList<>();
        this.shield = Optional.empty();
        this.trait = changeTrait(trait);
    }

    private Optional<Trait> changeTrait(String trait) {
        switch (trait) {
            case "Vicious":
                return Optional.of(new Vicious());
            case "Veteran":
                return Optional.of(new Veteran());
            default:
                throw new IllegalArgumentException();
        }
    }

    protected Contender equip(String equipement) {
        switch (equipement) {
            case "buckler":
                if (weapon.isPresent() && weapon.get().nbHands() == 2) {
                    weapon = Optional.empty();
                }
                shield = Optional.of(new Buckler());
                break;
            case "axe":
                weapon = Optional.of(new Axe());
                break;
            default:
                inventory.add(equipement);
        }
        return this;
    }

    public void engage(Contender adversary){
        while(hitPoints != 0) {
            hit(adversary);
            if (adversary.hitPoints() <= adversary.maxHitPoints * 30 / 100 && adversary.trait.isPresent() && adversary.trait.get() instanceof Veteran) {
                adversary.trait.get().setActive(true);
            }
            if (adversary.hitPoints != 0) {
                adversary.hit(this);
                if (hitPoints() <= maxHitPoints * 30 / 100 && trait.isPresent() && trait.get() instanceof Veteran) {
                    trait.get().setActive(true);
                }
            } else {
                break;
            }
        }
    }

    private void hit(Contender adversary) {
        if (weapon.isPresent()) {
            int weaponDamage = weapon.get().hit(adversary);
            if (weaponDamage != 0) {
                int totalDamage = getTotalDamage(weaponDamage, adversary);
                if (adversary.hitPoints < totalDamage) {
                    adversary.setHitPoints(0);
                } else {
                    adversary.setHitPoints(adversary.hitPoints - totalDamage);
                }
            }
        }
    }

    private int getTotalDamage(int weaponDamage, Contender adversary) {
        if (adversary.inventory.contains("armor")) {
            weaponDamage -= 3;
        }
        if (inventory.contains("armor")) {
            weaponDamage -= 1;
        }
        if (trait.isPresent() && trait.get() instanceof Vicious) {
            Vicious vicious = (Vicious) trait.get();
            if (vicious.isActive()) {
                weaponDamage += 20;
                vicious.setStacks(vicious.getStacks() - 1);
                if (vicious.getStacks() == 0) {
                    vicious.setActive(false);
                }
            }
        }
        if (trait.isPresent() && trait.get() instanceof Veteran && trait.get().isActive()) {
            weaponDamage *= 2;
        }
        return weaponDamage;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int hitPoints() {
        return this.hitPoints;
    }

    public Optional<Shield> getShield() {
        return shield;
    }
}
