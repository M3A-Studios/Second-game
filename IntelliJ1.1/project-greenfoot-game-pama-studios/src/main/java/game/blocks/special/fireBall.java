package game.blocks.special;

import game.Options;
import game.Physics;
import game.entities.enemies.*;
import greenfoot.GreenfootImage;


public class fireBall extends Physics {
    private static GreenfootImage fireBall = new GreenfootImage("fireball.png");
    private boolean started;
    private boolean rotate;
    public String direction;

    fireBall(){
        this.started = false;
        this.rotate = true;
        this.direction = "right";
        fireBall.scale(Options.blockSize * 2,Options.blockSize * 2);
        setImage(fireBall);
    }

    public void act(){
        killEnemy();
        if (!started) {
            setDoubleX(getX());
            setDoubleY(getY());
            started = true;
        }
        entityOffset();
        if (direction.equals("right")) {
            setRelativeLocation(+10, 0);
        } else {
            setRelativeLocation(-10, 0);
        }
        if (rotate) {
            setRotation(getRotation() + 15);
        }
    }

    public void killEnemy(){
        Bee bee = (Bee) getOneIntersectingObject(Bee.class);
        Slime slime = (Slime) getOneIntersectingObject(Slime.class);
        if (bee != null) {
            bee.getWorld().removeObject(bee);
        }
        if (slime != null) {
            slime.getWorld().removeObject(slime);
        }
    }
}
