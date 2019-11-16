package nl.rocmondriaan.greenfoot.game;

import com.sun.xml.internal.bind.v2.model.core.ID;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Keys extends Actor
{
    static GreenfootImage blueKey = new GreenfootImage("185.png");
    static GreenfootImage greenKey = new GreenfootImage("186.png");
    static GreenfootImage redKey = new GreenfootImage("187.png");
    static GreenfootImage yellowKey = new GreenfootImage("188.png");

    /**
     * This is the constructor that should be used in every tile
     * All tiles should simply extend Blocks and call Super with the ID
     *
     * @param ID refers to what the ID of the tile is, used for the image
     */
    Keys(int ID) {
        if (ID == 185) {
            blueKey.scale(Options.blockSize, Options.blockSize);
            setImage(blueKey);
        } else if (ID == 186) {
            greenKey.scale(Options.blockSize, Options.blockSize);
            setImage(greenKey);
        } else if (ID == 187) {
            redKey.scale(Options.blockSize, Options.blockSize);
            setImage(redKey);
        } else if (ID == 188) {
            yellowKey.scale(Options.blockSize, Options.blockSize);
            setImage(yellowKey);
        }
    }
    public void act() {
        pickUpKey();
    }
    private void pickUpKey()
    {
        if(isTouching(Player.class))
        {
            String itemToPickup = "nothing";
            if(getImage() == blueKey)
            {
                itemToPickup = "blueKey";
            }
            if(getImage() == greenKey)
            {
                itemToPickup = "greenKey";
            }
            if(getImage() == redKey)
            {
                itemToPickup = "redKey";
            }
            if(getImage() == yellowKey)
            {
                itemToPickup = "yellowKey";
            }
            if (Player.inventoryItem.equals("") && !itemToPickup.equals("nothing") && Player.pickUpCooldown == 0) {
                Player.inventoryItem = itemToPickup;
                getWorld().removeObject(this);
                Player.pickUpCooldown = 120;
            }
        }
    }
}
