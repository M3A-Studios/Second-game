package game;

import greenfoot.GreenfootImage;
import game.Options;
import game.Physics;
import game.Camera;

/**
 *
 */
public class DesertStorm extends Physics {

    /** Boolean value for if the storm is going left or right */
    public static boolean movingLeft = true;
    /** Boolean value for if the storm is currently moving or not */
    public static boolean moving = false;
    /** Counts what frame of the animation we're on */
    private static int frame = 0;

    /**
     * Constructor method, sets the image and base values for movement and animation
     */
    public DesertStorm() {
        GreenfootImage image = new GreenfootImage ("desertstorm.png");
        image.scale(Options.screenWidth * 4, Options.screenHeight * 4);
        image.setTransparency(200);
        setImage(image);
        moving = false;
        movingLeft = true;
        frame = 0;
    }

    /**
     * Act method being called every frame to update the location and count the animation for when the storm should
     * start and switch directions etc.
     */
    public void act() {
        entityOffset();
        frame ++;
        setNewLocation(getDoubleX(), Options.screenHeight/2.0);
        if (frame == 1) {
            if (!movingLeft) {
                setNewLocation(Camera.scrolledX + Options.screenWidth*2, getDoubleY());
            } else {
                setNewLocation(Camera.scrolledX -Options.screenWidth, getDoubleY());
            }
            moving = false;
            getImage().setTransparency(0);
        }
        if (frame == 600) {
            moving = true;
            movingLeft = !movingLeft; //switch between true and false
        }
        if (frame >= 1500 && (getX() > Options.screenWidth * 2 || getX() < -Options.screenWidth)) {
            frame = 0;
        }
        if (moving) {
            getImage().setTransparency(100);
            if (movingLeft) {
                setRelativeLocation(-5,0);
            } else {
                setRelativeLocation(5, 0);
            }
        }
    }
}
