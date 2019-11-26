package game.world.menu;

import greenfoot.GreenfootImage;
import greenfoot.World;
import game.Options;

/**
 *
 */
class PlayerSelectorScreen extends World {

    /**
     * Makes a simple world with the dimensions set in options, has a black background and calls renderMenu() to render
     * the selection menu
     */
    PlayerSelectorScreen() {
        super(Options.screenWidth, Options.screenHeight, 1, false); //render the screen with said screensize
        GreenfootImage background = new GreenfootImage("black.jpg");
        background.scale(Options.screenWidth, Options.screenHeight);
        setBackground(background);

        renderMenu();
    }

    /**
     * Renders the text and aliens to choose from in the menu
     */
    private void renderMenu() {
        showText("Choose your player character", Options.blockSize * 10, Options.blockSize * 2);
        addObject(new PlayerSelectorPlayer("Green"), Options.blockSize * 4, Options.blockSize * 5);
        addObject(new PlayerSelectorPlayer("Blue"), Options.blockSize * 8, Options.blockSize * 5);
        addObject(new PlayerSelectorPlayer("Pink"), Options.blockSize * 12, Options.blockSize * 5);
        addObject(new PlayerSelectorPlayer("Yellow"), Options.blockSize * 16, Options.blockSize * 5);
    }
}
