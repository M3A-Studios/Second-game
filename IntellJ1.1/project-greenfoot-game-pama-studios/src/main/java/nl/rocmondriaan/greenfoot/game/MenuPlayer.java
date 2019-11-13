package nl.rocmondriaan.greenfoot.game;

import greenfoot.*;

public class MenuPlayer extends Physics {

    private GreenfootImage greenWalk1 = new GreenfootImage("alienGreen_walk1.png");
    private GreenfootImage greenWalk2 = new GreenfootImage("alienGreen_walk2.png");
    private GreenfootImage blueWalk1 = new GreenfootImage("alienBlue_walk1.png");
    private GreenfootImage blueWalk2 = new GreenfootImage("alienBlue_walk2.png");
    private GreenfootImage pinkWalk1 = new GreenfootImage("alienPink_walk1.png");
    private GreenfootImage pinkWalk2 = new GreenfootImage("alienPink_walk2.png");
    private GreenfootImage yellowWalk1 = new GreenfootImage("alienYellow_walk1.png");
    private GreenfootImage yellowWalk2 = new GreenfootImage("alienYellow_walk2.png");
    private int frame;
    private int color;
    private boolean started = false;

    MenuPlayer() {
        greenWalk1.scale((Options.blockSize) * 2,(int) (Options.blockSize * 2.8));
        greenWalk2.scale((Options.blockSize) * 2,(int) (Options.blockSize * 2.8));
        blueWalk1.scale((Options.blockSize) * 2,(int) (Options.blockSize * 2.8));
        blueWalk2.scale((Options.blockSize) * 2,(int) (Options.blockSize * 2.8));
        pinkWalk1.scale((Options.blockSize) * 2,(int) (Options.blockSize * 2.8));
        pinkWalk2.scale((Options.blockSize) * 2,(int) (Options.blockSize * 2.8));
        yellowWalk1.scale((Options.blockSize) * 2,(int) (Options.blockSize * 2.8));
        yellowWalk2.scale((Options.blockSize) * 2,(int) (Options.blockSize * 2.8));
        setImage(greenWalk1);
    }
    public void act() {
        if (!started) {
            started = true;
            setNewLocation(Options.blockSize * -1, (int) (Options.blockSize * 5.95));
        }
        frame ++;
        if (frame % 13 == 0 || frame == 1) {
            if (color == 0) {
                if (getImage() == greenWalk1) {
                    setImage(greenWalk2);
                } else {
                    setImage(greenWalk1);
                }
            } else if (color == 1) {
                if (getImage() == blueWalk1) {
                    setImage(blueWalk2);
                } else {
                    setImage(blueWalk1);
                }
            } else if (color == 2) {
                if (getImage() == pinkWalk1) {
                    setImage(pinkWalk2);
                } else {
                    setImage(pinkWalk1);
                }
            } else {
                if (getImage() == yellowWalk1) {
                    setImage(yellowWalk2);
                } else {
                    setImage(yellowWalk1);
                }
            }
        }
        if (frame < 100) {
            setRelativeLocation(Options.blockSize / 64.0 * 3,0);
        } else if (frame < 140) {
            setRelativeLocation(Options.blockSize / 64.0 * 3,Options.blockSize / 64.0 * 3);
        } else if (frame < 200) {
            setRelativeLocation(Options.blockSize / 64.0 * 3,0);
        } else if (frame == 200) {
        } else if (frame < 280) {
            setRelativeLocation( Options.blockSize / 64.0 * 3,0);
        } else if (frame < 360) {
            setRelativeLocation(Options.blockSize / 64.0 * 3, -Options.blockSize / 64.0 * 3);
        } else if (frame > 500) {
            setNewLocation(Options.blockSize * -1, (int) (Options.blockSize * 5.95));
            frame = 0;
            color ++;
            if (color >= 4) {
                color = 0;
            }
        } else {
            setRelativeLocation(Options.blockSize / 64.0 * 3, 0);
        }
    }

}
