package nl.rocmondriaan.greenfoot.game;

import greenfoot.Greenfoot;

import greenfoot.*;

public class Lever extends Blocks
{
    // Animation en cd timers
    private int cooldownTimer = 0;
    private int animationTimer;

    // Lever Images
    GreenfootImage links = new GreenfootImage("233.png");
    GreenfootImage middle = new GreenfootImage ("234.png");
    GreenfootImage rechts = new GreenfootImage ("235.png");
    /**
     * Calls for the constructor in Blocks.java to set the image of the tile
     *
     * @param ID    ID used to get what image this block should use
     */
    Lever(int ID) {
        super(ID);
    }

    public void act()
    {
        // Methodes voor Animatie en lever systeem
        leverAnim();
        leverSys();

        //Timer
        if (cooldownTimer > 0)
        { cooldownTimer--; }

    }
    public void leverSys()
    {

        if (Greenfoot.isKeyDown(Options.interact))
        {
            animationTimer ++;
            if (cooldownTimer <= 0)
            {
                animationTimer = 0;
                setImage(middle);
                middle.scale((Options.blockSize),(Options.blockSize));
                cooldownTimer = 100;
            }
        }
    }
    public void leverAnim()
    {

        if (animationTimer== 30)
        {
            setImage(middle);
            middle.scale((Options.blockSize),(Options.blockSize));
        }
        if (animationTimer== 60)
        {
            setImage(rechts);
            rechts.scale((Options.blockSize),(Options.blockSize));
        }
    }
    public boolean isLinks()
    {
        if()
        {
            return true;
        }
        return false;
    }
}


