package nl.rocmondriaan.greenfoot.game;


public class Lever extends Blocks
{
    /**
     * Calls for the constructor in Blocks.java to set the image of the tile
     *
     * @param ID    ID used to get what image this block should use
     */
    private int cdTimer = 100;

    Lever(int ID) {
        super(ID);
    }

    public void act()
    {
        if (cdTimer == 0)
        {
            if (Greenfoot.isKeyDown(Options.interact))

        }
        else
        {
            System.out.print(cdTimer + ", ");
            cdTimer --;
        }

    }

}


