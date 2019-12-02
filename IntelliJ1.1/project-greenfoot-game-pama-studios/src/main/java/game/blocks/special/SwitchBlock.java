package game.blocks.special;

import game.Options;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.List;

public class SwitchBlock extends Actor
{
    private GreenfootImage switchOff = new GreenfootImage("282.png");
    private GreenfootImage switchOn = new GreenfootImage("283.png");
    private GreenfootImage redBlockOff = new GreenfootImage("284.png");
    private GreenfootImage redBlockOn = new GreenfootImage("285.png");
    private GreenfootImage blueBlockOn = new GreenfootImage("359.png");
    private GreenfootImage blueBlockOff = new GreenfootImage("360.png");

    private int activationTimer = 30;
    public static boolean switchActive = false;
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
        switchActive = false;
        switchOff.scale(Options.blockSize,Options.blockSize);
        switchOn.scale(Options.blockSize,Options.blockSize);
        redBlockOff.scale(Options.blockSize,Options.blockSize);
        redBlockOn.scale(Options.blockSize,Options.blockSize);
        blueBlockOff.scale(Options.blockSize,Options.blockSize);
        blueBlockOn.scale(Options.blockSize,Options.blockSize);
        if (ID == 282 || ID == 283) //switch
        {
            setImage(switchOff);
            this.purpose = "switch";
        }
        if(ID == 284 || ID == 285) //red block
        {
            setImage(redBlockOff);
            this.purpose = "red";
        }
        if (ID == 359 || ID == 360) //blue block
        {
            setImage(blueBlockOn);
            this.purpose = "blue";
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
        switchActive = active;

        List<SwitchBlock> switchBlockerus = (List<SwitchBlock>) (getWorld().getObjects(SwitchBlock.class));
        for(SwitchBlock block : switchBlockerus) {
            if (!block.purpose.equals("switch")) {
                if (active) {
                    if (block.purpose.equals("red")) {
                        block.setImage(redBlockOn);
                    } else if (block.purpose.equals("blue")) {
                        block.setImage(blueBlockOff);
                    }
                } else {
                    if (block.purpose.equals("red")) {
                        block.setImage(redBlockOff);
                    } else if (block.purpose.equals("blue")) {
                        block.setImage(blueBlockOn);
                    }
                }
            } else {
                if (active) {
                    block.setImage(switchOn);
                } else {
                    block.setImage(switchOff);
                }
            }
        }
    }
}
