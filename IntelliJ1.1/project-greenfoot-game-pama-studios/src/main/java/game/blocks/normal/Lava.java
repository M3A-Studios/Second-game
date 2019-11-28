package game.blocks.normal;

import game.Options;
import game.blocks.Blocks;
import greenfoot.GreenfootImage;

/**
 * Lava is a simple class that kills whatever touches it in Physics
 */
public class Lava extends Blocks {
    private String type = "";
    private int aTime;
    private GreenfootImage lavaLow1 = new GreenfootImage("225.png");
    private GreenfootImage lavaLow2 = new GreenfootImage("225.png");
    private GreenfootImage lavaHigh1 = new GreenfootImage("224.png");
    private GreenfootImage lavaHigh2 = new GreenfootImage("224.png");
    private GreenfootImage lava1 = new GreenfootImage("223.png");
    private GreenfootImage lava2 = new GreenfootImage("223.png");

    /**
     * Calls for the constructor in Blocks.java to set the image of the tile.
     * All of these images will be 1x1 in the grid. (Options.blockSize * Options.blockSize)
     *
     * @param ID    used to get what file should be displayed as the image of this object
     * @see Blocks#Blocks(int)
     */
    public Lava(int ID){
        super(ID);
        if (ID == 225) {
            this.type = "lavaLow";
        }
        if (ID == 224) {
            this.type = "lavaHigh";
        }
        if (ID == 223) {
            this.type = "lava";
        }

        lavaLow1.scale(Options.blockSize,Options.blockSize);
        lavaLow2.scale(Options.blockSize,Options.blockSize);
        lavaHigh1.scale(Options.blockSize,Options.blockSize);
        lavaHigh2.scale(Options.blockSize,Options.blockSize);
        lava1.scale(Options.blockSize,Options.blockSize);
        lava2.scale(Options.blockSize,Options.blockSize);
        lavaHigh2.mirrorHorizontally();
        lavaLow2.mirrorHorizontally();
        lava2.mirrorHorizontally();
    }

    public void act(){
        animateLava();
    }

    private void animateLava(){
        aTime++;
        if(aTime > 240){aTime = 0;}
        if(aTime == 120){
            if(type.equals("lavaLow")){
                this.setImage(lavaLow1);
            }
            if(type.equals("lavaHigh")){
                this.setImage(lavaHigh1);
            }
            if(type.equals("lava")){
                this.setImage(lava1);
            }
        }
        if(aTime == 240){
            if(type.equals("lavaLow")){
                this.setImage(lavaLow2);
            }
            if(type.equals("lavaHigh")){
                this.setImage(lavaHigh2);
            }
            if(type.equals("lava")){
                this.setImage(lava2);
            }
        }
    }
}
