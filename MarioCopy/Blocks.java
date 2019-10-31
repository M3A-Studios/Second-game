import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Blocks here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Blocks extends Actor
{
    Blocks(int ID){
        GreenfootImage image = new GreenfootImage (ID + ".png");
        image.scale((Options.blockSize),(Options.blockSize));
        setImage(image);
    }
}
