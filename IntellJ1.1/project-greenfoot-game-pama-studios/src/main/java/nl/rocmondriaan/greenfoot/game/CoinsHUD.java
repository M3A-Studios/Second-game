package nl.rocmondriaan.greenfoot.game;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import java.util.*;

public class CoinsHUD extends Actor
{
    private boolean started = false; //gets checked first frame to get the startX and startY
    private int startX;
    private int startY;
    private GreenfootImage coinImg = new GreenfootImage("hudCoin.png"); //full image
    private int coinHUDSpot;
    private int[] coins = {0,0,0};
    private String imageID;
    private int coinid;

    private void coinImg() {
        coinImg.scale((Options.blockSize),(Options.blockSize));
        setImage(coinImg);
    }
    private void coinFix(){
        imageID = "hud" + coinid + ".png";
        coinid = coins[0];
    }

    CoinsHUD(int coinHUDSpot) {
        this.coinHUDSpot = coinHUDSpot;
    }

    private void updateCoins(){
        coinFix();
        if (coinHUDSpot == 0){
            if(coins[0] >= 0 && coins[0] < 10){
                coins[0] = Globals.levelCoinsCollected;
                renderCoin();
            }
            if(coins[0] > 10 && coins[0] < 20){
            }
        }
        if (coinHUDSpot == 1) {
            renderCoin();
        }
        if (coinHUDSpot == 2) {
            renderCoin();
        }
        if (coinHUDSpot == 3){
            coinImg();
        }
    }
    private void renderCoin(){
        GreenfootImage numberHUD = new GreenfootImage(imageID); //full image
        numberHUD.scale((Options.blockSize),(Options.blockSize));
        setImage(numberHUD);
    }

    public void act() {
        if (!started) {
            startX = getX();
            startY = getY();
            started = true;
        } else {
            setLocation(startX, startY);
            updateCoins();
        }
    }
}

