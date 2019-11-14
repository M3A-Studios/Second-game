package nl.rocmondriaan.greenfoot.game;
import greenfoot.Actor;
import greenfoot.GreenfootImage;
import java.util.Random;


public class JumpPad extends Blocks {
    private GreenfootImage jumppadDown = new GreenfootImage("250.png");
    private GreenfootImage jumppadUp = new GreenfootImage("251.png");
    private int atime;

    JumpPad(int ID){
        super(ID);
    }

    public void act(){
        setImage(jumppadDown);
        jumpPad();
        jumppadDown.scale((Options.blockSize),(Options.blockSize));
        jumppadUp.scale((Options.blockSize),(Options.blockSize));
    }

    public void jumpPad() {
        if (isTouching(Player.class)){
            atime=atime + 1;
            if (atime > 6) {setImage(jumppadUp);}
            if (atime >= 15) {atime=0; setImage(jumppadDown);}//Reset
        }
    }
}
