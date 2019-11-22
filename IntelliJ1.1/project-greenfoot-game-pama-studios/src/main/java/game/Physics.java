package game;

import greenfoot.Actor;
import game.blocks.normal.Ladder;
import game.blocks.normal.SlopeLeft;
import game.blocks.normal.SlopeRight;
import game.blocks.normal.Solid;
import game.blocks.special.Door;
import game.blocks.special.JumpPad;
import game.blocks.special.LockedBlocks;
import game.entities.Player;

/**
 *
 */
public class Physics extends Actor
{
    protected double vSpeed = 0;
    protected double acceleration = 0.9;
    private int jtime;

    private double doubleX;
    private double doubleY;
    /**
     * Simple method to get the accurate X coordinate of the entity on the screen
     *
     * @return      returns the X coordinate of the entity as a double (so like 5.21), more accurate than getX()
     */
    protected double getDoubleX() { return doubleX; }

    /**
     * Simple method to get the accurate Y coordinate of the entity on the screen
     *
     * @return      returns the Y coordinate of the entity as a double (so like 5.21), more accurate than getY()
     */
    protected double getDoubleY() { return doubleY; }

    /**
     * Simple method to set the double X coordinate, should only be used at the very start
     * like the first frame that the entity is in the world to set this to getX()
     * Should never be messed with apart from that
     *
     * @param x             expects a double for what to set doubleX to
     */
    protected void setDoubleX(double x) {
        doubleX = x;
    }

