package nl.rocmondriaan.greenfoot.game;

public class Globals
{
    //Simply a class used to store values that should be accessible across the entire game
    //Holds some basic values, should eventually be moved but eh :shrug:

    //Keep track of some world related variables
    public static int worldWidth;
    public static int worldHeight;
    public static int levelsUnlocked;
    public static int currentLevel;

    //Keep track of the coins and score
    public static int levelCoinsCollected;
    public static int totalCoinsCollected;

    //Used in the levels and levelSelector class to know what ID is what kind of block with the check method there.
    public static Integer[] platforms = {};
    public static Integer[] slopeLefts = {};
    public static Integer[] slopeRights = {};
    public static Integer[] lavas = {};
    public static Integer[] waters = {};
    public static Integer[] spikes = {};
    public static Integer[] finishFlag = {};
    public static Integer[] nonSolids = {215,216,269,260,217,218};
    public static Integer[] animatedObjects = {252,253,254};
    public static Integer[] ladder = {220,221};
    public static Integer[] lever = {225,226,227};
    public static Integer[] coins = {165,166,167};
    public static Integer[] gems = {180,181,182,183};
    public static Integer[] keys = {184,185,186,187};
    public static Integer[] star = {188};
    public static Integer[] bombs = {189,190};
    public static Integer[] door = {213,214,215,216};

}


