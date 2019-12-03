package game.blocks.special;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import game.Options;
import game.entities.Player;

/**
 *
 */
public class Keys extends Actor {

    /** The image for the blue key */
    public static GreenfootImage blueKey = new GreenfootImage("185.png");
    public static GreenfootImage greenKey = new GreenfootImage("186.png");
    public static GreenfootImage redKey = new GreenfootImage("187.png");
    public static GreenfootImage yellowKey = new GreenfootImage("188.png");

    /**
     * This is the constructor that should be used in every tile
     * All tiles should simply extend Blocks and call Super with the ID
     *
     * @param ID refers to what the ID of the tile is, used for the image
     */
    public Keys(int ID) {
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
        Player player = (Player) getOneIntersectingObject(Player.class);
        if (player != null) {
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
            if (!player.lastDroppedItem.equals(itemToPickup) || player.lastItemCD <= 0) {
                if (Player.inventoryItem.equals("") && !itemToPickup.equals("nothing") && player.pickUpCooldown == 0)
                {
                    Player.inventoryItem = itemToPickup;
                    getWorld().removeObject(this);
                    player.pickUpCooldown = 120;
                }
                player.lastItemCD = 30;
            }
        }
    }
}
