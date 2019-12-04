package game.world.intro;

import game.Options;
import game.world.menu.Menu;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

public class TitleScreen extends Actor {

    private int frame = 0;
    private boolean started = false;
    static GreenfootSound select = new GreenfootSound("soundeffects/Select.wav");

    public TitleScreen() {
        GreenfootImage image = new GreenfootImage("Titlescreen.png");
        image.scale(Options.screenWidth, Options.screenHeight);
        setImage(image);
        started = false;
    }

    public void act() {
        if (started) {
            frame ++;
        }
        if (frame == 0) {
            String key = Greenfoot.getKey();
            if (key != null) {
                started = true;
                select.setVolume(Options.soundeffectVolume);
                select.play();
                getWorld().showText("", Options.screenWidth/2, Options.screenHeight/10*9);
            }
        }
        if (frame / 5 % 2 == 0) {
            getWorld().showText("Press any key to continue", Options.screenWidth/2, Options.screenHeight/10*9);
        } else if (frame / 5 % 2 == 1) {
            getWorld().showText("", Options.screenWidth/2, Options.screenHeight/10*9);
        }
        if (frame == 41) {
            getWorld().showText("", Options.screenWidth/2, Options.screenHeight/10*9);
            Greenfoot.setWorld(new Menu());
        }
    }
}
