package nl.rocmondriaan.greenfoot.game;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Flagpole extends Actor {

    private String purpose;
    private int frame;
    private GreenfootImage waving1 = new GreenfootImage("280.png");
    private GreenfootImage waving2 = new GreenfootImage("177.png");
    private GreenfootImage pole = new GreenfootImage("279.png");

    Flagpole(int ID) {
        waving1.scale(Options.blockSize, Options.blockSize);
        waving2.scale(Options.blockSize, Options.blockSize);
        pole.scale(Options.blockSize, Options.blockSize);
        if (ID == 272) {
            purpose = "top";
            setImage(waving1);
        } else {
            purpose = "pole";
            setImage(pole);
        }
    }

    public void act() {
        if (purpose.equals("top")) {
            frame++;
            if (frame == 1) {
                setImage(waving1);
            } else if (frame == 15) {
                setImage(waving2);
            } else if (frame >= 30) {
                frame = 0;
            }
        }
    }
}
