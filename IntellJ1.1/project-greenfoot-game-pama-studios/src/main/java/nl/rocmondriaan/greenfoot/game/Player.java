package nl.rocmondriaan.greenfoot.game;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Player extends Physics
{
    private int imageWidth;
    private int imageHeight;
    public static int health = 6; //health amount
    private boolean started = false;
    private boolean moving;
    private double leftKeyDown;
    private double rightKeyDown;
    private double spaceKeyDown;
    private int atime = 0;
    static boolean dead = false;
    private int dyingAnimation = 0;
    private int skip;

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

    Player() {
        GreenfootImage image = new GreenfootImage ("alienGreen_front.png");
        image.scale((Options.blockSize),(Options.blockSize)*3/2);
        setImage(image);
        started = false;

        climb1.scale((Options.blockSize),(Options.blockSize)*3/2);
        climb2.scale((Options.blockSize),(Options.blockSize)*3/2);
        front.scale((Options.blockSize),(Options.blockSize)*3/2);
        jump.scale((Options.blockSize),(Options.blockSize)*3/2);
        jumpm.scale((Options.blockSize),(Options.blockSize)*3/2);
        deadimg.scale((Options.blockSize),(Options.blockSize)*3/2);
        walk1.scale((Options.blockSize),(Options.blockSize)*3/2);
        walk2.scale((Options.blockSize),(Options.blockSize)*3/2);
        walk1m.scale((Options.blockSize),(Options.blockSize)*3/2);
        walk2m.scale((Options.blockSize),(Options.blockSize)*3/2);
        walk1m.mirrorHorizontally();
        walk2m.mirrorHorizontally();
        jumpm.mirrorHorizontally();
    }
    public void act()
    {
        entityOffset();
        if (!started) {
            started = true;
            setDoubleX(getX());
            setDoubleY(getY());
        }
        if (!deathCheck()) {
            updateGravity();
            walkingAnim();
            checkinput();
            standingStill();
        }
    }
    public void checkinput() {
        if (Greenfoot.isKeyDown("escape")) {
            Greenfoot.setWorld(new LevelSelector(Globals.currentLevel));
        }
        if (Greenfoot.isKeyDown("space")) {
            if (onGround()) {
                spaceKeyDown = 0;
                jump(14);
            } else {
                spaceKeyDown += 1;
                if (spaceKeyDown < 60) {
                    if (vSpeed < 0) {
                        jump(0.4);
                    }
                }
            }
        }
        moving = false;
        if (Greenfoot.isKeyDown("d") && (!Greenfoot.isKeyDown("a"))) { //&& (!Greenfoot.isKeyDown("a")) toegevoegd voor links rechts bug
            rightKeyDown += 0.2;
            if (rightKeyDown > 60) rightKeyDown = 60;
            double speed = 5 + rightKeyDown / 10;
            if (canMoveRight(speed)) {
                moveRight(speed);
                moving = true;
            }
        } else {
            rightKeyDown = 0;
        }
        if (Greenfoot.isKeyDown("a") && (!Greenfoot.isKeyDown("d"))) { //&& (!Greenfoot.isKeyDown("d")) toegevoegd voor links rechts bug
            leftKeyDown += 0.2;
            if (leftKeyDown > 60) leftKeyDown = 60;
            double speed = 5 + leftKeyDown / 10;
            if (canMoveLeft(speed)) {
                moveLeft(speed);
                moving = true;
            }
        } else {
            leftKeyDown = 0;
        }

        if (onLadder()) {
            if (Greenfoot.isKeyDown("W")) {
                setRelativeLocation(0, -5);
            }
            if (Greenfoot.isKeyDown("S") && !onGround()) {
                setRelativeLocation(0, 5);
            }
        }

        if (Greenfoot.isKeyDown(Options.interact)) {
            health = health - 1;
        }
        if (Greenfoot.isKeyDown("X")) {
            health = health - 1;
        }

        if (deathCheck() && !dead) {
            dead = true;
            dyingAnimation = 0;
        }
        if (dead) {
            if (dyingAnimation < 300) {
                dyingAnimation += 1;
                Particles beam = new Particles("beam");
                getWorld().addObject(beam, getX(), getY());
                //} else {
                //Greenfoot.setWorld(new Levels(LevelSelector.getSelectedLevel()));
            }
        }
    }
    public void standingStill(){
        if (!moving && !onLadder()){ //&& onGround() weg gehaalt --Michael
            setImage(front);
            atime = 0; //Reset animation timer
        }
    }
    public void walkingAnim(){
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

    public void animateMovement(String Direction){
        if (Direction == "Right"){
            atime=atime+1;
            if (atime > 0 && atime < 5) {setImage(walk1);}
            if (atime==5) {setImage(walk2);}
            if (atime==10) {setImage(walk1);}
            if (atime>15) {atime=0;}
        }
        if (Direction == "Left"){
            atime=atime+1;
            if (atime > 0 && atime < 5) {setImage(walk1m);}
            if (atime==5) {setImage(walk2m);}
            if (atime==10) {setImage(walk1m);}
            if (atime>15) {atime=0;}
        }
        if (Direction == "Jump"){
            atime=atime+1;
            if (atime > 0 && atime < 5) {setImage(jump);}
            if (atime==5) {setImage(jump);}
            if (atime==10) {setImage(jump);}
            if (atime>15) {atime=0;}
        }
        if (Direction == "Jumpm"){
            atime=atime+1;
            if (atime > 0 && atime < 5) {setImage(jumpm);}
            if (atime==5) {setImage(jumpm);}
            if (atime==10) {setImage(jumpm);}
            if (atime>15) {atime=0;}
        }
        if (Direction == "Ladder"){
            atime=atime+1;
            if (atime > 0 && atime < 5) {setImage(climb1);}
            if (atime==5) {setImage(climb2);}
            if (atime==10) {setImage(climb1);}
            if (atime>15) {atime=0;}
        }
        if (Direction == "Death") {
            atime=atime+1;
            setImage(deadimg);
            if (atime > 0 && atime < 10) {setRelativeLocation(0,-5); }
            if (atime>10) {atime=0;}
        }
    }
    public boolean deathCheck()
    {
        if (health == 0) {
            animateMovement("Death");
            return true;
        }
            return false;
    }
    }//Ends here
