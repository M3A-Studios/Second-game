package game.blocks.normal;

import game.blocks.Blocks;

/**
 * Spikes damage any player that walks over them and gives a slight invulnerability time frame
 */
public class Spikes extends Blocks {
    /**
     * Calls for the constructor in Blocks.java to set the image of the tile.
     * All of these images will be 1x1 in the grid. (Options.blockSize * Options.blockSize)
     *
     * @param ID    used to get what file should be displayed as the image of this object
     * @see Blocks#Blocks(int)
     */
    public Spikes(int ID){
        super(ID);
    }
}
