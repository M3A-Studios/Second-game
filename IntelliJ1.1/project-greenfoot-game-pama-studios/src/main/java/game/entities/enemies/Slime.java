package game.entities.enemies;

import greenfoot.GreenfootImage;
import game.Options;
import game.Physics;

public class Slime extends Physics {
    static boolean isMirrored = false;
    //Menu slime images
    static GreenfootImage menuSlimeAlive = new GreenfootImage("40.png");
    static GreenfootImage menuSlimeDead = new GreenfootImage("37.png");
    //install blue slime immages
    static GreenfootImage blueSlime = new GreenfootImage("40.png");
    static GreenfootImage blueSlimeM = new GreenfootImage("40.png");
    static GreenfootImage blueSlimeDead = new GreenfootImage("37.png");
    static GreenfootImage blueSlimeDeadM = new GreenfootImage("37.png");
    //install pink slime immages
    static GreenfootImage pinkSlime = new GreenfootImage("48.png");
    static GreenfootImage pinkSlimeM = new GreenfootImage("48.png");
    static GreenfootImage pinkSlimeDead = new GreenfootImage("45.png");
    static GreenfootImage pinkSlimeDeadM = new GreenfootImage("45.png");
    static GreenfootImage pinkSlimeJump = new GreenfootImage("46.png");
    static GreenfootImage pinkSlimeJumpM = new GreenfootImage("46.png");
    //install green slime immages
    static GreenfootImage greenSlime = new GreenfootImage("44.png");
    static GreenfootImage greenSlimeM = new GreenfootImage("44.png");
    static GreenfootImage greenSlimeDead = new GreenfootImage("41.png");
    static GreenfootImage greenSlimeDeadM = new GreenfootImage("41.png");

    private int slimeWidth = 64;
    private int slimeHeight = 32;

    static int movementRange = 4;
    private double speed = 2.5;
    private boolean isMovingLeft = false;
    private boolean started = false;
    private int startingX;
    public boolean dead;
    int aTime = 0;
    private int pinkJump = 0;
    private String color;
    private boolean isMenuSlime;

    public Slime(int ID) {
        this(ID, false);
    }

    public Slime(int ID, boolean menuSlime) {
        this.isMenuSlime = menuSlime;
        blueSlime.scale(slimeWidth, slimeHeight);//blue slime scale
        blueSlime2.scale(slimeWidth, slimeHeight);
        blueSlimeDead.scale(slimeWidth, slimeHeight);
        blueSlimeDeadM.scale(slimeWidth, slimeHeight);
        blueSlime2M.scale(slimeWidth, slimeHeight);
        blueSlimeM.scale(slimeWidth, slimeHeight);

        pinkSlime.scale(slimeWidth, slimeHeight);//pink slime scale
        pinkSlime2.scale(slimeWidth, slimeHeight);
        pinkSlimeDead.scale(slimeWidth, slimeHeight);
        pinkSlimeJump.scale(slimeWidth, slimeHeight / 2);
        pinkSlimeDeadM.scale(slimeWidth, slimeHeight);
        pinkSlimeM.scale(slimeWidth, slimeHeight);
        pinkSlime2M.scale(slimeWidth, slimeHeight);
        pinkSlimeJumpM.scale(slimeWidth, slimeHeight);

        greenSlime.scale(slimeWidth, slimeHeight);//green slime scale
        greenSlime2.scale(slimeWidth, slimeHeight);
        greenSlimeDead.scale(slimeWidth, slimeHeight);
        greenSlimeDeadM.scale(slimeWidth, slimeHeight);
        greenSlimeM.scale(slimeWidth, slimeHeight);
        greenSlime2M.scale(slimeWidth, slimeHeight);


        if (!isMirrored) {
            blueSlimeDeadM.mirrorHorizontally();
            blueSlimeM.mirrorHorizontally();
            blueSlime2M.mirrorHorizontally();
            //blue slime moving animation
            pinkSlimeDeadM.mirrorHorizontally();
            pinkSlimeM.mirrorHorizontally();
            pinkSlime2M.mirrorHorizontally();
            pinkSlimeJumpM.mirrorHorizontally();
            //pink slime moving animation
            greenSlimeDeadM.mirrorHorizontally();
            greenSlimeM.mirrorHorizontally();
            greenSlime2M.mirrorHorizontally();
            isMirrored = true;
        }
        //looking for slime
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
        if (menuSlime) {
            menuSlimeAlive.scale(slimeWidth * 2,slimeHeight * 2);
            menuSlimeDead.scale(slimeWidth * 2,slimeHeight * 2);
            setImage(menuSlimeAlive);
        }
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
        if (!isMenuSlime) {
            updateGravity();
            if (!dead) {
                moving();
            }
        }
        if (dead) {
            aTime++;
            if (aTime <= 3) {
                if (!isMenuSlime) {
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
                } else {
                    setImage(menuSlimeDead);
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