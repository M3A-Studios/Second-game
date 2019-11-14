package nl.rocmondriaan.greenfoot.game;
import greenfoot.Actor;
import greenfoot.GreenfootImage;
import java.util.Random;


public class JumpPad extends Blocks {
    private GreenfootImage jumppadDown = new GreenfootImage("250.png");
    private GreenfootImage jumppadUp = new GreenfootImage("251.png");

    JumpPad(int ID){
        super(ID);
        jumppadDown.scale((Options.blockSize),(Options.blockSize));
        jumppadUp.scale((Options.blockSize),(Options.blockSize));
        setImage(jumppadUp);
    }

    public void act(){
        if (!isTouching(Player.class)) {
            setImage(jumppadUp);
        }
    }

    void jumpPad() {
        setImage(jumppadDown);
    }
}
