package game.world.menu;

import greenfoot.*;
import game.Options;
import game.world.levelselector.LevelSelector;

import java.util.List;

/**
 *
 */
public class PlayerSelectorPlayer extends Actor {

    /** The default width of the button */
    private int normalWidth;
    /** The default height of the button */
    private int normalHeight;
    /** Boolean for if the mouse is hovering over a continue object */
    private boolean overObject = false;
    /** The color of this object, being Green, Blue, Pink or Yellow */
    private String color;
    //Click sound
    static GreenfootSound select = new GreenfootSound("soundeffects/Select.wav");

    /**
     * Simply sets the image and the default sizes
     *
     * @param color     What color alien it should display
     */
    PlayerSelectorPlayer(String color) {
        GreenfootImage image = new GreenfootImage("alien" + color + "_front.png");
        image.scale(Options.blockSize * 2, Options.blockSize * 3);
        setImage(image);
        this.color = color;
        normalWidth = image.getWidth();
        normalHeight = image.getHeight();
    }

    /**
     * Act method being executed every frame, checks for if player clicked on the button to start a new game
     * also checks for hovering and if so makes the image 10% bigger
     */
    public void act() {
        //check for if clicked on
        if (Greenfoot.mouseClicked(this))
        {
            Options.player1Color = this.color;
            Greenfoot.setWorld(new LevelSelector());
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
