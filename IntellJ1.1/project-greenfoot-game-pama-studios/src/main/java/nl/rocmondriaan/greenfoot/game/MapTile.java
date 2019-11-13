package nl.rocmondriaan.greenfoot.game;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

class MapTile extends Actor
{
    /**
     * Constructor method for the maptiles, simply handles what image the tile should have
     * not used together with Blocks.java due to weird file naming and not wanting to rename 200 files
     *
     * @param ID    ID used to get what image this block should use
     */
    MapTile(int ID) {
        GreenfootImage image;
        //get what image it should be
        if (ID < 10) image = new GreenfootImage ("mapTile_00" + (ID) + ".png");
        else if (ID < 100) image = new GreenfootImage ("mapTile_0" + (ID) + ".png");
        else image = new GreenfootImage ("mapTile_" + (ID) + ".png");
        image.scale((Options.blockSize),(Options.blockSize)); //scale image to 1x1 tile
        setImage(image); //set the image
    }
}
