package game.blocks.special;

import greenfoot.GreenfootImage;
import game.GameLogic;
import game.Options;
import game.blocks.Blocks;
import game.entities.Player;
import greenfoot.GreenfootSound;

import java.util.ArrayList;
import java.util.List;


public class LockedBlocks extends Blocks {

    /** The image for the blue locked block */
    private static GreenfootImage blueBlock = new GreenfootImage("229.png");
    /** The image for the green locked block */
    private static GreenfootImage greenBlock = new GreenfootImage("230.png");
    /** The image for the red locked block */
    private static GreenfootImage redBlock = new GreenfootImage("231.png");
    /** The image for the yellow locked block */
    private static GreenfootImage yellowBlock = new GreenfootImage("232.png");
    /** Value for what color the block extends so if a block of that color next to it gets unlocked this unlocks */
    private String extend = "";
    /** Value for if the block has a lock or not */
    private String blockType;
    /** List of all blocks that need to be unlocked in the next frame */
    public static List<LockedBlocks> blocksToUnlock = new ArrayList<LockedBlocks>();
    //Sound effect
    static GreenfootSound openLockedBlock = new GreenfootSound("soundeffects/openLockedBlock.wav");

    /**
     * Constructor to set the image and what block it extends
     *
     * @param ID    ID to identify what kind of block it is
     */
    public LockedBlocks(int ID) {
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

    /**
     * Act method getting called every frame to check if it should unlock nearby blocks
     */
    public void act() {
        if (GameLogic.lockFrame >= 15) {
            GameLogic.lockFrame = 0;
            List<LockedBlocks> executing = new ArrayList<LockedBlocks>(blocksToUnlock);
            blocksToUnlock.clear();
            for (LockedBlocks lockedBlock : executing) {
                if (lockedBlock != null) {
                    lockedBlock.unlockExtendedBlock(lockedBlock.blockType);
                }
            }
        }
    }

    /**
     * Unlocks the nearby blocks of the selected blockType
     *
     * @param blockType     The color of block it should unlock
     */
    private void unlockExtendedBlock(String blockType) {
        List lockedBlocks = getObjectsInRange(Options.blockSize, LockedBlocks.class);
        getWorld().removeObject(this);
        for (Object lockedBlock : lockedBlocks) {
            LockedBlocks block = (LockedBlocks) lockedBlock;
            if (block != null) {
                if (block.extend.equals(blockType)) {
                    block.blockType = blockType;
                    blocksToUnlock.add(block);
                }
            }
        }
    }

    /**
     * Unlocks a block and makes a new list of all blocks to unlock in the next frames
     */
    public void unlockBlock()
    {
        String blockToRemove = "";

        if (getImage() == blueBlock && Player.inventoryItem.equals("blueKey")) {
            blockToRemove = "blueBlock";
        }
        if (getImage() == greenBlock && Player.inventoryItem.equals("greenKey")) {
            blockToRemove = "greenBlock";
        }
        if (getImage() == redBlock && Player.inventoryItem.equals("redKey")) {
            blockToRemove = "redBlock";
        }
        if (getImage() == yellowBlock && Player.inventoryItem.equals("yellowKey")) {
            blockToRemove = "yellowBlock";
        }
        if (!Player.inventoryItem.equals("") && !blockToRemove.equals("")) {
            unlockExtendedBlock(blockToRemove);
            Player.inventoryItem = "";
            openLockedBlock.setVolume(Options.soundeffectVolume);
            openLockedBlock.play();
        }
    }
}


