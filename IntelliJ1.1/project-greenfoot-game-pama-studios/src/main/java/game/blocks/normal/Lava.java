package game.blocks.normal;

import game.blocks.Blocks;

/**
 * Lava is a simple class that kills whatever touches it in Physics
 */
public class Lava extends Blocks {
    /**
     * Calls for the constructor in Blocks.java to set the image of the tile.
     * All of these images will be 1x1 in the grid. (Options.blockSize * Options.blockSize)
     *
     * @param ID    used to get what file should be displayed as the image of this object
     * @see Blocks#Blocks(int)
     */
    public Lava(int ID){
        super(ID);
    }
}
