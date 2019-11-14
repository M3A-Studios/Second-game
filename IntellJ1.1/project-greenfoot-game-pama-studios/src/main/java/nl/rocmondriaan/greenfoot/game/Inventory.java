package nl.rocmondriaan.greenfoot.game;

import greenfoot.GreenfootImage;

public class Inventory extends Player
{
    GreenfootImage inventory = new GreenfootImage("glassPanel.png");

    private boolean started = false; //gets checked first frame to get the startX and startY
    private int startX; //gets set to where the heart was placed initially
    private int startY; //gets set to where the heart was placed initially


    Inventory() {
        inventory.scale((Options.blockSize),(Options.blockSize));
        setImage(inventory);
    }
    public void act() {
        //get start location if first frame
        if (!started) {
            startX = getX();
            startY = getY();
            started = true;
        } else { //set the heart to where it started to overwrite any kind of camera or entity offset (rlly shouldnt move as its a HUD)
            setLocation(startX, startY);
        }
    }
}
