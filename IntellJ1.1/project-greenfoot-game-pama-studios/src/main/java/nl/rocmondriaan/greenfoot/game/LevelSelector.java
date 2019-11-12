package nl.rocmondriaan.greenfoot.game;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import nl.rocmondriaan.greenfoot.engine.*;
import java.io.*;
import java.util.*;

public class LevelSelector extends World
{
    // Declareren van TileEngine
    private TileEngine te;

    private static int levelX;
    private static int levelY;
    private static int selectedLevel;
    private static int levelWidth;
    private static int levelHeight;
    private static int[][] world; //world map 2 dimensional. [layer][position]
    private static int totalLayers; //total layers in tile map
    private static GreenfootImage background = new GreenfootImage("background.png");;

    Camera2 camera; //camera object created later at spawnCamera()
    Actor selectorPlayer; //player object created later at renderMap()

    public void act() {
        scroll(); //scroll the camera
    }
    public LevelSelector() {
        this(1);
    }
    public LevelSelector(int level) {
        super(Options.screenWidth, Options.screenHeight, 1, false); //render the screen with said screensize

        selectedLevel = level;
        levelX = (int) (Options.blockSize * getLevelX(level));
        levelY = (int) (Options.blockSize * getLevelY(level));

        getMap(); //get the map of this level
        renderMap(4); //spawn the map and player as said layer
        spawnCamera(); //spawn the camera
    }
    public static int getSelectedLevel() {
        return selectedLevel;
    }
    public static void setSelectedLevel(int input) {
        selectedLevel = input;
    }
    public static double getLevelX(int level) {
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
    public static double getLevelY(int level) {
        switch(level) {
            case 1:
                return 28.5;
            case 2:
                return 26.5;
            case 3:
                return 24.5;
            case 4:
                return 19.5;
            case 5:
                return 17.5;
            case 6:
                return 18.5;
            case 7:
                return 17.5;
            case 8:
                return 15.5;
            case 9:
                return 13.5;
            case 10:
                return 12.5;
            case 11:
                return 11.5;
            case 12:
                return 9.5;
            case 13:
                return 5.5;
            case 14:
                return 6.5;
            case 15:
                return 6.5;
            default:
                return 28.5;
        }
    }
    private void spawnCamera() {
        camera = new Camera2(this, background, Globals.worldWidth, Globals.worldHeight);
        scroll();
    }
    private void scroll() {
        int loX = Options.screenWidth/16*7; //Barrier left of center to move
        int hiX = Options.screenWidth-(Options.screenWidth/16*7); //Barrier right of center to move
        int loY = Options.screenHeight/8*2; //Barrier from the ceiling to move
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
    private static void getMap() {
        levelWidth = 0; //reset level width
        levelHeight = 0; //reset level height
        String mapString = ""; //clear out map
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
        while(dataReader.hasNext()) //while there's a next line
        {
            String line = dataReader.next(); //line is next line
            if (line.contains("width=\"") && !line.contains("tile")) { //check for width
                if (levelWidth == 0) { //only do it the first time it sees height that isnt 0
                    line = line.replaceAll("[^\\d]",""); //replace all non digits with nothing
                    levelWidth = Integer.parseInt(line); //set levelWidth
                }
            }
            if (line.contains("height=\"") && !line.contains("tile")) { //check for height
                if (levelHeight == 0) { //only do it the first time it sees height that isnt 0
                    line = line.replaceAll("[^\\d]",""); //replace all non digits with nothing
                    levelHeight = Integer.parseInt(line); //set levelHeight
                    //System.out.println("Size: " + levelWidth + "x" + levelHeight); //debug show levelheight and width
                }
            }
            if (line.contains("nextlayerid=\"")) { //check for total amount of layers
                line = line.replaceAll("[^\\d]",""); //replace all non digits with nothing
                totalLayers = Integer.parseInt(line) - 1; //get total layers as int
                //System.out.println("Array off [" + (totalLayers - 1) + "][" + (levelWidth * levelHeight - 1) + "]"); //debug total layer
                world = new int[totalLayers][levelWidth * levelHeight]; //make the array to hold all the layers and their information (2 dimensional)
            }
            if (line.contains("<layer")) { //check if entering a new layer
                if (!mapString.equals("")) { //check if map isn't empty before moving on to next layer
                    //System.out.println("Layer " + currentLayer + ": " + mapString); //debug print this layer out.

                    String[] layer = mapString.split(","); //split values by ,
                    for (int i = 0; i < (levelWidth * levelHeight); i++){
                        world[currentLayer - 1][i] = Integer.parseInt(layer[i]); //turn array into int array just some parsing from text to integer
                    }
                    currentLayer++; //go to next layer in the map
                    mapString = ""; //reset mapString for new layer back to having no values
                }
            }
            if (line.contains(",")) { //check if line is part of the map
                mapString = mapString += line; //add new line to total map
            }
        }

        //System.out.println("Layer " + currentLayer + ": " + mapString); //debug print the last layer

        String[] layer = mapString.split(","); //split values by , so 0,0 -> 0 and 0
        for (int i = 0; i < (levelWidth * levelHeight); i++){
            world[currentLayer - 1][i] = Integer.parseInt(layer[i]); //turn array into int array
        }
        Globals.worldHeight = levelHeight * Options.blockSize;
        Globals.worldWidth = levelWidth * Options.blockSize;
        dataReader.close(); //remove the scanner. we don't need it anymore
    }
    private void renderMap(int playerLayer) {
        //System.out.println(levelWidth + ", " + levelHeight);
        int width = -1;
        int height = 0;
        for (int laag = 0; laag < totalLayers; laag++) { //conditie
            for (int positie = 0; positie < (levelWidth * levelHeight); positie++) {
                width++;
                if (width >= levelWidth) {
                    height ++;
                    width = 0;
                }
                if (height > levelHeight - 1) {
                    System.out.println("Error in loading the map, out of bounds");
                    break;
                }
                placeBlock: {
                    Actor nextBlock;
                    if (world[laag][positie] != 0)
                    {
                        nextBlock = new MapTile(world[laag][positie]);
                    }
                    else
                    {
                        break placeBlock;
                    }
                    Add(width, height, nextBlock);
                }
            }
            width = -1;
            height = 0;
            if (laag == playerLayer) {
                selectorPlayer = new SelectorPlayer();
                addObject(selectorPlayer, levelX, levelY);
            }
        }
    }
    private void Add(int width, int height, Actor nextBlock) {
        addObject(nextBlock, width*Options.blockSize + Options.blockSize/2,
                height*Options.blockSize + Options.blockSize/2);
    }
}
