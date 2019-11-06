import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.io.*;
import java.util.*;
public class LevelSelector extends World
{
    private static int levelWidth;
    private static int levelHeight;
    private static String mapString; //mapstring used in getMap() for world[][]
    private static int world[][]; //world map 2 dimensional. [layer][position]
    private static int currentLayer; //current layer in tile map (for getMap())
    private static int totalLayers; //total layers in tile map
    private static GreenfootImage background = new GreenfootImage("background.png");;
    Camera camera; //camera object created later at spawnCamera()
    Actor player; //player object created later at renderMap()
    
    LevelSelector() {
        this(1);
    }
    LevelSelector(int level) {
        super(Options.screenWidth * 2, Options.screenHeight *5/2, 1, false); //render the screen with said screensize
        getMap(); //get the map of this level
        renderMap(1); //spawn the map and player as said layer
        spawnCamera(); //spawn the camera
    }
    
    //camera shit
    
    private void spawnCamera() {
        camera = new Camera(this, background, Globals.worldWidth, Globals.worldHeight);
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
        if (player.getX() < loX) dsx = player.getX()-loX;
        if (player.getX() > hiX) dsx = player.getX()-hiX;
        if (player.getY() < loY) dsy = player.getY()-loY;
        if (player.getY() > hiY) dsy = player.getY()-hiY;
        camera.scroll(dsx, dsy); //scroll the world
    }  
    
    //get map and render it
    
    private static void getMap() {
        levelWidth = 0; //reset level width
        levelHeight = 0; //reset level height
        mapString = ""; //clear out map
        currentLayer = 1; //set to first layer
        
        //read the level layout
        File readFile = new File("levels/levelselector.tmx"); 
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
                   currentLayer ++; //go to next layer in the map
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
                player = new Player();
                addObject(player, 60, 300);
            }
        }
    }
    private void Add(int width, int height, Actor nextBlock) {
        addObject(nextBlock, width*Options.blockSize + Options.blockSize/2,
                height*Options.blockSize + Options.blockSize/2);
    }
}
