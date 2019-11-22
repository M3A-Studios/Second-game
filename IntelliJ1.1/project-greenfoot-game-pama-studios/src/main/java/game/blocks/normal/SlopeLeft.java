package game.blocks.normal;

import game.blocks.Blocks;

/**
 * Left slopes are blocks that have a triangle shape to the left, Entities can only walk over a line between the
 * top left and bottom right of the block and otherwise are not treated as being on ground
 */
public class SlopeLeft extends Blocks {
    /**
     * Calls for the constructor in Blocks.java to set the image of the tile.
     * All of these images will be 1x1 in the grid. (Options.blockSize * Options.blockSize)
     *
     * @param ID    used to get what file should be displayed as the image of this object
     * @see Blocks#Blocks(int)
     */
    public SlopeLeft(int ID) {
        super(ID);
    }
}
