package nl.rocmondriaan.greenfoot.game.world.menu;

import greenfoot.GreenfootImage;
import greenfoot.World;
import nl.rocmondriaan.greenfoot.game.Options;
import nl.rocmondriaan.greenfoot.game.world.menu.PlayerSelectorPlayer;

public class PlayerSelectorScreen extends World {
    public PlayerSelectorScreen() {
        super(Options.screenWidth, Options.screenHeight, 1, false); //render the screen with said screensize
        GreenfootImage background = new GreenfootImage("black.jpg");
        background.scale(Options.screenWidth, Options.screenHeight);
        setBackground(background);

        renderMenu();
    }
    private void renderMenu() {
        showText("Choose your player character", Options.blockSize * 10, Options.blockSize * 2);
        addObject(new PlayerSelectorPlayer("Green"), Options.blockSize * 4, Options.blockSize * 5);
        addObject(new PlayerSelectorPlayer("Blue"), Options.blockSize * 8, Options.blockSize * 5);
        addObject(new PlayerSelectorPlayer("Pink"), Options.blockSize * 12, Options.blockSize * 5);
        addObject(new PlayerSelectorPlayer("Yellow"), Options.blockSize * 16, Options.blockSize * 5);
    }
}
