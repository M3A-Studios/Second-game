package nl.rocmondriaan.greenfoot.game;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LevelSelector extends World
{
    private static int selectedLevel;
    private static int levelWidth;
    private static int levelHeight;
    private static int totalLayers; //total layers in tile map
    private static GreenfootImage background = new GreenfootImage("background.png");;

    private Camera2 camera; //camera object created later at spawnCamera()
    private Actor selectorPlayer; //player object created later at renderMap()

    /**
     * Act method called every frame to scroll the camera as long as the death condition isn't met.
     */
    public void act() {
        scroll(); //scroll the camera
    }

    //if no level argument is given will just call itself with level -1. this will be handled over in the one argument
    //constructor to make sure of what level you should spawn on (or just level 1)
    public LevelSelector() {
        this(0);
    }

    /**
     * Constructor method of the level worlds. Creates the screen based on Options.screenWidth and Options.screenHeight
     * (see Options class) world has a cellsize of 1 pixel and isn't bounded (bounded meaning we can spawn blocks past
     * the screen border like say x -10 or x 5819291
     *
     * @param level         pass on what level the game should be loading the player at, basically if this is set to 1
     *                      the player will load on the first level node, if 15 they will load on the 15th. spares you
     *                      a bit of walking when in later levels ;)
     */
    public LevelSelector(int level) {
        super(Options.screenWidth, Options.screenHeight, 1, false); //render the screen with said screensize
        if (level < 1) {
            if (selectedLevel < 1) {
                selectedLevel = 1;
            }
        } else {
            selectedLevel = level;
            System.out.println(selectedLevel);
        }

        //reset camera position
        Globals.worldHeight = 0;
        Globals.worldWidth = 0;
        Camera2.scrolledX = 0;
        Camera2.scrolledY = 0;
        Camera2.entityXOffset = 0;
        Camera2.entityYOffset = 0;

        renderMap(getMap(), level, 4); //spawn the map and player as said layer
        renderLocks(); //render the locks over locked levels
        spawnCamera(); //spawn the camera
        renderHud();
    }
    private void renderHud() {
        addObject (new HUDNumber(0, "coin"), (int) (Options.blockSize * 0.5), (int) (Options.blockSize * 0.5));
        addObject (new HUDNumber(1, "coin"), (int) (Options.blockSize * 1.25) , (int) (Options.blockSize * 0.5));
        addObject (new HUDNumber(2, "coin"), (int) (Options.blockSize * 1.75) , (int) (Options.blockSize * 0.5));
        addObject (new HUDNumber(3, "coin"), (int) (Options.blockSize * 2.25), (int) (Options.blockSize * 0.5));
        addObject (new HUDNumber(4, "coin"), (int) (Options.blockSize * 2.75), (int) (Options.blockSize * 0.5));
        addObject (new HUDNumber(1, "score"), (int) (Options.blockSize * 16.5), (int) (Options.blockSize * 0.5));
        addObject (new HUDNumber(2, "score"), (int) (Options.blockSize * 17), (int) (Options.blockSize * 0.5));
        addObject (new HUDNumber(3, "score"), (int) (Options.blockSize * 17.5), (int) (Options.blockSize * 0.5));
        addObject (new HUDNumber(4, "score"), (int) (Options.blockSize * 18), (int) (Options.blockSize * 0.5));
        addObject (new HUDNumber(5, "score"), (int) (Options.blockSize * 18.5), (int) (Options.blockSize * 0.5));
        addObject (new HUDNumber(6, "score"), (int) (Options.blockSize * 19), (int) (Options.blockSize * 0.5));
        addObject (new HUDNumber(7, "score"), (int) (Options.blockSize * 19.5), (int) (Options.blockSize * 0.5));
    }

    /**
     * Returns the currently selected level
     *
     * @return          return currently selected level as int
     */
    static int getSelectedLevel() {
        return selectedLevel;
    }

    /**
     * Sets the current selected level
     *
     * @param input     sets the current selected level to this
     */
    static void setSelectedLevel(int input) {
        selectedLevel = input;
    }

    /**
     * Render the locks over levels which are not yet unlocked.
     * does this for all 15 levels by checking Globals.levelsUnlocked vs what index it's at.
     */
    private void renderLocks() {
        for (int i = 0; i < 15; i++) { //loop to check all levels
            if (i > Globals.levelsUnlocked) { //if this level is unlocked
                addObject(new LevelLock(),                                                  //actor
                        (int) (getLevelX(i) * Options.blockSize),                           //x location in proper cords from levelX
                        (int) (getLevelY(i) * Options.blockSize  + Options.blockSize / 4)); //y location in proper cords and offset from levelY
            }
        }
    }

    /**
     * Simple method to return the tile location of the level, hardcoded. tiny bit increased for the offset we want
     * the SelectorPlayer to have when calling this method
     *
     * @param level         Pass on what level it should get the X value off
     * @return              returns the X value as the tile location (needs to be scaled up elsewhere)
     */
    static double getLevelX(int level) {
        switch(level) {
            case 1:
                return 22.5;
            case 2:
                return 19.5;
            case 3:
                return 21.5;
            case 4:
                return 23.5;
            case 5:
                return 22.5;
            case 6:
                return 20.5;
            case 7:
                return 16.5;
            case 8:
                return 13.5;
            case 9:
                return 15.5;
            case 10:
                return 21.5;
            case 11:
                return 24.5;
            case 12:
                return 22.5;
            case 13:
                return 21.5;
            case 14:
                return 18.5;
            case 15:
                return 14.5;
            default:
                return 22.5;
        }
    }

    /**
     * Simple method to return the tile location of the level, hardcoded. tiny bit increased for the offset we want
     * the SelectorPlayer to have when calling this method
     *
     * @param level         Pass on what level it should get the Y value off
     * @return              returns the Y value as the tile location (needs to be scaled up elsewhere)
     */
    static double getLevelY(int level) {
        switch(level) {
            case 1:
                return 28.25;
            case 2:
                return 26.25;
            case 3:
                return 24.25;
            case 4:
                return 19.25;
            case 5:
                return 17.25;
            case 6:
                return 18.25;
            case 7:
                return 17.25;
            case 8:
                return 15.25;
            case 9:
                return 13.25;
            case 10:
                return 12.25;
            case 11:
                return 11.25;
            case 12:
                return 9.25;
            case 13:
                return 5.25;
            case 14:
                return 6.25;
            case 15:
                return 6.25;
            default:
                return 28.25;
        }
    }

    /**
     * Simple method to spawn the camera and update it to the player's position background is just given because
     * the camera was coded that way... will need a rework later for use without background.
     */
    private void spawnCamera() {
        camera = new Camera2(this, background, Globals.worldWidth, Globals.worldHeight);
        scroll();
    }

    /**
     * Collects information on how far the camera should scroll and then scrolls by that much
     * only moves when set barrier is crossed to create a deadzone in the center that doesn't scroll
     * this makes the camera a bit nicer as it isn't as annoying
     * might want to add a bit of drag later to this.
     *
     * loX (low X)  = barrier left of center
     * hiX (high X) = barrier right of center
     * loY (low Y)  = barrier above the center
     * hiY (high Y) = barrier below the center
     *
     * calls up camera.scroll with resulted dsx and dsy to scroll by those cords relative to current camera position
     */
    private void scroll() {
        int loX = Options.screenWidth/16*7; //Barrier left of center to move
        int hiX = Options.screenWidth-(Options.screenWidth/16*7); //Barrier right of center to move
        int loY = Options.screenHeight/8*3; //Barrier from the ceiling to move
        int hiY = Options.screenHeight-(Options.screenHeight/8*3); //Barrier from the bottom to move
        // determine offsets and scroll
        int dsx = 0, dsy = 0;
        //check if player is past the barriers ^
        if (selectorPlayer.getX() < loX) dsx = selectorPlayer.getX()-loX;
        if (selectorPlayer.getX() > hiX) dsx = selectorPlayer.getX()-hiX;
        if (selectorPlayer.getY() < loY) dsy = selectorPlayer.getY()-loY;
        if (selectorPlayer.getY() > hiY) dsy = selectorPlayer.getY()-hiY;
        camera.scroll(dsx, dsy); //scroll the world
    }

    /**
     * Method that reads a file and based on that returns a 2 dimensional array later to be used when rendering the map
     * Unlike Levels.java this one has no arguments because it will always load the same map
     *
     * @return              returns 2 dimensional array as [layer][ID]
     */
    private static int[][] getMap() {
        levelWidth = 0; //reset level width
        levelHeight = 0; //reset level height
        int[][] world = new int[0][]; //world map 2 dimensional. [layer][position]
        StringBuilder mapString = new StringBuilder(); //clear out map
        int currentLayer = 1; //set to first layer

        //read the level layout
        File readFile = new File("src/main/resources/tilemap/levelselector.tmx");
        Scanner dataReader = null; //scanner for the file
        try
        {
            dataReader = new Scanner(readFile); //try to read the file
        }
        catch(IOException e) //error report
        {
            System.out.println("File Read error" + e); //show error
        }
        //read the next line as long as one exists, checks if there is any data first
        assert dataReader != null : "The map file that is being read has no text in it!";
        while(dataReader.hasNext()) //while there's a next line
        {
            String line = dataReader.next(); //String line is the next line in the file
            if (line.contains("width=\"") && !line.contains("tile")) { //check for width text
                if (levelWidth == 0) { //only do it the first time it sees height that isnt 0
                    line = line.replaceAll("[^\\d]",""); //replace all non digits with nothing
                    levelWidth = Integer.parseInt(line); //set levelWidth
                }
            }
            if (line.contains("height=\"") && !line.contains("tile")) { //check for height text
                if (levelHeight == 0) { //only do it the first time it sees height that isnt 0
                    line = line.replaceAll("[^\\d]",""); //replace all non digits with nothing
                    levelHeight = Integer.parseInt(line); //set levelHeight
                }
            }
            if (line.contains("nextlayerid=\"")) { //check for total amount of layers
                line = line.replaceAll("[^\\d]",""); //replace all non digits with nothing
                totalLayers = Integer.parseInt(line) - 1; //get total layers as int
                world = new int[totalLayers][levelWidth * levelHeight]; //make the array to hold all the layers and their information (2 dimensional)
            }
            if (line.contains("<layer")) { //check if entering a new layer
                if (!mapString.toString().equals("")) { //check if map isn't empty before moving on to next layer

                    String[] layer = mapString.toString().split(","); //split values by ,
                    for (int i = 0; i < (levelWidth * levelHeight); i++){
                        world[currentLayer - 1][i] = Integer.parseInt(layer[i]); //turn array into int array just some parsing from text to integer
                    }
                    currentLayer++; //go to next layer in the map
                    mapString = new StringBuilder(); //reset mapString for new layer back to having no values
                }
            }
            if (line.contains(",")) { //check if line is part of the map
                mapString = new StringBuilder(mapString.append(line)); //add new line to total map
                //note we are using StringBuilder here to it being more efficient being that we do this a lot of times.
            }
        }

        String[] layer = mapString.toString().split(","); //split values by "," so 0,0 -> 0 and 0
        for (int i = 0; i < (levelWidth * levelHeight); i++){
            world[currentLayer - 1][i] = Integer.parseInt(layer[i]); //turn the String array into int array
        }
        Globals.worldHeight = levelHeight * Options.blockSize;
        Globals.worldWidth = levelWidth * Options.blockSize;
        dataReader.close(); //remove the scanner. we don't need it anymore

        return world; //return our two dimensional array
    }

    /**
     * Method used to render the actual map into the game based on a 2 dimensional array
     * @param worldMap          Expects a 2 dimensional array which holds all layers and their values. used to render the world
     * @param level             Tells the player on what node it has to be rendered.
     * @param playerLayer       When the layer that is being rendered is equal to this the player gets added at the end of rendering this layer
     */
    private void renderMap(int[][] worldMap, int level, int playerLayer) {
        int width = -1;
        int height = 0;
        for (int layer = 0; layer < totalLayers; layer++) { //for loop to check all layers
            for (int position = 0; position < (levelWidth * levelHeight); position++) { //for loop to check all data within the layer
                width++; //go right by one
                //when done doing a full row go to the next and reset width to 0
                if (width >= levelWidth) {
                    height ++;
                    width = 0;
                }
                //check for out of bounds
                if (height > levelHeight - 1) {
                    System.out.println("Error in loading the map, out of bounds");
                    break;
                }
                placeBlock: {
                    //check the ID thats at that point in the map against different types of blocks
                    //if no block matches it will simply not place anything there (empty tile, so air)
                    Actor nextBlock;
                    if (worldMap[layer][position] != 0)
                    {
                        nextBlock = new MapTile(worldMap[layer][position]);
                    }
                    else
                    {
                        break placeBlock;
                    }
                    Add(nextBlock, width, height); //method used to place block at the right cords using the tile x,y
                }
            }
            //reset width and height for next layer
            width = -1;
            height = 0;
            //if the just rendered layer is the layer of the player then spawn the player here.
            if (layer == playerLayer) {
                selectorPlayer = new SelectorPlayer();
                addObject(selectorPlayer, (int) (Options.blockSize * getLevelX(level)), (int) (Options.blockSize * getLevelY(level)));
            }
        }
    }

    /**
     * Method used to add a block/tile/object into the map based on the X,Y of the tile
     * this x,y will then be sized up to be equals to the Options.blockSize
     *
     * @param nextBlock         actor for what block should be placed
     * @param width             x coordinate for where the block should be placed
     * @param height            y coordinate for where the block should be placed
     */
    private void Add(Actor nextBlock, int width, int height) {
        addObject(
                nextBlock,                                           //Actor
                width*Options.blockSize + Options.blockSize/2,   //x coordinate
                height*Options.blockSize + Options.blockSize/2); //y coordinate;
    }
}
