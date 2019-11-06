import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Heart extends Actor
{
    GreenfootImage image;
    private int heart;
    private boolean started = false;
    private int startX;
    private int startY;
    private GreenfootImage half = new GreenfootImage("heartHalf.png");
    private GreenfootImage full = new GreenfootImage("heartFull.png");
    private GreenfootImage empty = new GreenfootImage("heartEmpty.png");
    Heart(int heart)
    {
        this.heart = heart;
        half.scale((Options.blockSize),(Options.blockSize));
        full.scale((Options.blockSize),(Options.blockSize));
        empty.scale((Options.blockSize),(Options.blockSize));
        renderHeart();
        
    }
    public void renderHeart() {
        String state;
        if (Player.health == (heart * 2 - 1)) state = "half";
        else if (Player.health >= heart * 2) state = "full";
        else state = "empty";
        if (state == "half") {
            setImage(half);
        } else if (state == "full") {
            setImage(full);
        } else {
            setImage(empty);
        }
    }      
    public void act() {
        if (!started) {
            startX = getX();
            startY = getY();
            started = true;
        } else { setLocation(startX, startY);}
        renderHeart();
    }
}
