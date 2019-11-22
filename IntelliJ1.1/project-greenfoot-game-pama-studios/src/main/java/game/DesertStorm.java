package game;

import greenfoot.GreenfootImage;
import game.Options;
import game.Physics;
import game.Camera;

public class DesertStorm extends Physics {

    public static boolean movingLeft = true;
    public static boolean moving = false;
    private static int frame = 0;

    public DesertStorm() {
        GreenfootImage image = new GreenfootImage ("100.png");
        image.scale(Options.screenWidth * 2, Options.screenHeight);
        image.setTransparency(100);
        setImage(image);
        moving = false;
        movingLeft = true;
        frame = 0;
    }
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
