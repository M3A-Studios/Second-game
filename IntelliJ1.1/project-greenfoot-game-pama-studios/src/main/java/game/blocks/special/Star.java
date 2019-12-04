package game.blocks.special;

import game.blocks.Blocks;
import game.entities.Player;

public class Star extends Blocks
{
    /**
     * This is the constructor that should be used in every tile
     * All tiles should simply extend Blocks and call Super with the ID
     *
     * @param ID refers to what the ID of the tile is, used for the image
     */
    public Star(int ID) {
        super(ID);
    }
}
