package game.world.level;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import game.Options;

/**
 * The Background class is to handle the background image of the world
 */
public class Background extends Actor {

    /** What level this background object is being made for */
    private int level;
    /** Boolean for if started yet so it can set the location */
    private boolean started = false;

    /**
     * Constructor to set the background image
     *
     * @param image     The image this background object should use
     * @param level     The level this object is made for
     */
    Background(GreenfootImage image, int level) {
        if (level > 3) {
            image.scale(Options.screenWidth, Options.screenHeight);
        }
        setImage(image);
    }

    /**
     * Updates the location of the background every frame to make it static or move along based on what level
     */
    public void act() {
        setLocation(Options.screenWidth/2, Options.screenHeight/2);
    }
}
