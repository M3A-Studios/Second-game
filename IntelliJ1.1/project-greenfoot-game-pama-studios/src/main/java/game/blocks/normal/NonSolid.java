package game.blocks.normal;

import game.blocks.Blocks;

/**
 * NonSolids are blocks that do not have any physics to them, any entity can walk right through them.
 */
public class NonSolid extends Blocks {
    /**
     * Calls for the constructor in Blocks.java to set the image of the tile.
     * All of these images will be 1x1 in the grid. (Options.blockSize * Options.blockSize)
     *
     * @param ID    used to get what file should be displayed as the image of this object
     * @see Blocks#Blocks(int)
     */
    public NonSolid(int ID) {
        super(ID);
    }
}
