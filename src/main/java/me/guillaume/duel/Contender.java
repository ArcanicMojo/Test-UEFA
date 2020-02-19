package me.guillaume.duel;

public class Contender {
    private int hitPoints;
    private final int damage;

    protected Contender(int hitPoints, int damage){
        this.hitPoints = hitPoints;
        this.damage = damage;
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

    public void hit(Contender contender){
        if(contender.hitPoints < damage){
            contender.setHitPoints(0);
        }else {
            contender.setHitPoints(contender.hitPoints - damage);
        }
    }
}
