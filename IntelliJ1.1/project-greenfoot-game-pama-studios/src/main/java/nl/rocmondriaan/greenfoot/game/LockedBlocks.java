package nl.rocmondriaan.greenfoot.game;

import com.sun.xml.internal.bind.v2.model.core.ID;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.concurrent.locks.Lock;


public class LockedBlocks extends Blocks
{
    static GreenfootImage blueBlock = new GreenfootImage("229.png");
    static GreenfootImage greenBlock = new GreenfootImage("230.png");
    static GreenfootImage redBlock = new GreenfootImage("231.png");
    static GreenfootImage yellowBlock = new GreenfootImage("232.png");
    static boolean canUnlock;


    /**
     * This is the constructor that should be used in every tile
     * All tiles should simply extend Blocks and call Super with the ID
     *
     * @param ID refers to what the ID of the tile is, used for the image
     */

    public void act()
    {
    }

    LockedBlocks(int ID)
    {
        super(ID);

        if (ID == 229)
        {
            blueBlock.scale(Options.blockSize, Options.blockSize);
            setImage(blueBlock);
        }
        if (ID == 232)
        {
            yellowBlock.scale(Options.blockSize, Options.blockSize);
            setImage(yellowBlock);
        }
        if (ID == 230)
        {
            greenBlock.scale(Options.blockSize, Options.blockSize);
            setImage(greenBlock);
        }
        if (ID == 231)
        {
            redBlock.scale(Options.blockSize, Options.blockSize);
            setImage(redBlock);
        }
    }
    void unlockBlock()
    {
        canUnlock = false;
        String blockToRemove = "";

        if(getImage() == blueBlock && Inventory.inventoryItem.equals("blueKey"))
        {
            blockToRemove = "blueBlock";
            canUnlock = true;
        }
        if(getImage() == greenBlock && Inventory.inventoryItem.equals("greenKey"))
        {
            blockToRemove = "greenBlock";
        }
        if(getImage() == redBlock && Inventory.inventoryItem.equals("redKey") )
        {
            blockToRemove = "redBlock";
        }
        if(getImage() == yellowBlock && Inventory.inventoryItem.equals("yellowKey"))
        {
            blockToRemove = "yellowBlock";
        }
        if (!Player.inventoryItem.equals("") && !blockToRemove.equals(""))
        {
            getWorld().removeObject(this);
            Inventory.inventoryItem = "";
        }
    }
}


