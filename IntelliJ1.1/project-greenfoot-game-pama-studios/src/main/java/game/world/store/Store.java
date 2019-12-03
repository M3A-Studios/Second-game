package game.world.store;

import game.Music;
import game.Options;
import game.overlays.HUDNumber;
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
        renderHud();
        renderMenu();
        addObject(new StoreLogic(), -100, -100);

        Music.stopMusic();
        addObject(new Music(), -100, -100);
    }
    /**
     * Method for rendering the Heads Up Display (HUD) or well. the overlay, things like hearts, score,
     * etc. Really only just makes objects and places them
     */
    private void renderHud() {
        addObject(new HUDNumber(0, "coin"), (int) (Options.blockSize * 0.5), (int) (Options.blockSize * 0.5));
        addObject(new HUDNumber(1, "coin"), (int) (Options.blockSize * 1.25), (int) (Options.blockSize * 0.5));
        addObject(new HUDNumber(2, "coin"), (int) (Options.blockSize * 1.75), (int) (Options.blockSize * 0.5));
        addObject(new HUDNumber(3, "coin"), (int) (Options.blockSize * 2.25), (int) (Options.blockSize * 0.5));
        addObject(new HUDNumber(4, "coin"), (int) (Options.blockSize * 2.75), (int) (Options.blockSize * 0.5));
    }
    /**
     * Simply renders the menu buttons and sets them to the right place on the screen
     */
    private void renderMenu() {
        addObject(new StoreDoubleJump(), (int) (Options.blockSize * 5.75), (int) (Options.blockSize * 3.5));
        if (Options.doubleJumpUnlocked) {
            addObject(new StoreCross(), (int) (Options.blockSize * 5.75), (int) (Options.blockSize * 3.5));
        }
        addObject(new StoreSpeedBoost(), (int) (Options.screenWidth - Options.blockSize * 5.75), (int) (Options.blockSize * 3.5));
        if (Options.speedBoostUnlocked) {
            addObject(new StoreCross(), (int) (Options.screenWidth - Options.blockSize * 5.75), (int) (Options.blockSize * 3.5));
        }
        addObject(new StoreBonusHeart(), (int) (Options.blockSize * 5.75), (int) (Options.blockSize * 7.5));
        if (Options.bonusHeartUnlocked) {
            addObject(new StoreCross(), (int) (Options.blockSize * 5.75), (int) (Options.blockSize * 7.5));
        }
        addObject(new StoreBonusTorchUse(), (int) (Options.screenWidth - Options.blockSize * 5.75), (int) (Options.blockSize * 7.5));
        if (Options.bonusTorchUseUnlocked) {
            addObject(new StoreCross(), (int) (Options.screenWidth - Options.blockSize * 5.75), (int) (Options.blockSize * 7.5));
        }
    }
}