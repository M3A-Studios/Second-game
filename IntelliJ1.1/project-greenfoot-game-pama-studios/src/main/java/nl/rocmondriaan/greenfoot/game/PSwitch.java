package nl.rocmondriaan.greenfoot.game;

import greenfoot.GreenfootImage;

public class PSwitch extends Blocks
{
        private GreenfootImage pSwitchDown = new GreenfootImage("275.png");
        private GreenfootImage pSwitchUp = new GreenfootImage("274.png");
        double eventTimer = 600;
    /**
     * This is the constructor that should be used in every tile
     * All tiles should simply extend Blocks and call Super with the ID
     *
     * @param ID refers to what the ID of the tile is, used for the image
     */
    PSwitch(int ID) {
        super(ID);
        System.out.print(ID);
        pSwitchDown.scale(Options.blockSize, Options.blockSize);
        setImage(pSwitchUp);
        pSwitchUp.scale(Options.blockSize, Options.blockSize);
    }

    public void act()
    {
        jumpPad();
        Event();
    }

        void jumpPad() {

            if (isTouching(Player.class))
            {
                setImage(pSwitchDown);
            }
        }
        void Event()
        {
            if (getImage() == pSwitchDown && eventTimer > 0)
            {
                eventTimer --;
                System.out.print("Event time!" + "," + eventTimer);
            }
        }
}
