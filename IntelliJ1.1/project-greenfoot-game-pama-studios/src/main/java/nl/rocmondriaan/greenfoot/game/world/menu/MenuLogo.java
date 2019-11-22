package nl.rocmondriaan.greenfoot.game.world.menu;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import nl.rocmondriaan.greenfoot.game.Options;

class MenuLogo extends Actor
{
    /**
     * Simply sets the image, scales it and sets it to that
     */
    MenuLogo() {
        GreenfootImage image = new GreenfootImage("pamaLogo.png");
        image.scale((Options.blockSize * 5), (Options.blockSize * 5));
        setImage(image);
    }
}
