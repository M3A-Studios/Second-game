package game.blocks.normal;

import game.blocks.Blocks;

/**
 * Solid blocks have a collision check in Physics that disallows entities from going through them if the
 * canMoveLeft(double speed) or canMoveRight(double speed) are used, also used for willBumpHead() and onGround().
 */
public class Solid extends Blocks {

    /**
     * Calls for the constructor in Blocks.java to set the image of the tile.
     * All of these images will be 1x1 in the grid. (Options.blockSize * Options.blockSize)
     *
     * @param ID    used to get what file should be displayed as the image of this object
     * @see Blocks#Blocks(int)
     */
    public Solid(int ID) {
        super(ID);
    }
}
