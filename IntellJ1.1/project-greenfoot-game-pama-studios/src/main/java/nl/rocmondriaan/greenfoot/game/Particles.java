package nl.rocmondriaan.greenfoot.game;
import greenfoot.*;
import nl.rocmondriaan.greenfoot.engine.*;
import java.util.*;

public class Particles extends Mover {
    private GreenfootImage smoke1 = new GreenfootImage("WhiteSmoke1.png");
    private GreenfootImage smoke2 = new GreenfootImage("WhiteSmoke2.png");
    private GreenfootImage smoke3 = new GreenfootImage("WhiteSmoke3.png");

    Random rn = new Random();
    private int random3 = rn.nextInt(3) + 1; //Randomly 1-3
    private int randomImage = random3;
    private int deathFadeTime;
    private int deathTime;
    private int timePassed;

    public void act() {
        setLocation(getX(), getY() - 1); //Making it fly up
        timePassed += 1;
        if (timePassed >= deathTime) {
            fade();
        }
    }

    Particles(String type) {
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
}