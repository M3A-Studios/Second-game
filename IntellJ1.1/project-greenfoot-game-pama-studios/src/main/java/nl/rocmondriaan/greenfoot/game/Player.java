package nl.rocmondriaan.greenfoot.game;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.util.List;

public class Player extends Physics
{
    private Actor popup;
    static int health;
    private boolean started;
    private boolean moving;
    private double leftKeyDown;
    private double rightKeyDown;
    private double spaceKeyDown;
    private int atime;
    static boolean dead;
    private int dyingAnimation;

    //sizes for the images
    private int playerWidth = Options.blockSize;          //1 block
    private int playerHeight = Options.blockSize * 3 / 2; //1.5 blocks
    
    //Images 
    private GreenfootImage climb1 = new GreenfootImage("alienGreen_climb1.png");
    private GreenfootImage climb2 = new GreenfootImage("alienGreen_climb2.png");
    private GreenfootImage front = new GreenfootImage("alienGreen_front.png");
    private GreenfootImage deadimg = new GreenfootImage("alienGreen_dead.png");
    private GreenfootImage walk1 = new GreenfootImage("alienGreen_walk1.png");
    private GreenfootImage walk2 = new GreenfootImage("alienGreen_walk2.png");
    private GreenfootImage walk1m = new GreenfootImage("alienGreen_walk1.png");
    private GreenfootImage walk2m = new GreenfootImage("alienGreen_walk2.png");
    private GreenfootImage jump = new GreenfootImage("alienGreen_jump.png");
    private GreenfootImage jumpm = new GreenfootImage("alienGreen_jump.png");

    /**
     * Constructor method used to simply size the images and set it, also sets started to false
     * which is used in the first frame of act() to get the player's spawn location
     */
    Player() {
        dead = false;
        atime = 0;
        dyingAnimation = 0;
        health = 6;
        started = false;
        Globals.levelCoinsCollected = 0;

        climb1.scale(playerWidth,playerHeight);
        climb2.scale(playerWidth,playerHeight);
        front.scale(playerWidth,playerHeight);
        jump.scale(playerWidth,playerHeight);
        jumpm.scale(playerWidth,playerHeight);
        deadimg.scale(playerWidth,playerHeight);
        walk1.scale(playerWidth,playerHeight);
        walk2.scale(playerWidth,playerHeight);
        walk1m.scale(playerWidth,playerHeight);
        walk2m.scale(playerWidth,playerHeight);
        walk1m.mirrorHorizontally();
        walk2m.mirrorHorizontally();
        jumpm.mirrorHorizontally();

        setImage(front);
    }

    /**
     * Act method gets executed every frame Checks for various methods. also checks if game has started and if not
     * it will set the startlocation for physics
     */
    public void act() {
        //check if started, if not set the start coordinates for physics
        if (!started) {
            started = true;
            setDoubleX(getX());
            setDoubleY(getY());
        }
        //execute normal gameplay as long as the player is alive
        if (!deathCheck()) {
            entityOffset(); //make it immune to camera scrolling
            updateGravity();
            walkingAnim();
            checkinput();
            standingStill();
            checkinput();
            isTouchingObject();
            collectCoin();
            checkCheckpoint();
        } else {
            deadAnimation();
        }
    }

    private void deadAnimation() {
        if (dead) {
            if (dyingAnimation < 200) {
                animateMovement("Death");
                dyingAnimation += 1;
            } else {
                Greenfoot.setWorld(new Levels(LevelSelector.getSelectedLevel(), Levels.activeCheckpoint));
            }
        }
    }
    /**
     * Check for various player inputs
     *
     * escape = back to level selector
     *
     * these keys still need to be put into options as rn only interact is dynamic and the rest is hardcoded
     *
     * jumpkey = jump
     * leftkey = moving left
     * rightkey = moving right
     * downkey = moving down
     * interact = interact with object
     */
    private void checkinput() {
        if (Greenfoot.isKeyDown("escape")) {
            Greenfoot.setWorld(new LevelSelector(Globals.currentLevel));
        }
        if (Greenfoot.isKeyDown("space")) {
            if (onGround()) {
                spaceKeyDown = 0;
                jump(15);
            } else {
                spaceKeyDown += 1;
                if (spaceKeyDown < 60) {
                    if (vSpeed < 0) {
                        jump(0.15);
                    }
                }
            }
        }
        moving = false;
        if (Greenfoot.isKeyDown("d") && (!Greenfoot.isKeyDown("a"))) { //&& (!Greenfoot.isKeyDown("a")) toegevoegd voor links rechts bug
            rightKeyDown += 1;
            if (rightKeyDown > 60) rightKeyDown = 60;
            double speed = 2 + rightKeyDown / 60.0;
            if (canMoveRight(speed)) {
                moveRight(speed);
                moving = true;
            }
        } else {
            rightKeyDown = 0;
        }
        if (Greenfoot.isKeyDown("a") && (!Greenfoot.isKeyDown("d"))) { //&& (!Greenfoot.isKeyDown("d")) toegevoegd voor links rechts bug
            leftKeyDown += 1;
            if (leftKeyDown > 60) leftKeyDown = 60;
            double speed = 2 + leftKeyDown / 60.0;
            if (canMoveLeft(speed)) {
                moveLeft(speed);
                moving = true;
            }
        } else {
            leftKeyDown = 0;
        }

        if (onLadder()) {
            if (Greenfoot.isKeyDown("W")) {
                setRelativeLocation(0, -3);
            }
            if (Greenfoot.isKeyDown("S") && !onGround()) {
                setRelativeLocation(0, 3);
            }
        }
        if (Greenfoot.isKeyDown("X")) {
            health = health - 1;
        }

        if (deathCheck() && !dead) {
            Particles beam = new Particles("beam");
            getWorld().addObject(beam, getX(), getY());
            dead = true;
            dyingAnimation = 0;
        }
    }

