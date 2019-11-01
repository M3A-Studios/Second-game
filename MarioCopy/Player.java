import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Physics
{
    private int imageWidth;
    private int imageHeight;
    private int moveDelay = 0;

    Player() {
        GreenfootImage image = new GreenfootImage ("alienGreen_front.png");
        image.scale((Options.blockSize),(Options.blockSize));
        setImage(image);
    }   
    public void act() 
    {
        moveAround();
        checkFalling();
        fall();
        jump();
        climbing();
        Camera.playerX = getX();
        Camera.playerY = getY();
    }
}