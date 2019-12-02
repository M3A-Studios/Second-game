package game.world.store;

import game.Options;
import greenfoot.GreenfootImage;
import greenfoot.World;

/**
 * Menu is the main menu of the game
 */
public class Store extends World {

    /**
     * Simple constructor that sets the background when this world is loaded and also adds the background
     * then proceeds to use renderMenu() at the end to render the actual menu buttons
     */
    public Store() {
        super(Options.screenWidth, Options.screenHeight, 1, false); //render the screen with said screensize
        GreenfootImage background = new GreenfootImage("menuBackground.png");
        background.scale(Options.screenWidth, Options.screenHeight);
        setBackground(background);
        renderMenu();
    }

    /**
     * Simply renders the menu buttons and sets them to the right place on the screen
     */
    private void renderMenu() {

    }
}