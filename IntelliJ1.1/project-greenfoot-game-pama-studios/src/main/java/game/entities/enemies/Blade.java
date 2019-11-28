package game.entities.enemies;

import greenfoot.GreenfootImage;
import game.Options;
import game.Physics;

public class Blade extends Physics {
    static boolean isMirrored = false;
    static GreenfootImage blade1 = new GreenfootImage("32.png");
    static GreenfootImage blade2 = new GreenfootImage("33.png");


    private int bladeWidth = (int) (Options.blockSize);
    private int bladeHeight = (int) (Options.blockSize / 2);

    private int startingX;
    private double speed = 2.5;
    private boolean isMovingLeft = false;
    private boolean started = false;
    public boolean dead;
    int aTime = 0;
    int maTime = 0;

    public Blade(int ID) {
        if (!isMirrored) {
            blade2.mirrorHorizontally();
            blade2.scale(bladeWidth, bladeHeight);
            isMirrored = true;
        }
        blade1.scale(bladeWidth, bladeHeight);//blue slime scale
        dead = false;
    }

    public void act() {
        entityOffset();
        if (!started) {
            started = true;
            setDoubleX(getX());
            setDoubleY(getY());
            startingX = getX();
            setNewLocation(getDoubleX(), getDoubleY() - getImage().getHeight());
        }
        if (!dead) {
            moving();
            updateGravity();

        }
        if (aTime == 50) {
            getWorld().removeObject(this);
        }
    }
    private void moving() {
        maTime++;
        if (isMovingLeft) {
            if (canMoveLeft(speed)) {
                moveLeft(speed);
                if(maTime > 0 && maTime < 10) {setImage(blade1);}
                else if(maTime == 10) {setImage(blade2);}
                if(maTime > 20) {maTime = 0;}
            } else {
                isMovingLeft = !isMovingLeft;
            }
        } else {
            if (canMoveRight(speed)) {
                moveRight(speed);
                if(maTime > 0 && maTime < 10) {setImage(blade1);}
                else if(maTime == 10) {setImage(blade2);}
                if(maTime > 20) {maTime = 0;}
            }else {
                isMovingLeft = !isMovingLeft;
            }
        }
    }
}