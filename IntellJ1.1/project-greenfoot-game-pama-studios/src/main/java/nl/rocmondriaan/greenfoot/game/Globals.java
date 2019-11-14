package nl.rocmondriaan.greenfoot.game;

class Globals
{
    //Simply a class used to store values that should be accessible across the entire game
    //Holds some basic values, should eventually be moved but eh :shrug:

    //Keep track of some world related variables
    static int worldWidth;
    static int worldHeight;
    static int levelsUnlocked = 15;
    static int currentLevel;

    //Keep track of the coins and score
    static int levelCoinsCollected;
    static int totalCoinsCollected;

    //Used in the levels and levelSelector class to know what ID is what kind of block with the check method there.
    static Integer[] platforms = {};
    static Integer[] slopeLefts = {};
    static Integer[] slopeRights = {};
    static Integer[] lavas = {};
    static Integer[] waters = {};
    static Integer[] spikes = {};
    static Integer[] checkpoints = {168,169,170,171,172,173,174,175,176,177,178,179};
    static Integer[] finishFlag = {270, 271};
    static Integer[] nonSolids = {215,216,269,260,217,218};
    static Integer[] animatedObjects = {252,253,254};
    static Integer[] ladder = {220,221};
    static Integer[] lever = {225,226,227};
    static Integer[] coins = {165,166,167};
    static Integer[] gems = {180,181,182,183};
    static Integer[] keys = {184,185,186,187};
    static Integer[] star = {188};
    static Integer[] bombs = {189,190};
    static Integer[] door = {213,214,215,216};

}


