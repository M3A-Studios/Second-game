package nl.rocmondriaan.greenfoot.game;

import greenfoot.Actor;

public class GameLogic extends Actor {

    public static int lockFrame = 0;

    public void act() {
        lockFrame ++;
    }
}
