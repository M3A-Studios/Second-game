package game.blocks.special;

import game.blocks.normal.BreakableBlocks;
import greenfoot.*;
import game.Options;
import game.Particles;
import game.entities.Player;
import game.Physics;

public class Bomb extends Physics {
    static GreenfootImage bomb = new GreenfootImage("190.png");
    static GreenfootImage bomb2 = new GreenfootImage("191.png");
    static GreenfootImage explode = new GreenfootImage("explosion00.png");
    static GreenfootImage explode2 = new GreenfootImage("explosion01.png");
    static GreenfootImage explode3 = new GreenfootImage("explosion02.png");
    static GreenfootSound tnt = new GreenfootSound("soundeffects/tnt.wav");

    private int selectImage;
    private int animationTimer;
    private int fadeTime = 0;
    public boolean dropped;
    private boolean spamfire = false;
    public boolean holding;
    public boolean exploded;

    public Bomb(int ID){
        GreenfootImage image = new GreenfootImage ((ID) + ".png");
        image.scale((Options.blockSize),(Options.blockSize)); //scale to the size of 1 block
        setImage(image);
        selectImage = ID;
        dropped = false;
        holding = false;
        bomb.scale(Options.blockSize, Options.blockSize);
        bomb2.scale(Options.blockSize, Options.blockSize);
        explode.scale(Options.blockSize * 3 , Options.blockSize * 3);
        explode2.scale(Options.blockSize * 2 , Options.blockSize * 2);
        explode3.scale(Options.blockSize, Options.blockSize);
    }

    public void act(){
        setDoubleX(getX());
        setDoubleY(getY());
        if (!holding && animationTimer < 200 || Player.dead) {
            updateGravity();
            jumpPad();
        }
        if (spamfire){
            if (animationTimer >= 50 && animationTimer <= 200 && animationTimer % 10 == 0) {lit();}
        }
        checkDropped();
    }

    private void checkDropped(){
        if (animationTimer > 360) { animationTimer = 0; }
        if (selectImage == 190 || selectImage == 191) {
            if (dropped) {
                animationTimer++;
                changeImage();
                if (animationTimer == 50) {spamfire = true;}
                if (animationTimer == 150) {tnt.setVolume(Options.soundeffectVolume); tnt.play();}
                else if (animationTimer == 200){spamfire = false; }
                if (animationTimer >= 200 && animationTimer < 300){Explode();}
                if (animationTimer >= 200 && animationTimer < 225){exploded = true; }
                if (animationTimer >= 200 && animationTimer < 250){deleteBlocks();}
                if (animationTimer >= 200 && animationTimer < 300){fadeTime += 1; fadelong(); }
                else if (animationTimer > 301){getWorld().removeObject(this);}
            }
        }
    }
    private void lit(){
        Particles Bomb = new Particles("fire");
        getWorld().addObject(Bomb, this.getX() - (int) (Options.blockSize / 64.0 * 20), this.getY() -(int) (Options.blockSize / 64.0 * 30));
    }

    private void Explode(){
        if (animationTimer == 200){setImage(explode);}
        else if (animationTimer == 225){setImage(explode2);}
        else if (animationTimer > 250){setImage(explode3);}
    }

    private void changeImage(){
        if (animationTimer >= 25 && animationTimer <= 175) {
            if (animationTimer % 50 == 25) {setImage(bomb2);}
            if (animationTimer % 50 == 0) {setImage(bomb);}
        }
    }
    private void fadelong(){ //Fade out en kill object
        if (255 - fadeTime > 0) {
            this.getImage().setTransparency(255 - fadeTime);
        }
    }

    private void deleteBlocks() {
        getWorld().removeObjects(getObjectsInRange(Options.blockSize * 3, BreakableBlocks.class));
    }
}
