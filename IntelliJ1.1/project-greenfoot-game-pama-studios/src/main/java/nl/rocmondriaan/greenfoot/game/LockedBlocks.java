package nl.rocmondriaan.greenfoot.game;

import com.sun.xml.internal.bind.v2.model.core.ID;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.List;
import java.util.concurrent.locks.Lock;

import static greenfoot.Greenfoot.delay;


public class LockedBlocks extends Blocks
{
    private static GreenfootImage blueBlock = new GreenfootImage("229.png");
    private static GreenfootImage greenBlock = new GreenfootImage("230.png");
    private static GreenfootImage redBlock = new GreenfootImage("231.png");
    private static GreenfootImage yellowBlock = new GreenfootImage("232.png");
    private String extend = "";
    private int frame = 0;
    private boolean delayed;

    LockedBlocks(int ID) {
        super(ID);
        if (ID ==  276) {
            extend = "blueBlock";
        }
        if (ID == 277) {
            extend = "greenBlock";
        }
        if (ID == 278) {
            extend = "redBlock";
        }
        if (ID == 279) {
            extend = "yellowBlock";
        }

        if (ID == 229)
        {
            blueBlock.scale(Options.blockSize, Options.blockSize);
            setImage(blueBlock);
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
        if (ID == 232)
        {
            yellowBlock.scale(Options.blockSize, Options.blockSize);
            setImage(yellowBlock);
        }
    }

    private void unlockExtendedBlock(String blockType) {
        List lockedBlocks = getObjectsInRange(Options.blockSize, LockedBlocks.class);
        getWorld().removeObject(this);
        for (Object lockedBlock : lockedBlocks) {
            if (lockedBlock instanceof LockedBlocks) {
                if (((LockedBlocks) lockedBlock).extend.equals(blockType)) {
                    ((LockedBlocks) lockedBlock).unlockExtendedBlock(blockType);
                }
            }
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
            frame = 0;
            unlockExtendedBlock(blockToRemove);
            Inventory.inventoryItem = "";
        }
    }
}


