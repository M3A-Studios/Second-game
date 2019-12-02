package game.world.levelselector;

import game.Options;
import game.Saver;
import game.world.store.Store;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;

import java.util.List;

public class StoreButton extends Actor {

    /** The default width of the button */
    private int normalWidth;
    /** The default height of the button */
    private int normalHeight;
    /** Boolean for if the mouse is hovering over a continue object */
    private boolean overObject = false;
    private int startingX;
    private int startingY;
    private boolean started = false;
    /**
     * Simply sets the image, scales it and sets what the dimensions of the default image are to the end result
     */
    StoreButton() {
        GreenfootImage image = new GreenfootImage("store.png");
        image.scale((Options.blockSize),(Options.blockSize));
        setImage(image);
        normalWidth = image.getWidth();
        normalHeight = image.getHeight();
    }
    /**
     * Act method being executed every frame, checks for if player clicked on the button to load the save file,
     * also checks for hovering and if so makes the image 10% bigger
     */
    public void act() {
        if (!started) {
            startingX = getX();
            startingY = getY();
            started = true;
        }
        setLocation(startingX, startingY);
        //check for if clicked on
        if (Greenfoot.mouseClicked(this))
        {
            Greenfoot.setWorld(new Store());
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

