package game;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class Camera extends Actor
{
    /** Values of how far the camera has scrolled so far for X and Y */
    public static int scrolledX, scrolledY;
    /** Values for how much entities should update their doubleX and doubleY to correct for scrolling, done for EntityOffset() */
    public static int entityXOffset, entityYOffset;
    /** The width and height of the entire world */
    private int wide, high;
    /** What world the camera should be active in */
    private World world;

    /**
     * Constructor for the Camera, used to make the camera
     * Scroll is used at the end to instantly put it in the right place
     *
     * @param activeWorld     expects a Greenfoot.World, used to set what world it should be in
     * @param width           int value for the total world width
     * @param height          int value for the total world height
     */
    public Camera(World activeWorld, int width, int height) {
        world = activeWorld; //set the world the camera is in
        wide = width; //set the world size for the camera
        high = height; //set the world size for the camera
        scroll(0, 0);
    }

    /**
     * Scrolls the camera by dsx and dsy (relative to current location)
     *
     * @param dsx       int value for how far the camera should move along the x-axis
     * @param dsy       int value for how far the camera should move along the y-axis
     */
    public void scroll(int dsx, int dsy) {
        //get max limit (basically dont scroll past the borders)
        int maxX = wide-world.getWidth();
        int maxY = high-world.getHeight();
        // apply limits to distances to scroll (dont let it scroll lower than 0 or let it scroll past max
        if (scrolledX+dsx < 0) dsx = -scrolledX;
        if (scrolledX+dsx >= maxX) dsx = maxX-scrolledX;
        if (scrolledY+dsy < 0) dsy = -scrolledY;
        if (scrolledY+dsy >= maxY) dsy = maxY-scrolledY;
        //update the scrolled variables of total space scrolled
        //world coordinates would be getX() + scrolledX() (and same for Y) to account for camera offset.
        scrolledX += dsx;
        scrolledY += dsy;
        //scroll every actor in the map.
        for (Object obj : world.getObjects(null))
        {
            //set entity offset, used for when something shouldn't scroll.
            entityXOffset = -dsx;
            entityYOffset = -dsy;
            Actor actor = (Actor) obj;
            //line below was used in the past to skip certain classes, replaced with entityoffset for now
            //String actorString = "" + actor.getClass().getName();
            actor.setLocation(actor.getX()-dsx, actor.getY()-dsy);
        }
    }

    /**
     * Method used to get the cameraOffset (how far it has scrolled)
     * Remember that the accurate world cords of an object is getX() + getScrolledX() (and same for Y)
     *
     * @return      returns integer off how far the camera has scrolled from 0,0 for the x-axis
     */
    public int getScrolledX()
    {
        return scrolledX;
    }

    /**
     * Method used to get the cameraOffset (how far it has scrolled)
     * Remember that the accurate world cords of an object is getY() + getScrolledY() (and same for X)
     *
     * @return      returns integer off how far the camera has scrolled from 0,0 for the y-axis
     */
    public int getScrolledY()
    {
        return scrolledY;
    }
}

