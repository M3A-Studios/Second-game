package nl.rocmondriaan.greenfoot.game;

import greenfoot.*;

public class GreenfootSpeed
{
    private static int i_s_Speed=50;

    public static void set(int i_newSpeed)
    {
        Greenfoot.setSpeed(i_newSpeed);
        i_s_Speed=i_newSpeed;
    }

    public static int get()
    {
        return i_s_Speed;
    }
}