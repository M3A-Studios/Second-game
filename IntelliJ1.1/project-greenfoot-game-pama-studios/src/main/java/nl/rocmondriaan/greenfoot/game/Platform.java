package nl.rocmondriaan.greenfoot.game;

import greenfoot.GreenfootImage;

import java.awt.*;
import java.awt.image.BufferedImage;

class Platform extends Blocks {

    private boolean started = false;
    private int id;

    Platform(int ID) {
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
