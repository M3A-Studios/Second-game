package game.blocks.normal;

import game.blocks.Blocks;

/**
 * BreakableBlocks are blocks that can be broken by bombs.
 */
public class BreakableBlocks extends Solid {
    /**
     * Calls for the constructor in Blocks.java to set the image of the tile.
     * All of these images will be 1x1 in the grid. (Options.blockSize * Options.blockSize)
     *
     * @param ID    used to get what file should be displayed as the image of this object
     * @see Blocks#Blocks(int)
     */
    public BreakableBlocks(int ID){
        super(ID);
    }
}
