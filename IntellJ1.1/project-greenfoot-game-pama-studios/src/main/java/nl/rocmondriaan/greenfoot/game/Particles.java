package nl.rocmondriaan.greenfoot.game;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.Random;

public class Particles extends Actor {
    private GreenfootImage smoke1 = new GreenfootImage("WhiteSmoke1.png");
    private GreenfootImage smoke2 = new GreenfootImage("WhiteSmoke2.png");
    private GreenfootImage smoke3 = new GreenfootImage("WhiteSmoke3.png");
    private GreenfootImage beam = new GreenfootImage("BeamVanAllah.png");

    Random rn = new Random();
    private int random3 = rn.nextInt(3) + 1; //Randomly 1-3
    private int randomImage = random3;
    private int deathFadeTime;
    private int deathTime;
    private int timePassed;
    private String type;

    public void act() {
        setLocation(getX(), getY() - 1); //Making it fly up
        timePassed += 1;
        if (type == "smoke") {
            if (timePassed >= deathTime) {
                fade();
            }
        }
        if (type == "beam") {
            if (timePassed >= deathTime) {
                fadelong();
            }
        }
    }

    Particles(String type) {
        this.type = type;
        if (type == "smoke"){
            deathTime = 60; //How long to be destroyed yes it's randomised now
            if (randomImage == 1){ //3 random images
                setImage(smoke1);
                smoke1.scale((Options.blockSize) / 3,(Options.blockSize) / 3);
                smoke1.setTransparency(150);
            }
            else if (randomImage == 2) {
                setImage(smoke2);
                smoke2.scale((Options.blockSize) / 3,(Options.blockSize) / 3);
                smoke2.setTransparency(150);
            }
            else{
                setImage(smoke3);
                smoke3.scale((Options.blockSize) / 3,(Options.blockSize) / 3);
                smoke3.setTransparency(150);
            }
        }
        if (type == "beam"){
            setImage(beam);
            beam.setTransparency(150);
        }
    }

    public void fade(){ //Fade out en kill object
        deathFadeTime += 1;
        if (deathFadeTime==10) {this.getImage().setTransparency(150);} //255 is solid en 0 is invisible
        if (deathFadeTime==20) {this.getImage().setTransparency(125);}
        if (deathFadeTime==30) {this.getImage().setTransparency(100);}
        if (deathFadeTime==40) {this.getImage().setTransparency(50);}
        if (deathFadeTime==50) {this.getImage().setTransparency(40);}
        if (deathFadeTime>=60) {deathFadeTime = 0; getWorld().removeObject(this); timePassed = 0;}
    }
    public void fadelong(){ //Fade out en kill object
        deathFadeTime += 1;
        if (deathFadeTime==60) {this.getImage().setTransparency(125);}
        if (deathFadeTime==90) {this.getImage().setTransparency(100);}
        if (deathFadeTime==125) {this.getImage().setTransparency(70);}
        if (deathFadeTime==150) {this.getImage().setTransparency(50);}
        if (deathFadeTime==200) {this.getImage().setTransparency(40);}
        if (deathFadeTime==250) {this.getImage().setTransparency(20);}
        if (deathFadeTime>=300) {deathFadeTime = 0; getWorld().removeObject(this); timePassed = 0;}
    }
}
