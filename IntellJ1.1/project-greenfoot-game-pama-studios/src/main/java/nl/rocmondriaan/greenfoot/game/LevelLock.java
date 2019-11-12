package nl.rocmondriaan.greenfoot.game;

import greenfoot.*;

public class LevelLock extends Actor {
    LevelLock(){
        GreenfootImage image = new GreenfootImage ("lock.png");
        image.scale((Options.blockSize),(Options.blockSize));
        setImage(image);
    }
}
