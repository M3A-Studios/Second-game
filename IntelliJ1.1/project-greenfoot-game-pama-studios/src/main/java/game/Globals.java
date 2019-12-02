package game;

/**
 * Globals is a class containing a lot of options that are game sided, These options should only be changed by the
 * developers and are never accesible to the player. Also holds some key information that multiple classes need
 * access to like worldWidth, Height, LevelsUnlocked, Coins, Score etc.
 */
public class Globals
{
    //Simply a class used to store values that should be accessible across the entire game
    //Holds some basic values, should eventually be moved but eh :shrug:
    //To match png -1

    //Keep track of some world related variables
    public static int worldWidth;
    public static int worldHeight;
    public static int levelsUnlocked = 15;
    public static int currentLevel = 5;

    //Keep track of the coins, stars and score
    public static int levelCoinsCollected;
    public static int totalCoinsCollected;
    public static int levelStarsCollected;
    public static int totalStarsCollected;
    public static int levelScore;
    public static int totalScore;

    //Used in the levels and levelSelector class to know what ID is what kind of block with the check method there.
    public static Integer[] platforms = {66,67,68,69,84,85,86,87,102,103,104,105,120,121,122,123,138,139,140,141,156,
            157,158,159,208,209};
    public static Integer[] slopeLefts = {70,88,106,124,142,160};
    public static Integer[] slopeRights = {71,89,107,125,143,161};
    public static Integer[] lavas = {222,223,224};
    public static Integer[] waters = {};
    public static Integer[] spikes = {261,262,263,264,315,316,317,318,319,320,321};
    public static Integer[] finishFlag = {270, 271,272};
    public static Integer[] nonSolids = {210,211,212,215,216,217,218,219,232,233,234,235,236,237,238,239,240,269,270,
            260,265,266,267,268,290,291,345,346,347,348,349,354,355,356,357,306,307,308,309,310,311,312,313,314,322,323,
            324,325,326,327,328,329,330,339,340,341,345,346,347,348,349};
    public static Integer[] animatedObjects = {252,253,254};
    public static Integer[] ladder = {220,221};
    public static Integer[] lever = {225,226,227};
    public static Integer[] coins = {165,166,167};
    public static Integer[] gems = {180,181,182,183};
    public static Integer[] keys = {184,185,186,187};
    public static Integer[] star = {188};
    public static Integer[] bombs = {189,190};
    public static Integer[] door = {213,214,215,216};
    public static Integer[] jumpPad = {242,243};
    public static Integer[] lockedBlocks = {228,229,230,231,275,276,277,278};
    public static Integer[] breakableBlocks = {192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,207,341,342,
            343,344};
    public static Integer[] slime = {36,37,38,39,40,41,42,43,44,45,46,47};
    public static Integer[] bee = {3,4,5};
    public static Integer[] sawBlade = {31,32};
    public static Integer[] pswitch = {273,274,194}; //273 and 274 is the power switch, the rest of je IDs change when the switch is turned on.
    public static Integer[] switchBlock = {281,282,283,284};
    public static Integer[] hiddenBlocks = {352,353};
}

