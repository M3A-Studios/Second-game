package game;

import greenfoot.Actor;

/**
 * One version of GameLogic is always present in Levels but not visible, done to have a global act method that only
 * goes off 1 time per frame
 */
public class GameLogic extends Actor {

    /** Animation for the {@link game.blocks.special.LockedBlocks} to unlock their next set */
    public static int lockFrame = 0;

    /** Act method to handle everything that must be done at least once and at most once per frame */
    public void act() {
        lockFrame ++; //Updates lockFrame for LockedBlocks.java
    }
}
