package game.blocks.special;

import game.Options;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.List;

public class SwitchBlock extends Actor
{
    private GreenfootImage switchOff = new GreenfootImage("282.png");
    private GreenfootImage switchOn = new GreenfootImage("283.png");
    private GreenfootImage blockOff = new GreenfootImage("284.png");
    private GreenfootImage blockOn = new GreenfootImage("285.png");
    private int activationTimer = 30;
    public static boolean isSolid;
    public String purpose = "block";

    public void act()
    {
        if(activationTimer >= 0)
        {
            activationTimer --;
        }
    }
    /**
     * This is the constructor that should be used in every tile
     * All tiles should simply extend Blocks and call Super with the ID
     *
     * @param ID refers to what the ID of the tile is, used for the image
     */

    public SwitchBlock(int ID) {
        switchOff.scale(Options.blockSize,Options.blockSize);
        switchOn.scale(Options.blockSize,Options.blockSize);
        blockOff.scale(Options.blockSize,Options.blockSize);
        blockOn.scale(Options.blockSize,Options.blockSize);
        if (ID == 282)
        {
            setImage(switchOff);
            this.purpose = "switch";
        }
        if(ID == 283)
        {
            setImage(switchOn);
            this.purpose = "switch";
        }
        if(ID == 284)
        {
            setImage(blockOff);
        }
        if (ID == 285)
        {
            setImage(blockOn);
        }
    }
    public void switchBlock()
    {
        if (activationTimer <= 0)
        {
            if (getImage() == switchOff)
            {
                setImage(switchOn);
                activationTimer = 30;
                switchBlocksTo(true);
            } else if (getImage() == switchOn) {
                setImage(switchOff);
                activationTimer = 30;
                switchBlocksTo(false);
            }
        }
    }
    private void switchBlocksTo(boolean active) {
        isSolid = active;

        List<SwitchBlock> switchBlockerus = (List<SwitchBlock>) (getWorld().getObjects(SwitchBlock.class));
        for(SwitchBlock block : switchBlockerus) {
            if (!(block.getImage() == switchOn) && !(block.getImage() == switchOff)) {
                if (active) {
                    block.setImage(blockOn);
                } else {
                    block.setImage(blockOff);
                }
            }
        }
    }
}

