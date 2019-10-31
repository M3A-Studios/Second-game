import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Camera extends Actor
{
    public static int playerX;
    public static int playerY;
    Camera() {
        setImage((GreenfootImage)null);
    }
    public void act() {
        setLocation(playerX, playerY);
    }
    public void scroll(int dsx, int dsy) {
    }
}
