package nl.rocmondriaan.greenfoot.game;

import greenfoot.GreenfootImage;

public class MenuPlayer extends Physics {
    //set all the images for walking & jumping
    private GreenfootImage greenWalk1 = new GreenfootImage("alienGreen_walk1.png");
    private GreenfootImage greenWalk2 = new GreenfootImage("alienGreen_walk2.png");
    private GreenfootImage blueWalk1 = new GreenfootImage("alienBlue_walk1.png");
    private GreenfootImage blueWalk2 = new GreenfootImage("alienBlue_walk2.png");
    private GreenfootImage pinkWalk1 = new GreenfootImage("alienPink_walk1.png");
    private GreenfootImage pinkWalk2 = new GreenfootImage("alienPink_walk2.png");
    private GreenfootImage yellowWalk1 = new GreenfootImage("alienYellow_walk1.png");
    private GreenfootImage yellowWalk2 = new GreenfootImage("alienYellow_walk2.png");

    //some counters and check if started or not in act method first frame
    private int frame;
    private int color;
    private boolean started = false;


    /**
     * Simply scales some images in the constructor and sets image to the first of the walking green alien
     */
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

    /**
     * Act method being executed every frame, first frame will set the proper location of the alien in the memory
     * For the rest simply counts the frames to apply the right image and move the player around in the animation
     */
    public void act() {
        if (!started) {
            started = true;
            setNewLocation(Options.blockSize * -1, (int) (Options.blockSize * 6.01));
        }
        frame ++; //count frames
        if (frame % 13 == 0 || frame == 1) { //if first frame or frame is a multiplier of 13 it changes images
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
        //hardcoded bs that simply does the whole animation of the guy in the background
        if (frame < 100) {
            setRelativeLocation(3,0);
        } else if (frame < 140) {
            setRelativeLocation(3,3);
        } else if (frame < 200) {
            setRelativeLocation(3,0);
        } else if (frame == 200) {
            vSpeed = 0;
            jump(20);
        } else if (frame <= 243) {
            vSpeed = vSpeed + acceleration;
            if (vSpeed > 20) vSpeed = 20;
            setRelativeLocation(0, vSpeed);
            setRelativeLocation(3, 0);
        } else if (frame == 244) {
            setRelativeLocation(3, 8);
        } else if (frame < 280) {
            setRelativeLocation( 3,0);
        } else if (frame < 360) {
            setRelativeLocation(3, -3);
        } else if (frame > 500) {
            setNewLocation(Options.blockSize * -1, (int) (Options.blockSize * 6.01));
            frame = 0;
            color ++;
            if (color >= 4) {
                color = 0;
            }
        } else {
            setRelativeLocation(3, 0);
        }
    }
}