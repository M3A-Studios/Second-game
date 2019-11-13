package nl.rocmondriaan.greenfoot.game;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class MenuNewGame extends Actor {

    private int normalWidth;
    private int normalHeight;
    private boolean overObject = false;

    MenuNewGame() {
        GreenfootImage image = new GreenfootImage("MenuNewGame.png");
        image.scale((Options.blockSize) * 6,(Options.blockSize) * 3 / 2);
        setImage(image);

        normalWidth = image.getWidth();
        normalHeight = image.getHeight();
    }public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            LevelSelector.setSelectedLevel(1);
            Globals.levelCoinsCollected = 0;
            Globals.levelsUnlocked = 1;
            Greenfoot.setWorld(new LevelSelector());
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
