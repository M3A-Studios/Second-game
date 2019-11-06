import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MapTile extends Actor
{
    private GreenfootImage image;
    MapTile(int ID) {
        if (ID < 10) image = new GreenfootImage ("mapTile_00" + (ID) + ".png");
        else if (ID < 100) image = new GreenfootImage ("mapTile_0" + (ID) + ".png");
        else image = new GreenfootImage ("mapTile_" + (ID) + ".png");
        image.scale((Options.blockSize),(Options.blockSize));
        setImage(image); 
    }
}
