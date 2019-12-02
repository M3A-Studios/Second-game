package game.world.store;

import game.Options;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class StoreCross extends Actor {

    public StoreCross() {
        GreenfootImage image = new GreenfootImage("redcross.png");
        image.scale((int) ((Options.blockSize) * 7.5), (Options.blockSize) * 3);
        setImage(image);
    }
}
