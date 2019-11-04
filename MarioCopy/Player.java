import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Physics
{
    private int imageWidth;
    private int imageHeight;
    private int moveDelay = 0;
    private int hAmount = 6;

    Player() {
        GreenfootImage image = new GreenfootImage ("alienGreen_front.png");
        image.scale((Options.blockSize),(Options.blockSize));
        setImage(image);
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
        Health();
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
    public void Health()
    {
        String Hp = Integer.toString(hAmount);
        getWorld().showText(Hp, 100, 400);
    }   
}