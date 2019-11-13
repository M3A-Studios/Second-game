package nl.rocmondriaan.greenfoot.game;

import greenfoot.Actor;

public class Physics extends Actor
{
    double vSpeed = 0;
    double acceleration = 0.9;

    private double doubleX;
    private double doubleY;

    public double getDoubleX() {
        return doubleX;
    }

    public double getDoubleY() {
        return doubleY;
    }

    void setDoubleX(double x) {
        doubleX = x;
    }

    void setDoubleY(double y) {
        doubleY = y;
    }

    void setNewLocation(double x, double y) {
        doubleX = x;
        doubleY = y;
        setLocation((int) doubleX, (int) doubleY);
    }

    void setRelativeLocation(double x, double y) {
        doubleX = doubleX + x;
        doubleY = doubleY + y;
        setLocation((int) doubleX, (int) doubleY);
    }

    void entityOffset() {
        doubleX = doubleX + Camera2.entityXOffset;
        doubleY = doubleY + Camera2.entityYOffset;
    }

    //OWO
    //
    //SHOULD DEFINITELY BE CHANGED TO ACTUALLY WORK DYNAMICALLY WITH THE SCREENSIZES AND NOT BE BASED ON PIXELS
    //
    //OWO
    void jump(double height) {
        setRelativeLocation(0, - 1);
        vSpeed = vSpeed - height;
    }

    void updateGravity() {
        if(!onGround() && !willBumpHead() && !onLadder())
        {
            vSpeed = vSpeed + acceleration;
            if (vSpeed > 20) vSpeed = 20;
            setRelativeLocation(0,vSpeed);
        }
        else
        {
            vSpeed = 0;
        }
    }

    boolean onLadder() {
        if (getOneObjectAtOffset(0, -1 + getImage().getHeight() / 2 + (int) vSpeed, Ladder.class) != null) {
            return getOneObjectAtOffset(0, -1 + (int) vSpeed, Ladder.class) != null;
        } else {
            return false;
        }
    }

    boolean onGround() {
        return getOneObjectAtOffset(getImage().getWidth() / -2 + 1, getImage().getHeight() / 2 + (int) vSpeed + 1, Solid.class) != null ||
                getOneObjectAtOffset(0, getImage().getHeight() / 2 + (int) vSpeed + 1, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2 - 1, getImage().getHeight() / 2 + (int) vSpeed + 1, Solid.class) != null;
    }

    private boolean willBumpHead(){
        return getOneObjectAtOffset(getImage().getWidth() / -2, getImage().getHeight() / -2 + (int) vSpeed, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2, getImage().getHeight() / -2 + (int) vSpeed, Solid.class) != null ||
                getOneObjectAtOffset(0, getImage().getHeight() / -2 + (int) vSpeed, Solid.class) != null;
    }

    boolean canMoveLeft(double speed){
        if (getOneObjectAtOffset(getImage().getWidth() / -2 - (int) speed - 1, getImage().getHeight() / -2, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / -2 - (int) speed - 1, 0,  Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / -2 - (int) speed - 1, getImage().getHeight() / 2 - 1,  Solid.class) != null)
            return false;
        return !(doubleX - speed < getImage().getWidth() / 2.0);
    }

    boolean canMoveRight(double speed){
        if (getOneObjectAtOffset(getImage().getWidth() / 2 + (int) speed + 1, getImage().getHeight() / -2, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2 + (int) speed + 1, 0, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2 + (int) speed + 1, getImage().getHeight() / 2 - 1, Solid.class) != null)
            return false;
        return !(doubleX - speed > Options.screenWidth - getImage().getWidth() / 2.0);
    }

    void moveRight(double speed)
    {
        setRelativeLocation(speed,0);
    }

    void moveLeft(double speed)
    {
        setRelativeLocation(- speed,0);
    }
}

