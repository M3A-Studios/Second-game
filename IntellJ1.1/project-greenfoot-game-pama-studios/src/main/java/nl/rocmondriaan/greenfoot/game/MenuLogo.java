package nl.rocmondriaan.greenfoot.game;

import greenfoot.*;

public class MenuLogo extends Actor
{
    MenuLogo() {
        GreenfootImage image = new GreenfootImage("pamaLogo.png");
        image.scale((Options.blockSize * 5), (Options.blockSize * 5));
        setImage(image);
    }
}
