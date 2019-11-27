package game.blocks.special;

import greenfoot.Greenfoot;

import greenfoot.*;
import game.Options;
import game.entities.Player;
import game.blocks.Blocks;

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
        leverAnimation();
        leverSystem();

        //Timer
        if (cooldownTimer > 0) {
            cooldownTimer--;
        }

    }

    /**
     * Method for switching the lever when holding down the interact button
     */
    private void leverSystem() {
        if (Greenfoot.isKeyDown(Options.interact)  && getOneIntersectingObject(Player.class) != null) {
            animationTimer ++;
            if (cooldownTimer <= 0) {
                animationTimer = 0;
                cooldownTimer = 100;
                if (getImage() == left) {
                    isSwitched = false;
                }
                if (getImage() == right) {
                    isSwitched = true;
                }
            }
        }
    }

    /**
     * Method to update the image when it's being switched around
     */
    private void leverAnimation() {
        if (!isSwitched) {
            if (animationTimer== 30) {
                setImage(middle);
            }
            if (animationTimer== 60) {
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

    /**
     * Method to switch all doors to whatever switchto says
     *
     * @param switchTo      What state the doors should be switched to, open or closed
     */
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


