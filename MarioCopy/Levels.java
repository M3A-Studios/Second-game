import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
import java.util.*;
public class Levels extends World
{
    private static int levelWidth;
    private static int levelHeight;
    private static String mapString;
    private static int world[];
    public Levels(int level)
    {    
        super(1280, 720, 1); 
        getMap(level);
    }
    private static void getMap(int level) {
        levelWidth = 0;
        levelHeight = 0;
        mapString = "";
        
        //read the level layout
        File readFile = new File("Levels/Level" + level + ".tmx"); //set what file to read
        Scanner dataReader = null; //scanner for the file
        
        try
        {
            dataReader = new Scanner(readFile); //try to read the file
        }
        catch(IOException e) //error report
        {
            System.out.println("File Read error" + e); //show error
        }
        while(dataReader.hasNext()) //while there's a next line
        {
           String line = dataReader.next(); //line is next line
           if (line.contains("width=\"") && !line.contains("tile")) { //check for width
               line = line.replaceAll("[^\\d]",""); //replace all non numbers with nothing
               levelWidth = Integer.parseInt(line); //set levelWidth
           }
           if (line.contains("height=\"") && !line.contains("tile")) { //check for height
               line = line.replaceAll("[^\\d]",""); //replace all non numbers with nothing
               levelHeight = Integer.parseInt(line); //set levelHeight
           }
           if (line.contains(",")) { //check if line is part of the map
                mapString = mapString += line; //add new line to total map
           }
        }
        String[] integerStrings = mapString.split(","); //split values by ,
        world = new int[integerStrings.length]; //make an array with space for every slot 
        for (int i = 0; i < world.length; i++){ 
            world[i] = Integer.parseInt(integerStrings[i]); //turn array into int array
        }
        
        dataReader.close(); //remove the scanner. we don't need it anymore

    }
}
