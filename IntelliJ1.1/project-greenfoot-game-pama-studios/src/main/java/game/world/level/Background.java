package game.world.level;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import game.Options;

public class Background extends Actor {

    Background(GreenfootImage image) {
        image.scale(Options.screenWidth, Options.screenHeight);
        setImage(image);
    }
    public void act() {
        setLocation(Options.screenWidth/2, Options.screenHeight/2);
    }
}
