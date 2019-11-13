package nl.rocmondriaan.greenfoot.game;

import greenfoot.*;
import java.util.List;

public class MenuExit extends Actor
{

    private int normalWidth;
    private int normalHeight;
    private boolean overObject = false;

    MenuExit() {
        GreenfootImage image = new GreenfootImage("MenuExit.png");
        image.scale((Options.blockSize) * 4/3*2,(Options.blockSize) * 2/3*2);
        setImage(image);
        normalWidth = image.getWidth();
        normalHeight = image.getHeight();
    }
    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            System.exit(0);
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
                        getImage().scale(normalWidth + normalWidth / 10
                                , normalHeight + normalHeight / 10);
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

