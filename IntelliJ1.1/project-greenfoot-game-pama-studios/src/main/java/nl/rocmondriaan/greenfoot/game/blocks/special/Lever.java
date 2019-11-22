package nl.rocmondriaan.greenfoot.game.blocks.special;

import greenfoot.Greenfoot;

import greenfoot.*;
import nl.rocmondriaan.greenfoot.game.Options;
import nl.rocmondriaan.greenfoot.game.entities.Player;
import nl.rocmondriaan.greenfoot.game.blocks.Blocks;

import java.util.List;

public class Lever extends Blocks
{
    // Animation and cd timers
    int leverID = 1;
    private int cooldownTimer = 0;
    private int animationTimer;
    boolean isSwitched;


    // Lever Images
    private GreenfootImage left = new GreenfootImage("226.png");
    private GreenfootImage middle = new GreenfootImage ("227.png");
    private GreenfootImage right = new GreenfootImage ("228.png");
    /**
     * Calls for the constructor in Blocks.java to set the image of the tile
     *
     * @param ID    ID used to get what image this block should use
     */
    public Lever(int ID, int leverID) {
        super(ID);
        this.leverID = leverID;
        left.scale((Options.blockSize),(Options.blockSize));
        middle.scale((Options.blockSize),(Options.blockSize));
        right.scale((Options.blockSize),(Options.blockSize));
    }

    public void act()
    {
        // Methods for Lever Anim en sys.
        leverAnim();
        leverSys();

        //Timer
        if (cooldownTimer > 0)
        { cooldownTimer--; }

    }
    public void leverSys()
    {

        if (Greenfoot.isKeyDown(Options.interact)  && getOneIntersectingObject(Player.class) != null)
        {
            animationTimer ++;
            if (cooldownTimer <= 0)
            {
                animationTimer = 0;
                cooldownTimer = 100;
                if (getImage() == left) {

                    isSwitched = false;
                }
                if (getImage() == right)
                {
                    isSwitched = true;
                }
            }
        }
    }
    public void leverAnim()
    {

        if (!isSwitched)
        {
            if (animationTimer== 30)
        {
            setImage(middle);
        }
            if (animationTimer== 60)
            {
                setImage(right);
                switchdoor("open");
            }
        }

        if (isSwitched)
        {
            if (animationTimer== 30)
            {
                setImage(middle);
            }
            if (animationTimer== 60)
            {
                setImage(left);
                switchdoor("closed");
            }
        }

    }
    private void switchdoor(String switchTo) {
        //find all door objects into an array
        List<Door> deuren = (List<Door>) (getWorld().getObjects(Door.class));
        for(Door deur : deuren) {
            if (leverID == deur.doorID) {
                if (switchTo.equals("open")) {
                    deur.open();
                } else {
                    deur.close();
                }
            }
        }

    }

}


