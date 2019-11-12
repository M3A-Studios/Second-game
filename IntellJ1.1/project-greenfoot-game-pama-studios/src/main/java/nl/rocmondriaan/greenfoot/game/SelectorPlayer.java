package nl.rocmondriaan.greenfoot.game;
import greenfoot.*;
import nl.rocmondriaan.greenfoot.demo.DemoWorld;

import java.util.List;

public class SelectorPlayer extends Physics {

    private int timeTillInput;
    private boolean started = false;


    SelectorPlayer() {
        GreenfootImage image = new GreenfootImage ("mapTile_136.png");
        image.scale((Options.blockSize),(Options.blockSize));
        setImage(image);
    }
    public void act() {
        entityOffset();
        if (!started) {
            started = true;
            setDoubleX(getX());
            setDoubleY(getY());
        }
        if (Greenfoot.isKeyDown("d")) {
        }
        if (Greenfoot.isKeyDown("w")) {
        }
        if (Greenfoot.isKeyDown("s")) {
        }
        if (Greenfoot.isKeyDown("a")) {
        }
        if (Greenfoot.isKeyDown("enter") || Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(new Levels());
        }
    }
}
