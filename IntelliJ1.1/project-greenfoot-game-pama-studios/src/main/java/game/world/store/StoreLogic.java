package game.world.store;

import game.Saver;
import game.world.levelselector.LevelSelector;
import greenfoot.Actor;
import greenfoot.Greenfoot;

public class StoreLogic extends Actor {


    public void act() {
        String key = Greenfoot.getKey();
        if (key != null) {
            if (key.equals("escape")) {
                Saver.saveGame(); //save the game when going to the level selector
                Greenfoot.setWorld(new LevelSelector(LevelSelector.getSelectedLevel()));
            }
        }
    }
}
