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
        Camera.playerX = getX();
        Camera.playerY = getY();
    }

    public void ClimbingAnim(){
        if (isClimbing()){
            for(int i=0;i<100;i++){
                if (i>=50){
                    setImage(new GreenfootImage("alienGreen_climb1.png"));
                    getImage().scale(getImage().getWidth()/3,getImage().getHeight()/3);
                }
                if (i<50){
                    setImage(new GreenfootImage("alienGreen_climb2.png"));
                    getImage().scale(getImage().getWidth()/3,getImage().getHeight()/3);
                }
            }
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
            if (moveAround()){
                setImage(new GreenfootImage("alienGreen_walk2.png"));
                getImage().scale(getImage().getWidth()/3,getImage().getHeight()/3);
            }
        }
        if (onGround() && Greenfoot.isKeyDown("a") && canMoveLeft()){
                setImage(new GreenfootImage("alienGreen_walk1.png"));
                getImage().scale(getImage().getWidth()/3,getImage().getHeight()/3);
                getImage().mirrorHorizontally();
            if (moveAround()){
                setImage(new GreenfootImage("alienGreen_walk2.png"));
                getImage().scale(getImage().getWidth()/3,getImage().getHeight()/3);
                getImage().mirrorHorizontally();
            }
        }
    }
}