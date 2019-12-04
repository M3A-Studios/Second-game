package game.world.level;

import game.entities.enemies.Bee;
import game.entities.enemies.Blade;
import greenfoot.*;
import game.*;
import game.blocks.normal.*;
import game.blocks.special.*;
import game.overlays.HUDNumber;
import game.overlays.Heart;
import game.overlays.Inventory;
import game.GameLogic;
import game.entities.Player;
import game.entities.enemies.Slime;
import game.Camera;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Levels extends World
{
    /** What checkpoint is currently active in the world */
    public static int activeCheckpoint = 1;
    /** The total width of the entire level in tiles */
    private static int levelWidth;
    /** The total height of the entire level in tiles */
    private static int levelHeight;
    /** The total amount of layers this world has */
    private static int totalLayers;
    /** Arraylist for all the checkpoint X locations */
    private ArrayList<Integer> checkpointX = new ArrayList<Integer>();
    /** Arraylist for all the checkpoint Y locations */
    private ArrayList<Integer> checkpointY = new ArrayList<Integer>();
    /** The camera object, initialized later in spawnCamera() */
    private Camera camera;
    /** The player object, initialized later in renderMap() */
    private Actor player;

    private int cameraShakeX, cameraShakeY;
    /**
     * Act method called every frame to scroll the camera as long as the death condition isn't met.
     */
    public void act() {
        if (!Player.dead && !Player.won){
            scroll(); //scrolls the camera to keep the player within bounds
        }
    }

    /**
     * Collects information on how far the camera should scroll and then scrolls by that much
     * only moves when set barrier is crossed to create a deadzone in the center that doesn't scroll
     * this makes the camera a bit nicer as it isn't as annoying
     * might want to add a bit of drag later to this.
     * <p>
     * Only
     *
     * loX (low X)  = barrier left of center
     * hiX (high X) = barrier right of center
     * loY (low Y)  = barrier above the center
     * hiY (high Y) = barrier below the center
     *
     * calls up camera.scroll with resulted dsx and dsy to scroll by those cords relative to current camera position
     */
    private void scroll() {
        int player1x = 0;
        int player1y = 0;
        int player2x = 0;
        int player2y = 0;

        boolean moving = false;
        String direction = null;

        List<Player> players = (List<Player>)(getObjects(Player.class));
        for(Player player : players) {
            if (player.player == 2) {
                player2x = player.getX();
                player2y = player.getY();
            } else {
                player1x = player.getX();
                player1y = player.getY();
            }
            if (player.moving) {
                moving = true;
                direction = player.movementDirection;
            }
        }
        int highestX = Math.max(player2x, player1x); //grab highest off the 2
        int highestY = Math.max(player2y, player1y); //grav highest off the 2

        //camera shake
        int addShakeX = 0; //how much to shake in X
        int addShakeY = 0; //how much to shake in Y
        if (cameraShakeX == 0 && cameraShakeY == 0) {
            if (moving) {
                if (direction.equals("left")) {
                    //addShakeX = new Random().nextInt(4) - 1;
                } else if (direction.equals("right")) {
                    //addShakeX = new Random().nextInt(4) - 1;
                }
            }
        }

        int loX = Options.screenWidth/16*7; //Barrier left of center to move
        int hiX = Options.screenWidth-(Options.screenWidth/16*7); //Barrier right of center to move
        int loY = Options.screenHeight/8*2; //Barrier from the ceiling to move
        int hiY = Options.screenHeight-(Options.screenHeight/8*3); //Barrier from the bottom to move
        // determine offsets and scroll
        int dsx = 0, dsy = 0;
        //check if player is past the barriers ^
        if (highestX < loX) dsx = highestX-loX;
        if (highestX > hiX) dsx = highestX-hiX;
        if (highestY < loY) dsy = highestY-loY;
        if (highestY > hiY) dsy = highestY-hiY;
        if (!(cameraShakeX == 0) || !(cameraShakeY == 0)) {
            dsx += cameraShakeX;
            dsy += cameraShakeY;
            cameraShakeX = 0;
            cameraShakeY = 0;
        } else {
            dsx += addShakeX;
            dsy += addShakeY;
            cameraShakeX = addShakeX;
            cameraShakeY = addShakeY;
        }
        camera.scroll(dsx, dsy); //scroll the camera
    }

    /**
     * Constructor to call {@link #Levels(int, int)} with level 1, checkpoint 0 for when no checkpoint or level is passed along
     *
     * @see #Levels(int, int)
     */
    public Levels() {
        this(1, 0);
    }

    /**
     * Constructor for if no checkpoint is given up
     *
     * @param level           pass on what level the game should be loading. based on this loads the file at
     *                        the location (src/main/resources/tilemap/level" + level + ".tmx"). the file is expected
     *                        to be a .tmx
     * @see #Levels(int, int)
     */
    public Levels(int level) {
        this(level, 0);
    }

    /**
     * Constructor method of the level worlds. Creates the screen based on Options.screenWidth and Options.screenHeight (see Options class)
     * world has a cellsize of 1 pixel and isn't bounded (bounded meaning we can spawn blocks past the screen border like say x -10 or x 5819291
     *
     * @param level             pass on what level the game should be loading. based on this loads the file at
     *                          the location (src/main/resources/tilemap/level" + level + ".tmx"). the file is expected
     *                          to be a .tmx
     * @param activeCheckpoint  pass on what checkpoint the player should be spawning at. should be 0 for spawning at the start
     */
    public Levels(int level, int activeCheckpoint) {
        super(Options.screenWidth, Options.screenHeight, 1, false); //render the screen with said screensize

        Levels.activeCheckpoint = activeCheckpoint;

        //reset camera
        Globals.worldHeight = 0;
        Globals.worldWidth = 0;
        Camera.scrolledX = 0;
        Camera.scrolledY = 0;
        Camera.entityXOffset = 0;
        Camera.entityYOffset = 0;

        //getMap(level); //get the map of this level
        getCheckpointLocations(level);
        if (level >= 10 && level <= 12) {
            addObject(new Background(new GreenfootImage("backgroundForest.png"), 0), 0, Options.screenHeight/2);
        }
        addObject(new Background(getBackground(level), level), 0, Options.screenHeight);
        renderMap(getMap(level), getPlayerLayer(level), level); //spawn the map and player as said layer

        if (level >= 7 && level <= 9) {
            addObject(new DesertStorm(), 0, Options.screenHeight);
        }

        renderText(level);
        spawnCamera(); //spawn the camera
        renderHUD(); //render the Heads Up Display (overlay like hearts and score)
        addObject(new GameLogic(), -10000, -10000); //add gamelogic object off the map, we want to use it's act method for counters etc like lockedblocks chain

        Music.stopMusic();
        addObject(new Music(), -100, -100);
    }

    /**
     * Gets all the checkpoint locations and adds them to {@link #checkpointX} and {@link #checkpointY}
     *
     * @param level     the level to get the checkpoints off
     */
    private void getCheckpointLocations(int level) {
        switch (level) {
            case 1:
                //Spawn location (checkpoint 0)
                checkpointX.add(1);
                checkpointY.add(14);
                //Checkpoint 1
                checkpointX.add(61);
                checkpointY.add(9);
                break;
            case 2:
                //Spawn location (checkpoint 0)
                checkpointX.add(2);
                checkpointY.add(41);
                //Checkpoint 1
                checkpointX.add(23);
                checkpointY.add(36);
                break;
            case 3:
                //Spawn location (checkpoint 0)
                checkpointX.add(1);
                checkpointY.add(26);
                break;
            case 4:
                checkpointX.add(2);
                checkpointY.add(15);
                break;
            case 5:
                checkpointX.add(2);
                checkpointY.add(15);
                break;
            case 6:
                //Spawn location (checkpoint 0)
                checkpointX.add(2);
                checkpointY.add(8);
                break;
            case 7:
                checkpointX.add(2);
                checkpointY.add(16);
                //check 1
                checkpointX.add(58);
                checkpointY.add(3);
                break;
            case 8:
                checkpointX.add(2);
                checkpointY.add(16);
                break;
            case 9:
                //Spawn location (checkpoint 0)
                checkpointX.add(2);
                checkpointY.add(8);
                break;
            case 10:
                checkpointX.add(2);
                checkpointY.add(14);
                //check 1
                checkpointX.add(28);
                checkpointY.add(26);
                break;
            case 11:
                //Spawn location (checkpoint 0)
                checkpointX.add(2);
                checkpointY.add(9);
                break;
            case 12:
                checkpointX.add(2);
                checkpointY.add(7);
                break;
            case 13:
                //Spawn location (checkpoint 0)
                checkpointX.add(2);
                checkpointY.add(8);
                break;
            case 14:
                //Spawn location (checkpoint 0)
                checkpointX.add(2);
                checkpointY.add(8);
                break;
            case 15:
                //Spawn location (checkpoint 0)
                checkpointX.add(2);
                checkpointY.add(8);
                break;
            default:
                //Spawn location (checkpoint 0)
                checkpointX.add(2);
                checkpointY.add(8);
        }
    }

    /**
     * Renders the checkpoints in the world
     *
     * @param color   The color the flag should be
     */
    private void renderCheckpoints(String color) {
        int flag = 168; //default blue
        if (color.equals("Blue")) {
            flag = 168;
        }
        for (int i = 1; i < checkpointX.size(); i++) {
            Add(new Checkpoint(i, flag), checkpointX.get(i) - 1, checkpointY.get(i));
        }
    }

    /**
     * Renders the text in the world
     *
     * @param level     The level of which the text should be rendered
     */
    private void renderText(int level) {
        if (level == 1) {

        }
    }

    /**
     * Method used to get on what screenlayer the player should be spawned (screenlayers being the layers in Tiled)
     *
     * @param level         int of what level the playerlayer should be returned. handled in a switch case
     * @return              returns int of what layer the player should spawn on
     */
    private int getPlayerLayer(int level) {
        switch (level) {
            case 1:
                return 3;
            case 2:
                return 0;
            case 5:
                return 3;
            default:
                return 0;
        }
    }

    /**
     * Method for rendering the Heads Up Display (HUD) or well. the overlay, things like hearts, score,
     * items in inventory etc. Really only just makes objects and places them
     */
    private void renderHUD() {
        addObject (new Heart(1), Options.blockSize, Options.blockSize);
        addObject (new Heart(2), Options.blockSize * 2, Options.blockSize);
        addObject (new Heart(3), Options.blockSize * 3, Options.blockSize);
        if (Options.bonusHeartUnlocked) {
            addObject(new Heart(4,true), Options.blockSize * 4, Options.blockSize);
            Player.health += 2;
        }
        addObject(new Inventory("panel"),Options.blockSize * 4, Options.blockSize);
        addObject(new Inventory("item"),Options.blockSize * 4, Options.blockSize);
        addObject (new HUDNumber(0, "coin"), Options.blockSize , Options.blockSize * 2);
        addObject (new HUDNumber(1, "coin"), (int) (Options.blockSize * 1.75) , Options.blockSize * 2);
        addObject (new HUDNumber(2, "coin"), (int) (Options.blockSize * 2.25) , Options.blockSize * 2);
        addObject (new HUDNumber(3, "coin"), (int) (Options.blockSize * 2.75), Options.blockSize * 2);
        addObject (new HUDNumber(1, "score"), (int) (Options.blockSize * 17.5), (int) (Options.blockSize * 0.5));
        addObject (new HUDNumber(2, "score"), (int) (Options.blockSize * 18), (int) (Options.blockSize * 0.5));
        addObject (new HUDNumber(3, "score"), (int) (Options.blockSize * 18.5), (int) (Options.blockSize * 0.5));
        addObject (new HUDNumber(4, "score"), (int) (Options.blockSize * 19), (int) (Options.blockSize * 0.5));
        addObject (new HUDNumber(5, "score"), (int) (Options.blockSize * 19.5), (int) (Options.blockSize * 0.5));
        addObject (new HUDNumber(0, "star"), (int) (Options.blockSize * 19.5), (int) (Options.blockSize * 1.25));
    }

    /**
     * Simple method to spawn the camera and update it to the player's position
     * grabs what background the level should use based on the level argument
     */
    private void spawnCamera() {
        camera = new Camera(this, Globals.worldWidth, Globals.worldHeight);
        scroll(); //scroll to the player's position properly
    }

    /**
     * Method used to get the image to use as background, mainly used later in spawnCamera()
     *
     * @param level         int argument for what level it should get the background from, handled in a switch
     * @return              returns an image
     */
    private static GreenfootImage getBackground(int level) {
        //currently has no case in this because we only have one background.. will update later
        String file;
        if (level == 1 | level == 3) {
            file = "backgroundStone.png";
        }else if (level == 2) {
            file = "backgroundStone2.png";
        } else if (level <= 6) {
            file = "backgroundColorFall.png";
        } else if (level <= 9) {
            file = "backgroundColorDesert.png";
        } else if (level <= 10) {
            file = "level10BG.png" ;
        } else if (level <= 12) {
            file = "backgroundForest.png";
        } else {
            file = "backgroundColorGrass.png";
        }
        return new GreenfootImage(file);
    }

    /**
     * Method that reads a file and based on that returns a 2 dimensional array later to be used when rendering the map
     *
     * @param level         int argument to tell the method what level it should load, the level file should be at
     * @return              returns 2 dimensional array as [layer][ID]
     */
    private static int[][] getMap(int level) {
        levelWidth = 0; //reset level width (in tiles)
        levelHeight = 0; //reset level height (in tiles)
        int[][] world = new int[0][]; //world map 2 dimensional. [layer][position]
        StringBuilder mapString = new StringBuilder(); //clear out mapString (current layer)
        int currentLayer = 1; //set to first layer

        //create the scanner
        File readFile = new File("src/main/resources/tilemap/level" + level + ".tmx"); //set what file to read files should be named "level(number).tmx" so "level1.tmx" just count tutorial as 0 or -1
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
     * @param playerLayer       When the layer that is being rendered is equal to this the player gets added at the end of rendering this layer
     */
    private void renderMap(int[][] worldMap, int playerLayer, int level) {
        String checkpointColor;
        if (level == 1 || level == 2 || level == 3) {
            checkpointColor = "blue";
        } else {
            checkpointColor = "blue";
        }
        int width = -1;
        int height = 0;
        boolean didCheckpoints = false;
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
                    //check the ID that's at that point in the map against different types of blocks
                    //if no block matches it will simply not place anything there (empty tile, so air)
                    Actor nextBlock;
                    if (check(Globals.nonSolids,worldMap[layer][position]))
                    {
                        nextBlock = new NonSolid(worldMap[layer][position]);
                    }
                    else if (check(Globals.platforms, worldMap[layer][position]))
                    {
                        nextBlock = new Platform(worldMap[layer][position]);
                    }
                    else if (check(Globals.slopeLefts, worldMap[layer][position]))
                    {
                        nextBlock = new SlopeLeft(worldMap[layer][position]);
                    }
                    else if (check(Globals.slopeRights, worldMap[layer][position]))
                    {
                        nextBlock = new SlopeRight(worldMap[layer][position]);
                    }
                    else if (check(Globals.ladder, worldMap[layer][position]))
                    {
                        nextBlock = new Ladder(worldMap[layer][position]);
                    }
                    else if (check(Globals.lever, worldMap[layer][position]))
                    {
                        nextBlock = new Lever(worldMap[layer][position], 1);
                    }
                    else if (check(Globals.door, worldMap[layer][position]))
                    {
                        nextBlock = new Door(worldMap[layer][position], 1);
                    }
                    else if (check(Globals.hiddenBlocks, worldMap[layer][position]))
                    {
                        nextBlock = new HiddenBlocks(worldMap[layer][position]);
                    }
                    else if (check(Globals.star, worldMap[layer][position]))
                    {
                        nextBlock = new Star(worldMap[layer][position]);
                    }
                    else if (check(Globals.bombs, worldMap[layer][position]))
                    {
                        nextBlock = new Bomb(worldMap[layer][position]);
                    }
                    else if (check(Globals.keys, worldMap[layer][position]))
                    {
                        nextBlock = new Keys(worldMap[layer][position]);
                    }
                    else if (check(Globals.breakableBlocks, worldMap[layer][position]))
                    {
                        nextBlock = new BreakableBlocks(worldMap[layer][position]);
                    }
                    else if (check(Globals.jumpPad, worldMap[layer][position]))
                    {
                        nextBlock = new JumpPad(worldMap[layer][position]);
                    }
                    else if (check(Globals.slime, worldMap[layer][position]))
                    {
                        nextBlock = new Slime(worldMap[layer][position]);
                    }
                    else if (check(Globals.sawBlade, worldMap[layer][position]))
                    {
                        nextBlock = new Blade(worldMap[layer][position]);
                    }
                    else if (check(Globals.bee, worldMap[layer][position]))
                    {
                        nextBlock = new Bee(worldMap[layer][position]);
                    }
                    else if (check(Globals.lavas, worldMap[layer][position]))
                    {
                        nextBlock = new Lava(worldMap[layer][position]);
                    }
                    else if (check(Globals.coins, worldMap[layer][position]))
                    {
                        nextBlock = new Coins(worldMap[layer][position]);
                    }
                    else if (check(Globals.animatedObjects, worldMap[layer][position]))
                    {
                        nextBlock = new AnimatedObject(worldMap[layer][position]);
                    }
                    else if (check(Globals.spikes, worldMap[layer][position]))
                    {
                        nextBlock = new Spikes(worldMap[layer][position]);
                    }
                    else if (check(Globals.finishFlag, worldMap[layer][position])) {
                        nextBlock = new Flagpole((worldMap[layer][position]));
                    }
                    else if (check(Globals.lockedBlocks, worldMap[layer][position])) {
                        nextBlock = new LockedBlocks((worldMap[layer][position]));
                    }
                    else if (check(Globals.switchBlock, worldMap[layer][position])) {
                        nextBlock = new SwitchBlock((worldMap[layer][position]));
                    }
                    else if (check(Globals.pswitch, worldMap[layer][position])) {
                        nextBlock = new PSwitch((worldMap[layer][position]));
                    }
                    else if (worldMap[layer][position] != 0)
                    {
                        nextBlock = new Solid(worldMap[layer][position]);
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
            if ((layer - 1) >= 0 && layer == playerLayer - 1) {
                renderCheckpoints(checkpointColor); //render the checkpoints per level, hardcoded
                didCheckpoints = true;
            }
            if (layer == playerLayer) {
                player = new Player(1);
                addObject(player, Options.blockSize * (checkpointX.get(activeCheckpoint) - 1) + Options.blockSize / 2, Options.blockSize * (checkpointY.get(activeCheckpoint)) + Options.blockSize / 4);
                if (!didCheckpoints) { //if not done last layer
                    renderCheckpoints(checkpointColor); //render the checkpoints per level, hardcoded
                }
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

    /**
     * Method used to check if the given ID is part of the array given to it
     * basically checks if the number is in that Integer array (Integer because we're using arraylist here)
     *
     * @param array     the ID to check if it's in the array
     * @param ID        the array to check if the ID is part of it
     * @return          returns a true or false based on if the ID is in the array
     */
    private static boolean check(Integer[] array, int ID) {
        return Arrays.asList(array).contains(ID - 1);
    }
}