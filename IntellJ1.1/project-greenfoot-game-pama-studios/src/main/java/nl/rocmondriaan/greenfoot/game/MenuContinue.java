package nl.rocmondriaan.greenfoot.game;

import greenfoot.*;
import java.util.List;

public class MenuContinue extends Actor {

    private int normalWidth;
    private int normalHeight;
    private boolean overObject = false;
    MenuContinue() {
        GreenfootImage image = new GreenfootImage("MenuContinue.png");
        image.scale((Options.blockSize) * 4,(Options.blockSize) * 3/2);
        setImage(image);
        normalWidth = image.getWidth();
        normalHeight = image.getHeight();
    }
    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            //action to take when clicked
        }
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            List objects = getWorld().getObjectsAt(mouse.getX(), mouse.getY(), getClass());
            boolean overObjectCheck = false;
            for (Object object : objects)
            {
                if (object == this)
                {
                    overObjectCheck = true;
                    if (!overObject) {
                        getImage().scale(normalWidth + normalWidth / 10, normalHeight + normalHeight / 10);
                        overObject = true;
                    }
                }
            }
            if (!overObjectCheck) {
                overObject = false;
                getImage().scale(normalWidth, normalHeight);
            }
        }
    }
}
