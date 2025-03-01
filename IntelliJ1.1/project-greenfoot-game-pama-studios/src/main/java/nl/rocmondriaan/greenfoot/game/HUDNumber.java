package nl.rocmondriaan.greenfoot.game;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import java.util.*;

public class HUDNumber extends Physics
{
    private boolean started = false; //gets checked first frame to get the startX and startY
    private int startX;
    private int startY;
    private int position;
    private GreenfootImage[] images = new GreenfootImage[10];
    private String value = "0";
    private String coins;
    private String purpose;
    private int start;

    HUDNumber(int position, String purpose) {
        this.purpose = purpose;
        this.position = position;
        if (position != 0) {
            renderImages();
            setImage(images[0]);
        } else {
            if (purpose.equals("coin")) {
                GreenfootImage coinImage = new GreenfootImage("hudCoin.png");
                coinImage.scale(Options.blockSize, Options.blockSize);
                setImage(coinImage);
            }
        }
    }
    private void renderImages() {
        for(int i = 0; i <= 9; i++) {
           images[i] = new GreenfootImage("hud" + i + ".png");
           images[i].scale(Options.blockSize, Options.blockSize);
        }
    }

    public void act() {
        if (!started) {
            startX = getX();
            startY = getY();
            started = true;
        } else {
            setLocation(startX, startY);
            if (position != 0) {
                if (purpose.equals("coin")) {
                    if (getWorld() instanceof LevelSelector) {
                        coins = Integer.toString(Globals.totalCoinsCollected);
                        start = 4;
                    } else {
                        coins = Integer.toString(Globals.levelCoinsCollected);
                        start = 3;
                    }
                    String[] strArray = coins.split("");
                    start = start - strArray.length + 1;
                    if (position >= start) {
                        value = strArray[position - start];
                    }
                } else if (purpose.equals("score")) {
                    if (getWorld() instanceof LevelSelector) {
                        coins = Integer.toString(Globals.totalScore);
                        start = 7;
                    } else {
                        coins = Integer.toString(Globals.levelScore);
                        start = 5;
                    }
                    String[] strArray = coins.split("");
                    start = start - strArray.length + 1;
                    if (position >= start) {
                        value = strArray[position - start];
                    }

                }
                setImage(images[Integer.parseInt(value)]);
            }
        }
    }
}

