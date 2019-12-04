package game.overlays;

import game.world.level.Levels;
import game.world.store.Store;
import greenfoot.GreenfootImage;
import game.Globals;
import game.world.levelselector.LevelSelector;
import game.Options;
import game.Physics;

/**
 *
 */
public class HUDNumber extends Physics {
    /** gets checked first frame to get the startX and startY */
    private boolean started = false;
    /** Initial starting coordinates of the heart */
    private int startX, startY;
    /** What position this number is in the hud, positions being like 123,456 */
    private int position;
    /** Array that will hold the images for all the numbers */
    private GreenfootImage[] images = new GreenfootImage[10];
    /** The value of this number in the HUD */
    private String value = "0";
    private String coins;
    private String star;
    /** The purpose of the HUD number, if it should be a "coin" or "score" counter */
    private String purpose;

    private GreenfootImage emptyImage = new GreenfootImage("starempty.png");
    private GreenfootImage starImage = new GreenfootImage("189.png");

    /**
     * Constructor for the HUD Numbers which simply sets the images
     *
     * @param position      What position in the number this number is (123,456)
     * @param purpose       What the purpose of this number is ("coin" or "score")
     */
    public HUDNumber(int position, String purpose) {
        this.purpose = purpose;
        this.position = position;
        if (position != 0) {
            renderImages();
            setImage(images[0]);
        } else {
            if (purpose.equals("coin")) {
                GreenfootImage coinImage = new GreenfootImage("hudCoin.png");
                coinImage.scale(Options.blockSize, Options.blockSize);
                setImage(coinImage);
            }
            if (purpose.equals("star")) {
                starImage.scale(Options.blockSize, Options.blockSize);
                emptyImage.scale(Options.blockSize, Options.blockSize);
                setImage(emptyImage);
            }
        }
    }

    /**
     * Renders the images for all the numbers
     */
    private void renderImages() {
        for(int i = 0; i <= 9; i++) {
            images[i] = new GreenfootImage("hud" + i + ".png");
            images[i].scale(Options.blockSize, Options.blockSize);
        }
    }

    /**
     * Updates the number to what it should be according to the coins or score (whatever it purpose is) and also
     * updates it's position to always be located where it started on the screen
     */
    public void act() {
        if (!started) {
            startX = getX();
            startY = getY();
            started = true;
        } else {
            setLocation(startX, startY);
            if (position != 0) {
                String coins;
                int start;
                if (purpose.equals("coin")) {
                    if (getWorld() instanceof LevelSelector) {
                        coins = Integer.toString(Globals.totalCoinsCollected);
                        start = 4;
                    } else if (getWorld() instanceof Store) {
                        coins = Integer.toString(Globals.totalCoinsCollected);
                        start = 4;
                    } else {
                        coins = Integer.toString(Globals.levelCoinsCollected);
                        start = 3;
                    }
                    String[] strArray = coins.split("");
                    start = start - strArray.length + 1;
                    if (position >= start) {
                        value = strArray[position - start];
                    } else {
                        value = "0";
                    }
                } else if (purpose.equals("score")) {
                    if (getWorld() instanceof LevelSelector) {
                        coins = Integer.toString(Globals.totalScore);
                        start = 7;
                    } else {
                        coins = Integer.toString(Globals.levelScore);
                        start = 5;
                    }
                    String[] strArray = coins.split("");
                    start = start - strArray.length + 1;
                    if (position >= start) {
                        value = strArray[position - start];
                    }
                }
                else if (purpose.equals("star")) {
                    if (getWorld() instanceof LevelSelector) {
                        start = 2;
                        int stars = 0;
                        for (int i = 1; i <= 15; i++) {
                            if (Globals.starsCollected[i] = true) {
                                stars += 1;
                            }
                        }
                        String startext = Integer.toString(stars);
                        String[] strArray = startext.split("");
                        start = start - strArray.length + 1;
                        if (position >= start) {
                            value = strArray[position - start];
                        }
                    }
                }
                setImage(images[Integer.parseInt(value)]);
            } else {
                if (purpose.equals("star")) {
                    if (getWorld() instanceof Levels) {
                        if (Globals.levelStarsCollected > 0) {
                            setImage(starImage);
                        }
                    }
                }
            }
        }
    }
}
