import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    }
}