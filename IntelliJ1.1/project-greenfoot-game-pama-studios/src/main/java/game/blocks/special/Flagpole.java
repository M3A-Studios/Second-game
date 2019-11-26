package game.blocks.special;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import game.Options;

/**
 * Flagpole is an object that starts the winninganimation when touched. This results in the player clearing the level
 */
public class Flagpole extends Actor {

    /** String value to tell the game if this is the top of the pole or not */
    private String purpose;
    /** Animation timer to see when to switch between {@link #waving1} and {@link #waving2} */
    private int frame;
    /** The first image in the waving flag animation */
    private static GreenfootImage waving1 = new GreenfootImage("273.png");
    /** The second image in the waving flag animation */
    private static GreenfootImage waving2 = new GreenfootImage("170.png");
    /** The image for the pole of the flagpole */
    private static GreenfootImage pole = new GreenfootImage("272.png");

    /**
     * Sets the image and the {@link #purpose} of the flagpole part
     *
     * @param ID    Determines what purpose it serves, 273 = top, 272 or others are pole
     */
    public Flagpole(int ID) {
        waving1.scale(Options.blockSize, Options.blockSize);
        waving2.scale(Options.blockSize, Options.blockSize);
        pole.scale(Options.blockSize, Options.blockSize);
        if (ID == 273) {
            purpose = "top";
            setImage(waving1);
        } else {
            purpose = "pole";
            setImage(pole);
        }
    }

    /**
     * Act method gets called every frame, used to switch between {@link #waving1} and {@link #waving2} images o animate
     * the waving of the flag every 15 frames
     */
    public void act() {
        if (purpose.equals("top")) {
            frame++;
            if (frame == 1) {
                setImage(waving1);
            } else if (frame == 15) {
                setImage(waving2);
            } else if (frame >= 30) {
                frame = 0;
            }
        }
    }
}
