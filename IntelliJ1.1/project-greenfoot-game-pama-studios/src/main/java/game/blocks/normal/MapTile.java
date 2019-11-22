package game.blocks.normal;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import game.Options;

/**
 * MapTiles are just simple images of 1x1 in the grid with no physics, they're purely there for the cosmetics and
 * do absolutely nothing anywhere.
 */
public class MapTile extends Actor {
    /**
     * Gets an ID to use for what image the tile should be, tiles are 1by1 as in Options.blockSize x Options.blockSize
     *
     * @param ID    ID used to get what image this block should use
     */
    public MapTile(int ID) {
        //get what Image it should be, have to do this awkwardly due to weirdly named files
        GreenfootImage image;
        if (ID < 10) image = new GreenfootImage ("mapTile_00" + (ID) + ".png");
        else if (ID < 100) image = new GreenfootImage ("mapTile_0" + (ID) + ".png");
        else image = new GreenfootImage ("mapTile_" + (ID) + ".png");

        image.scale((Options.blockSize),(Options.blockSize)); //scale image to 1x1 tile
        setImage(image); //set the image
    }
}
