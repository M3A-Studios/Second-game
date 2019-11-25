package game.world.level;

import game.Camera;
import game.Globals;
import greenfoot.Actor;
import greenfoot.GreenfootImage;
import game.Options;

public class Background extends Actor {

    private int level;
    private boolean started = false;

    Background(GreenfootImage image, int level) {
        if (level > 3) {
            image.scale(Options.screenWidth, Options.screenHeight);
        }
        setImage(image);
        this.level = level;
    }
    public void act() {
        if (level <= 3) {
            if (!started) {
                getImage().scale(Globals.worldWidth, Globals.worldHeight);
                setLocation(Globals.worldWidth / 2 - Camera.scrolledX, Globals.worldHeight / 2 - Camera.scrolledY);
                started = true;
            }
        } else {
            setLocation(Options.screenWidth / 2, Options.screenHeight / 2);
        }
    }
}
