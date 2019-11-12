package nl.rocmondriaan.greenfoot.game;
import greenfoot.*;
import nl.rocmondriaan.greenfoot.demo.DemoWorld;

import java.io.*;
import java.util.*;
import java.util.logging.Level;

public class SelectorPlayer extends Physics {

    private int animationBlocks;
    private int framesPerBlock = 20;
    private int animatedTime;
    private int movingTo = 1;
    private String[] movingDir;
    private boolean moving;
    private boolean started = false;


    SelectorPlayer() {
        GreenfootImage image = new GreenfootImage ("mapTile_136.png");
        image.scale((Options.blockSize),(Options.blockSize));
        setImage(image);
    }
    public void act() {
        if (!started) {
            started = true;
            setDoubleX(getX());
            setDoubleY(getY());
        }
        entityOffset();
        checkInput();
        move();
    }
    private void move() {
        if (moving) {
            animatedTime += 1;
            if (animatedTime < (animationBlocks * framesPerBlock)) {
                int index = (int) (animatedTime / framesPerBlock);
                String direction = movingDir[index];
                if (direction.equals("up")) {
                    setRelativeLocation(0, -(double) Options.blockSize / framesPerBlock);
                } else if (direction.equals("down")) {
                    setRelativeLocation(0, (double) Options.blockSize / framesPerBlock);
                } else if (direction.equals("left")) {
                    setRelativeLocation(-(double) Options.blockSize / framesPerBlock, 0);
                } else if (direction.equals("right")) {
                    setRelativeLocation((double) Options.blockSize / framesPerBlock, 0);
                } else {
                    System.out.println("Not moving any direction, nani");
                }
            } else {
                int levelX = (int) (Options.blockSize * LevelSelector.getLevelX(movingTo));
                int levelY = (int) (Options.blockSize * LevelSelector.getLevelY(movingTo));
                setNewLocation((levelX - Camera2.scrolledX), (levelY - Camera2.scrolledY));
                LevelSelector.setSelectedLevel(movingTo);
                moving = false;
            }
        }
    }
    private void checkInput() {
        if (!moving) {
            if (Greenfoot.isKeyDown("d")) {
                if (LevelSelector.getSelectedLevel() == 2) {
                    movingDir = new String[]{"right","right","down","down", "right"};
                    movingTo = 1;
                }
                if (LevelSelector.getSelectedLevel() == 3) {
                    movingDir = new String[]{"right","right","up","up","up","up","up"};
                    movingTo = 4;
                }
                if (LevelSelector.getSelectedLevel() == 5) {
                    movingDir = new String[]{"right","down","down"};
                    movingTo = 4;
                }
                if (LevelSelector.getSelectedLevel() == 6) {
                    movingDir = new String[]{"right","right","up"};
                    movingTo = 5;
                }
                if (LevelSelector.getSelectedLevel() == 9) {
                    movingDir = new String[]{"right","right","right","right","up","right","right"};
                    movingTo = 10;
                }
                if (LevelSelector.getSelectedLevel() == 10) {
                    movingDir = new String[]{"right","right","right","up"};
                    movingTo = 11;
                }
                if (LevelSelector.getSelectedLevel() == 13) {
                    movingDir = new String[]{"right","down","down","down","down"};
                    movingTo = 12;
                }
                if (LevelSelector.getSelectedLevel() == 15) {
                    movingDir = new String[]{"right","right","right","right"};
                    movingTo = 14;
                }
            }
            if (Greenfoot.isKeyDown("w")) {
                if (LevelSelector.getSelectedLevel() == 2) {
                    movingDir = new String[]{"up","up","right","right"};
                    movingTo = 3;
                }
                if (LevelSelector.getSelectedLevel() == 4) {
                    movingDir = new String[]{"up","up","left"};
                    movingTo = 5;
                }
                if (LevelSelector.getSelectedLevel() == 7) {
                    movingDir = new String[]{"up","left","left","left","up"};
                    movingTo = 8;
                }
                if (LevelSelector.getSelectedLevel() == 8) {
                    movingDir = new String[]{"up","up","right","right"};
                    movingTo = 9;
                }
                if (LevelSelector.getSelectedLevel() == 11) {
                    movingDir = new String[]{"up","left","left","up"};
                    movingTo = 12;
                }
                if (LevelSelector.getSelectedLevel() == 12) {
                    movingDir = new String[]{"up","up","up","up","left"};
                    movingTo = 13;
                }
                if (LevelSelector.getSelectedLevel() == 14) {
                    movingDir = new String[]{"up","right","right","right"};
                    movingTo = 13;
                }
            }
            if (Greenfoot.isKeyDown("s")) {
                if (LevelSelector.getSelectedLevel() == 4) {
                    movingDir = new String[]{"down","down","down","down","down","left","left"};
                    movingTo = 3;
                }
                if (LevelSelector.getSelectedLevel() == 5) {
                    movingDir = new String[]{"down","left","left"};
                    movingTo = 6;
                }
                if (LevelSelector.getSelectedLevel() == 7) {
                    movingDir = new String[]{"down","right","right","right","right"};
                    movingTo = 6;
                }
                if (LevelSelector.getSelectedLevel() == 8) {
                    movingDir = new String[]{"down","right","right","right","down"};
                    movingTo = 7;
                }
                if (LevelSelector.getSelectedLevel() == 11) {
                    movingDir = new String[]{"down","left","left","left"};
                    movingTo = 10;
                }
                if (LevelSelector.getSelectedLevel() == 12) {
                    movingDir = new String[]{"down","right","right","down"};
                    movingTo = 11;
                }
            }
            if (Greenfoot.isKeyDown("a")) {
                if (LevelSelector.getSelectedLevel() == 1) {
                    movingDir = new String[]{"left", "up", "up", "left", "left"};
                    movingTo = 2;
                }
                if (LevelSelector.getSelectedLevel() == 3) {
                    movingDir = new String[]{"left","left","down","down"};
                    movingTo = 2;
                }
                if (LevelSelector.getSelectedLevel() == 6) {
                    movingDir = new String[]{"left","left","left","left","up"};
                    movingTo = 7;
                }
                if (LevelSelector.getSelectedLevel() == 9) {
                    movingDir = new String[]{"left","left","down","down"};
                    movingTo = 8;
                }
                if (LevelSelector.getSelectedLevel() == 10) {
                    movingDir = new String[]{"left","left","down","left","left","left","left"};
                    movingTo = 9;
                }
                if (LevelSelector.getSelectedLevel() == 13) {
                    movingDir = new String[]{"left","left","left","down"};
                    movingTo = 14;
                }
                if (LevelSelector.getSelectedLevel() == 14) {
                    movingDir = new String[]{"left","left","left","left"};
                    movingTo = 15;
                }
            }
            if (Greenfoot.isKeyDown("enter") || Greenfoot.isKeyDown("space")) {
                Greenfoot.setWorld(new Levels(LevelSelector.getSelectedLevel()));
                Globals.currentLevel = LevelSelector.getSelectedLevel();
            }
            if (movingTo != LevelSelector.getSelectedLevel() && movingTo <= Globals.levelsUnlocked && movingDir != null) {
                moving = true;
                animationBlocks = movingDir.length;
                animatedTime = 0;
            }
        }
    }
}
