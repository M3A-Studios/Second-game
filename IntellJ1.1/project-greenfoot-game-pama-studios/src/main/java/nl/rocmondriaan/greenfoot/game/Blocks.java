package nl.rocmondriaan.greenfoot.game;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import nl.rocmondriaan.greenfoot.engine.*;

public class Blocks extends Mover
{
    Blocks(int ID){
        GreenfootImage image = new GreenfootImage ((ID + 7) + ".png");
        image.scale((Options.blockSize),(Options.blockSize));
        setImage(image);
    }
}
