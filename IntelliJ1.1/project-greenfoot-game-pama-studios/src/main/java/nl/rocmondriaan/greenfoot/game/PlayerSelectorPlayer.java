package nl.rocmondriaan.greenfoot.game;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;

import java.util.List;

public class PlayerSelectorPlayer extends Actor {

    private int normalWidth;
    private int normalHeight;
    private boolean overObject = false;
    private String color;

    PlayerSelectorPlayer(String color) {
        GreenfootImage image = new GreenfootImage("alien" + color + "_front.png");
        image.scale(Options.blockSize * 2, Options.blockSize * 3);
        setImage(image);
        this.color = color;
        normalWidth = image.getWidth();
        normalHeight = image.getHeight();
    }

    public void act() {
        //check for if clicked on
        if (Greenfoot.mouseClicked(this))
        {
            LevelSelector.setSelectedLevel(1);
            Globals.levelCoinsCollected = 0;
            //Globals.levelsUnlocked = 1;
            Options.player1Color = this.color;
            Greenfoot.setWorld(new LevelSelector());
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
