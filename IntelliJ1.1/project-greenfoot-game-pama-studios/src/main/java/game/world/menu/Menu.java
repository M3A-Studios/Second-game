package game.world.menu;

import game.blocks.special.Coins;
import game.entities.enemies.Slime;
import greenfoot.GreenfootImage;
import greenfoot.World;
import game.*;

/**
 * Menu is the main menu of the game
 */
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
        renderMenu();
    }

    /**
     * Simply renders the menu buttons and sets them to the right place on the screen
     */
    private void renderMenu() {
        addObject(new MenuPlayer(), Options.blockSize * -1, (int) (Options.blockSize * 5.95));
        addObject(new Coins(166, true), Options.blockSize * 13, (int) (Options.blockSize * 7));
        addObject(new Coins(168, true), Options.blockSize * 15, (int) (Options.blockSize * 5));
        addObject(new Coins(167, true), Options.blockSize * 17, (int) (Options.blockSize * 3));
        addObject(new Slime(40, true), (int) (Options.blockSize * 8.5), (int) (Options.blockSize * 7.8));
        addObject(new MenuNewGame(), Options.blockSize * 10, (int) (Options.blockSize * 1.5));
        addObject(new MenuContinue(), Options.blockSize * 10, (int) (Options.blockSize * 3.5));
        addObject(new MenuOptions(), Options.blockSize * 10, (int) (Options.blockSize * 5.5));
        addObject(new MenuExit(), Options.blockSize * 10, (int) (Options.blockSize * 10.5));
        addObject(new MenuLogo(), Options.blockSize * 3, Options.blockSize * 2);
    }

}
