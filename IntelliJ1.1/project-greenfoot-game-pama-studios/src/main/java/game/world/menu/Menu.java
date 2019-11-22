package game.world.menu;

import greenfoot.GreenfootImage;
import greenfoot.World;
import game.*;

public class Menu extends World {

    /**
     * Simple constructor that sets the background when this world is loaded and also adds the background
     * then proceeds to use renderMenu() at the end to render the actual menu buttons
     */
    public Menu() {
        super(Options.screenWidth, Options.screenHeight, 1, false); //render the screen with said screensize
        GreenfootImage background = new GreenfootImage("menuBackground.png");
        background.scale(Options.screenWidth, Options.screenHeight);
        setBackground(background);

        addObject(new MenuPlayer(), Options.blockSize * -1, (int) (Options.blockSize * 5.95));
        renderMenu();
    }

    /**
     * Simply renders the menu buttons and sets them to the right place on the screen
     */
    private void renderMenu() {
        MenuNewGame startButton = new MenuNewGame();
        addObject(startButton, Options.blockSize * 10, (int) (Options.blockSize * 1.5));
        MenuContinue continueButton = new MenuContinue();
        addObject(continueButton, Options.blockSize * 10, (int) (Options.blockSize * 3.5));
        MenuExit exitButton = new MenuExit();
        addObject(exitButton, Options.blockSize * 10, (int) (Options.blockSize * 10.5));
        MenuLogo menuLogo = new MenuLogo();
        addObject(menuLogo, Options.blockSize * 3, Options.blockSize * 2);
    }

}
