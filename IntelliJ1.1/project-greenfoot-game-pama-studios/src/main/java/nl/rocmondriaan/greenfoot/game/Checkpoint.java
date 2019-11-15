package nl.rocmondriaan.greenfoot.game;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Checkpoint extends Actor {

    private int checkpoint;
    private int imageID;
    boolean active;
    private int activeStartImage;
    private int frame;
    private GreenfootImage inactiveImage;
    private GreenfootImage activeImage1;
    private GreenfootImage activeImage2;

    Checkpoint(int checkpoint, int ID) {
        if (ID == 168 || ID == 169 || ID == 170) {
            imageID = 171;
        } else if (ID == 171 || ID == 172 || ID == 173) {
            imageID = 174;
        } else if (ID == 174 || ID == 175 || ID == 176) {
            imageID = 177;
        } else if (ID == 177 || ID == 178 || ID == 179) {
            imageID = 180;
        }
        inactiveImage = new GreenfootImage(imageID + ".png");
        activeImage1 = new GreenfootImage((imageID - 2) + ".png");
        activeImage2 = new GreenfootImage((imageID - 1) + ".png");
        activeStartImage = imageID - 2;
        if (Levels.activeCheckpoint == checkpoint) {
            imageID -= 2;
            active = true;
        }
        GreenfootImage image = new GreenfootImage (imageID + ".png");
        image.scale((Options.blockSize),(Options.blockSize)); //scale to the size of 1 block
        inactiveImage.scale((Options.blockSize),(Options.blockSize)); //scale to the size of 1 block
        activeImage1.scale((Options.blockSize),(Options.blockSize)); //scale to the size of 1 block
        activeImage2.scale((Options.blockSize),(Options.blockSize)); //scale to the size of 1 block
        setImage(image);
        this.checkpoint = checkpoint;
    }
    public void act() {
        frame ++;
        if (frame >= 30) frame = 0;
        if (active) {
            if (frame == 0) {
                setImage(activeImage1);
            } else if (frame == 15) {
                setImage(activeImage2);
            }

        }
    }
    public int getCheckpoint() {
        return checkpoint;
    }
}
