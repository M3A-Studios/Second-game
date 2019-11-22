package game.blocks.normal;

import game.blocks.Blocks;

/**
 * Ladders are objects that are climbable in the Physics class
 */
public class Ladder extends Blocks {
    /**
     * Calls for the constructor in Blocks.java to set the image of the tile.
     * All of these images will be 1x1 in the grid. (Options.blockSize * Options.blockSize)
     *
     * @param ID    used to get what file should be displayed as the image of this object
     * @see Blocks#Blocks(int)
     */
    public Ladder(int ID) {
        super(ID);
    }
}