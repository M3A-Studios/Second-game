package nl.rocmondriaan.greenfoot.game;

import greenfoot.*;

import javax.swing.text.html.Option;

public class Menu extends World {
    private int frame = 0;

    public Menu() {
        super(Options.screenWidth, Options.screenHeight, 1, false); //render the screen with said screensize
        GreenfootImage background = new GreenfootImage("menuBackground.png");
        background.scale(Options.screenWidth, Options.screenHeight);
        setBackground(background);

        addObject(new MenuPlayer(), Options.blockSize * -1, (int) (Options.blockSize * 5.95));
        renderMenu();
    }
    public void act() {
        frame ++;
        if (frame > 600) {
            frame = 0;
        }
    }
    public void renderMenu() {
        MenuNewGame startButton = new MenuNewGame();
        addObject(startButton, Options.blockSize * 10, (int) (Options.blockSize * 1.5));
        MenuContinue continueButton = new MenuContinue();
        addObject(continueButton, Options.blockSize * 10, (int) (Options.blockSize * 3.5));
        MenuExit exitButton = new MenuExit();
        addObject(exitButton, Options.blockSize * 10, (int) (Options.blockSize * 10.5));
    }

}
