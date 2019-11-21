package nl.rocmondriaan.greenfoot.game;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Slime extends Physics {
    static boolean isMirrored = false;
    static GreenfootImage blueSlime = new GreenfootImage("40.png");
    static GreenfootImage blueSlimeM = new GreenfootImage("40.png");
    static GreenfootImage blueSlimeDead = new GreenfootImage("37.png");
    static GreenfootImage blueSlimeDeadM = new GreenfootImage("37.png");

    static GreenfootImage pinkSlime = new GreenfootImage("48.png");
    static GreenfootImage pinkSlimeM = new GreenfootImage("48.png");
    static GreenfootImage pinkSlimeDead = new GreenfootImage("45.png");
    static GreenfootImage pinkSlimeDeadM = new GreenfootImage("45.png");

    static GreenfootImage greenSlime = new GreenfootImage("44.png");
    static GreenfootImage greenSlimeM = new GreenfootImage("44.png");
    static GreenfootImage greenSlimeDead = new GreenfootImage("41.png");
    static GreenfootImage greenSlimeDeadM = new GreenfootImage("41.png");

    private int slimeWidth = (int) (Options.blockSize / 1.1);  //1 block
    private int slimeHeight = (int) (Options.blockSize / 2); //1.5 blocks

    static int movementRange = 4;
    private double speed = 2.5;
    private boolean isMovingLeft = false;
    private boolean started = false;
    private int startingX;
    boolean dead;
    int aTime = 0;
    private int pinkJump = 0;
    private String color;

    Slime(int ID) {
        if (!isMirrored) {
            blueSlimeDeadM.mirrorHorizontally();
            blueSlimeDeadM.scale(slimeWidth, slimeHeight);
            blueSlimeM.mirrorHorizontally();
            blueSlimeM.scale(slimeWidth, slimeHeight);

            pinkSlimeDeadM.mirrorHorizontally();
            pinkSlimeDeadM.scale(slimeWidth, slimeHeight);
            pinkSlimeM.mirrorHorizontally();
            pinkSlimeM.scale(slimeWidth, slimeHeight);

            greenSlimeDeadM.mirrorHorizontally();
            greenSlimeDeadM.scale(slimeWidth, slimeHeight);
            greenSlimeM.mirrorHorizontally();
            greenSlimeM.scale(slimeWidth, slimeHeight);
            isMirrored = true;
        }
        blueSlime.scale(slimeWidth, slimeHeight);
        blueSlimeDead.scale(slimeWidth, slimeHeight);
        pinkSlime.scale(slimeWidth, slimeHeight);
        pinkSlimeDead.scale(slimeWidth, slimeHeight);
        greenSlime.scale(slimeWidth, slimeHeight);
        greenSlimeDead.scale(slimeWidth, slimeHeight);

        if (ID >= 45 && ID <= 48) {
            setImage(pinkSlime);
            color = "pink";
        } else if (ID >= 41 && ID <= 44) {
            color = "green";
            speed = 5.0;
            setImage(greenSlime);
        } else {
            setImage(blueSlime);
            color = "blue";
        }
        dead = false;
    }

    public void act() {
        if (!started) {
            started = true;
            setDoubleX(getX());
            setDoubleY(getY());
            startingX = getX();
            setNewLocation(getDoubleX(), getDoubleY() - getImage().getHeight());
        }
        updateGravity();
        entityOffset();
        if (!dead) {
            moving();
        }
        if (dead) {
            aTime++;
            if (aTime >= 0) {
                if (isMovingLeft) {
                    if (color.equals("pink")) {
                        setImage(pinkSlimeDead);
                    } else if (color.equals("green")) {
                        setImage(greenSlimeDead);
                    } else {
                        setImage(blueSlimeDead);
                    }
                } else if (!isMovingLeft) {
                    if (color.equals("pink")) {
                        setImage(pinkSlimeDeadM);
                    } else if (color.equals("green")) {
                        setImage(greenSlimeDeadM);
                    } else {
                        setImage(blueSlimeDeadM);
                    }
                }
            }
            if (aTime == 50) {
                getWorld().removeObject(this);
            }
        }
    }

    private void moving() {
        if (color.equals("pink")) {
            if (onGround()) {
                pinkJump++;
            }
            if (pinkJump == 30) {
                if (onGround()) {
                    jump(15);
                }
                pinkJump = 0;
            }
        }
        if (isMovingLeft) {
            if (canEntityMoveLeft(speed) || (canMoveLeft(speed) && color.equals("pink"))) {
                moveLeft(speed);
                if (color.equals("pink")) {
                    setImage(pinkSlime);
                } else if (color.equals("green")) {
                    setImage(greenSlime);
                } else {
                    setImage(blueSlime);
                }
            } else {
                isMovingLeft = false;
            }
        }
        if (!isMovingLeft) {
            if (canEntityMoveRight(speed) || (canMoveRight(speed) && color.equals("pink"))) {
                moveRight(speed);
                if (color.equals("pink")) {
                    setImage(pinkSlimeM);
                } else if (color.equals("green")) {
                    setImage(greenSlimeM);
                } else {
                    setImage(blueSlimeM);
                }
            } else {
                isMovingLeft = true;
            }
        }
    }
}