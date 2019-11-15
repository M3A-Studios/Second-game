package nl.rocmondriaan.greenfoot.game;

import greenfoot.*;
public class PopUp extends Actor
{
    PopUp(String key) {
        GreenfootImage popup = new GreenfootImage("Keyboard_Black_" + key + ".png");
        setImage(popup);
    }
}
