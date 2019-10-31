import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
import java.util.*;
public class Levels extends World
{
    private static int levelWidth;
    private static int levelHeight;
    private static String mapString; //mapstring used in getMap() for world[][]
    private static int world[][]; //world map 2 dimensional. [layer][position]
    private static int currentLayer; //current layer in tile map (for getMap())
    private static int totalLayers; //total layers in tile map
    Camera camera; //camera object created later at spawnCamera()
    public Levels()
    {    
        this(1,1); //by default load level 1 if non is specified
    }
    public void act() {
        scroll(); //scroll the camera
    }
    public Levels(int level, int playerLayer)
    {    
        super(Options.screenWidth, Options.screenHeight, 1, false); //render the screen with said screensize
        getMap(level); //get the map of this level
        renderMap(playerLayer); //spawn the map and player as said layer
        spawnCamera(); //spawn the camera 
    }
    private void spawnCamera() {
        camera = new Camera();
        addObject(camera, 0, 0);
    }
    private void scroll() {
        int dsx = 0, dsy = 0;
        int minX = Options.screenWidth/10*2;
        int maxX = Options.screenWidth-(Options.screenWidth/10*2);
        int minY = Options.screenHeight/10*2;
        int maxY = Options.screenHeight-(Options.screenHeight/10*2);
        if (camera.getX() < minX) dsx = camera.getX() - minX;
        if (camera.getX() < maxX) dsx = camera.getX() - maxX;
        if (camera.getY() < minY) dsy = camera.getY() - minY;
        if (camera.getY() < maxY) dsy = camera.getY() - maxY;
        camera.scroll(dsx, dsy);
    }
    private static void getMap(int level) {
        levelWidth = 0; //reset level width
        levelHeight = 0; //reset level height
        mapString = ""; //clear out map
        currentLayer = 1; //set to first layer
        
        //read the level layout
        File readFile = new File("levels/level" + level + ".tmx"); //set what file to read files should be named "level(number).tmx" so "level1.tmx" just count tutorial as 0 or -1
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
        dataReader.close(); //remove the scanner. we don't need it anymore
    }
    private void renderMap(int playerLayer) {
        System.out.println(levelWidth + ", " + levelHeight);
        int width = -1;
        int height = 0;
        for (int laag = 0; laag < totalLayers; laag++) { //conditie
            for (int positie = 0; positie < (levelWidth * levelHeight); positie++) {
                width++;
                if (width >= levelWidth) {
                    height ++;
                    width = 0;
                }
                //System.out.println(width + ", " + height);
                if (height > levelHeight - 1) {
                    System.out.println("Error in loading the map, out of bounds");
                    break;
                }
                placeBlock: {
                    Actor nextBlock;;
                    if (check(Globals.nonSolids,world[laag][positie] - 1)) 
                    {
                        nextBlock = new NonSolid(world[laag][positie]);
                    }
                    else if (world[laag][positie] != 0)
                    {
                        nextBlock = new Solid(world[laag][positie]);
                    }
                    else if (world[laag][positie] != 0)
                    {
                        System.out.print("Ladder created");
                        nextBlock = new Ladder(world[laag][positie]);
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
                Actor player = new Player();
                addObject(player, 60, 300);
            }
        }
    }
    private void Add(int width, int height, Actor nextBlock) {
        addObject(nextBlock, width*Options.blockSize + Options.blockSize/2,
                height*Options.blockSize + Options.blockSize/2);
    }
    public static boolean check(Integer[] arr, int toCheckValue) {
        return Arrays.asList(arr).contains(toCheckValue);
    }
}
