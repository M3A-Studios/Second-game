import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.*;

/**
 * Shit animates shit man
 * 
 * @author Michael
 * @version 1.0
 */
public class AnimatedObject extends Blocks
{
    private int atime = 0; //Animation time
    int selectImage;
    private GreenfootImage Torch1 = new GreenfootImage("260.png");
    private GreenfootImage Torch2 = new GreenfootImage("261.png");
    private GreenfootImage Torch3 = new GreenfootImage("262.png");

    
    public void act(){
        System.out.println(atime);
        changeImage();
        Torch1.scale((Options.blockSize),(Options.blockSize));
        Torch2.scale((Options.blockSize),(Options.blockSize));
        Torch3.scale((Options.blockSize),(Options.blockSize));
    }
    AnimatedObject(int ID){
        super(ID);
        selectImage = ID;
    }
    public void changeImage(){
        if (selectImage == 253){
            animateTorch();
        }
        else if (selectImage == 253){

        }
    }

    /*public void changeImage(){ //Object selection werkt nog nieeeee
        switch (selectImage)
        {
            case 1: selectImage = 252;
                    animateTorch();
                    break;
            case 2: selectImage = 226;
                    animateLever();
                    break;
            case 3: selectImage = 3;
                    break;
            case 4: selectImage = 4;
                    break;
            case 5: selectImage = 5;
                    break;
            default: selectImage = 2;
                    break;

        }
    }*/
    public void animateTorch(){ //Fix this shit --reminder to self (Blocken moet automatisch scalen als ingeladen maar is temp shit jaaaa)
        atime=atime+1; //Counter
        if (atime==30) {atime=0;} //Reset
        if (atime==0) {setImage(Torch1);}
        if (atime==15) {setImage(Torch2);}
    }
}


