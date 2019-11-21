package nl.rocmondriaan.greenfoot.game;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;

import java.util.List;

public class MenuContinue extends Actor {

    private int normalWidth;
    private int normalHeight;
    private boolean overObject = false;

    /**
     * Simply sets the image, scales it and sets what the dimensions of the default image are to the end result
     */
    MenuContinue() {
        GreenfootImage image = new GreenfootImage("MenuContinue.png");
        image.scale((Options.blockSize) * 4,(Options.blockSize) * 3/2);
        setImage(image);
        normalWidth = image.getWidth();
        normalHeight = image.getHeight();
    }

    /**
     * Act method being executed every frame, checks for if player clicked on the button to execute code
     * also checks for hovering and if so makes the image 10% bigger
     */
    public void act() {
        //check for if clicked on
        if (Greenfoot.mouseClicked(this))
        {
            Saver.loadGame();
            Greenfoot.setWorld(new LevelSelector(LevelSelector.getSelectedLevel()));
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
