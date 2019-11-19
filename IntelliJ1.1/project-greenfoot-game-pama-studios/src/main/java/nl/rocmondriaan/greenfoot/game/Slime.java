package nl.rocmondriaan.greenfoot.game;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Slime extends Physics
{
    static boolean isMirrored = false;
    static GreenfootImage blueSlime = new GreenfootImage("40.png");
    static GreenfootImage blueSlimeM = new GreenfootImage("40.png");
    static GreenfootImage blueSlimeDead = new GreenfootImage("37.png");
    static GreenfootImage blueSlimeDeadM = new GreenfootImage("37.png");
    private int slimeWidth = (int) (Options.blockSize / 1.1);          //1 block
    private int slimeHeight = (int) (Options.blockSize / 2); //1.5 blocks

    static int movementRange = 4;
    private double speed = 2.5;
    private boolean isMovingLeft = false;
    private boolean started = false;
    private int startingX;
    boolean dead;
    int aTime = 0;

    Slime(int ID) {
        if (!isMirrored) {
            blueSlimeDeadM.mirrorHorizontally();
            blueSlimeDeadM.scale(slimeWidth,slimeHeight);
            blueSlimeM.mirrorHorizontally();
            blueSlimeM.scale(slimeWidth, slimeHeight);
            isMirrored = true;
        }
        blueSlime.scale(slimeWidth,slimeHeight);
        blueSlimeDead.scale(slimeWidth,slimeHeight);
        setImage(blueSlime);
        dead = false;
    }

    public void act(){
        if (!started) {
            started = true;
            setDoubleX(getX());
            setDoubleY(getY());
            startingX = getX();
            setNewLocation(getDoubleX(), getDoubleY() - getImage().getHeight());
        }
        updateGravity();
        entityOffset();
        if(!dead){
            moving();
        }
        if (dead){
            aTime ++;
            if (aTime >= 0){
                if(isMovingLeft){

                    setImage(blueSlimeDead);
                }
                else if(!isMovingLeft){
                    setImage(blueSlimeDead);
                }
            }
            if( aTime == 50){
                getWorld().removeObject(this);
            }
        }
    }
    private void moving(){
        if (isMovingLeft) {
            if (canEntityMoveLeft(speed)) {
                moveLeft(speed);
                setImage(blueSlime);
            } else {
                isMovingLeft = false;
            }
        }
        if (!isMovingLeft) {
            if (canEntityMoveRight(speed)) {
                moveRight(speed);
                setImage(blueSlimeM);
            } else {
                isMovingLeft = true;
            }
        }
    }
}
