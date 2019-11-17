package nl.rocmondriaan.greenfoot.game;
import greenfoot.Actor;
import greenfoot.GreenfootImage;


public class JumpPad extends Physics {
    private GreenfootImage jumppadDown = new GreenfootImage("243.png");
    private GreenfootImage jumppadUp = new GreenfootImage("244.png");
    boolean holding;

    JumpPad(int ID){
        GreenfootImage image = new GreenfootImage ((ID) + ".png");
        image.scale((Options.blockSize),(Options.blockSize)); //scale to the size of 1 block
        setImage(image);
        jumppadDown.scale((Options.blockSize),(Options.blockSize));
        jumppadUp.scale((Options.blockSize),(Options.blockSize));
        setImage(jumppadUp);
        holding = false;
    }

    public void act(){
        setDoubleX(getX());
        setDoubleY(getY());
        if (isTouching(Player.class) || isTouching(Bomb.class) || isTouching(JumpPad.class)) {
            setImage(jumppadDown);
        } else if (getImage() != jumppadUp){
            setImage(jumppadUp);
        }
        if (!holding) {
            updateGravity();
            jumpPad();
        }
    }
}
