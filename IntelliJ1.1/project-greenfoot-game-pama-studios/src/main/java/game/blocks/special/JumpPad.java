package game.blocks.special;
import greenfoot.GreenfootImage;
import game.Options;
import game.entities.Player;
import game.Physics;


/**
 *
 */
public class JumpPad extends Physics {

    /** The image for when the jumppad is in the down position (compressed, something is on it) */
    private GreenfootImage jumppadDown = new GreenfootImage("243.png");
    /** The image for when the jumppad is in the up position (default) */
    private GreenfootImage jumppadUp = new GreenfootImage("244.png");
    /** Boolean for if the jumpPad is currently being held */
    public boolean holding;

    /**
     * Sets the image and scales everything properly, defaults the image to the up position
     *
     * @param ID    Does nothing yet
     */
    public JumpPad(int ID){
        jumppadDown.scale((Options.blockSize),(Options.blockSize));
        jumppadUp.scale((Options.blockSize),(Options.blockSize));
        setImage(jumppadUp);
        holding = false;
    }
    /**
     * Act method to keep the spring working fine with physics and compress when something is touching it
     * also keeps the gravity going.
     */
    public void act(){
        setDoubleX(getX());
        setDoubleY(getY());
        if (isTouching(Player.class) || isTouching(Bomb.class) || isTouching(JumpPad.class)) {
            setImage(jumppadDown);
        } else if (getImage() != jumppadUp){
            setImage(jumppadUp);
        }
        if (!holding || Player.dead) {
            updateGravity();
            jumpPad();
        }
    }
}
