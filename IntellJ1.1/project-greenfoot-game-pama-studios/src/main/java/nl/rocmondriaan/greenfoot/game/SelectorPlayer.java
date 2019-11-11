package nl.rocmondriaan.greenfoot.game;
import greenfoot.*;

import java.util.logging.Level;

public class SelectorPlayer extends Physics {

    private int timeTillInput;
    private boolean started = false;


    SelectorPlayer() {
        GreenfootImage image = new GreenfootImage ("mapTile_136.png");
        image.scale((Options.blockSize),(Options.blockSize));
        setImage(image);
    }
    public void act() {
        entityOffset();
        if (!started) {
            started = true;
            setDoubleX(getX());
            setDoubleY(getY());
        }
        if (Greenfoot.isKeyDown("d")) {
            if (LevelSelector.getSelectedLevel() == 2) {
                //animation
                LevelSelector.setSelectedLevel(1);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 3) {
                //animation
                LevelSelector.setSelectedLevel(4);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 5) {
                //animation
                LevelSelector.setSelectedLevel(4);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 6) {
                //animation
                LevelSelector.setSelectedLevel(5);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 9) {
                //animation
                LevelSelector.setSelectedLevel(10);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 10) {
                //animation
                LevelSelector.setSelectedLevel(11);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 13) {
                //animation
                LevelSelector.setSelectedLevel(12);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 15) {
                //animation
                LevelSelector.setSelectedLevel(14);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
        }
        if (Greenfoot.isKeyDown("w")) {
            if (LevelSelector.getSelectedLevel() == 2) {
                //animation
                LevelSelector.setSelectedLevel(3);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 4) {
                //animation
                LevelSelector.setSelectedLevel(5);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 7) {
                //animation
                LevelSelector.setSelectedLevel(8);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 8) {
                //animation
                LevelSelector.setSelectedLevel(9);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 11) {
                //animation
                LevelSelector.setSelectedLevel(12);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 12) {
                //animation
                LevelSelector.setSelectedLevel(13);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
        }
        if (Greenfoot.isKeyDown("s")) {
            if (LevelSelector.getSelectedLevel() == 4) {
                //animation
                LevelSelector.setSelectedLevel(3);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 5) {
                //animation
                LevelSelector.setSelectedLevel(6);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 7) {
                //animation
                LevelSelector.setSelectedLevel(6);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 8) {
                //animation
                LevelSelector.setSelectedLevel(7);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 11) {
                //animation
                LevelSelector.setSelectedLevel(10);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 12) {
                //animation
                LevelSelector.setSelectedLevel(11);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
        }
        if (Greenfoot.isKeyDown("a")) {
            if (LevelSelector.getSelectedLevel() == 1) {
                //animation
                LevelSelector.setSelectedLevel(2);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 3) {
                //animation
                LevelSelector.setSelectedLevel(2);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }

            if (LevelSelector.getSelectedLevel() == 6) {
                //animation
                LevelSelector.setSelectedLevel(7);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 9) {
                //animation
                LevelSelector.setSelectedLevel(8);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 10) {
                //animation
                LevelSelector.setSelectedLevel(9);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 13) {
                //animation
                LevelSelector.setSelectedLevel(14);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
            if (LevelSelector.getSelectedLevel() == 14) {
                //animation
                LevelSelector.setSelectedLevel(15);
                setLocation((int) (LevelSelector.getLevelX(LevelSelector.getSelectedLevel()) * Options.blockSize) , (int) (LevelSelector.getLevelY(LevelSelector.getSelectedLevel()) * Options.blockSize));
            }
        }
    }
}
