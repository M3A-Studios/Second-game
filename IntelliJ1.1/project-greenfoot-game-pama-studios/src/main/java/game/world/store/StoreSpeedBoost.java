package game.world.store;

import game.Globals;
import game.Options;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;

import java.util.List;

public class StoreSpeedBoost extends Actor {
    /**
     * The default width of the button
     */
    private int normalWidth;
    /**
     * The default height of the button
     */
    private int normalHeight;
    /**
     * Boolean for if the mouse is hovering over a continue object
     */
    private boolean overObject = false;

    /**
     * Simply sets the image, scales it and sets what the dimensions of the default image are to the end result
     */
    public StoreSpeedBoost() {
        GreenfootImage image = new GreenfootImage("emptystore.png");
        image.scale((int) ((Options.blockSize) * 7.5), (Options.blockSize) * 3);
        setImage(image);
        normalWidth = image.getWidth();
        normalHeight = image.getHeight();
    }

    /**
     * Act method being executed every frame, checks for if player clicked on the button to load the save file,
     * also checks for hovering and if so makes the image 10% bigger
     */
    public void act() {
        if (!Options.speedBoostUnlocked) {
            //check for if clicked on
            if (Greenfoot.mouseClicked(this)) {
                if (Globals.totalCoinsCollected >= 50) {
                    Globals.totalCoinsCollected -= 50;
                    Options.speedBoostUnlocked = true;
                    getWorld().addObject(new StoreCross(), getX(), getY());
                }
            }
            //check if hovering over the button
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouse != null) {
                List objects = getWorld().getObjectsAt(mouse.getX(), mouse.getY(), getClass());
                boolean overObjectCheck = false;
                for (Object object : objects) {
                    if (object == this) {
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
        } else {
            getImage().scale(normalWidth, normalHeight);
        }
    }
}
