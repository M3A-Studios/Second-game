package game.overlays;

import greenfoot.*;
import game.Options;

/**
 * Simple class that just has an image for the popup for example when walking over a lever
 */
public class PopUp extends Actor {

    /**
     * Simply sets and scales the popup image
     *
     * @param key       the key the popup should display (any keyboard key)
     */
    public PopUp(String key) {
        GreenfootImage popup = new GreenfootImage("Keyboard_Black_" + key + ".png");
        popup.scale((int) (Options.blockSize * 1.4), (int) (Options.blockSize * 1.4));
        setImage(popup);
    }
}
