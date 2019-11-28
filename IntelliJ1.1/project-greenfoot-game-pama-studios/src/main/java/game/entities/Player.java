package game.entities;

import game.entities.enemies.Bee;
import game.entities.enemies.Blade;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import game.*;
import game.blocks.special.Coins;
import game.blocks.normal.Lava;
import game.blocks.normal.Spikes;
import game.blocks.special.*;
import game.entities.enemies.Slime;
import game.overlays.PopUp;
import game.DesertStorm;
import game.world.level.Levels;
import game.world.levelselector.LevelSelector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player extends Physics {

    public int player;

    private Actor popup;
    public static int health;
    private boolean started;
    private boolean moving;
    private double leftKeyDown;
    private double rightKeyDown;
    private double spaceKeyDown;
    public static String inventoryItem;
    private double animationTimer;
    public static boolean dead;
    private int dyingAnimation;
    public static boolean won;
    private int endingAnimation;
    private String holding;
    private int dropCooldown = 60;
    public int pickUpCooldown;
    public boolean canTakeDmg;
    private int dmgTimer;
    public String lastDroppedItem;
    public int lastItemCD = 30;
    private static boolean player2exists;

    //sizes for the images
    private int playerWidth = Options.blockSize;          //1 block
    private int playerHeight = Options.blockSize * 3 / 2; //1.5 blocks

    //Images
    private GreenfootImage climb1;
    private GreenfootImage climb2;
    private GreenfootImage front;
    private GreenfootImage deadimg;
    private GreenfootImage walk1;
    private GreenfootImage walk2;
    private GreenfootImage walk1m;
    private GreenfootImage walk2m;
    private GreenfootImage jump;
    private GreenfootImage jumpm;
    private GreenfootImage hit;
    private GreenfootImage hitm;
    private String knockbackDirection;


    /**
     * Constructor method used to simply size the images and set it, also sets started to false
     * which is used in the first frame of act() to get the player's spawn location
     */
    public Player(int player) {
        this.player = player;
        this.animationTimer = 0;
        this.dyingAnimation = 0;
        this.started = false;
        this.endingAnimation = 0;
        if (player == 1) {
            Globals.levelCoinsCollected = 0;
            Globals.levelScore = 0;
            inventoryItem = "";
            health = 6;
            player2exists = false;
        }
        this.holding = "";
        this.lastDroppedItem = "";
        this.canTakeDmg = true;
        LockedBlocks.blocksToUnlock = new ArrayList<LockedBlocks>();
        won = false;
        dead = false;

        String color;
        if (player == 2) {
            color = Options.player2Color;
        } else {
            color = Options.player1Color;
        }

        climb1 = new GreenfootImage("alien" + color+ "_climb1.png");
        climb2 = new GreenfootImage("alien" + color+ "_climb2.png");
        front = new GreenfootImage("alien" + color+ "_front.png");
        deadimg = new GreenfootImage("alien" + color + "_dead.png");
        walk1 = new GreenfootImage("alien" + color+ "_walk1.png");
        walk2 = new GreenfootImage("alien" + color+ "_walk2.png");
        walk1m = new GreenfootImage("alien" + color+ "_walk1.png");
        walk2m = new GreenfootImage("alien" + color+ "_walk2.png");
        jump = new GreenfootImage("alien" + color+ "_jump.png");
        jumpm = new GreenfootImage("alien" + color+ "_jump.png");
        hit = new GreenfootImage("alien" + color+ "_hit.png");
        hitm = new GreenfootImage("alien" + color+ "_hit.png");

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
        hit.scale(playerWidth, playerHeight);
        hitm.scale(playerWidth, playerHeight);
        hitm.mirrorHorizontally();
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
            entityOffset(); //fix camera scrolling issues
            checkForSecondPlayer();
            updateGravity();
            updateWind();
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
            dmgTimer();
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
    private void checkForSecondPlayer() {
        if (!player2exists && (Greenfoot.isKeyDown(Options.player2Left) || Greenfoot.isKeyDown(Options.player2Right) ||
                Greenfoot.isKeyDown(Options.player2Down) || Greenfoot.isKeyDown(Options.player2Up) || Greenfoot.isKeyDown(Options.player2Jump))) {
            getWorld().addObject(new Player(2), this.getX(), this.getY());
            player2exists = true;
        }
    }
    private void updateWind() {

        if (DesertStorm.moving && isTouching(DesertStorm.class)) {
            if (DesertStorm.movingLeft) {
                if (canMoveLeft(1)) moveLeft(1);
            } else {
                if (canMoveRight(1)) moveRight(1);
            }
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
        moving = false;
        String key = Greenfoot.getKey();
        if (key != null) {
            if (key.equals("escape")) {
                Greenfoot.setWorld(new LevelSelector(LevelSelector.getSelectedLevel()));
            }
        }
        if ((Greenfoot.isKeyDown(Options.player1Jump) && player == 1) || (Greenfoot.isKeyDown(Options.player2Jump) && player == 2)) {
            if (onGround()) {
                spaceKeyDown = 0;
                jump(15);
            } else {
                spaceKeyDown += 1;
                if (spaceKeyDown < 60) {
                    if (vSpeed < 0) {
                        jumpExtend(0.22);
                    }
                }
            }

            if (!onLadder() && !onGround()) { //start
                if ((Greenfoot.isKeyDown(Options.player1Left) && player == 1) || (Greenfoot.isKeyDown(Options.player2Left) && player == 2)) {
                    animateMovement("Jumpm");
                } else if ((Greenfoot.isKeyDown(Options.player1Right) && player == 1) || (Greenfoot.isKeyDown(Options.player2Right) && player == 2)) {
                    animateMovement("Jump");
                }
            }
        }
        if ((Greenfoot.isKeyDown(Options.player1Right) && !Greenfoot.isKeyDown(Options.player1Left) && player == 1)
                || (Greenfoot.isKeyDown(Options.player2Right) && (!Greenfoot.isKeyDown(Options.player2Left) && player == 2))) {
            rightKeyDown += 1;
            if (rightKeyDown > 60) rightKeyDown = 60;
            double speed = 2 + rightKeyDown / 60.0;
            if (canMoveRight(speed)) {
                moveRight(speed);
                moving = true;
                if (onGround()) animateMovement("Right");
            }
        } else {
            rightKeyDown = 0;
        }
        if ((Greenfoot.isKeyDown(Options.player1Left) && !Greenfoot.isKeyDown(Options.player1Right) && player == 1)
                || (Greenfoot.isKeyDown(Options.player2Left) && (!Greenfoot.isKeyDown(Options.player2Right) && player == 2))) {
            leftKeyDown += 1;
            if (leftKeyDown > 60) leftKeyDown = 60;
            double speed = 2 + leftKeyDown / 60.0;
            if (canMoveLeft(speed)) {
                moveLeft(speed);
                moving = true;
                if (onGround()) animateMovement("Left");
            }
        } else {
            leftKeyDown = 0;
        }
        if (onLadder()) {
            if ((Greenfoot.isKeyDown(Options.player1Up) && player == 1) || (Greenfoot.isKeyDown(Options.player2Up) && player == 2)) {
                setRelativeLocation(0, -3);
                animateMovement("Ladder");
            }
            if (((Greenfoot.isKeyDown(Options.player1Down) && player == 1) || (Greenfoot.isKeyDown(Options.player2Down) && player == 2)) && !onGround()) {
                setRelativeLocation(0, 3);
                animateMovement("Ladder");
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
        String itemToDrop = inventoryItem;
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
            if (Greenfoot.isKeyDown(Options.interact)) { //Sets image if key down
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
            animationTimer = 0; //Reset animation timer
        }
    }


    /**
     * Handles animations for the character
     *
     * @param Direction String of what direction the player is moving in to know which animations to use
     */
    private void animateMovement(String Direction) {

        animationTimer = animationTimer + 0.25;
        if (animationTimer > 10) {
            animationTimer = 0;
        }
        if (Direction.equals("Right")) {
            if (animationTimer == 0) {
                setImage(walk1);
            } else if (animationTimer == 5) {
                setImage(walk2);
            }
        } else if (Direction.equals("Left")) {
            if (animationTimer == 0) {
                setImage(walk1m);
            } else if (animationTimer == 5) {
                setImage(walk2m);
            }
        } else if (Direction.equals("Jump")) {
            if (animationTimer == 0) {
                setImage(jump);
            } else if (animationTimer == 5) {
                setImage(jump);
            }
        } else if (Direction.equals("Jumpm")) {
            if (animationTimer == 0) {
                setImage(jumpm);
            } else if (animationTimer == 5) {
                setImage(jumpm);
            }
        } else if (Direction.equals("Ladder")) {
            if (animationTimer == 0) {
                setImage(climb1);
            } else if (animationTimer == 5) {
                setImage(climb2);
            }
        } else if (Direction.equals("Death")) {
            setImage(deadimg);
            if (animationTimer > 0 && animationTimer < 10) {
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
            Globals.levelScore += coin.getValue() * 10;
            Globals.levelCoinsCollected += coin.getValue();
            getWorld().removeObject(coin);
        }
    }

    private void checkCheckpoint() {
        Checkpoint checkpoint = (Checkpoint) getOneIntersectingObject(Checkpoint.class);
        if (checkpoint != null) {
            List<Checkpoint> allCheckpoints = (List<Checkpoint>) (getWorld().getObjects(Checkpoint.class));
            for (Checkpoint currentcheckpoint : allCheckpoints) {
                currentcheckpoint.setActiveCheckpoint(false);
            }
            Levels.activeCheckpoint = checkpoint.getCheckpoint();
            checkpoint.setActiveCheckpoint(true);
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
                if (canMoveRight(5) || (getX() + getImage().getWidth() > Options.screenWidth && getX() - getImage().getWidth() < Options.screenWidth)) {
                    moveRight(5);
                    moving = true;
                    animateMovement("Right");
                    if (getX() - getImage().getWidth() > Options.screenWidth) {
                        endingAnimation = 999;
                    }
                }
            } else if (isTouching(Flagpole.class)) {
                if (vSpeed > 5)
                    vSpeed = 5;
            }
        } else {
            Globals.totalCoinsCollected += Globals.levelCoinsCollected;
            Globals.totalScore += Globals.levelScore;
            if (LevelSelector.getSelectedLevel() == Globals.levelsUnlocked) {
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
                        bomb.setLocation(this.getX(), this.getY());
                        bomb.dropped = true;
                    }
                }
            }
            if(holding.equals("JumpPad")) {
                JumpPad jumppad = (JumpPad) getOneIntersectingObject(JumpPad.class);
                if (jumppad != null) {
                    if (jumppad.holding) {
                        holding = "";
                        jumppad.setLocation(this.getX(), this.getY());
                        jumppad.holding = false;
                    }
                }
            }
        }
    }
    private void moveToPlayer() {
        if(holding.length() > 0){
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

    private void dmgTimer(){ //Adds delay to taking dmg
        if(canTakeDmg){ getImage().setTransparency(255); }
        if(!canTakeDmg){
            dmgTimer++;
            if (dmgTimer == 10){this.getImage().setTransparency(50); jump(5);}
            if (dmgTimer > 10 && dmgTimer < 40){knockBack();}
            if (dmgTimer == 20){this.getImage().setTransparency(255);}
            if (dmgTimer == 30){this.getImage().setTransparency(50);}
            if (dmgTimer == 40){this.getImage().setTransparency(255);}
            if (dmgTimer == 50){this.getImage().setTransparency(50);}
            if (dmgTimer > 60) {
                this.getImage().setTransparency(255);
                canTakeDmg = true;
                dmgTimer = 0;
            }
        }
    }

    private void CollectStar()
    {
        Star star = (Star) getOneIntersectingObject(Star.class);
        if(star != null)
        {
            Globals.levelScore += 650;
            Globals.levelStarsCollected += 1;
            getWorld().removeObject(star);
        }
    }

    private void knockBack(){
        if (knockbackDirection.equals("right")) {
            if (canMoveRight(3)) {
                moveRight(3);
            }
            this.setImage(hitm);
        } else{
            if (canMoveLeft(3)) {
                moveLeft(3);
            }
            this.setImage(hit);
        }
    }

    private void takeDmg(){
        Slime();
        Lava();
        spikes();
        Bee();
        Blade();
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
                    if (getX() < slimedmg.getX()) {
                        knockbackDirection = "left";
                    } else {
                        knockbackDirection = "right";
                    }
                    canTakeDmg = false;
                }
            }
        }
    }

    private void Blade(){
        Blade bladedmg = (Blade) getOneIntersectingObject(Blade.class);
        if(bladedmg != null){
            if (!bladedmg.dead){
                if(canTakeDmg) {
                    Player.health -= 1;
                    if (getX() < bladedmg.getX()) {
                        knockbackDirection = "left";
                    } else {
                        knockbackDirection = "right";
                    }
                    canTakeDmg = false;
                }
            }
        }
    }

    private void Bee(){
        Bee bee = (Bee) getObjectBelowOfClass(Bee.class);
        Bee beedmg = (Bee) getOneIntersectingObject(Bee.class);
        if (bee != null) {
            if (!bee.dead) {
                bee.dead = true;
                vSpeed = 0;
                jump(20);
            }
        }
        if(beedmg != null){
            if (!beedmg.dead){
                if(canTakeDmg) {
                    Player.health -= 0.5;
                    if (getX() < beedmg.getX()) {
                        knockbackDirection = "left";
                    } else {
                        knockbackDirection = "right";
                    }
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
        Spikes spikes = (Spikes) getOneIntersectingObject(Spikes.class);
        if (isTouching(Spikes.class)){
            if(canTakeDmg) {
                Player.health -= 0.5;
                if (getX() < spikes.getX()) {
                    knockbackDirection = "left";
                } else {
                    knockbackDirection = "right";
                }
                canTakeDmg = false;
            }
        }
    }
}