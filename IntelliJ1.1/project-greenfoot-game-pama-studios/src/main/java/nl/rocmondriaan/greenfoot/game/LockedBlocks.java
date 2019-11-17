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
    static GreenfootImage blueBlockE = new GreenfootImage("276.png");
    static GreenfootImage greenBlockE = new GreenfootImage("277.png");
    static GreenfootImage redBlockE = new GreenfootImage("278.png");
    static GreenfootImage yellowBlockE = new GreenfootImage("279.png");

    /**
     * This is the constructor that should be used in every tile
     * All tiles should simply extend Blocks and call Super with the ID
     *
     * @param ID refers to what the ID of the tile is, used for the image
     */
    LockedBlocks(int ID)
    {
        super(ID);

        if (ID == 229)
        {
            blueBlock.scale(Options.blockSize, Options.blockSize);
            setImage(blueBlock);
        }
        if (ID == 276) //Empty block
        {
            blueBlockE.scale(Options.blockSize, Options.blockSize);
            setImage(blueBlockE);
        }
        if (ID == 232)
        {
            yellowBlock.scale(Options.blockSize, Options.blockSize);
            setImage(yellowBlock);
        }
        if (ID == 277) //Empty block
        {
            yellowBlockE.scale(Options.blockSize, Options.blockSize);
            setImage(yellowBlockE);
        }
        if (ID == 230)
        {
            greenBlock.scale(Options.blockSize, Options.blockSize);
            setImage(greenBlock);
        }
        if (ID == 278) //Empty block
        {
            greenBlockE.scale(Options.blockSize, Options.blockSize);
            setImage(greenBlockE);
        }
        if (ID == 231)
        {
            redBlock.scale(Options.blockSize, Options.blockSize);
            setImage(redBlock);
        }
        if (ID == 279) //Empty block
        {
            redBlockE.scale(Options.blockSize, Options.blockSize);
            setImage(redBlockE);
        }

    }
    void unlockBlock()
    {
        String blockToRemove = "";
        if(getImage() == blueBlock && Inventory.inventoryItem.equals("blueKey"))
        {
            blockToRemove = "blueBlock";
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


