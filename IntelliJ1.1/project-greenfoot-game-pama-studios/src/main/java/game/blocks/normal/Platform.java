package game.blocks.normal;

import greenfoot.GreenfootImage;
import game.Options;
import game.blocks.Blocks;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Platforms are half height blocks that are only solid when on top of them, an entity can walk over them but also
 * jump onto them from below without hitting its head.
 */
public class Platform extends Blocks {

    /** Checks if the game has passed it's first frame yet or not */
    private boolean started = false;

    /**
     * Calls for the constructor in Blocks.java to set the image of the tile.
     * All of these images will be 1x1 in the grid. (Options.blockSize * Options.blockSize).
     * <p>
     * This image is then cut by getting the subImage of the top half.
     *
     * @param ID    used to get what file should be displayed as the image of this object
     * @see Blocks#Blocks(int)
     */
    public Platform(int ID) {
        super(ID);

        //get the image as an AwtImage and then cut off the bottom half
        BufferedImage bufImage = getImage().getAwtImage();
        bufImage = bufImage.getSubimage(0, 0, getImage().getWidth(), getImage().getHeight() / 2);

        GreenfootImage gImage = new GreenfootImage(bufImage.getWidth(), bufImage.getHeight());
        BufferedImage gBufImg = gImage.getAwtImage();
        Graphics2D graphics = (Graphics2D)gBufImg.getGraphics();
        graphics.drawImage(bufImage, null, 0, 0);

        setImage(gImage);
    }

    /**
     * Act method getting called every frame, checks using {@link #started} for the first frame and corrects the
     * positioning of the object if true
     *
     * @see #started
     */
    public void act() {
        if (!started) {
            started = true;
            setLocation(getX(), getY() - Options.blockSize / 4);
        }
    }
}
