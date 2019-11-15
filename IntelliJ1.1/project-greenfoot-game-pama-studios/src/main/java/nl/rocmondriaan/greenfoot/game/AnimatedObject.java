package nl.rocmondriaan.greenfoot.game;

import greenfoot.GreenfootImage;

import java.util.Random;


public class AnimatedObject extends Blocks
{
    private GreenfootImage Torch1 = new GreenfootImage("253.png");
    private GreenfootImage Torch2 = new GreenfootImage("254.png");
    private GreenfootImage jumppadDown = new GreenfootImage("243.png");
    private GreenfootImage jumppadUp = new GreenfootImage("244.png");
    private int atime = 0; //Animation time
    int selectImage;
    private int timeTillNewParticle;
    Random rn = new Random();
    private int randomParticleSpawnTime = rn.nextInt(30) + 70; //Makes particles spawn randomly

    AnimatedObject(int ID){
        super(ID);
        selectImage = ID;
    }

    public void act(){
        changeImage();
        Torch1.scale((Options.blockSize),(Options.blockSize));
        Torch2.scale((Options.blockSize),(Options.blockSize));
        jumppadDown.scale((Options.blockSize),(Options.blockSize));
        jumppadUp.scale((Options.blockSize),(Options.blockSize));
    }


    public void changeImage(){
        if (selectImage == 253 || selectImage == 254 ){
            animateTorch();
            timeTillNewParticle -= 1;
            if (timeTillNewParticle <= 0) { //Spawns new particle in
                timeTillNewParticle = randomParticleSpawnTime;
                torchParticle(); //Put particle here
            }
        }
        else{}
    }

    public void animateTorch(){
        atime=atime + 1; //Counter
        if (atime >= 0) {setImage(Torch1);}
        if (atime >= 30) {setImage(Torch2);}
        if (atime >= 60) {atime=0;}//Reset
    }
    public void torchParticle() {
        Particles torchFlame = new Particles("smoke");
        Random rn = new Random();
        int randomX = rn.nextInt(20) - 9;
        getWorld().addObject(torchFlame, getX() + randomX, getY() - Options.blockSize / 2);
    }
}


