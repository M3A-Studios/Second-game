package game.world.menu;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;
import game.Options;

import java.util.List;

/**
 * This is the exit button on the main menu screen
 */
public class MenuExit extends Actor
{

    /** The default width of the button */
    private int normalWidth;
    /** The default height of the button */
    private int normalHeight;
    /** Boolean for if the mouse is hovering over a continue object */
    private boolean overObject = false;

    /**
     * Simply sets the image, scales it and sets what the dimensions of the default image are to the end result
     */
    MenuExit() {
        GreenfootImage image = new GreenfootImage("MenuExit.png");
        image.scale((Options.blockSize) * 4/3*2,(Options.blockSize) * 2/3*2);
        setImage(image);
        normalWidth = image.getWidth();
        normalHeight = image.getHeight();
    }

    /**
     * Act method being executed every frame, checks for if player clicked on the button to quit the game
     * also checks for hovering and if so makes the image 10% bigger
     */
    public void act() {
        //check for if clicked on
        if (Greenfoot.mouseClicked(this))
        {
            System.exit(0);
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

