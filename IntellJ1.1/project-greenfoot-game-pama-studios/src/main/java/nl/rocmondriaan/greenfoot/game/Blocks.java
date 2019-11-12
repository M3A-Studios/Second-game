package nl.rocmondriaan.greenfoot.game;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Blocks extends Actor
{
    Blocks(int ID){
        GreenfootImage image = new GreenfootImage ((ID + 7) + ".png");
        image.scale((Options.blockSize),(Options.blockSize));
        setImage(image);
    }
}
