package game.entities.enemies;

import greenfoot.GreenfootImage;
import game.Options;
import game.Physics;

public class Bee extends Physics {
    static boolean isMirrored = false;
    static GreenfootImage bee = new GreenfootImage("5.png");
    static GreenfootImage beeM = new GreenfootImage("5.png");
    static GreenfootImage beeDead = new GreenfootImage("4.png");
    static GreenfootImage beeDeadM = new GreenfootImage("4.png");
    static GreenfootImage bee2 = new GreenfootImage("6.png");
    static GreenfootImage bee2M = new GreenfootImage("6.png");

    private int beeWidth = (int) (Options.blockSize);
    private int beeHeight = (int) (Options.blockSize);

    static int movementRange = 4;
    private int startingX;
    private double speed = 2.5;
    private boolean isMovingLeft = false;
    private boolean started = false;
    private boolean dead = false;
    int aTime = 0;
    int maTime = 0;
    private int pinkJump = 0;

    public Bee(int ID) {
        if (!isMirrored) {
            beeDeadM.mirrorHorizontally();
            beeDeadM.scale(beeWidth, beeHeight);
            beeM.mirrorHorizontally();
            beeM.scale(beeWidth, beeHeight);
            bee2M.mirrorHorizontally();
            bee2M.scale(beeWidth, beeHeight);
            isMirrored = true;
        }
        bee.scale(beeWidth, beeHeight);//blue slime scale
        bee2.scale(beeWidth, beeHeight);
        beeDead.scale(beeWidth, beeHeight);
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
        }
        if (dead) {
            aTime++;
            if (aTime >= 0) {
                if (isMovingLeft) {
                    setImage(beeDead);
                }
            } else if (!isMovingLeft) {
                setImage(beeDeadM);
            }
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
                if(maTime > 0 && maTime < 10) {setImage(bee);}
                else if(maTime == 10) {setImage(bee2);}
                if(maTime > 20) {maTime = 0;}
            } else {
                isMovingLeft = !isMovingLeft;
            }
        } else {
            if (canMoveRight(speed)) {
                moveRight(speed);
                if(maTime > 0 && maTime < 10) {setImage(beeM);}
                else if(maTime == 10) {setImage(bee2M);}
                if(maTime > 20) {maTime = 0;}
            }else {
                isMovingLeft = !isMovingLeft;
            }
        }
    }
}