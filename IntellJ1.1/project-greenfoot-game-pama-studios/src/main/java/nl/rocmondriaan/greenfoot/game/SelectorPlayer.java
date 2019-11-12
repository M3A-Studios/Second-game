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
                    movingDir = new String[]{"right", "right", "down", "down", "right"};
                    movingTo = 1;
                }
            }
            if (Greenfoot.isKeyDown("w")) {
            }
            if (Greenfoot.isKeyDown("s")) {
            }
            if (Greenfoot.isKeyDown("a")) {
                if (LevelSelector.getSelectedLevel() == 1) {
                    movingDir = new String[]{"left", "up", "up", "left", "left"};
                    movingTo = 2;
                }
            }
            if (Greenfoot.isKeyDown("enter") || Greenfoot.isKeyDown("space")) {
                Greenfoot.setWorld(new Levels(LevelSelector.getSelectedLevel()));
            }
            if (movingTo != LevelSelector.getSelectedLevel() && movingTo <= Globals.levelsUnlocked) {
                moving = true;
                animationBlocks = movingDir.length;
                animatedTime = 0;
            }
        }
    }
}
