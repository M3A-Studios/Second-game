package nl.rocmondriaan.greenfoot.game;

import greenfoot.Actor;

public class Physics extends Actor
{
    double vSpeed = 0;
    double acceleration = 0.9;

    private double doubleX;
    private double doubleY;

    /**
     * Simple method to get the accurate X coordinate of the entity on the screen
     *
     * @return      returns the X coordinate of the entity as a double (so like 5.21), more accurate than getX()
     */
    public double getDoubleX() { return doubleX; }

    /**
     * Simple method to get the accurate Y coordinate of the entity on the screen
     *
     * @return      returns the Y coordinate of the entity as a double (so like 5.21), more accurate than getY()
     */
    public double getDoubleY() { return doubleY; }

    /**
     * Simple method to set the double X coordinate, should only be used at the very start
     * like the first frame that the entity is in the world to set this to getX()
     * Should never be messed with apart from that
     *
     * @param x             expects a double for what to set doubleX to
     */
    void setDoubleX(double x) {
        doubleX = x;
    }

    /**
     * Simple method to set the double Y coordinate, should only be used at the very start
     * like the first frame that the entity is in the world to set this to getY()
     * Should never be messed with apart from that
     *
     * @param y             expects a double for what to set doubleY to
     */
    void setDoubleY(double y) {
        doubleY = y;
    }

    /**
     * Replacement for setLocation for everything that extends physics. this method is a literal replacement
     * the only thing different here is that it instantly updates doublex and doubley so you get no problems
     * in the future
     *
     * @param x         excepts a double X value to set the entity to
     * @param y         expects a double Y value to set the entity to
     */
    void setNewLocation(double x, double y) {
        doubleX = x;
        doubleY = y;
        setLocation((int) doubleX, (int) doubleY);
    }


    /**
     * Method that changes the position of the object only slightly, instead of hardcoding the position it simply
     * takes the current position in doubleX and doubleY and adds to it. so moving 3 up would be (0, -3) to change
     * the X by 0 and the Y by -3
     *
     * @param x         expects a double X value to move the object along the x-axis
     * @param y         expects a double Y value to move the object along the y-axis
     */
    void setRelativeLocation(double x, double y) {
        doubleX = doubleX + x;
        doubleY = doubleY + y;
        setLocation((int) doubleX, (int) doubleY);
    }


    /**
     * Simple little method to keep the doubleX and doubleY updated of objects that get scrolled by the camera
     * to make sure no bugs happen (like things not moving properly). Should be used in the act method of every object
     * that extends physics, has no arguments
     */
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

    /**
     * Checks if the player is gonna hit the ground, will bump its head or is on a ladder
     * if so it will set your vertical movement to zero, if not it will update the gravity
     */
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


    /**
     * Checks if the player is currently on a ladder or not
     *
     * @return      returns true or false based on if the player is on a ladder or not
     */
    boolean onLadder() {
        if (getOneObjectAtOffset(0, -1 + getImage().getHeight() / 2 + (int) vSpeed, Ladder.class) != null) {
            return getOneObjectAtOffset(0, -1 + (int) vSpeed, Ladder.class) != null;
        } else {
            return false;
        }
    }

    /**
     * Check if in the next frame the player will hit the ground if we make them fall by the gravity + 1 pixel
     * returns true or false
     *
     * @return          returns true or false based on if the player is hitting the ground (or just standing on it)
     */
    boolean onGround() {
        return getOneObjectAtOffset(getImage().getWidth() / -2 + 1, getImage().getHeight() / 2 + (int) vSpeed + 1, Solid.class) != null ||
                getOneObjectAtOffset(0, getImage().getHeight() / 2 + (int) vSpeed + 1, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2 - 1, getImage().getHeight() / 2 + (int) vSpeed + 1, Solid.class) != null;
    }

    /**
     * Checks if in the next frame the player will hit his head against a solid block above him.
     * @return
     */
    private boolean willBumpHead(){
        return getOneObjectAtOffset(getImage().getWidth() / -2, getImage().getHeight() / -2 + (int) vSpeed, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2, getImage().getHeight() / -2 + (int) vSpeed, Solid.class) != null ||
                getOneObjectAtOffset(0, getImage().getHeight() / -2 + (int) vSpeed, Solid.class) != null;
    }

    /**
     * Checks if the player will be inside of a solid block in the next frame, takes a double to see the movement of the next frame
     *
     * @param speed         how far it should check to the left of you to see if you get into a block or not.
     * @return              returns true or false based on if you can move to the left or not
     */
    boolean canMoveLeft(double speed){
        if (getOneObjectAtOffset(getImage().getWidth() / -2 - (int) speed - 1, getImage().getHeight() / -2, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / -2 - (int) speed - 1, 0,  Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / -2 - (int) speed - 1, getImage().getHeight() / 2 - 1,  Solid.class) != null)
            return false;
        return !(doubleX - speed < getImage().getWidth() / 2.0);
    }

    /**
     * Checks if the player will be inside of a solid block in the next frame, takes a double to see the movement of the next frame
     *
     * @param speed         how far it should check to the right of you to see if you get into a block or not.
     * @return              returns true or false based on if you can move to the right or not
     */
    boolean canMoveRight(double speed){
        if (getOneObjectAtOffset(getImage().getWidth() / 2 + (int) speed + 1, getImage().getHeight() / -2, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2 + (int) speed + 1, 0, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2 + (int) speed + 1, getImage().getHeight() / 2 - 1, Solid.class) != null)
            return false;
        return !(doubleX - speed > Options.screenWidth - getImage().getWidth() / 2.0);
    }

    /**
     * Moves the player by speed pixels to the right, uses doubleX to maintain accuracy with subpixels
     *
     * @param speed         how far the player should move to the right
     */
    void moveRight(double speed)
    {
        setRelativeLocation(speed,0);
    }

    /**
     * Moves the player by speed pixels to the left, uses doubleX to maintain accuracy with subpixels
     *
     * @param speed         how far the player should move to the left
     */
    void moveLeft(double speed)
    {
        setRelativeLocation(- speed,0);
    }
}

