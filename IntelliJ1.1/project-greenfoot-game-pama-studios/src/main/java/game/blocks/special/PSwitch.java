package game.blocks.special;

import greenfoot.GreenfootImage;
import game.Options;
import game.blocks.Blocks;
import game.entities.Player;

public class PSwitch extends Blocks {

    /** The image for when the pSwitch is down */
    private GreenfootImage pSwitchDown = new GreenfootImage("275.png");
    /** The image for when the pSwitch is up */
    private GreenfootImage pSwitchUp = new GreenfootImage("274.png");
    /** The image for the block that will change into coins */
    private GreenfootImage box = new GreenfootImage("195.png");
    /** How long an event should last in frames */
    double eventTimer = 600;

    /**
     * Calls for the constructor in Blocks.java to set the image of the tile.
     * All of these images will be 1x1 in the grid. (Options.blockSize * Options.blockSize)
     *
     * @param ID    used to get what file should be displayed as the image of this object
     * @see Blocks#Blocks(int)
     */
    public PSwitch(int ID) {
        super(ID);
        System.out.print(ID);
        if (ID == 275)
        {
            pSwitchUp.scale(Options.blockSize, Options.blockSize);
            setImage(pSwitchUp);
            pSwitchDown.scale(Options.blockSize, Options.blockSize);
        }
        if (ID == 195)
        {
            box.scale(Options.blockSize, Options.blockSize);
            setImage(box);
        }


    }

    /**
     * Simple act method getting called every frame to
     */
    public void act()
    {
        Switch();
        Event();
    }

    void Switch() {

        if (getImage() == pSwitchUp && isTouching(Player.class))
        {
            setImage(pSwitchDown);
        }
    }

    void Event()
    {
        if (getImage() == pSwitchDown && eventTimer > 0)
        {
            eventTimer --;
            if(getImage() == box)
            {

            }
        }
    }
}