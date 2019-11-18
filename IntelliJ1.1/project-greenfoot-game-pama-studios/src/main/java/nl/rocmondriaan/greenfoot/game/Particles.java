package nl.rocmondriaan.greenfoot.game;
import greenfoot.Actor;
import greenfoot.GreenfootImage;
import java.util.Random;

public class Particles extends Physics {
    private GreenfootImage smoke1 = new GreenfootImage("WhiteSmoke1.png");
    private GreenfootImage smoke2 = new GreenfootImage("WhiteSmoke2.png");
    private GreenfootImage smoke3 = new GreenfootImage("WhiteSmoke3.png");
    private GreenfootImage beam = new GreenfootImage("BeamVanAllah.png");
    private GreenfootImage confetti = new GreenfootImage("confetti.png");
    private GreenfootImage fire = new GreenfootImage("fire01.png"); //need new fire image

    private int random3;
    private int random2;
    private int deathFadeTime;
    private int deathTime;
    private int timePassed;
    private String type;
    private boolean started = false;

    public void act() {
        if (!started) {
            setDoubleX(getX());
            setDoubleY(getY());
            started = true;
        }
        entityOffset();
        Random rn = new Random();
        random3 = rn.nextInt(3) + 1;
        timePassed += 1;
        if (type.equals("smoke")) {
            setRelativeLocation(0, -1); //Making it fly up
            if (timePassed >= deathTime) {
                fade();
            }
        }
        else if (type.equals("beam")) {
            if (timePassed >= deathTime) {
                fadelong();
            }
        }
        else if (type.equals("confetti")) {
            setRelativeLocation(-2, -4); //Making it fly up
            if (timePassed >= deathTime) {
                fade();
            }
        }
        else if (type.equals("confettim")) { //This is mirrored
            setRelativeLocation(2, -4); //Making it fly up
            if (timePassed >= deathTime) {
                fade();
            }
        }
        else if (type.equals("fire")) {
            setRelativeLocation(0, -1); //Making it fly up
            if (timePassed >= deathTime) {
                fade();
            }
        }
        else { System.out.println(type); }
    }

    Particles(String type) {
        this.type = type;
        if (type.equals("smoke")){
            smoke();
        }
        if (type.equals("beam")){
            beam.scale(Options.blockSize * 2, Options.screenHeight * 2);
            setImage(beam);
        }
        if (type.equals("confetti") || type.equals("confettim")){
            confetti.scale((Options.blockSize) * 4, (Options.blockSize) * 4);
            setImage(confetti);
        }
        if (type.equals("fire")){
            fire.scale((Options.blockSize) / 3, (Options.blockSize) / 3);
            setImage(fire);
        }
    }

    public void fade(){ //Fade out en kill object
        deathFadeTime += 1;
        if (150 - deathFadeTime*2 > 0) {
            this.getImage().setTransparency(150-deathFadeTime*2);
        }
        if (deathFadeTime>=60) {deathFadeTime = 0; getWorld().removeObject(this); timePassed = 0;}
    }
    public void fadelong(){ //Fade out en kill object
        deathFadeTime += 1;
        if (220 - deathFadeTime > 0) {
            this.getImage().setTransparency(220 - deathFadeTime);
        }
        if (deathFadeTime >= 220) {deathFadeTime = 0; getWorld().removeObject(this); timePassed = 0;}
    }
    public void smoke(){
        deathTime = 60; //How long to be destroyed yes it's randomised now
        if (random3 == 1){ //3 random images
            setImage(smoke1);
            smoke1.scale((Options.blockSize) / 3,(Options.blockSize) / 3);
            smoke1.setTransparency(150);
        }
        else if (random3 == 2) {
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
}
