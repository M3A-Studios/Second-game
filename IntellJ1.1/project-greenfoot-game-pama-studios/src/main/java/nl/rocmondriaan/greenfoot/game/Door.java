package nl.rocmondriaan.greenfoot.game;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Door extends Actor
{

    int doorID;
    boolean opened;
    private int imageID;

    private GreenfootImage topOpen = new GreenfootImage("224.png");
    private GreenfootImage bottomOpen = new GreenfootImage("223.png");
    private GreenfootImage topClosed = new GreenfootImage("222.png");
    private GreenfootImage bottomClosed = new GreenfootImage("221.png");

    /**
     * This is the constructor that should be used in every tile
     * All tiles should simply extend Blocks and call Super with the ID
     *
     * @param ID refers to what the ID of the tile is, used for the image
     */
    Door(int ID, int doorID) {
        this.doorID = doorID;
        imageID = ID;
        topOpen.scale((Options.blockSize),(Options.blockSize));
        bottomOpen.scale((Options.blockSize),(Options.blockSize));
        topClosed.scale((Options.blockSize),(Options.blockSize));
        bottomClosed.scale((Options.blockSize),(Options.blockSize));
        if (ID == 214) {
            setImage(bottomClosed);
            opened = false;

        }
        if (ID == 215) {
            setImage(topClosed);
            opened = false;
        }
        if (ID == 216)
        {
            setImage(bottomOpen);
            opened = true;
        }
        if (ID == 217)
        {
            setImage(topOpen);
            opened = true;
        }


    }
    public void act() {
    }
    void open() {
        if (imageID == 215) {
            setImage(topOpen);
            imageID = 217;
        }
        if (imageID == 214) {
            setImage(bottomOpen);
            imageID = 216;
        }
        opened = true;
    }
    void close()
    {
        if (imageID == 217) {
            setImage(topClosed);
            imageID = 215;
        }
        if (imageID == 216) {
            setImage(bottomClosed);
            imageID = 214;
        }
        opened = false;
    }
}
