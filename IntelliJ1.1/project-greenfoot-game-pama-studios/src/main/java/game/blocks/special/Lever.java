package game.blocks.special;

import greenfoot.Greenfoot;

import greenfoot.*;
import game.Options;
import game.entities.Player;
import game.blocks.Blocks;

import java.util.List;

/**
 *
 */
public class Lever extends Blocks {

    /** ID of the lever to link it to the door, if doorID and leverID are the same they are affected by eachother */
    int leverID = 1;
    /** Cooldown timer for when the lever can be switched */
    private int cooldownTimer = 0;
    /** Timer for the animation of the lever being switched over */
    private int animationTimer;
    /** Boolean for if the lever is currently activated or not */
    boolean isSwitched;
    /** Image for when the lever is inactive */
    private GreenfootImage left = new GreenfootImage("226.png");
    /** Image for when the lever is in the middle */
    private GreenfootImage middle = new GreenfootImage ("227.png");
    /** Image for when the lever is active */
    private GreenfootImage right = new GreenfootImage ("228.png");
    //Sound effects
    static GreenfootSound doorClose = new GreenfootSound("soundeffects/door_close.wav");
    static GreenfootSound doorOpen = new GreenfootSound("soundeffects/door_open.wav");

    /**
     * Calls for the constructor in Blocks.java to set the image of the tile.
     * All of these images will be 1x1 in the grid. (Options.blockSize * Options.blockSize)
     *
     * @param ID    used to get what file should be displayed as the image of this object
     * @see Blocks#Blocks(int)
     */
    public Lever(int ID, int leverID) {
        super(ID);
        this.leverID = leverID;
        left.scale((Options.blockSize),(Options.blockSize));
        middle.scale((Options.blockSize),(Options.blockSize));
        right.scale((Options.blockSize),(Options.blockSize));
    }

    /**
     * Act method that gets called every frame to update the lever animation and system as well as handle the cooldown
     */
    public void act()
    {
        // Methods for Lever Anim en sys.
        leverAnimation();
        leverSystem();

        //Timer
        if (cooldownTimer > 0) {
            cooldownTimer--;
        }

    }

    /**
     * Method for switching the lever when holding down the interact button
     */
    private void leverSystem() {
        if (Greenfoot.isKeyDown(Options.interact)  && getOneIntersectingObject(Player.class) != null) {
            animationTimer ++;
            if (cooldownTimer <= 0) {
                animationTimer = 0;
                cooldownTimer = 100;
                if (getImage() == left) {
                    isSwitched = false;
                }
                if (getImage() == right) {
                    isSwitched = true;
                }
            }
        }
    }

    /**
     * Method to update the image when it's being switched around
     */
    private void leverAnimation() {
        if (!isSwitched) {
            if (animationTimer== 30) {
                setImage(middle);
            }
            if (animationTimer== 60) {
                setImage(right);
                switchdoor("open");
                doorOpen.setVolume(Options.soundeffectVolume);
                doorOpen.play();
            }
        }
        if (isSwitched) {
            if (animationTimer== 30){
                setImage(middle);
            }
            if (animationTimer== 60){
                setImage(left);
                switchdoor("closed");
                doorClose.setVolume(Options.soundeffectVolume);
                doorClose.play();
            }
        }
    }

    /**
     * Method to switch all doors to whatever switchto says
     *
     * @param switchTo      What state the doors should be switched to, open or closed
     */
    private void switchdoor(String switchTo) {
        //find all door objects into an array
        List<Door> deuren = (List<Door>) (getWorld().getObjects(Door.class));
        for(Door deur : deuren) {
            if (leverID == deur.doorID) {
                if (switchTo.equals("open")) {
                    deur.open();
                } else {
                    deur.close();
                }
            }
        }

    }

}