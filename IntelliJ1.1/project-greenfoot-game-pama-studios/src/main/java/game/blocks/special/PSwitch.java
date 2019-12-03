package game.blocks.special;

import game.blocks.normal.BreakableBlocks;
import greenfoot.GreenfootImage;
import game.Options;
import game.blocks.Blocks;
import game.entities.Player;

import java.util.List;

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
        if (ID == 274)
        {
            setImage(pSwitchUp);
            pSwitchUp.scale(Options.blockSize, Options.blockSize);
        }
        if (ID == 275)
        {
            setImage(pSwitchUp);
            pSwitchUp.scale(Options.blockSize, Options.blockSize);
        }
        if (ID == 195)
        {
            box.scale(Options.blockSize, Options.blockSize);
            setImage(box);
        }
    }

    public void act()
    {
        if (getImage() == pSwitchDown)
        {
            if (eventTimer >= 0)
            {
                Event();
                System.out.print(eventTimer + ", ");
            }
        }
    }

    // Check if switch is up or down.
    public void Switch() {

        if (getImage() == pSwitchUp && isTouching(Player.class))
        {
            setImage(pSwitchDown);
            pSwitchDown.scale(Options.blockSize, Options.blockSize);
        }

    }

    //run the event where coins are replaced into blocks or blocks into coins.
    void Event()
    {
        if (getImage() == pSwitchDown && eventTimer >= 0)
        {
            eventTimer --;

            if (eventTimer == 599)
            {
                System.out.println("yes");
            List<BreakableBlocks> blocks = (List<BreakableBlocks>) (getWorld().getObjects(BreakableBlocks.class));
            for(BreakableBlocks block : blocks) {
                Coins coinToAdd = new Coins(166, false, block.id);
                coinToAdd.fromPSwitch = true;
                getWorld().addObject(coinToAdd, block.getX(), block.getY());
                getWorld().removeObject(block);
                }
            }
            if (eventTimer <= 0)
            {
                List<Coins> coins = (List<Coins>) (getWorld().getObjects(Coins.class));
                for(Coins coin : coins) {
                    if (coin.fromPSwitch) {
                        BreakableBlocks block = new BreakableBlocks(coin.fromBreakableBlockID);
                        getWorld().addObject(block, coin.getX(), coin.getY());
                        getWorld().removeObject(coin);
                    }
                }
            }
        }

    }
}