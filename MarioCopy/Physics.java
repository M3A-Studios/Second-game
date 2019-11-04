import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Physics extends Actor
{
    protected double vSpeed = 0;
    protected double acceleration = 0.75;
    
    //Emily is a gay tranny who wanted doubles
    private double doubleX;
    private double doubleY;
    public double getDoubleX() {
        return doubleX;
    }
    public double getDoubleY() {
        return doubleY;
    }
    protected void setDoubleX(double x) {
        doubleX = x;
    }
    protected void setDoubleY(double y) {
        doubleY = y;
    }
    public void setNewLocation(double x, double y) {
        doubleX = x;
        doubleY = y;
        setLocation((int) doubleX, (int) doubleY);
    }
    public void setRelativeLocation(double x, double y) {
        doubleX = doubleX + x;
        doubleY = doubleY + y;
        setLocation((int) doubleX, (int) doubleY);
    }   
    public void entityOffset() {
        doubleX = doubleX + Camera.entityXOffset;
        doubleY = doubleY + Camera.entityYOffset;
    }
    public void jump(int height)
    {
        setRelativeLocation(0, - 1);
        vSpeed = -height;
    }
    public void updateGravity()
    {
        if(!onGround() && !willBumpHead())
        {
            vSpeed = vSpeed + acceleration;
            if (vSpeed > 20) vSpeed = 10;
            setRelativeLocation(0,vSpeed);
        }
        else
        {
            vSpeed = 0;
        }
    }
    public boolean onGround()
    {
        if (getOneObjectAtOffset(getImage().getWidth() / -2, getImage().getHeight() / 2 + (int) Math.ceil(vSpeed), Solid.class) != null ||
        getOneObjectAtOffset(0, getImage().getHeight() / 2 + (int) Math.ceil(vSpeed), Solid.class) != null ||
        getOneObjectAtOffset(getImage().getWidth() / 2, getImage().getHeight() / 2 + (int) Math.ceil(vSpeed), Solid.class) != null) { 
            return true; 
        } else { 
            return false;
        }
    }
    public boolean willBumpHead(){
         if (getOneObjectAtOffset(getImage().getWidth() / -2, getImage().getHeight() / -2 + (int) Math.ceil(vSpeed), Solid.class) != null ||
            getOneObjectAtOffset(getImage().getWidth() / 2, getImage().getHeight() / -2 + (int) Math.ceil(vSpeed), Solid.class) != null ||
            getOneObjectAtOffset(0, getImage().getHeight() / -2 + (int) Math.ceil(vSpeed), Solid.class) != null)
            return true;
         return false;
    }
    public boolean canMoveLeft(double speed){
         if (getOneObjectAtOffset(getImage().getWidth() / -2 - (int) Math.ceil(speed) - 1, getImage().getHeight() / -2, Solid.class) != null ||
            getOneObjectAtOffset(getImage().getWidth() / -2 - (int) Math.ceil(speed) - 1, 0,  Solid.class) != null ||
            getOneObjectAtOffset(getImage().getWidth() / -2 - (int) Math.ceil(speed) - 1, getImage().getHeight() / 2 - 1,  Solid.class) != null)
            return false;
         return true; 
    }
    public boolean canMoveRight(double speed){
         if (getOneObjectAtOffset(getImage().getWidth() / 2 + (int) Math.ceil(speed) + 1, getImage().getHeight() / -2, Solid.class) != null ||
            getOneObjectAtOffset(getImage().getWidth() / 2 + (int) Math.ceil(speed) + 1, 0, Solid.class) != null ||
            getOneObjectAtOffset(getImage().getWidth() / 2 + (int) Math.ceil(speed) + 1, getImage().getHeight() / 2 - 1, Solid.class) != null)
            return false;
         return true; 
    } 
    public void moveRight(double speed)
    {
        setRelativeLocation(speed,0);
    }
     public void moveLeft(double speed)
    {
        setRelativeLocation(- speed,0);
    }
}
