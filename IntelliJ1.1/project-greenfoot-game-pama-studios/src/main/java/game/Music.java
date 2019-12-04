package game;

import game.entities.Player;
import game.world.level.Levels;
import game.world.levelselector.LevelSelector;
import game.world.menu.Menu;
import game.world.store.Store;
import greenfoot.Actor;
import greenfoot.GreenfootSound;

public class Music extends Actor {

    private static GreenfootSound music = new GreenfootSound("soundeffects/Music/Menu.wav");
    private static boolean started = false;
    static int vTime = 0;
    static int holdVolume;

    public Music () {
        started = false;
    }
    public void act() {
        if (!started) {
            started = true;
            holdVolume = Options.musicVolume;
            music.stop();
            if (getWorld() instanceof LevelSelector) {
                music = new GreenfootSound("soundeffects/Music/Main theme.wav");
            } else if (getWorld() instanceof Levels) {
                int level = LevelSelector.getSelectedLevel();
                if (level <= 3) {
                    music = new GreenfootSound("soundeffects/Music/Stage1.wav");
                } else if (level <= 6) {
                    music = new GreenfootSound("soundeffects/Music/Stage2.wav");
                } else if (level <= 9) {
                    music = new GreenfootSound("soundeffects/Music/Stage3.wav");
                } else if (level <= 12) {
                    music = new GreenfootSound("soundeffects/Music/Stage4.wav");
                } else if (level <= 14) {
                    music = new GreenfootSound("soundeffects/Music/Stage5.wav");
                } else if (level <= 15) {
                    music = new GreenfootSound("soundeffects/Music/Boss.wav");
                }
            } else if (getWorld() instanceof Menu) {
                music = new GreenfootSound("soundeffects/Music/Main theme.wav");
            } else if (getWorld() instanceof Store) {
                music = new GreenfootSound("soundeffects/Music/Wii.wav");
            } else {
                music = new GreenfootSound("soundeffects/Music/Wii.wav");
            }
            if (music != null && !music.isPlaying()) {
                music.playLoop();
                music.setVolume(Options.musicVolume);
            }
        }
    }

    public static void stopMusic() {
        if (music != null) {
            music.stop();
        }
    }
}