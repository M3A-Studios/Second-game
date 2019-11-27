package game.blocks.special;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import game.Options;

/**
 *
 */
public class Door extends Actor {
    /** ID of the door to link it to the lever, if doorID and leverID are the same they are affected by eachother */
    int doorID;
    public boolean opened;
    private int imageID;

    /** The image for the top part of the door when it's open */
    private GreenfootImage topOpen = new GreenfootImage("217.png");
    /** The image for the bottom part of the door when it's open */
    private GreenfootImage bottomOpen = new GreenfootImage("216.png");
    /** The image for the top part of the door when it's closed */
    private GreenfootImage topClosed = new GreenfootImage("215.png");
    /** The image for the bottom part of the door when it's closed */
    private GreenfootImage bottomClosed = new GreenfootImage("214.png");

    /**
     * This is the constructor that should be used in every tile
     * All tiles should simply extend Blocks and call Super with the ID
     *
     * @param ID refers to what the ID of the tile is, used for the image
     */
    public Door(int ID, int doorID) {
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

    /**
     * Sets the door images to the open varients and sets {@link #opened} to true
     */
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

    /**
     * Sets the door images to closed varients and sets {@link #opened} to false
     */
    void close() {
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
