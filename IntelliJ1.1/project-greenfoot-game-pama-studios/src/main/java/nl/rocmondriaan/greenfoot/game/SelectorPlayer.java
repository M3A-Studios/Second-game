package nl.rocmondriaan.greenfoot.game;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class SelectorPlayer extends Physics {

    private int animationBlocks;
    private int framesPerBlock = 20;
    private int animatedTime;
    private int movingTo = 1;
    private String[] movingDir;
    private boolean moving;
    private boolean started = false;

    /**
     * Simply sets the image and scales it
     */
    SelectorPlayer() {
        GreenfootImage image;
        if (Options.player1Color.equals("Blue")) {
            image = new GreenfootImage("mapTile_137.png");
        } else if (Options.player1Color.equals("Pink")) {
            image = new GreenfootImage("mapTile_153.png");
        } else if (Options.player1Color.equals("Yellow")) {
            image = new GreenfootImage("mapTile_154.png");
        } else {
            image = new GreenfootImage("mapTile_136.png");
        }
        image.scale((Options.blockSize),(Options.blockSize));
        setImage(image);
    }


    /**
     * Act method gets executed every frame, first frame it will get the start locations for physics
     *
     * checks for some input and then moves
     */
    public void act() {
        //if just started sets the coordinates for the physics
        if (!started) {
            started = true;
            setDoubleX(getX());
            setDoubleY(getY());
        }
        entityOffset(); //makes it immune to camera scrolling
        checkInput(); //check for user input
        if (moving) { //if player is supposed to be in an animation
            move(); //move the player
        }
        if (Greenfoot.isKeyDown("escape")) {
            Saver.saveGame(); //save the game when going to the main menu
            Greenfoot.setWorld(new Menu());
        }
    }

    /**
     * Method used to animate the player based on the array movingDir which gets set in checkInput()
     */
    private void move() {
        animatedTime += 1;
        if (animatedTime < (animationBlocks * framesPerBlock)) {
            int index = (int) (animatedTime / framesPerBlock);
            String direction = movingDir[index];
            switch (direction) {
                case "up":
                    setRelativeLocation(0, -64.0 / framesPerBlock);
                    break;
                case "down":
                    setRelativeLocation(0, 64.0 / framesPerBlock);
                    break;
                case "left":
                    setRelativeLocation(-64.0 / framesPerBlock, 0);
                    break;
                case "right":
                    setRelativeLocation(64.0 / framesPerBlock, 0);
                    break;
                default:
                    System.out.println("Not moving any direction, nani"); //debug for when no direction is properly given
                    break;
            }
        } else {
            int levelX = (int) (Options.blockSize * LevelSelector.getLevelX(movingTo)); //gets x of the level you're gonna be at
            int levelY = (int) (Options.blockSize * LevelSelector.getLevelY(movingTo)); //gets y of the level you're gonna be at
            setNewLocation((levelX - Camera2.scrolledX), (levelY - Camera2.scrolledY)); //set you to center of the level, incase due to too low screenrez you aren't perfectly on it
            LevelSelector.setSelectedLevel(movingTo); //set selected level to where you just arrived
            moving = false; //sets moving back to false
        }
    }

    /**
     * Big hardcoded check for input and what level you're on, used to get the movement that needs to be done for move()
     */
    private void checkInput() {
        if (!moving) {
            if (Greenfoot.isKeyDown("d")) {
                if (LevelSelector.getSelectedLevel() == 2) {
                    movingDir = new String[]{"right","right","down","down", "right"};
                    movingTo = 1;
                }
                if (LevelSelector.getSelectedLevel() == 3) {
                    movingDir = new String[]{"right","right","up","up","up","up","up"};
                    movingTo = 4;
                }
                if (LevelSelector.getSelectedLevel() == 5) {
                    movingDir = new String[]{"right","down","down"};
                    movingTo = 4;
                }
                if (LevelSelector.getSelectedLevel() == 6) {
                    movingDir = new String[]{"right","right","up"};
                    movingTo = 5;
                }
                if (LevelSelector.getSelectedLevel() == 9) {
                    movingDir = new String[]{"right","right","right","right","up","right","right"};
                    movingTo = 10;
                }
                if (LevelSelector.getSelectedLevel() == 10) {
                    movingDir = new String[]{"right","right","right","up"};
                    movingTo = 11;
                }
                if (LevelSelector.getSelectedLevel() == 13) {
                    movingDir = new String[]{"right","down","down","down","down"};
                    movingTo = 12;
                }
                if (LevelSelector.getSelectedLevel() == 15) {
                    movingDir = new String[]{"right","right","right","right"};
                    movingTo = 14;
                }
            }
            if (Greenfoot.isKeyDown("w")) {
                if (LevelSelector.getSelectedLevel() == 2) {
                    movingDir = new String[]{"up","up","right","right"};
                    movingTo = 3;
                }
                if (LevelSelector.getSelectedLevel() == 4) {
                    movingDir = new String[]{"up","up","left"};
                    movingTo = 5;
                }
                if (LevelSelector.getSelectedLevel() == 7) {
                    movingDir = new String[]{"up","left","left","left","up"};
                    movingTo = 8;
                }
                if (LevelSelector.getSelectedLevel() == 8) {
                    movingDir = new String[]{"up","up","right","right"};
                    movingTo = 9;
                }
                if (LevelSelector.getSelectedLevel() == 11) {
                    movingDir = new String[]{"up","left","left","up"};
                    movingTo = 12;
                }
                if (LevelSelector.getSelectedLevel() == 12) {
                    movingDir = new String[]{"up","up","up","up","left"};
                    movingTo = 13;
                }
                if (LevelSelector.getSelectedLevel() == 14) {
                    movingDir = new String[]{"up","right","right","right"};
                    movingTo = 13;
                }
            }
            if (Greenfoot.isKeyDown("s")) {
                if (LevelSelector.getSelectedLevel() == 4) {
                    movingDir = new String[]{"down","down","down","down","down","left","left"};
                    movingTo = 3;
                }
                if (LevelSelector.getSelectedLevel() == 5) {
                    movingDir = new String[]{"down","left","left"};
                    movingTo = 6;
                }
                if (LevelSelector.getSelectedLevel() == 7) {
                    movingDir = new String[]{"down","right","right","right","right"};
                    movingTo = 6;
                }
                if (LevelSelector.getSelectedLevel() == 8) {
                    movingDir = new String[]{"down","right","right","right","down"};
                    movingTo = 7;
                }
                if (LevelSelector.getSelectedLevel() == 11) {
                    movingDir = new String[]{"down","left","left","left"};
                    movingTo = 10;
                }
                if (LevelSelector.getSelectedLevel() == 12) {
                    movingDir = new String[]{"down","right","right","down"};
                    movingTo = 11;
                }
            }
            if (Greenfoot.isKeyDown("a")) {
                if (LevelSelector.getSelectedLevel() == 1) {
                    movingDir = new String[]{"left", "up", "up", "left", "left"};
                    movingTo = 2;
                }
                if (LevelSelector.getSelectedLevel() == 3) {
                    movingDir = new String[]{"left","left","down","down"};
                    movingTo = 2;
                }
                if (LevelSelector.getSelectedLevel() == 6) {
                    movingDir = new String[]{"left","left","left","left","up"};
                    movingTo = 7;
                }
                if (LevelSelector.getSelectedLevel() == 9) {
                    movingDir = new String[]{"left","left","down","down"};
                    movingTo = 8;
                }
                if (LevelSelector.getSelectedLevel() == 10) {
                    movingDir = new String[]{"left","left","down","left","left","left","left"};
                    movingTo = 9;
                }
                if (LevelSelector.getSelectedLevel() == 13) {
                    movingDir = new String[]{"left","left","left","down"};
                    movingTo = 14;
                }
                if (LevelSelector.getSelectedLevel() == 14) {
                    movingDir = new String[]{"left","left","left","left"};
                    movingTo = 15;
                }
            }
            if (Greenfoot.isKeyDown("enter") || Greenfoot.isKeyDown("space")) {
                Saver.saveGame(); //save the game when going into a level
                Greenfoot.setWorld(new Levels(LevelSelector.getSelectedLevel()));
            }
            if (movingTo != LevelSelector.getSelectedLevel() && movingTo <= Globals.levelsUnlocked && movingDir != null) {
                moving = true;
                animationBlocks = movingDir.length;
                animatedTime = 0;
            }
        }
    }
}
