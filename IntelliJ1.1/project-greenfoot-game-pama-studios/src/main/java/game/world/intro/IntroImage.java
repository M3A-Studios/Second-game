package game.world.intro;

import game.Options;
import game.world.levelselector.LevelSelector;
import game.world.menu.Menu;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class IntroImage extends Actor {

    private int introLength = 480;

    IntroImage(String image, int width, int height) {
        GreenfootImage introImage  = new GreenfootImage(image);
        introImage.scale(width, height);
        setImage(introImage);
    }
    public void act() {
        introLength -= 1;
        if (introLength <= 170) {
            getImage().setTransparency(introLength * 3 / 2);
        }
        if (introLength == 0) {
            Greenfoot.setWorld(new Menu());
        }
    }
}
