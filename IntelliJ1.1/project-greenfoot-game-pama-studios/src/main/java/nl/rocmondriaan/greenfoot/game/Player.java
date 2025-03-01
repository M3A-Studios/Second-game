package nl.rocmondriaan.greenfoot.game;

import com.sun.xml.internal.bind.v2.model.core.ID;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class Player extends Physics {
    //
    private Actor popup;
    static int health;
    private boolean started;
    private boolean moving;
    private double leftKeyDown;
    private double rightKeyDown;
    private double spaceKeyDown;
    static String inventoryItem;
    static boolean carryingObject;
    private int atime;
    static boolean dead;
    private int dyingAnimation;
    static boolean won;
    private int endingAnimation;
    static String holding;
    static String itemToDrop;
    static int dropCooldown = 60;
    static int pickUpCooldown;
    static boolean canTakeDmg;
    private int deathTimer;
    static String lastDroppedItem;
    static int lastItemCD = 30;


    //sizes for the images
    private int playerWidth = Options.blockSize;          //1 block
    private int playerHeight = Options.blockSize * 3 / 2; //1.5 blocks

    //Images 
    private GreenfootImage climb1 = new GreenfootImage("alien" + Options.player1Color + "_climb1.png");
    private GreenfootImage climb2 = new GreenfootImage("alien" + Options.player1Color + "_climb2.png");
    private GreenfootImage front = new GreenfootImage("alien" + Options.player1Color + "_front.png");
    private GreenfootImage deadimg = new GreenfootImage("alienGreen_dead.png"); //no death images for other aliens
    private GreenfootImage walk1 = new GreenfootImage("alien" + Options.player1Color + "_walk1.png");
    private GreenfootImage walk2 = new GreenfootImage("alien" + Options.player1Color + "_walk2.png");
    private GreenfootImage walk1m = new GreenfootImage("alien" + Options.player1Color + "_walk1.png");
    private GreenfootImage walk2m = new GreenfootImage("alien" + Options.player1Color + "_walk2.png");
    private GreenfootImage jump = new GreenfootImage("alien" + Options.player1Color + "_jump.png");
    private GreenfootImage jumpm = new GreenfootImage("alien" + Options.player1Color + "_jump.png");

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
        won = false;
        endingAnimation = 0;
        Globals.levelCoinsCollected = 0;
        Globals.levelScore = 0;
        holding = ""; //Michael
        inventoryItem = "";
        lastDroppedItem = "";
        carryingObject = false;
        canTakeDmg = true;
        LockedBlocks.blocksToUnlock = new ArrayList<LockedBlocks>();


        climb1.scale(playerWidth, playerHeight);
        climb2.scale(playerWidth, playerHeight);
        front.scale(playerWidth, playerHeight);
        jump.scale(playerWidth, playerHeight);
        jumpm.scale(playerWidth, playerHeight);
        deadimg.scale(playerWidth, playerHeight);
        walk1.scale(playerWidth, playerHeight);
        walk2.scale(playerWidth, playerHeight);
        walk1m.scale(playerWidth, playerHeight);
        walk2m.scale(playerWidth, playerHeight);
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
        if (!dead && deathCheck() && !won) {
            dead = true;
            Particles beam = new Particles("beam");
            getWorld().addObject(beam, getX(), getY());
            dyingAnimation = 0;
        }
        if (!dead && !won) {
            entityOffset(); //make it immune to camera scrolling
            updateGravity();
            walkingAnim();
            checkinput();
            standingStill();
            checkinput();
            isTouchingObject();
            collectCoin();
            checkCheckpoint();
            jumpPad();
            checkFlagpole();
            holdObject();
            dropObject();
            cooldowns();
            takeDmg();
            deathTimer();
        } else if (won) {
            winAnimation();
            isTouchingObject();
            collectCoin();
            jumpPad();
            winConfetti();
            updateGravity();
        } else {
            deadAnimation();
        }
    }
    private void deadAnimation() {
        if (!won) {
            if (dyingAnimation < 200) {
                animateMovement("Death");
                dyingAnimation += 1;
            } else {
                Greenfoot.setWorld(new Levels(LevelSelector.getSelectedLevel(), Levels.activeCheckpoint));
            }
        }
    }

    private void cooldowns() {
        if (pickUpCooldown > 0) {
            pickUpCooldown--;
        }
        if (dropCooldown > 0) {
            dropCooldown--;
        }
        if (lastItemCD > 0)
        {
            lastItemCD --;
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
            //save to save file
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
                        jumpExtend(0.15);
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
        if (Greenfoot.isKeyDown("k")) {
            health = health - 6;
        }



        if (Greenfoot.isKeyDown(Options.dropItem)) {
            Inventory();
        }
    }

    public void Inventory() {
        itemToDrop = inventoryItem;
        if (!inventoryItem.equals("")) {
            lastDroppedItem = inventoryItem;
        }
        switch (itemToDrop) {
            case (""):
                break;
            case ("greenKey"):
                Keys greenKey = new Keys(186);
                getWorld().addObject(greenKey, getX(), getY());
                inventoryItem = "";
                break;
            case ("blueKey"):
                Keys blueKey = new Keys(185);
                getWorld().addObject(blueKey, getX(), getY());
                inventoryItem = "";
                break;
            case ("yellowKey"):
                Keys yellowKey = new Keys(188);
                getWorld().addObject(yellowKey, getX(), getY());
                inventoryItem = "";
                break;
            case ("redKey"):
                Keys redKey = new Keys(187);
                getWorld().addObject(redKey, getX(), getY());
                inventoryItem = "";
                break;
        }
    }

    /**
     * Checks if the player is touching a lever and gives a popup based on that
     */
    private void isTouchingObject() {
        Actor lever = getOneIntersectingObject(Lever.class);

        if (lever != null) {
            if (popup == null) {
                popup = new PopUp(Options.interact);
            }
            getWorld().addObject(popup, lever.getX(), lever.getY() - Options.blockSize * 2);
            if (Greenfoot.isKeyDown("e")) { //Sets image if key down
                popup.getImage().scale((int) (Options.blockSize * 1.2), (int) (Options.blockSize * 1.2));
            } else {
                popup.getImage().scale((int) (Options.blockSize * 1.4), (int) (Options.blockSize * 1.4));
            }
            //Sets to normal size if not down
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
    private void standingStill() {
        if (!moving && !onLadder()) { //&& onGround() weg gehaalt --Michael
            setImage(front);
            atime = 0; //Reset animation timer
        }
    }


    /**
     * Checks what animation should be played and based on that calls animateMovement with said animation
     */
    private void walkingAnim() {
        if (!onLadder() && Greenfoot.isKeyDown("d") && onGround()) {
            animateMovement("Right");
        }
        if (!onLadder() && Greenfoot.isKeyDown("a") && onGround()) {
            animateMovement("Left");
        }
        if (onLadder() && Greenfoot.isKeyDown("w") || onLadder() && Greenfoot.isKeyDown("s")) {
            animateMovement("Ladder");
        }
        if (!onLadder() && Greenfoot.isKeyDown("space") && !onGround()) { //start
            if (Greenfoot.isKeyDown("a")) {
                animateMovement("Jumpm");
            } else if (Greenfoot.isKeyDown("d")) {
                animateMovement("Jump");
            }
        }
    }

    /**
     * Handles animations for the character
     *
     * @param Direction String of what direction the player is moving in to know which animations to use
     */
    private void animateMovement(String Direction) {
        atime = atime + 1;
        if (atime > 10) {
            atime = 0;
        }
        if (Direction.equals("Right")) {
            if (atime == 0) {
                setImage(walk1);
            } else if (atime == 5) {
                setImage(walk2);
            }
        } else if (Direction.equals("Left")) {
            if (atime == 0) {
                setImage(walk1m);
            } else if (atime == 5) {
                setImage(walk2m);
            }
        } else if (Direction.equals("Jump")) {
            if (atime == 0) {
                setImage(jump);
            } else if (atime == 5) {
                setImage(jump);
            }
        } else if (Direction.equals("Jumpm")) {
            if (atime == 0) {
                setImage(jumpm);
            } else if (atime == 5) {
                setImage(jumpm);
            }
        } else if (Direction.equals("Ladder")) {
            if (atime == 0) {
                setImage(climb1);
            } else if (atime == 5) {
                setImage(climb2);
            }
        } else if (Direction.equals("Death")) {
            setImage(deadimg);
            if (atime > 0 && atime < 10) {
                if (getX() - getImage().getWidth()/2 < Options.screenWidth) {
                    setRelativeLocation(0, -5);
                }
            }
        }
    }

    /**
     * Simple method to check if the health has reached zero or if the player has fallen into the void
     *
     * @return returns true or false, true being that the player has died
     */
    private boolean deathCheck() {
        return health <= 0 || getY() >= Options.screenHeight - 2;
    }

    /**
     * Checks if you are intersecting with a coin, if so it removes the coin and adds it to your levelCoinsCollected
     */
    private void collectCoin() {
        Coins coin = (Coins) getOneIntersectingObject(Coins.class);
        if (coin != null) {
            getWorld().removeObject(coin);
            if (coin.CoinID == 166) {
                Globals.levelCoinsCollected += 1;
                Globals.levelScore += 10;
            }
            if (coin.CoinID == 168) {
                Globals.levelCoinsCollected += 10;
                Globals.levelScore += 100;
            }
            if (coin.CoinID == 167) {
                Globals.levelCoinsCollected += 20;
                Globals.levelScore += 200;
            }
        }
    }

    private void checkCheckpoint() {
        Checkpoint checkpoint = (Checkpoint) getOneIntersectingObject(Checkpoint.class);
        if (checkpoint != null) {
            List<Checkpoint> allCheckpoints = (List<Checkpoint>) (getWorld().getObjects(Checkpoint.class));
            for (Checkpoint currentcheckpoint : allCheckpoints) {
                currentcheckpoint.active = false;
            }
            Levels.activeCheckpoint = checkpoint.getCheckpoint();
            checkpoint.active = true;
        }
    }

    private void checkFlagpole() {
        Flagpole flagpole = (Flagpole) getOneIntersectingObject(Flagpole.class);
        if (flagpole != null && !won) {
            won = true;
            Globals.levelScore += 1000;
        }
    }

    private void winAnimation() {
        endingAnimation++;
        if (endingAnimation < 200) {
            if (onGround()) {
                if (canMoveRight(5) || getX() - getImage().getWidth()/2 < Options.screenWidth) {
                    moveRight(5);
                    moving = true;
                    animateMovement("Right");
                    if (getX() - getImage().getWidth() > Options.screenWidth) {
                        endingAnimation = 999;
                    }
                } else {
                    System.out.println("Out of bounds");
                }
            } else if (isTouching(Flagpole.class)) {
                if (vSpeed > 5)
                    vSpeed = 5;
            }
        } else {
            Globals.totalCoinsCollected += Globals.levelCoinsCollected;
            Globals.totalScore += Globals.levelScore;
            if (Globals.currentLevel == Globals.levelsUnlocked) {
                Globals.levelsUnlocked ++;
            }
            Greenfoot.setWorld(new LevelSelector(LevelSelector.getSelectedLevel()));
        }
    }

    private void winConfetti() {
        Particles torchFlameL = new Particles("confettim");
        Particles torchFlameR = new Particles("confetti");
        Random rn = new Random();
        int randomX = rn.nextInt(40) - 9;
        getWorld().addObject(torchFlameL, 30 + randomX, Options.screenHeight);
        getWorld().addObject(torchFlameR, (Options.screenWidth - 30) - randomX, Options.screenHeight);
    }


    private void holdObject(){ //Michael
        moveToPlayer(); //Moves holding object
        if(Greenfoot.isKeyDown(Options.interact)){
            if(isTouching(Bomb.class) && holding.equals("")){
                Bomb bomb = (Bomb) getOneIntersectingObject(Bomb.class);
                if (bomb != null) {
                    if (!bomb.holding && !bomb.dropped) {
                        bomb.holding = true;
                        holding = "Bomb";
                    }
                }
            }
            if(isTouching(JumpPad.class) && holding.equals("")){
                JumpPad jumppad = (JumpPad) getOneIntersectingObject(JumpPad.class);
                if (jumppad != null) {
                    if (!jumppad.holding) {
                        jumppad.holding = true;
                        jumppad.setLocation(this.getX(), this.getY());
                        holding = "JumpPad";
                    }
                }
            }
        }
    }
    private void dropObject(){ //Michael
        if(Greenfoot.isKeyDown(Options.dropObject)){ //Should be drop key in options
            if(holding.equals("Bomb")) {
                Bomb bomb = (Bomb) getOneIntersectingObject(Bomb.class);
                if (bomb != null) {
                    if (bomb.holding) {
                        holding = "";
                        bomb.holding = false;
                        bomb.setLocation(this.getX(), this.getY() + 17);
                        bomb.dropped = true;
                    }
                }
            }
            if(holding.equals("JumpPad")) {
                JumpPad jumppad = (JumpPad) getOneIntersectingObject(JumpPad.class);
                if (jumppad != null) {
                    if (jumppad.holding) {
                        holding = "";
                        jumppad.setLocation(this.getX(), this.getY() + 17);
                        jumppad.holding = false;
                    }
                }
            }
        }
    }
    public void moveToPlayer() {
        if(Player.holding.length() > 0){
            Bomb bomb = (Bomb) getOneIntersectingObject(Bomb.class); //Make this only see the object that you hold
            if (bomb != null) {
                if (bomb.holding && !bomb.dropped) {
                    bomb.setLocation(this.getX(), this.getY());
                }
            }
            JumpPad jumppad = (JumpPad) getOneIntersectingObject(JumpPad.class); //Make this only see the object that you hold
            if (jumppad != null) {
                if (jumppad.holding) {
                    jumppad.setLocation(this.getX(), this.getY());
                }
            }
        }
    }

    private void deathTimer(){ //Adds delay to taking dmg
        if(!canTakeDmg){
            deathTimer++;
            if (deathTimer > 40) {
                canTakeDmg = true;
                deathTimer = 0;
            }
        }
    }

    private void takeDmg(){
        Slime();
        Lava();
        spikes();
    }

    private void Slime(){
        Slime slime = (Slime) getObjectBelowOfClass(Slime.class);
        Slime slimedmg = (Slime) getOneIntersectingObject(Slime.class);
        if (slime != null) {
            if (!slime.dead) {
                slime.dead = true;
                vSpeed = 0;
                jump(20);
            }
        }
        if(slimedmg != null){
            if (!slimedmg.dead){
                if(canTakeDmg) {
                    Player.health -= 0.5;
                    canTakeDmg = false;
                }
            }
        }
    }

    private void Lava(){
        if (isTouching(Lava.class)){
            Player.health = 0;
        }
    }

    private void spikes(){
        if (isTouching(Spikes.class)){
            if(canTakeDmg) {
                Player.health -= 0.5;
                canTakeDmg = false;
            }
        }
    }
}
