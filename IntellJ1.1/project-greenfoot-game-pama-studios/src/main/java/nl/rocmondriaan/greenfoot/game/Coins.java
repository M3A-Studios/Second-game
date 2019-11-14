package nl.rocmondriaan.greenfoot.game;

import greenfoot.GreenfootImage;

import java.util.Random;


public class Coins extends Blocks {
    int CoinID;
    Coins(int ID){
        super(ID);
        CoinID = ID;
    }

    public void act() {
        if (isTouching(Player.class)){
            addCoin();
        }
    }
    public void addCoin(){
        if (CoinID == 166){
            Globals.levelCoinsCollected -= -1;
            System.out.println("Collected 1 coin, current coins now: " + Globals.levelCoinsCollected); //debug
            getWorld().removeObject(this);
        }
        else if (CoinID == 168) {
            Globals.levelCoinsCollected -= -10;
            System.out.println("Collected 10 coin, current coins now: " + Globals.levelCoinsCollected); //debug
            getWorld().removeObject(this);
        }
        else if (CoinID == 167){
            Globals.levelCoinsCollected -= -20;
            System.out.println("Collected 20 coins, current coins now: " + Globals.levelCoinsCollected); //debug
            getWorld().removeObject(this);
        }
        else{}
    }
}
