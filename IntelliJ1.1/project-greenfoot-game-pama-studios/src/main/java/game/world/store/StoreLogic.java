package game.world.store;

import game.Options;
import game.Saver;
import game.world.levelselector.LevelSelector;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;

public class StoreLogic extends Actor {
    //Sound effects
    static GreenfootSound back = new GreenfootSound("soundeffects/Back.wav");

    public void act() {
        String key = Greenfoot.getKey();
        if (key != null) {
            if (key.equals("escape")) {
                Saver.saveGame(); //save the game when going to the level selector
                Greenfoot.setWorld(new LevelSelector(LevelSelector.getSelectedLevel()));
                back.setVolume(Options.soundeffectVolume);
                back.play();
            }
        }
    }
}
