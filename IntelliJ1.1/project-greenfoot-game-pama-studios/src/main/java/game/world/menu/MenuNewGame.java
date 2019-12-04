package game.world.menu;

import greenfoot.*;
import game.Globals;
import game.Options;
import game.Saver;
import game.world.levelselector.LevelSelector;

import java.util.List;

/**
 * The new game button in the main menu
 */
public class MenuNewGame extends Actor {

    /** The default width of the button */
    private int normalWidth;
    /** The default height of the button */
    private int normalHeight;
    /** Boolean for if the mouse is hovering over a continue object */
    private boolean overObject = false;
    //Click sound
    static GreenfootSound select = new GreenfootSound("soundeffects/Select.wav");

    /**
     * Simply sets the image, scales it and sets what the dimensions of the default image are to the end result
     */
    MenuNewGame() {
        GreenfootImage image = new GreenfootImage("MenuNewGame.png");
        image.scale((Options.blockSize) * 6, (Options.blockSize) * 3 / 2);
        setImage(image);

        normalWidth = image.getWidth();
        normalHeight = image.getHeight();
    }

    /**
     * Act method being executed every frame, checks for if player clicked on the button to go to the player selector
     * also checks for hovering and if so makes the image 10% bigger
     */
    public void act() {
        //check for if clicked on
        if (Greenfoot.mouseClicked(this))
        {
            Saver.createSave();
            LevelSelector.setSelectedLevel(1);
            Globals.levelCoinsCollected = 0;
            Globals.totalCoinsCollected = 0;
            Globals.totalScore = 0;
            Globals.levelScore = 0;
            //Globals.levelsUnlocked = 1;
            Greenfoot.setWorld(new PlayerSelectorScreen());
            select.setVolume(Options.soundeffectVolume);
            select.play();
        }
        //check if hovering over the button
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            List objects = getWorld().getObjectsAt(mouse.getX(), mouse.getY(), getClass());
            boolean overObjectCheck = false;
            for (Object object : objects)
            {
                if (object == this)
                {
                    //if hovering over the button make it 10% bigger
                    overObjectCheck = true;
                    if (!overObject) {
                        getImage().scale(normalWidth + normalWidth / 10, normalHeight + normalHeight / 10);
                        overObject = true;
                    }
                }
            }
            //if not over the object reset to default
            if (!overObjectCheck && overObject) {
                overObject = false;
                getImage().scale(normalWidth, normalHeight);
            }
        }
    }
}
