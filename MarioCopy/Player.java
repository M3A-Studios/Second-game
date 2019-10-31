import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Physics
{
    private int imageWidth;
    private int imageHeight;
    private int moveDelay = 0;
    Player() {
        getImage().scale(getImage().getWidth()/3,getImage().getHeight()/3);
        imageWidth = getImage().getWidth();
        imageHeight = getImage().getHeight();
    }   
    public void act() 
    {
        moveAround();
        checkFalling();
        fall();
        jump();
        climbing();
        ClimbingAnim();
        LeftRightWalkingAnim();
        standingStill();
        delay();
    }
    private boolean delay(){
        moveDelay++;
        if (moveDelay == 5){
            moveDelay = 0;
            return true;
        }
        return false;
    }
    public void ClimbingAnim(){
        if (isClimbing()){
            setImage(new GreenfootImage("alienGreen_climb1.png"));
            getImage().scale(getImage().getWidth()/3,getImage().getHeight()/3);
        }
    }
    public void standingStill(){
        if (onGround() && !moveAround()){
            setImage(new GreenfootImage("alienGreen_front.png"));
            getImage().scale(getImage().getWidth()/3,getImage().getHeight()/3);
        }
    }
    public void LeftRightWalkingAnim(){
        if (onGround() && Greenfoot.isKeyDown("d") && canMoveRight()){
                setImage(new GreenfootImage("alienGreen_walk1.png"));
                getImage().scale(getImage().getWidth()/3,getImage().getHeight()/3);
            if (delay()){
                setImage(new GreenfootImage("alienGreen_walk2.png"));
                getImage().scale(getImage().getWidth()/3,getImage().getHeight()/3);
            }
        }
        if (onGround() && Greenfoot.isKeyDown("a") && canMoveLeft()){
                setImage(new GreenfootImage("alienGreen_walk1.png"));
                getImage().scale(getImage().getWidth()/3,getImage().getHeight()/3);
                getImage().mirrorHorizontally();
            if (delay()){
                setImage(new GreenfootImage("alienGreen_walk2.png"));
                getImage().scale(getImage().getWidth()/3,getImage().getHeight()/3);
                getImage().mirrorHorizontally();
            }
        }
    }
}