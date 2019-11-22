package game.blocks.special;

import greenfoot.GreenfootImage;
import game.blocks.Blocks;
import game.Options;
import game.Particles;

import java.util.Random;


public class AnimatedObject extends Blocks
{
    //Images for animations
    /** The first image for the torch animation */
    private static GreenfootImage Torch1 = new GreenfootImage("253.png");
    /** The second image for the torch animation */
    private static GreenfootImage Torch2 = new GreenfootImage("254.png");

    /** The timer to switch between things in the animations */
    private int animationTimer = 0;
    /** The type of object that this instance is */
    private String type = "";
    /** Time in frames till when the next particle should be generated off this object */
    private int timeTillNewParticle;
    
    private static Random rn = new Random();
    /** Randomly sets a value for how often a particle should be spawned from this object, being between 70 and 100. */
    private int randomParticleSpawnTime = rn.nextInt(30) + 70; //Makes particles spawn randomly

    /**
     * Calls for the constructor in Blocks.java to set the image of the tile.
     * All of these images will be 1x1 in the grid. (Options.blockSize * Options.blockSize).
     * <p>
     * Also scales the images of the animated objects to make sure theyre the same 1x1 grid size
     *
     * @param ID    used to get what file should be displayed as the image of this object
     * @see Blocks#Blocks(int)
     */
    public AnimatedObject(int ID){
        super(ID);
        if (ID == 253 || ID == 254) {
            this.type = "torch";
        }
        Torch1.scale((Options.blockSize),(Options.blockSize));
        Torch2.scale((Options.blockSize),(Options.blockSize));
    }

    /**
     * Simple method that gets executed every frame.
     * Updates the images of the animated Objects.
     */
    public void act() {
        UpdateObject();
    }

    /**
     * Updates the animated Object and makes it do whatever it has to do
     */
    private void UpdateObject() {
        if (type.equals("torch")) { //if the object is a torch
            animateTorch();
            timeTillNewParticle -= 1;
            if (timeTillNewParticle <= 0) { //Spawns new particle in
                timeTillNewParticle = randomParticleSpawnTime;
                torchParticle(); //Put particle here
            }
        }
    }

    /**
     * Switches between the torch images see {@link #Torch1} and {@link #Torch2} for the images it switches between
     */
    private void animateTorch(){
        animationTimer = animationTimer + 1; //Counter
        if (animationTimer == 30) {
            setImage(Torch1);
        }
        else if (animationTimer >= 60) {
            setImage(Torch2);
            animationTimer = 0;
        }
    }

    /**
     * Spawns a particle off of the torch at a random location along the middle of the torch image being offset in the
     * x-axis by -10 to 10 pixels
     */
    private void torchParticle() {
        Particles torchFlame = new Particles("smoke");
        Random rn = new Random();
        int randomX = rn.nextInt(20) - 9;
        getWorld().addObject(torchFlame, getX() + randomX, getY() - Options.blockSize / 2);
    }
}


