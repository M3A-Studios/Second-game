package nl.rocmondriaan.greenfoot.game.blocks.normal;

import greenfoot.GreenfootImage;
import nl.rocmondriaan.greenfoot.game.Options;
import nl.rocmondriaan.greenfoot.game.blocks.Blocks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Platform extends Blocks {

    private boolean started = false;
    private int id;

    public Platform(int ID) {
        super(ID);
        this.id = ID;
        //get the image as an AwtImage and then cut off the bottom half
        BufferedImage bufImage = getImage().getAwtImage();
        bufImage = bufImage.getSubimage(0, 0, getImage().getWidth(), getImage().getHeight() / 2);

        GreenfootImage gImage = new GreenfootImage(bufImage.getWidth(), bufImage.getHeight());
        BufferedImage gBufImg = gImage.getAwtImage();
        Graphics2D graphics = (Graphics2D)gBufImg.getGraphics();
        graphics.drawImage(bufImage, null, 0, 0);

        setImage(gImage);

    }
    public void act() {
        if (!started) {
            started = true;
            setLocation(getX(), getY() - Options.blockSize / 4);
        }
    }
}
