package game.world.level;

import game.Camera;
import game.Globals;
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
        if (level == 0 || level > 3 && !(level >= 10 && level <= 12)) {
            image.scale(Options.screenWidth, Options.screenHeight);
        }
        setImage(image);
        this.level = level;
    }

    /**
     * Updates the location of the background every frame to make it static or move along based on what level
     */
    public void act() {
        if (level > 0 && (level <= 6 || (level >= 10 && level <= 12))) {
            if (!started) {
                getImage().scale(Globals.worldWidth, Globals.worldHeight);
                setLocation(Globals.worldWidth / 2 - Camera.scrolledX, Globals.worldHeight / 2 - Camera.scrolledY);
                started = true;
            }
        } else if (level == 0) {
            setLocation(Options.screenWidth / 2, Options.screenHeight / 2);
        } else {
            setLocation(Options.screenWidth / 2, Options.screenHeight / 2);
        }
    }
}