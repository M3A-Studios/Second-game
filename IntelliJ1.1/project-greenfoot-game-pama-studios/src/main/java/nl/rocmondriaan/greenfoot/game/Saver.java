package nl.rocmondriaan.greenfoot.game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Saver {

    static void createSave() {
        BufferedWriter file = null;
        try {
            file = new BufferedWriter(new FileWriter("save.txt"));
            file.write("");
            file.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                assert file != null;
                file.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    static void saveGame() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("save.txt"));

            bw.write("Player1Color:" + Options.player1Color
                    + "\nPlayer2Color:" + Options.player2Color
                    + "\nScreenHeight:" + Options.screenHeight
                    //buttons
                    + "\nPlayer1LeftButton:" + Options.player1Left
                    + "\nPlayer1UpButton:" + Options.player1Up
                    + "\nPlayer1DownButton:" + Options.player1Down
                    + "\nPlayer1RightButton:" + Options.player1Right
                    + "\nPlayer2LeftButton:" + Options.player2Left
                    + "\nPlayer2UpButton:" + Options.player2Up
                    + "\nPlayer2DownButton:" + Options.player2Down
                    + "\nPlayer2RightButton:" + Options.player2Right
                    + "\nInteractButton:" + Options.interact
                    + "\nDropItemButton:" + Options.dropItem
                    + "\nDropObjectButton:" + Options.dropObject
                    //levels & score info
                    + "\nLevelsUnlocked:" + Globals.levelsUnlocked
                    + "\nSelectedLevel:" + LevelSelector.getSelectedLevel()
                    + "\nCoins:" + Globals.totalCoinsCollected
                    + "\nScore:" + Globals.totalScore
                    + "\n");
            bw.newLine();

            bw.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    static void loadGame() {
        File readFile = new File("save.txt"); //set what file to read
        if (!readFile.exists()) {
            createSave();
        }
        Scanner dataReader = null; //scanner for the file
        try {
            dataReader = new Scanner(readFile); //try to read the file
        } catch (IOException e) //error report
        {
            System.out.println("File Read error" + e); //show error
        }
        assert dataReader != null;
        while (dataReader.hasNext()) //while there's a next line
        {
            String line = dataReader.next(); //line is next line
            if (line.contains("Player1Color:")) {
                line = line.replaceAll("Player1Color:","");
                Options.player1Color = line;
            }
            else if (line.contains("Player2Color:")) {
                line = line.replaceAll("Player2Color:","");
                Options.player2Color = line;
            }
            else if (line.contains("ScreenHeight:")) {
                line = line.replaceAll("ScreenHeight:","");
                Options.screenHeight = Integer.parseInt(line);
                Options.screenWidth = Options.screenHeight / 9 * 16;
                Options.blockSize = Options.screenWidth / 20;
            }
            else if (line.contains("Player1LeftButton:")) {
                line = line.replaceAll("Player1LeftButton:","");
                Options.player1Left = line;
            }
            else if (line.contains("Player1UpButton:")) {
                line = line.replaceAll("Player1UpButton:","");
                Options.player1Up = line;
            }
            else if (line.contains("Player1DownButton:")) {
                line = line.replaceAll("Player1DownButton:","");
                Options.player1Down = line;
            }
            else if (line.contains("Player1RightButton:")) {
                line = line.replaceAll("Player1RightButton:","");
                Options.player1Right = line;
            }
            else if (line.contains("Player2LeftButton:")) {
                line = line.replaceAll("Player2LeftButton:","");
                Options.player2Left = line;
            }
            else if (line.contains("Player2UpButton:")) {
                line = line.replaceAll("Player2UpButton:","");
                Options.player2Up = line;
            }
            else if (line.contains("Player2DownButton:")) {
                line = line.replaceAll("Player2DownButton:","");
                Options.player2Down = line;
            }
            else if (line.contains("Player2RightButton:")) {
                line = line.replaceAll("Player2RightButton:","");
                Options.player2Right = line;
            }
            else if (line.contains("InteractButton:")) {
                line = line.replaceAll("InteractButton:", "");
                Options.interact = line;
            }
            else if (line.contains("DropItemButton:")) {
                line = line.replaceAll("DropItemButton:", "");
                Options.dropItem = line;
            }
            else if (line.contains("DropObjectButton:")) {
                line = line.replaceAll("DropObjectButton:", "");
                Options.dropObject= line;
            }
            else if (line.contains("LevelsUnlocked:")) {
                line = line.replaceAll("LevelsUnlocked:","");
                Globals.levelsUnlocked = Integer.parseInt(line);
            }
            else if (line.contains("SelectedLevel:")) {
                line = line.replaceAll("SelectedLevel:","");
                LevelSelector.setSelectedLevel(Integer.parseInt(line));
            }
            else if (line.contains("Coins:")) {
                line = line.replaceAll("Coins:","");
                Globals.totalCoinsCollected = Integer.parseInt(line);
            }
            else if (line.contains("Score:")) {
                line = line.replaceAll("Score:","");
                Globals.totalScore = Integer.parseInt(line);
            }
            else {
                System.out.println("Couldnt find the purpose of: " + line);
            }
        }
        dataReader.close();
    }
}
