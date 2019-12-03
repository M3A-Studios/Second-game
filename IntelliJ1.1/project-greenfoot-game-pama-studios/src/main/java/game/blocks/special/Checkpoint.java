package game.blocks.special;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import game.world.level.Levels;
import game.Options;

public class Checkpoint extends Actor {

    /** Keeps track of what checkpoint this is in the level, the first checkpoint would have a value of 1
     * the second checkpoint a value of 2 etc.
     */
    private int checkpoint;
    /** Boolean for if the checkpoint is active, used for the animation */
    private boolean active;
    /** Animation timer to see when to switch between {@link #activeImage1} and {@link #activeImage2} */
    private int animationTimer;
    /** The first image in the active checkpoint animation */
    private GreenfootImage activeImage1;
    /** The second image in the active checkpoint animation */
    private GreenfootImage activeImage2;

    /**
     * Constructor of the checkpoint class to set what image it should use when inactive, active and scaling these.
     *
     * @param checkpoint    sets what checkpoint this in the level (1, 2 or 3 etc.)
     * @param ID            used to know what images should be used for the checkpoint.
     */
    public Checkpoint(int checkpoint, int ID) {
        int imageID;
        switch(ID) {
            case 171:
            case 172:
            case 173:
                imageID = 174; //check green
                break;
            case 174:
            case 175:
            case 176:
                imageID = 177; //check red
                break;
            case 177:
            case 178:
            case 179:
                imageID = 180; //check yellow
                break;
            default:
                imageID = 171; //default blue
        }
        activeImage1 = new GreenfootImage((imageID - 2) + ".png");
        activeImage2 = new GreenfootImage((imageID - 1) + ".png");
        if (Levels.activeCheckpoint == checkpoint) {
            imageID -= 2;
            active = true;
        }
        GreenfootImage image = new GreenfootImage (imageID + ".png");
        image.scale((Options.blockSize),(Options.blockSize)); //scale to the size of 1 block
        activeImage1.scale((Options.blockSize),(Options.blockSize)); //scale to the size of 1 block
        activeImage2.scale((Options.blockSize),(Options.blockSize)); //scale to the size of 1 block
        setImage(image);
        this.checkpoint = checkpoint;
    }

    /**
     * Simple act method which gets executed every frame to animate the flag if the checkpoint is active
     */
    public void act() {
        if (active) {
            animationTimer++;
            if (animationTimer >= 30) animationTimer = 0;
            if (animationTimer == 0) {
                setImage(activeImage1);
            } else if (animationTimer == 15) {
                setImage(activeImage2);
            }

        }
    }

    /**
     * Simple method to return what checkpoint this is. (so checkpoint 1, 2 or 3 etc. in the level)
     *
     * @return      returns what checkpoint this is, see {@link #checkpoint}
     */
    public int getCheckpoint() {
        return checkpoint;
    }
    public void setActiveCheckpoint(boolean active) {
        this.active = active;
    }
}
