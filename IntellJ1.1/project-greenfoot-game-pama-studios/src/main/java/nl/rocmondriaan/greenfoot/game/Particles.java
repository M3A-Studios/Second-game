package nl.rocmondriaan.greenfoot.game;
import greenfoot.Actor;
import greenfoot.GreenfootImage;
import java.util.Random;

public class Particles extends Actor {
    private GreenfootImage smoke1 = new GreenfootImage("WhiteSmoke1.png");
    private GreenfootImage smoke2 = new GreenfootImage("WhiteSmoke2.png");
    private GreenfootImage smoke3 = new GreenfootImage("WhiteSmoke3.png");
    private GreenfootImage beam = new GreenfootImage("BeamVanAllah.png");

    private Random rn = new Random();
    private int random3 = rn.nextInt(3) + 1; //Randomly 1-3
    private int randomImage = random3;
    private int deathFadeTime;
    private int deathTime;
    private int timePassed;
    private String type;

    public void act() {
        setLocation(getX(), getY() - 1); //Making it fly up
        timePassed += 1;
        if (type.equals("smoke")) {
            if (timePassed >= deathTime) {
                fade();
            }
        }
        if (type.equals("beam")) {
            if (timePassed >= deathTime) {
                fadelong();
            }
        }
    }

    Particles(String type) {
        this.type = type;
        if (type.equals("smoke")){
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
        if (type.equals("beam")){
            setImage(beam);
            beam.setTransparency(150);
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
    }
}
