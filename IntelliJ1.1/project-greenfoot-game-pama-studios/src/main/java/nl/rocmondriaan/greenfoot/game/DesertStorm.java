package nl.rocmondriaan.greenfoot.game;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class DesertStorm extends Actor {

    static boolean movingLeft = true;
    static boolean moving = false;
    private static int frame = 0;

    DesertStorm() {
        GreenfootImage image = new GreenfootImage ("100.png");
        image.scale(Options.screenWidth * 2, Options.screenHeight);
        image.setTransparency(100);
        setImage(image);
        moving = false;
        movingLeft = true;
        frame = 0;
    }
    public void act() {
        frame ++;
        setLocation(getX(), Options.screenHeight/2);
        if (frame == 1) {
            if (!movingLeft) {
                setLocation(Options.screenWidth*2, getY());
            } else {
                setLocation(-Options.screenWidth, getY());
            }
            moving = false;
            getImage().setTransparency(0);
        }
        if (frame == 600) {
            moving = true;
            movingLeft = !movingLeft; //switch between true and false
        }
        if (frame >= 1500) {
            frame = 0;
        }
        if (moving) {
            getImage().setTransparency(100);
            if (movingLeft) {
                setLocation(getX() - 5, getY());
            } else {
                setLocation(getX() + 5, getY());
            }
        }
    }
}
