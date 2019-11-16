package nl.rocmondriaan.greenfoot.game;

class Globals
{
    //Simply a class used to store values that should be accessible across the entire game
    //Holds some basic values, should eventually be moved but eh :shrug:
    //To match png -1

    //Keep track of some world related variables
    static int worldWidth;
    static int worldHeight;
    static int levelsUnlocked = 15;
    static int currentLevel = 1;

    //Keep track of the coins and score
    static int levelCoinsCollected;
    static int totalCoinsCollected = 69;
    static int levelScore;
    static int totalScore = 420;

    //Used in the levels and levelSelector class to know what ID is what kind of block with the check method there.
    static Integer[] platforms = {66,67,68,69,84,85,86,87,102,103,104,105,120,121,122,123,138,139,140,141,156,157,158,159,208,209};
    static Integer[] slopeLefts = {};
    static Integer[] slopeRights = {};
    static Integer[] lavas = {};
    static Integer[] waters = {};
    static Integer[] spikes = {};
    static Integer[] checkpoints = {168,169,170,171,172,173,174,175,176,177,178,179};
    static Integer[] finishFlag = {270, 271,272};
    static Integer[] nonSolids = {215,216,269,270,260,217,218,238,239,240,241};
    static Integer[] animatedObjects = {252,253,254};
    static Integer[] ladder = {220,221};
    static Integer[] lever = {225,226,227};
    static Integer[] coins = {165,166,167};
    static Integer[] gems = {180,181,182,183};
    static Integer[] keys = {184,185,186,187};
    static Integer[] star = {188};
    static Integer[] bombs = {189,190};
    static Integer[] door = {213,214,215,216};
    static Integer[] jumpPad = {242,243};
    static Integer[] lockedBlocks = {228,229,230,231};
    static Integer[] pswitch = {273,274};
    static Integer[] breakableBlocks = {192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,207};

}


