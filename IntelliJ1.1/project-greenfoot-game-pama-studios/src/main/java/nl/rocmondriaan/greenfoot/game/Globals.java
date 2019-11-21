package nl.rocmondriaan.greenfoot.game;

class Globals
{
    //Simply a class used to store values that should be accessible across the entire game
    //Holds some basic values, should eventually be moved but eh :shrug:
    //To match png -1

    //Encryption key for save file
    static String key = "dAtAbAsE98765432"; // 128 bit key

    //Keep track of some world related variables
    static int worldWidth;
    static int worldHeight;
    static int levelsUnlocked = 2;

    //Keep track of the coins and score
    static int levelCoinsCollected;
    static int totalCoinsCollected;
    static int levelScore;
    static int totalScore;

    //Used in the levels and levelSelector class to know what ID is what kind of block with the check method there.
    static Integer[] platforms = {66,67,68,69,84,85,86,87,102,103,104,105,120,121,122,123,138,139,140,141,156,157,158,159,208,209};
    static Integer[] slopeLefts = {70,88,106,124,142,160};
    static Integer[] slopeRights = {71,89,107,125,143,161};
    static Integer[] lavas = {222,223,224};
    static Integer[] waters = {};
    static Integer[] spikes = {261,262,263,264};
    static Integer[] finishFlag = {270, 271,272};
    static Integer[] nonSolids = {210,211,212,215,216,217,218,219,232,233,234,235,236,237,238,239,240,269,270,260,265,266,267,268};
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
    static Integer[] lockedBlocks = {228,229,230,231,275,276,277,278};
    static Integer[] breakableBlocks = {192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,207};
    static Integer[] slime = {36,37,38,39,40,41,42,43,44,45,46,47};
    static Integer[] pswitch = {273,274,194}; //273 and 274 is the power switch, the rest of je IDs change when the switch is turned on.
}


