package game.blocks.special;

import game.blocks.Blocks;

public class Coins extends Blocks {

    /** Represents how many coins this coin object is worth*/
    private int value;

    /**
     * Calls for the constructor in Blocks.java to set the image of the tile.
     * All of these images will be 1x1 in the grid. (Options.blockSize * Options.blockSize)
     *
     * @param ID    used to get what file should be displayed as the image of this object
     * @see Blocks#Blocks(int)
     */
    public Coins(int ID){
        super(ID);
        switch(ID) {
            case 166:
                this.value = 1; //bronze
                break;
            case 167:
                this.value = 20; //gold
                break;
            case 168:
                this.value = 10; //silver
                break;
            default:
        }
    }

    /**
     * Gets the value of the coin as integer
     *
     * @return returns the {@link Coins#value} of the coin
     * @see Coins#value
     */
    public int getValue() {
        return value;
    }
}
