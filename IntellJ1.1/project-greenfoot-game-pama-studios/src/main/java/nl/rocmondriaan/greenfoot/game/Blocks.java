package nl.rocmondriaan.greenfoot.game;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Blocks extends Actor
{
    /**
     * This is the constructor that should be used in every tile
     * All tiles should simply extend Blocks and call Super with the ID
     *
     * @param ID        refers to what the ID of the tile is, used for the image
     */
    Blocks(int ID){
        GreenfootImage image = new GreenfootImage ((ID + 7) + ".png");
        image.scale((Options.blockSize),(Options.blockSize)); //scale to the size of 1 block
        setImage(image);
    }
}
