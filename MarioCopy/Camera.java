import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class Camera  
{
    private int scrolledX, scrolledY;
    public static int entityXOffset, entityYOffset;
    private int wide, high;
    private World world;
    private GreenfootImage background;
    Camera(World activeWorld, int width, int height) {
        world = activeWorld; //set the world the camera is in
        wide = width; //set the world size for the camera
        high = height; //set the world size for the camera
        scroll(0,0);
    }
    Camera(World activeWorld, GreenfootImage backgroundImage, int width, int height) {
        world = activeWorld; //set the world the camera is in
        wide = width; //set the world size for the camera
        high = height; //set the world size for the camera
        if (backgroundImage != null)
        {
            // create an image as large as scrolling area; tiled, if needeed
            background = new GreenfootImage(wide*world.getCellSize(), high*world.getCellSize());
            for (int x=0; x<wide*world.getCellSize(); x+= backgroundImage.getWidth()) //loop the image till the whole world has a background
                for (int y=0; y<high*world.getCellSize(); y+=high) //make the image as high as the world
                    background.drawImage(backgroundImage, x, y);
            // set initial background image
            scroll(0, 0);
        }
    }
    public void scroll(int dsx, int dsy) {
        //get max limit (basically dont scroll past the borders)
        int maxX = wide-world.getWidth();
        int maxY = high-world.getHeight();
        // apply limits to distances to scroll (dont let it scroll lower than 0 or let it scroll past max
        if (scrolledX+dsx < 0) dsx = -scrolledX;
        if (scrolledX+dsx >= maxX) dsx = maxX-scrolledX;
        if (scrolledY+dsy < 0) dsy = -scrolledY;
        if (scrolledY+dsy >= maxY) dsy = maxY-scrolledY;
        //update the scrolled variables
        scrolledX += dsx;
        scrolledY += dsy;
        
        //update the background image
        if (background != null)
        {
            world.getBackground().drawImage
            (   
                background,
                -scrolledX*world.getCellSize(),
                -scrolledY*world.getCellSize()
            );
        }
        //scroll every actor in the map.
        for (Object obj : world.getObjects(null))
        {
            entityXOffset = -dsx;
            entityYOffset = -dsy;
            Actor actor = (Actor) obj;
            String actorString = "" + actor.getClass().getName();
            actor.setLocation(actor.getX()-dsx, actor.getY()-dsy);
        }
    }
    //getter method to see how far the world has scrolled to get a better representation of the real X cooridnate (getX() + getScrolledX() is the new accurate X, same for Y)
    public int getScrolledX()
    {
        return scrolledX;
    } 
    public int getScrolledY()
    {
        return scrolledY;
    } 
}

