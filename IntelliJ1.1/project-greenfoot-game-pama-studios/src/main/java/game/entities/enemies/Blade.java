package game.entities.enemies;

import greenfoot.GreenfootImage;
import game.Options;
import game.Physics;

public class Blade extends Physics {
    static GreenfootImage blade1 = new GreenfootImage("32.png");
    static GreenfootImage blade2 = new GreenfootImage("33.png");

    private int bladeWidth = 128;
    private int bladeHeight = 59;

    private double speed = 3;
    private int startingX;
    private boolean isMovingLeft = false;
    private boolean started = false;
    public boolean dead;
    int maTime = 0;

    public Blade(int ID) {
        //blue slime scale
        blade2.scale(bladeWidth / 2, bladeHeight / 2);
        blade1.scale(bladeWidth / 2, bladeHeight / 2);
        dead = false;
    }

    public void act() {
        entityOffset();
        if (!started) {
            started = true;
            setDoubleX(getX());
            setDoubleY(getY());
            startingX = getX();
            setNewLocation(getDoubleX(), getDoubleY() + getImage().getHeight());
        }
        if (!dead) {
            moving();
            updateGravity();
        }
    }
    private void moving() {
        maTime++;
        //Blade moving left
        if (isMovingLeft) {
            if (canEntityMoveLeft(speed)) {
                moveLeft(speed);
                if(maTime > 0 && maTime < 10) {setImage(blade1);}
                else if(maTime == 10) {setImage(blade2);}
                if(maTime > 20) {maTime = 0;}
            } else {
                isMovingLeft = false;
            }
        }
        //Blade moving right
        if (!isMovingLeft) {
            if (canEntityMoveRight(speed)) {
                moveRight(speed);
                if(maTime > 0 && maTime < 10) {setImage(blade1);}
                else if(maTime == 10) {setImage(blade2);}
                if(maTime > 20) {maTime = 0;}
            }else {
                isMovingLeft = true;
            }
        }
    }
}