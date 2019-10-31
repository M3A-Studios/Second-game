import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Physics extends Actor
{
    protected int vSpeed = 0;
    protected int acceleration = 1;
    protected int jumpHeight = -15;
    protected int speed = 5;
    
    public boolean moveAround()
    {
        boolean check = true;
       if(Greenfoot.isKeyDown("d") && canMoveRight())
       {
           setLocation(getX() + speed, getY()); 
       }
       else if(Greenfoot.isKeyDown("a") && canMoveLeft())
       {
           setLocation(getX() - speed, getY());
       }else{
           check = false;
       }
       return check;
    }
    public void jump()
    {
        if(Greenfoot.isKeyDown("space") && onGround() && !isClimbing())
        {
            setLocation(getX(), getY() - 1);
            vSpeed = jumpHeight;
        }
    }
    public void fall()
    {
        if(!onGround() && !isClimbing()){ //Change to if(!onGround() || !isClimbing()){
            setLocation(getX(), getY() + vSpeed);
        }
    }
    public boolean onGround()
    {
        if (getOneObjectAtOffset(getImage().getWidth() / -2, getImage().getHeight() / 2 + vSpeed, Solid.class) != null ||
        getOneObjectAtOffset(getImage().getWidth() / 2, getImage().getHeight() / 2 + vSpeed, Solid.class) != null) { 
            return true; 
        } else { 
            return false;
        }
    }
    public void checkFalling()
    {
        if(!onGround())
        {
            vSpeed++;
        }
        else
        {
            vSpeed = 0;
        }
    }
    
    public boolean isOnSolidGround(){
     boolean onGround = false;
     
     if (getY() > getWorld().getHeight() -50) onGround = true;
     
     if (getOneObjectAtOffset(getImage().getWidth() / -2, getImage().getHeight() / -2, Solid.class) != null ||
        getOneObjectAtOffset(getImage().getWidth() / 2, getImage().getHeight() / -2, Solid.class) != null)
        onGround = true;
     
     return onGround;
    }
    
    public boolean didBumpHead(){
     boolean bumpedHead = false;
     if (getOneObjectAtOffset(getImage().getWidth() / -2, getImage().getHeight() / -2, Solid.class) != null ||
        getOneObjectAtOffset(getImage().getWidth() / 2, getImage().getHeight() / -2, Solid.class) != null)
        bumpedHead = true;
     
     return bumpedHead;
    }
    public boolean canMoveLeft(){
     boolean canMoveLeft = true;
     
     if (getOneObjectAtOffset(getImage().getWidth() / -2 - speed - 1, getImage().getHeight() / -2, Solid.class) != null ||
        getOneObjectAtOffset(getImage().getWidth() / -2 - speed - 1, getImage().getHeight() / 2 - 1,  Solid.class) != null)
        canMoveLeft = false;
     
     return canMoveLeft; 
    }
    public boolean canMoveRight(){
     boolean canMoveRight = true;
     
     if (getOneObjectAtOffset(getImage().getWidth() / 2 + speed + 1, getImage().getHeight() / -2, Solid.class) != null ||
        getOneObjectAtOffset(getImage().getWidth() / 2 + speed + 1, getImage().getHeight() / 2 - 1, Solid.class) != null)
        canMoveRight = false;
     
     return canMoveRight; 
    }
    
    public boolean isClimbing(){
            if (getOneObjectAtOffset(getImage().getWidth() / -2, getImage().getHeight() / 2 + vSpeed, Ladder.class) != null ||
            getOneObjectAtOffset(getImage().getWidth() / 2, getImage().getHeight() / 2 + vSpeed, Ladder.class) != null) { 
                return true; 
            } else { 
                return false;
            }
        }
    public void climbing(){
        if(Greenfoot.isKeyDown("w") && isClimbing())
        {
         setLocation(getX(), getY() - 3);
        }
        if(Greenfoot.isKeyDown("s") && isClimbing())
        {
         setLocation(getX(), getY() + 3);
        }
    }
        
}
