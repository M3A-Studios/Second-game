package game.blocks.special;

import greenfoot.GreenfootImage;
import game.Options;
import game.blocks.Blocks;
import game.entities.Player;

public class PSwitch extends Blocks
{
        private GreenfootImage pSwitchDown = new GreenfootImage("275.png");
        private GreenfootImage pSwitchUp = new GreenfootImage("274.png");
        private GreenfootImage box = new GreenfootImage("195.png");
        double eventTimer = 600;
    /**
     * This is the constructor that should be used in every tile
     * All tiles should simply extend Blocks and call Super with the ID
     *
     * @param ID refers to what the ID of the tile is, used for the image
     */
    public PSwitch(int ID) {
        super(ID);
        System.out.print(ID);
        if (ID == 275)
        {
            pSwitchUp.scale(Options.blockSize, Options.blockSize);
            setImage(pSwitchUp);
            pSwitchDown.scale(Options.blockSize, Options.blockSize);
        }
        if (ID == 195)
        {
            box.scale(Options.blockSize, Options.blockSize);
            setImage(box);
        }


    }

    public void act()
    {
        Switch();
        Event();
    }

        void Switch() {

            if (getImage() == pSwitchUp && isTouching(Player.class))
            {
                setImage(pSwitchDown);
            }
        }

        void Event()
        {
            if (getImage() == pSwitchDown && eventTimer > 0)
            {
                eventTimer --;
                if(getImage() == box)
                {

                    Coins coin = (Coins) getOneIntersectingObject(Coins.class);
                    if (coin.getValue() == 1)
                    {

                    }

                }
            }
        }
}