    /**
     * Simple method to set the double Y coordinate, should only be used at the very start
     * like the first frame that the entity is in the world to set this to getY()
     * Should never be messed with apart from that
     *
     * @param y             expects a double for what to set doubleY to
     */
    protected void setDoubleY(double y) {
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
    protected void setNewLocation(double x, double y) {
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
    protected void setRelativeLocation(double x, double y) {
        double scaledX = Options.blockSize / 64.0 * x;
        double scaledY = Options.blockSize / 64.0 * y;
        doubleX = doubleX + scaledX;
        doubleY = doubleY + scaledY;
        setLocation((int) doubleX, (int) doubleY);
    }
    /**
     * Simple little method to keep the doubleX and doubleY updated of objects that get scrolled by the camera
     * to make sure no bugs happen (like things not moving properly). Should be used in the act method of every object
     * that extends physics, has no arguments
     */
    protected void entityOffset() {
        doubleX = doubleX + Camera.entityXOffset;
        doubleY = doubleY + Camera.entityYOffset;
    }

    /**
     * @param height
     */
    protected void jump(double height) {
        setRelativeLocation(0, - 1);
        vSpeed = -height;
    }

    /**
     * @param height
     */
    protected void jumpExtend(double height) {
        vSpeed = vSpeed - height;
    }

    /**
     * Checks if the player is gonna hit the ground, will bump its head or is on a ladder
     * if so it will set your vertical movement to zero, if not it will update the gravity
     */
    protected void updateGravity() {
        if(!onGround() && !willBumpHead() && !onLadder())
        {
            vSpeed = vSpeed + acceleration;
            if (vSpeed > 20) vSpeed = 20;
            setRelativeLocation(0,vSpeed);
        }
        else if (willBumpHead())
        {
            vSpeed = 1;
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
    protected boolean onLadder() {
        if (getOneObjectAtOffset(0, -1 + getImage().getHeight() / 2 + (int) Math.floor(vSpeed), Ladder.class) != null) {
            return getOneObjectAtOffset(0, -1 + (int) Math.floor(vSpeed), Ladder.class) != null;
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
    protected boolean onGround() {
        boolean onGround = false;
        if (getOneObjectAtOffset(getImage().getWidth() / -2, getImage().getHeight() / 2 + (int) Math.ceil(vSpeed), Solid.class) != null ||
                getOneObjectAtOffset(0, getImage().getHeight() / 2 + (int) Math.ceil(vSpeed), Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2, getImage().getHeight() / 2 + (int) Math.ceil(vSpeed), Solid.class) != null) {
            onGround = true;
        }
        if (onSlope()) {
            onGround = true;
        }
        //Check if you're inside of a platform
        boolean insidePlatform = false;
        boolean insidePlatform2 = false;
        boolean insidePlatform3 = false;
        Actor platformBelow = getOneObjectAtOffset(getImage().getWidth() / -2 + 1, getImage().getHeight() / 2 + (int) Math.ceil(vSpeed), game.blocks.normal.Platform.class); //check bottom left
        Actor platformBelow2 = getOneObjectAtOffset(0, getImage().getHeight() / 2 + (int) Math.ceil(vSpeed), game.blocks.normal.Platform.class); //check bottom right
        Actor platformBelow3 = getOneObjectAtOffset(getImage().getWidth() / 2 - 1, getImage().getHeight() / 2 + (int) Math.ceil(vSpeed), game.blocks.normal.Platform.class); //check bottom right
        if (platformBelow != null) {
            insidePlatform = (platformBelow.getY() - platformBelow.getImage().getHeight()/2 < getY() + getImage().getHeight()/2);
        }
        if (platformBelow2 != null) {
            insidePlatform2 = (platformBelow2.getY() - platformBelow2.getImage().getHeight()/2 < getY() + getImage().getHeight()/2);
        }
        if (platformBelow3 != null) {
            insidePlatform3 = (platformBelow3.getY() - platformBelow3.getImage().getHeight()/2 < getY() + getImage().getHeight()/2);
        }
        //check if you're on top of a platform
        if (getOneObjectAtOffset(getImage().getWidth()/-2, (int) (getImage().getHeight()/2 + (int) Math.ceil(vSpeed)), game.blocks.normal.Platform.class) != null //get object at lower left pixel of object
                || getOneObjectAtOffset(0, (int) (getImage().getHeight()/2 + (int) Math.ceil(vSpeed)), game.blocks.normal.Platform.class) != null //get object at lower middle pixel of object
                || getOneObjectAtOffset(getImage().getWidth()/2, (int) (getImage().getHeight()/2 + (int) Math.ceil(vSpeed)), game.blocks.normal.Platform.class) != null) { //get object at lower right pixel of object
            //if your feet are on a platform but you're not inside one
            if (!insidePlatform && !insidePlatform2 && !insidePlatform3) {
                onGround = true;
            }
        }
        //Check if you're on top of Locked block

        if (this instanceof Player) {
            LockedBlocks lockBlock1 = (LockedBlocks) getOneObjectAtOffset(getImage().getWidth() / -2 + 1, getImage().getHeight() / 2 + (int) Math.ceil(vSpeed), LockedBlocks.class);
            if (lockBlock1 != null) {
                lockBlock1.unlockBlock();
            }
            LockedBlocks lockBlock2 = (LockedBlocks) getOneObjectAtOffset(0, getImage().getHeight() / 2 + (int) Math.ceil(vSpeed), LockedBlocks.class);
            if (lockBlock2 != null) {
                lockBlock2.unlockBlock();
            }
            LockedBlocks lockBlock3 = (LockedBlocks) (getOneObjectAtOffset(getImage().getWidth() / 2 - 1, getImage().getHeight() / 2 + (int) Math.ceil(vSpeed), LockedBlocks.class));
            if (lockBlock3 != null) {
                lockBlock3.unlockBlock();
            }
        }

        if (getOneObjectAtOffset(getImage().getWidth() / -2 + 1, getImage().getHeight() / 2 + (int) Math.ceil(vSpeed), LockedBlocks.class) != null ||
                getOneObjectAtOffset(0, getImage().getHeight() / 2 + (int) Math.ceil(vSpeed), LockedBlocks.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2 - 1, getImage().getHeight() / 2 + (int) Math.ceil(vSpeed), LockedBlocks.class) != null) {
            onGround = true;
        }
        return onGround;
    }

    /**
     * Checks if in the next frame the player will hit his head against a solid block above him.
     *
     * @return
     */
    private boolean willBumpHead(){
        boolean willHitSolid = false;
        boolean willHitLock = false;
        willHitSolid = getOneObjectAtOffset(getImage().getWidth() / -2, getImage().getHeight() / -2 + (int) Math.floor(vSpeed), Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2, getImage().getHeight() / -2 + (int) Math.floor(vSpeed), Solid.class) != null ||
                getOneObjectAtOffset(0, getImage().getHeight() / -2 + (int) Math.floor(vSpeed), Solid.class) != null;
        //check for locked block

        if (this instanceof Player) {
            LockedBlocks lockBlock1 = (LockedBlocks) getOneObjectAtOffset(getImage().getWidth() / -2, getImage().getHeight() / -2 + (int) Math.floor(vSpeed), LockedBlocks.class);
            if (lockBlock1 != null) {
                lockBlock1.unlockBlock();
            }
            LockedBlocks lockBlock2 = (LockedBlocks) getOneObjectAtOffset(getImage().getWidth() / 2, getImage().getHeight() / -2 + (int) Math.floor(vSpeed), LockedBlocks.class);
            if (lockBlock2 != null) {
                lockBlock2.unlockBlock();
            }
            LockedBlocks lockBlock3 = (LockedBlocks) getOneObjectAtOffset(0, getImage().getHeight() / -2 + (int) Math.floor(vSpeed), LockedBlocks.class);
            if (lockBlock3 != null) {
                lockBlock3.unlockBlock();
            }
        }
        willHitLock = (getOneObjectAtOffset(getImage().getWidth() / -2, getImage().getHeight() / -2 + (int) Math.floor(vSpeed), LockedBlocks.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2, getImage().getHeight() / -2 + (int) Math.floor(vSpeed), LockedBlocks.class) != null ||
                getOneObjectAtOffset(0, getImage().getHeight() / -2 + (int) Math.floor(vSpeed), LockedBlocks.class) != null);
        return (willHitLock || willHitSolid);
    }

    private boolean isOnSlopeLeft() {
        SlopeLeft slope;
        slope = (SlopeLeft) getOneObjectAtOffset(-getImage().getWidth() / 2, getImage().getHeight() / 2 + (int) Math.ceil(vSpeed) - 2, SlopeLeft.class);
        if (slope != null) {
            int slopeX = slope.getX() + slope.getImage().getWidth()/2;
            int slopeY = slope.getY() + slope.getImage().getHeight()/2;
            int height = Options.blockSize - ((getX() + getImage().getWidth()/2) - slopeX);
            int clip = ((getY() + getImage().getHeight()/2) - (slopeY - height));
            if (slopeY - height <= getY() + getImage().getHeight()/2 + (int) Math.ceil(vSpeed)) {
                setRelativeLocation(0, -clip);
                return true;
            }
            return false;
        }
        return false;
    }
    private boolean isOnSlopeRight() {
        SlopeRight slope;
        slope = (SlopeRight) getOneObjectAtOffset(getImage().getWidth() / 2, getImage().getHeight() / 2 + (int) Math.ceil(vSpeed) - 2, SlopeRight.class);
        if (slope != null) {
            int slopeX = slope.getX() - slope.getImage().getWidth()/2;
            int slopeY = slope.getY() + slope.getImage().getHeight()/2;
            int height = (getX() + getImage().getWidth()/2) - slopeX;
            int clip = ((getY() + getImage().getHeight()/2) - (slopeY - height));
            if (slopeY - height <= getY() + getImage().getHeight()/2 + (int) Math.ceil(vSpeed)) {
                setRelativeLocation(0, -clip);
                return true;
            }
            return false;
        }
        return false;
    }
    private boolean onSlope() {
        return (isOnSlopeLeft() || isOnSlopeRight());
    }
    /**
     * Checks if the player will be inside of a solid block in the next frame, takes a double to see the movement of the next frame
     *
     * @param speed         how far it should check to the left of you to see if you get into a block or not.
     * @return              returns true or false based on if you can move to the left or not
     */
    protected boolean canMoveLeft(double speed){
        boolean canMoveLeft = true;
        //if youre on a slope u move 1 pixel up cuz reasonse
        if (getOneObjectAtOffset(getImage().getWidth()/-2 + 1, getImage().getHeight()/2 + 2, SlopeRight.class) != null) {
            setRelativeLocation(0, -1);
        }
        if (getOneObjectAtOffset(getImage().getWidth() / -2 - (int) Math.ceil(speed), getImage().getHeight() / -2 + 2, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / -2 - (int) Math.ceil(speed), 0,  Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / -2 - (int) Math.ceil(speed), getImage().getHeight() / 2 - 2,  Solid.class) != null)
            canMoveLeft = false;
        //check for locked block

        if (this instanceof Player) {
            LockedBlocks lockBlock1 = (LockedBlocks) getOneObjectAtOffset(getImage().getWidth() / -2 + (int) Math.ceil(speed), getImage().getHeight() / -2 + 2, LockedBlocks.class);
            if (lockBlock1 != null) {
                lockBlock1.unlockBlock();
            }
            LockedBlocks lockBlock2 = (LockedBlocks) getOneObjectAtOffset(getImage().getWidth() / -2 - (int) Math.ceil(speed), 0, LockedBlocks.class);
            if (lockBlock2 != null) {
                lockBlock2.unlockBlock();
            }
            LockedBlocks lockBlock3 = (LockedBlocks) getOneObjectAtOffset(getImage().getWidth() / -2 - (int) Math.ceil(speed), getImage().getHeight() / 2 - 2, LockedBlocks.class);
            if (lockBlock3 != null) {
                lockBlock3.unlockBlock();
            }
        }
        if (getOneObjectAtOffset(getImage().getWidth() / -2 - (int) Math.ceil(speed), getImage().getHeight() / -2 - 2, LockedBlocks.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / -2 - (int) Math.ceil(speed), 0, LockedBlocks.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / -2 - (int) Math.ceil(speed), getImage().getHeight() / 2 + 2, LockedBlocks.class) != null)
        {
            canMoveLeft = false;
        }
        //end check for lockblock
        Door door = (Door) getOneObjectAtOffset(getImage().getWidth() / -2 - (int) Math.ceil(speed), 0,  Door.class);
        if (door != null) {
            if (!door.opened) {
                canMoveLeft = false;
            }
        }
        //check out of the world
        if (doubleX - (int) Math.ceil(speed) < getImage().getWidth() / 2.0) {
            canMoveLeft = false;
        }
        return canMoveLeft;
    }
    protected boolean canEntityMoveLeft(double speed){
        boolean canMoveLeft = true;
        if (getOneObjectAtOffset(getImage().getWidth() / -2 - (int) Math.ceil(speed), getImage().getHeight() / -2 + 2, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / -2 - (int) Math.ceil(speed), 0,  Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / -2 - (int) Math.ceil(speed), getImage().getHeight() / 2 - 2,  Solid.class) != null)
            canMoveLeft = false;
        if (getOneObjectAtOffset(getImage().getWidth() / -2 - (int) Math.ceil(speed), getImage().getHeight() / -2 - 2, LockedBlocks.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / -2 - (int) Math.ceil(speed), 0, LockedBlocks.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / -2 - (int) Math.ceil(speed), getImage().getHeight() / 2 + 2, LockedBlocks.class) != null)
            canMoveLeft = false;
        Door door = (Door) getOneObjectAtOffset(getImage().getWidth() / -2 - (int) Math.ceil(speed), 0,  Door.class);
        if (door != null) {
            if (!door.opened) {
                canMoveLeft = false;
            }
        }
        Actor block = getOneObjectAtOffset(getImage().getWidth()/-2 - (int) Math.ceil(speed), getImage().getHeight() / 2 + Options.blockSize / 2, Solid.class);
        if (block == null) {
            canMoveLeft = false;
        }
        //check out of the world
        if (doubleX - (int) Math.ceil(speed) < getImage().getWidth() / 2.0 - Camera.scrolledX) {
            canMoveLeft = false;
        }
        return canMoveLeft;
    }

    /**
     * Checks if the player will be inside of a solid block in the next frame, takes a double to see the movement of the next frame
     *
     * @param speed         how far it should check to the right of you to see if you get into a block or not.
     * @return              returns true or false based on if you can move to the right or not
     */
    protected boolean canMoveRight(double speed){
        boolean canMoveRight = true;
        //if youre on a slope u move 1 pixel up cuz reasons
        if (getOneObjectAtOffset(getImage().getWidth()/2 - 1, getImage().getHeight()/2 + 2, SlopeRight.class) != null) {
            setRelativeLocation(0, -1);
        }
        if (getOneObjectAtOffset(getImage().getWidth() / 2 + (int) Math.ceil(speed), getImage().getHeight() / -2 + 2, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2 + (int) Math.ceil(speed), 0, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2 + (int) Math.ceil(speed), getImage().getHeight() / 2 - 2, Solid.class) != null)
            canMoveRight = false;

        if (this instanceof Player) {
            LockedBlocks lockBlock1 = (LockedBlocks) getOneObjectAtOffset(getImage().getWidth() / 2 + (int) Math.ceil(speed), getImage().getHeight() / -2 + 2, LockedBlocks.class);
            if (lockBlock1 != null) {
                lockBlock1.unlockBlock();
            }
            LockedBlocks lockBlock2 = (LockedBlocks) getOneObjectAtOffset(getImage().getWidth() / 2 + (int) Math.ceil(speed), 0, LockedBlocks.class);
            if (lockBlock2 != null) {
                lockBlock2.unlockBlock();
            }
            LockedBlocks lockBlock3 = (LockedBlocks) getOneObjectAtOffset(getImage().getWidth() / 2 + (int) Math.ceil(speed), getImage().getHeight() / 2 - 2, LockedBlocks.class);
            if (lockBlock3 != null) {
                lockBlock3.unlockBlock();
            }
        }
        if (getOneObjectAtOffset(getImage().getWidth() / 2 + (int) Math.ceil(speed), getImage().getHeight() / -2 + 2, LockedBlocks.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2 + (int) Math.ceil(speed), 0, LockedBlocks.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2 + (int) Math.ceil(speed), getImage().getHeight() / 2 - 2, LockedBlocks.class) != null)
        {
            canMoveRight = false;
        }
        Door door = (Door) getOneObjectAtOffset(getImage().getWidth() / 2 + (int) Math.ceil(speed), 0, Door.class);
        if (door != null) {
            if (!door.opened) {
                canMoveRight = false;
            }
        }
        if (doubleX - (int) Math.ceil(speed) > Options.screenWidth - getImage().getWidth() / 2.0) {
            canMoveRight = false;
        }
        return canMoveRight;
    }
    protected boolean canEntityMoveRight(double speed){
        boolean canMoveRight = true;
        if (getOneObjectAtOffset(getImage().getWidth() / 2 + (int) Math.ceil(speed), getImage().getHeight() / -2 + 2, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2 + (int) Math.ceil(speed), 0, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2 + (int) Math.ceil(speed), getImage().getHeight() / 2 - 2, Solid.class) != null)
            canMoveRight = false;
        if (getOneObjectAtOffset(getImage().getWidth() / 2 + (int) Math.ceil(speed), getImage().getHeight() / -2 + 2, LockedBlocks.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2 + (int) Math.ceil(speed), 0, LockedBlocks.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2 + (int) Math.ceil(speed), getImage().getHeight() / 2 - 2, LockedBlocks.class) != null)
        {
            canMoveRight = false;
        }
        Door door = (Door) getOneObjectAtOffset(getImage().getWidth() / 2 + (int) Math.ceil(speed), 0, Door.class);
        if (door != null) {
            if (!door.opened) {
                canMoveRight = false;
            }
        }
        Actor block = getOneObjectAtOffset(getImage().getWidth()/2 + (int) Math.ceil(speed), getImage().getHeight() / 2 + Options.blockSize / 2, Solid.class);
        if (block == null) {
            canMoveRight = false;
        }
        if (doubleX - (int) Math.ceil(speed) > Globals.worldWidth - getImage().getWidth() / 2.0) {
            canMoveRight = false;
        }
        return canMoveRight;
    }
    /**
     * Check to see if a specific class is underneat the player, first checks in the middle then the left then right of
     * the bottom of the image of the player
     *
     * @param classToCheck      A class to check if its underneat the player
     * @return                  Returns the actor below you or null
     */
    protected Actor getObjectBelowOfClass(Class classToCheck) {
        if (vSpeed >= 0) {
            Actor actor;
            actor = getOneObjectAtOffset(0, getImage().getHeight() / 2 + 1, classToCheck);
            if (actor == null) {
                actor = getOneObjectAtOffset(getImage().getWidth() / -4, getImage().getHeight() / 2 + 1, classToCheck);
            }
            if (actor == null) {
                actor = getOneObjectAtOffset(getImage().getWidth() / 4, getImage().getHeight() / 2 + 1, classToCheck);
            }
            return actor;
        } else {
            return null;
        }
    }

    /**
     * Moves the player by speed pixels to the right, uses doubleX to maintain accuracy with subpixels
     *
     * @param speed         how far the player should move to the right
     */
    protected void moveRight(double speed)
    {
        setRelativeLocation(speed,0);
    }

    /**
     * Moves the player by speed pixels to the left, uses doubleX to maintain accuracy with subpixels
     *
     * @param speed         how far the player should move to the left
     */
    protected void moveLeft(double speed)
    {
        setRelativeLocation(- speed,0);
    }

    protected void jumpPad(){
        if (vSpeed >= 0) {
            JumpPad jumpPad = (JumpPad) getObjectBelowOfClass(JumpPad.class);
            if (jumpPad != null) {
                if (!jumpPad.holding){
                    jtime = jtime + 1;
                    if (jtime <= 9) {
                        if (jumpPad.vSpeed >= 0) {
                            vSpeed = jumpPad.vSpeed + 4;
                        } else {
                            this.vSpeed = jumpPad.vSpeed;
                        }
                    }
                    if (jtime == 10) {
                        this.vSpeed = 0;
                        this.jump(25);
                    }
                    if (jtime >= 10) {
                        jtime = 0;
                    }
                }
            }
        }
    }
}
