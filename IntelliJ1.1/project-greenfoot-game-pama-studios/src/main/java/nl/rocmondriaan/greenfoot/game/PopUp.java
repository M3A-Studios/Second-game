package nl.rocmondriaan.greenfoot.game;

import greenfoot.*;
public class PopUp extends Actor
{
    PopUp(String key) {
        GreenfootImage popup = new GreenfootImage("Keyboard_Black_" + key + ".png");
        popup.scale((int) (Options.blockSize * 1.4), (int) (Options.blockSize * 1.4));
        setImage(popup);
    }
}
