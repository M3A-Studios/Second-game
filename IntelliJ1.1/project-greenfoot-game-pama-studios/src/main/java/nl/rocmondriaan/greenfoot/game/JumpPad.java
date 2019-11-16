package nl.rocmondriaan.greenfoot.game;
import greenfoot.Actor;
import greenfoot.GreenfootImage;


public class JumpPad extends Blocks {
    private GreenfootImage jumppadDown = new GreenfootImage("243.png");
    private GreenfootImage jumppadUp = new GreenfootImage("244.png");
    boolean holding;

    JumpPad(int ID){
        super(ID);
        jumppadDown.scale((Options.blockSize),(Options.blockSize));
        jumppadUp.scale((Options.blockSize),(Options.blockSize));
        setImage(jumppadUp);
        holding = false;
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
