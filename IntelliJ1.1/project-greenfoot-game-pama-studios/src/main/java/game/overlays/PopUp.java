package game.overlays;

import greenfoot.*;
import game.Options;

public class PopUp extends Actor
{
    public PopUp(String key) {
        GreenfootImage popup = new GreenfootImage("Keyboard_Black_" + key + ".png");
        popup.scale((int) (Options.blockSize * 1.4), (int) (Options.blockSize * 1.4));
        setImage(popup);
    }
}
