package game.blocks.normal;

import game.Camera;
import game.Options;
import game.blocks.Blocks;

/**
 * Spikes damage any player that walks over them and gives a slight invulnerability time frame
 */
public class Spikes extends Blocks {
    private String type = "";
    private int ID;
    private double doubleX;
    private double doubleY;
    private boolean started = false;
    /**
     * Calls for the constructor in Blocks.java to set the image of the tile.
     * All of these images will be 1x1 in the grid. (Options.blockSize * Options.blockSize)
     *
     * @param ID    used to get what file should be displayed as the image of this object
     * @see Blocks#Blocks(int)
     */
    public Spikes(int ID){
        super(ID);
        if (ID == 262 || ID == 316 || ID == 317 || ID == 318) { //floor spikes
            this.type = "floorSpike";
            this.getImage().scale(Options.blockSize, Options.blockSize / 2);
        }
        if (ID == 263) { //left wall spikes
            this.type = "leftSpikes";
            this.getImage().scale(Options.blockSize / 2, Options.blockSize);
        }
        if (ID == 264 || ID == 319 || ID == 320 || ID == 321 || ID == 322) { //roof spikes
            this.type = "roofSpike";
            if (ID == 322){
                this.getImage().scale(Options.blockSize / 2, Options.blockSize / 2);
            } else {
                this.getImage().scale(Options.blockSize, Options.blockSize / 2);
            }
        }
        if (ID == 265) { //right wall spikes
            this.type = "rightSpike";
            this.getImage().scale(Options.blockSize / 2, Options.blockSize);
        }
    }
    public void act() {
        if (!started) {
            started = true;
            if (type.equals("roofSpike")) {
                setLocation(getX(), getY() - Options.blockSize / 4);
            } else if (type.equals("floorSpike")) {
                setLocation(getX(),getY() + Options.blockSize / 4);
            } else if (type.equals("leftSpikes")) {
                setLocation(getX() - Options.blockSize / 4,getY());
            } else if (type.equals("rightSpike")) {
                setLocation(getX() + Options.blockSize / 4, getY() );
            }
        }
    }
}