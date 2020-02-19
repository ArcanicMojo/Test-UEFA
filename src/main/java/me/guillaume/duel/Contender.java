package me.guillaume.duel;

import me.guillaume.duel.weapons.Axe;
import me.guillaume.duel.weapons.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contender {
    private int hitPoints;
    private final Weapon weapon;
    protected List<String> inventory;
    private Map<String, Integer> states;

    protected Contender(int hitPoints, Weapon weapon){
        this.hitPoints = hitPoints;
        this.weapon = weapon;
        this.inventory = new ArrayList<>();
        this.states = new HashMap<>();
    }

    public void engage(Contender adversary){
        while(hitPoints != 0){
            hit(adversary);
            if(adversary.hitPoints != 0){
                adversary.hit( this);
            }
        }
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int hitPoints(){
        return this.hitPoints;
    }

    private void hit(Contender adversary){
        if(adversary.inventory.contains("buckler") && !adversary.states.containsKey("bucklerDown")){
            block(adversary);
        } else {
            if(adversary.hitPoints < weapon.damage()){
                adversary.setHitPoints(0);
            } else {
                adversary.setHitPoints(adversary.hitPoints - weapon.damage());
            }
            if(adversary.inventory.contains("buckler")){
                adversary.states.remove("bucklerDown");
            }
        }
    }

    private void block(Contender adversary) {
        adversary.states.put("bucklerDown", 0);
        if (weapon instanceof Axe) {
            if (!adversary.states.containsKey("bucklerPlates")) {
                adversary.states.put("bucklerPlates", 2);
            } else {
                adversary.states.replace("bucklerPlates", adversary.states.get("bucklerPlates") - 1);
                if (adversary.states.get("bucklerPlates") == 0) {
                    destroyBuckler(adversary);
                }
            }
        }
    }

    private void destroyBuckler(Contender adversary) {
        adversary.states.remove("bucklerPlates");
        adversary.states.remove("bucklerDown");
        adversary.inventory.remove("buckler");
    }
}
