package nl.rocmondriaan.greenfoot.game;

import com.sun.xml.internal.bind.v2.model.core.ID;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Keys extends Actor
{
    GreenfootImage emptyKey = new GreenfootImage("HudEmpty.png");
    GreenfootImage greenKey = new GreenfootImage("193.png");
    GreenfootImage blueKey = new GreenfootImage("192.png");

    /**
     * This is the constructor that should be used in every tile
     * All tiles should simply extend Blocks and call Super with the ID
     *
     * @param ID refers to what the ID of the tile is, used for the image
     */
    Keys(int ID) {
        greenKey.scale(Options.blockSize, Options.blockSize);
        setImage(greenKey);
        blueKey.scale(Options.blockSize, Options.blockSize);
        setImage(blueKey);
    }


    public void act() {
    pickUpKey();
    renderKeyHud();
    }
    private void pickUpKey()
    {
        if(isTouching(Player.class))
        {
            if(getImage() == greenKey)
            {
                Player.inventoryItem = "Key";
                Player.carryingObject = true;
                System.out.print(greenKey);
            }

        }
        else
        {
            Player.carryingObject = false;
        }
    }
    private void renderKeyHud()
    {
    }
}
