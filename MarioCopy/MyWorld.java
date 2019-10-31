import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     *
     */
    public MyWorld()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(Options.screenWidth, Options.screenHeight, 1);
        getBackground().setColor(new Color(135, 206, 235));
        getBackground().fill();
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Player player = new Player();
        addObject(player,101,553);
        player.setLocation(37,567);

        LadderMid ladderMid = new LadderMid();
        addObject(ladderMid,307,555);

        LadderMid ladderMid2 = new LadderMid();
        addObject(ladderMid2,307,514);

        LadderTop ladderTop = new LadderTop();
        addObject(ladderTop,307,472);

        Solid solid = new Solid(99);
        addObject(solid,42,631);
        solid.setLocation(22,620);

        Solid solid2 = new Solid(99);
        addObject(solid2,64,620);

        Solid solid3 = new Solid(99);
        addObject(solid3,106,620);

        Solid solid4 = new Solid(99);
        addObject(solid4,148,620);

        Solid solid5 = new Solid(99);
        addObject(solid5,190,620);

        Solid solid6 = new Solid(99);
        addObject(solid6,232,620);

        Solid solid7 = new Solid(99);
        addObject(solid7,274,620);

        Solid solid8 = new Solid(99);
        addObject(solid8,316,620);
        Solid solid9 = new Solid(99);
        addObject(solid9,138,478);
        solid9.setLocation(147,508);
    }
}
