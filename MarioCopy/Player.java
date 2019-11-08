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
    
    
    private GreenfootImage climb1 = new GreenfootImage("alienGreen_climb1.png");
    private GreenfootImage climb2 = new GreenfootImage("alienGreen_climb2.png");
    private GreenfootImage front = new GreenfootImage("alienGreen_front.png");
    private GreenfootImage walk1 = new GreenfootImage("alienGreen_walk1.png");
    private GreenfootImage walk2 = new GreenfootImage("alienGreen_walk2.png");
    private GreenfootImage walk1m = new GreenfootImage("alienGreen_walk1.png");
    private GreenfootImage walk2m = new GreenfootImage("alienGreen_walk2.png");
    Player() {
        GreenfootImage image = new GreenfootImage ("alienGreen_front.png");
        image.scale((Options.blockSize),(Options.blockSize)*3/2);
        setImage(image);
        started = false;
        
        climb1.scale((Options.blockSize),(Options.blockSize)*3/2);
        climb2.scale((Options.blockSize),(Options.blockSize)*3/2);
        front.scale((Options.blockSize),(Options.blockSize)*3/2);
        walk1.scale((Options.blockSize),(Options.blockSize)*3/2);
        walk2.scale((Options.blockSize),(Options.blockSize)*3/2);
        walk1m.scale((Options.blockSize),(Options.blockSize)*3/2);
        walk2m.scale((Options.blockSize),(Options.blockSize)*3/2);
        walk1m.mirrorHorizontally();
        walk2m.mirrorHorizontally();
    }   
    public void act() 
    {
        if (!started) {
            started = true;
            setDoubleX(getX());
            setDoubleY(getY());
        } 
        updateGravity();
        walkingAnim();
        standingStill();
        entityOffset();
       if(Greenfoot.isKeyDown("space"))
        {   
            if(onGround())
            {
                spaceKeyDown = 0;
                jump(14 );
            }
            else
            {
                spaceKeyDown += 1;
                if (spaceKeyDown < 60) {
                    jump(0.4);
                }
            }
        }
       moving = false;
       if(Greenfoot.isKeyDown("d"))
       {
           rightKeyDown += 0.2;
           if (rightKeyDown > 60) rightKeyDown = 60;
           double speed = 5 + rightKeyDown/10;
           if (canMoveRight(speed)) {
               moveRight(speed);
               moving = true;
           }
       }
       else 
       {
           rightKeyDown = 0;
       }
       if(Greenfoot.isKeyDown("a"))
       {
           leftKeyDown += 0.2;
           if (leftKeyDown > 60) leftKeyDown = 60;
           double speed = 5 + leftKeyDown/10;
           if (canMoveLeft(speed)) {
               moveLeft(speed);
               moving = true;
           }
       }
       else 
       {
           leftKeyDown = 0;
       }
       
       if ( onLadder())
       {
           if (Greenfoot.isKeyDown("W"))
           {
           setRelativeLocation(0,-5);
           }
           if (Greenfoot.isKeyDown("S") && !onGround())
           {
           setRelativeLocation(0,5);
           }
        }
        
       if (isTouching(Lever.class))
       {
           
       }
    }
    public void standingStill(){
        if (onGround() && !moving){
            setImage(front);
        }
    }
    public void walkingAnim(){
        if (onGround() && Greenfoot.isKeyDown("d")){
            setImage(walk1);
            if (moving){
                setImage(walk2);
            }
        }
        if (onGround() && Greenfoot.isKeyDown("a")){
            setImage(walk1m);
            if (moving){
                setImage(walk2m);
            }
        }
    }
    
   
}