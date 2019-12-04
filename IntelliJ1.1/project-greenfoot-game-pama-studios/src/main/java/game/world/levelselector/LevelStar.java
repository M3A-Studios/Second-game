package game.world.levelselector;

import game.Globals;
import game.Options;
import greenfoot.Actor;
import greenfoot.GreenfootImage;


class LevelStar extends Actor {

    /**
     * Constructor method used to set the image to what should be the image overlaid on levels
     */
    LevelStar(int level){
        GreenfootImage image;
        if (Globals.starsCollected[level-1]) {
            image = new GreenfootImage("189.png");
        } else {
            image = new GreenfootImage("starempty.png");
        }
        image.scale((Options.blockSize),(Options.blockSize)); //scale to the size of 1 block
        setImage(image);
    }
}
