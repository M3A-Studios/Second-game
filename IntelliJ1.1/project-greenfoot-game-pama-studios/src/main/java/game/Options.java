package game;

/**
 * Options is a class containing a lot of options that are decided by the player, These options are only the defaults
 * and a player can change them at any time when in the level selector world by opening up the option menu.
 * <p>
 * Holds things like the color of the player, Controls & Screensize
 */
public class Options
{
    public static String player1Color = "Green";
    public static String player2Color = "Blue";

    public static String player1Left = "A";
    public static String player1Up = "W";
    public static String player1Down = "S";
    public static String player1Right = "D";
    public static String player1Jump = "space";

    public static String player2Left = "left";
    public static String player2Up = "up";
    public static String player2Down = "down";
    public static String player2Right = "right";
    public static String player2Jump = "control";

    public static String interact = "E";
    public static String dropItem = "Q";
    public static String dropObject = "Z";
    //Store stuff
    public static boolean doubleJumpUnlocked = false;
    public static boolean speedBoostUnlocked = false;
    public static boolean bonusHeartUnlocked = false;
    public static boolean bonusTorchUseUnlocked = false;
    //screen size
    public static int screenHeight = 720;
    public static int screenWidth = screenHeight / 9 * 16;
    public static int blockSize = screenWidth / 20;

    //Volume
    public static int musicVolume = 50; //40
    public static int soundeffectVolume = 100;
}