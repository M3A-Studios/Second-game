package game.blocks.special;

import game.Options;
import game.Physics;
import game.entities.enemies.*;
import greenfoot.GreenfootImage;


public class fireBall extends Physics {
    private static GreenfootImage fireBall = new GreenfootImage("fireball.png");
    private static GreenfootImage fire = new GreenfootImage("fire01.png");
    private boolean started;
    public String direction;
    //fall Time
    private int fTime;
    //remove image time
    private int rTime;
    //fade out image to fire then delete
    private boolean fade;

    fireBall(){
        started = false;
        direction = "right";
        fade = false;
        fireBall.scale((int) (Options.blockSize * 1.5), (int) (Options.blockSize * 1.5));
        fire.scale((int) (Options.blockSize * 1.5), (int) (Options.blockSize * 1.5));
        setImage(fireBall);
    }

    public void act(){
        if (!started) {
            setDoubleX(getX());
            setDoubleY(getY());
            started = true;
        }
        entityOffset();
        moveDirection();
        killEnemy();
        fadeout();
        /*if(this.isTouching(Solid.class)){
            getWorld().removeObject(this);
        }*/
    }

    public void moveDirection(){
        fTime++;
        if(!fade) {
            if (direction.equals("right")) {
                setRotation(getRotation() + 15);
                if (fTime > 0) { setRelativeLocation(+10, -2.5); }
                if (fTime > 15) { setRelativeLocation(-2.5, +5); }
                if (fTime > 30) { setRelativeLocation(-5, +2.5); }
            } else {
                setRotation(getRotation() - 15);
                if (fTime > 0) { setRelativeLocation(-10, -2.5); }
                if (fTime > 15) { setRelativeLocation(+2.5, +5); }
                if (fTime > 30) { setRelativeLocation(+5, +2.5); }
            }
        }
    }

    public void killEnemy(){
        Bee bee = (Bee) getOneIntersectingObject(Bee.class);
        Slime slime = (Slime) getOneIntersectingObject(Slime.class);
        if (bee != null) {
            bee.getWorld().removeObject(bee);
            fade = true;
        }
        if (slime != null) {
            slime.getWorld().removeObject(slime);
            fade = true;
        }
    }
    public void fadeout(){
        if (fade){
            rTime++;
            if(rTime > 0){setImage(fire);}
            if(rTime > 10){getWorld().removeObject(this);};
        }
    }
}
