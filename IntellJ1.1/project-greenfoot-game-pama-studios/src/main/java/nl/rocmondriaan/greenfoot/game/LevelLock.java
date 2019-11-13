package nl.rocmondriaan.greenfoot.game;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

class LevelLock extends Actor {
    /**
     * Constructor method used to set the image to what should be the image overlaid on locked levels
     * which are levels which are not yet unlocked through beating the last level or whatever condition.
     */
    LevelLock(){
        GreenfootImage image = new GreenfootImage ("lock.png");
        image.scale((Options.blockSize),(Options.blockSize)); //scale to the size of 1 block
        setImage(image);
    }
}
