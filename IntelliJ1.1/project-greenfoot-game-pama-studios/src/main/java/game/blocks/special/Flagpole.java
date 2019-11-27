package game.blocks.special;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import game.Options;

public class Flagpole extends Actor {

    private String purpose;
    private int frame;
    /** The first image in the waving flag animation */
    private static GreenfootImage waving1 = new GreenfootImage("273.png");
    /** The second image in the waving flag animation */
    private static GreenfootImage waving2 = new GreenfootImage("170.png");
    /** The image for the pole of the flagpole */
    private static GreenfootImage pole = new GreenfootImage("272.png");

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
