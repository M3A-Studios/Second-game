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
        addObject(ladderMid,456,573);
        ladderMid.setLocation(481,555);

        LadderMid ladderMid2 = new LadderMid();
        addObject(ladderMid2,465,481);
        ladderMid2.setLocation(481,514);

        LadderTop ladderTop = new LadderTop();
        addObject(ladderTop,507,460);
        ladderTop.setLocation(481,472);
        Solid solid = new Solid(99);
        addObject(solid,42,631);
        solid.setLocation(22,620);
        Solid solid2 = new Solid(99);
        addObject(solid2,100,613);
        solid2.setLocation(78,629);
        Solid solid3 = new Solid(99);
        addObject(solid3,137,608);
        solid3.setLocation(118,624);
    }
}