    /**
     *Checks if the player is touching a lever and gives a popup based on that
     */
    private void isTouchingObject() {
        Actor lever = getOneIntersectingObject(Lever.class);

        if (lever != null)
        {
            if (popup == null) {
                popup = new PopUp(Options.interact);
            }
            getWorld().addObject(popup, lever.getX(), lever.getY() - Options.blockSize * 2);

        } else {
            if (popup != null) {
                getWorld().removeObject(popup);
                popup = null;
            }
        }
    }

    /**
     * Checks if the player is standing still and isnt on a ladder, if so sets the image to the player looking forward
     */
    private void standingStill(){
        if (!moving && !onLadder()){ //&& onGround() weg gehaalt --Michael
            setImage(front);
            atime = 0; //Reset animation timer
        }
    }


    /**
     * Checks what animation should be played and based on that calls animateMovement with said animation
     */
    private void walkingAnim(){
        if (!onLadder() && Greenfoot.isKeyDown("d") && onGround()){
            animateMovement("Right");
        }
        if (!onLadder() && Greenfoot.isKeyDown("a") && onGround()){
            animateMovement("Left");
        }
        if (onLadder() && Greenfoot.isKeyDown("w") || onLadder() && Greenfoot.isKeyDown("s")){
            animateMovement("Ladder");
        }
        if (!onLadder() && Greenfoot.isKeyDown("space") && !onGround()){ //start
            if (Greenfoot.isKeyDown("a")){
                animateMovement("Jumpm");
            }
            else if (Greenfoot.isKeyDown("d")){
                animateMovement("Jump");
            }
        }
    }

    /**
     * Handles animations for the character
     *
     * @param Direction     String of what direction the player is moving in to know which animations to use
     */
    private void animateMovement(String Direction){
        atime = atime+1;
        if (atime>10) {atime=0;}
        if (Direction.equals("Right")){
            if (atime == 0) {setImage(walk1);}
            else if (atime == 5) {setImage(walk2);}
        }
        else if (Direction.equals("Left")){
            if (atime == 0) {setImage(walk1m);}
            else if (atime == 5) {setImage(walk2m);}
        }
        else if (Direction.equals("Jump")){
            if (atime == 0) {setImage(jump);}
            else if (atime == 5) {setImage(jump);}
        }
        else if (Direction.equals("Jumpm")){
            if (atime == 0) {setImage(jumpm);}
            else if (atime == 5) {setImage(jumpm);}
        }
        else if (Direction.equals("Ladder")){
            if (atime == 0) {setImage(climb1);}
            else if (atime == 5) {setImage(climb2);}
        }
        else if (Direction.equals("Death")) {
            setImage(deadimg);
            if (atime > 0 && atime < 10) {setRelativeLocation(0,-5); }
        }
    }

    /**
     * Simple method to check if the health has reached zero or if the player has fallen into the void
     *
     * @return      returns true or false, true being that the player has died
     */
    private boolean deathCheck() {
        if (health == 0 || getY() == Options.screenHeight - 2) {
            return true;
        }
            return false;
    }

    /**
     * Checks if you are intersecting with a coin, if so it removes the coin and adds it to your levelCoinsCollected
     */
    private void collectCoin() {
        Coins coin = (Coins) getOneIntersectingObject(Coins.class);
        if (coin != null) {
            if (coin.CoinID == 166) {
                Globals.levelCoinsCollected += 1;
                getWorld().removeObject(coin);
            }
            if (coin.CoinID == 168) {
                Globals.levelCoinsCollected += 10;
                getWorld().removeObject(coin);
            }
            if (coin.CoinID == 167) {
                Globals.levelCoinsCollected += 20;
                getWorld().removeObject(coin);
            }
        }
    }

    private void checkCheckpoint() {
        Checkpoint checkpoint = (Checkpoint) getOneIntersectingObject(Checkpoint.class);
        if (checkpoint != null) {
            List<Checkpoint> allCheckpoints = (List<Checkpoint>) (getWorld().getObjects(Checkpoint.class));
            for(Checkpoint currentcheckpoint : allCheckpoints) {
                currentcheckpoint.active = false;
            }
            Levels.activeCheckpoint = checkpoint.getCheckpoint();
            checkpoint.active = true;
        }
    }
}
