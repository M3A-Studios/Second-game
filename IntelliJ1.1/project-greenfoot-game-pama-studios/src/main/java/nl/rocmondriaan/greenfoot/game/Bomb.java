package nl.rocmondriaan.greenfoot.game;
import greenfoot.*;

public class Bomb extends Blocks {
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
        super(ID);
        selectImage = ID;
        dropped = false;
        ignited = false;
        holding = false;
    }

    public void act(){
        bomb.scale(Options.blockSize, Options.blockSize);
        bomb2.scale(Options.blockSize, Options.blockSize);
        explode.scale(Options.blockSize * 3 , Options.blockSize * 3);
        explode2.scale(Options.blockSize * 2 , Options.blockSize * 2);
        explode3.scale(Options.blockSize, Options.blockSize);

        if (spamfire){
            if (atime == 50){lit();}
            else if (atime == 60){lit();}
            else if (atime == 70){lit();}
            else if (atime == 80){lit();}
            else if (atime == 90){lit();}
            else if (atime == 100){lit();}
            else if (atime == 110){lit();}
            else if (atime == 120){lit();}
            else if (atime == 130){lit();}
            else if (atime == 140){lit();}
            else if (atime == 150){lit();}
            else if (atime == 160){lit();}
            else if (atime == 170){lit();}
            else if (atime == 180){lit();}
            else if (atime == 190){lit();}
            else if (atime == 200){lit();}
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
                else if (atime >= 225 && atime < 300){Explode();}
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
        if (atime == 25) {setImage(bomb2);}
        else if (atime == 50){setImage(bomb);}
        else if (atime == 75){setImage(bomb2);}
        else if (atime == 100){setImage(bomb);}
        else if (atime == 125){setImage(bomb2);}
        else if (atime == 150){setImage(bomb);}
        else if (atime == 175){setImage(bomb2);}
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
