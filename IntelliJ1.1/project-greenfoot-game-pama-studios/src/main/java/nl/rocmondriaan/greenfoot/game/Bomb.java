package nl.rocmondriaan.greenfoot.game;
import greenfoot.*;

public class Bomb extends Physics {
    static GreenfootImage bomb = new GreenfootImage("190.png");
    static GreenfootImage bomb2 = new GreenfootImage("191.png");
    static GreenfootImage explode = new GreenfootImage("explosion00.png");
    static GreenfootImage explode2 = new GreenfootImage("explosion01.png");
    static GreenfootImage explode3 = new GreenfootImage("explosion02.png");

    private int selectImage;
    private int atime;
    private int fadeTime = 0;
    boolean dropped;
    boolean spamfire = false;
    boolean ignited;
    boolean holding;

    Bomb(int ID){
        GreenfootImage image = new GreenfootImage ((ID) + ".png");
        image.scale((Options.blockSize),(Options.blockSize)); //scale to the size of 1 block
        setImage(image);
        selectImage = ID;
        dropped = false;
        ignited = false;
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
        if (!holding && atime < 200) {
            updateGravity();
            jumpPad();
        }
        if (spamfire){
            if (atime >= 50 && atime <= 200 && atime % 10 == 0) {lit();}
        }
        checkDropped();
    }

    private void checkDropped(){
        if (atime > 360) { atime = 0; }
        if (selectImage == 190 || selectImage == 191) {
            if (dropped) {
                atime++;
                changeimg();
                if (atime == 50) {spamfire = true;}
                else if (atime == 200){spamfire = false;}
                if (atime >= 200 && atime < 300){Explode();}
                if (atime >= 250 && atime < 300){deleteshit();}
                if (atime >= 200 && atime < 300){fadeTime += 1; fadelong(); }
                else if (atime > 301){getWorld().removeObject(this);}
            }
        }
    }
    public void lit(){
        Particles Bomb = new Particles("fire");
        getWorld().addObject(Bomb, this.getX() - 20, this.getY() -30);
    }

    public void Explode(){
        if (atime == 200){setImage(explode);}
        else if (atime == 225){setImage(explode2);}
        else if (atime > 250){setImage(explode3);}
    }

    public void changeimg(){
        if (atime >= 25 && atime <= 175) {
            if (atime % 50 == 25) {setImage(bomb2);}
            if (atime % 50 == 0) {setImage(bomb);}
        }
    }
    public void fadelong(){ //Fade out en kill object
        if (255 - fadeTime > 0) {
            this.getImage().setTransparency(255 - fadeTime);
        }
    }
    public void deleteshit() {
        getWorld().removeObjects(getObjectsInRange(Options.blockSize * 3,BreakableBlocks.class));
    }
}
