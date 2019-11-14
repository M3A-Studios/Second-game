package nl.rocmondriaan.greenfoot.game;

import com.sun.xml.internal.bind.v2.model.core.ID;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Keys extends Blocks
{

    boolean carryingObject = false;

    private int greenID = 2;

    GreenfootImage emptyKey = new GreenfootImage("HudEmpty.png");
    GreenfootImage greenKey = new GreenfootImage("193.png");

    /**
     * This is the constructor that should be used in every tile
     * All tiles should simply extend Blocks and call Super with the ID
     *
     * @param ID refers to what the ID of the tile is, used for the image
     */
    Keys(int ID) {
        super(ID);
    }


    public void act() {
    pickUpKey();
    renderKeyHud();
    }
    private void pickUpKey()
    {
        if(isTouching(Player.class))
        {
            getWorld().removeObject(this);
            carryingObject = true;
        }
        else
        {
            carryingObject = false;
        }
    }
    private void renderKeyHud()
    {
    }
}
