package game.world.menu;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import game.Options;

/**
 * MenuLogo is simply the Pama logo that we use in the main menu.
 */
class MenuLogo extends Actor {

    /**
     * Simply sets the image, scales it and sets it to that
     */
    MenuLogo() {
        GreenfootImage image = new GreenfootImage("pamaLogo.png");
        image.scale((Options.blockSize * 5), (Options.blockSize * 5));
        setImage(image);
    }
}
