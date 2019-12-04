package game.world.intro;

import game.Options;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class Intro extends World {

    /**
     * Simple constructor that sets the background when this world is loaded and also adds the background
     * then proceeds to use renderMenu() at the end to render the actual menu buttons
     */
    public Intro() {
        super(Options.screenWidth, Options.screenHeight, 1, false); //render the screen with said screensize
        setBackground(new GreenfootImage("introblack.jpg"));
        addObject(new IntroImage("unrealengine.png", Options.blockSize * 6, (int) (Options.blockSize * 6)), Options.blockSize * 6, Options.blockSize * 4);
        addObject(new IntroImage("pamaLogo.png", Options.blockSize * 7, Options.blockSize * 7), Options.screenWidth - Options.blockSize * 6, Options.blockSize * 4);
        addObject(new IntroImage("introtext.png", Options.blockSize * 16, Options.blockSize * 3), Options.screenWidth /2, Options.screenHeight - Options.blockSize * 2);
    }
    public Intro(boolean titlescreen) {
        super(Options.screenWidth, Options.screenHeight, 1, false); //render the screen with said screensize
        addObject(new TitleScreen(), Options.screenWidth/2, Options.screenHeight/2);
        showText("Press any key to continue", Options.screenWidth/2, Options.screenHeight/10*9);
    }
}
