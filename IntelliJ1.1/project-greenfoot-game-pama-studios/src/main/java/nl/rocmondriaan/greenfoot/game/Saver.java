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
            if (line.contains("LevelsUnlocked:")) {
                line = line.replaceAll("LevelsUnlocked:","");
                Globals.levelsUnlocked = Integer.parseInt(line);
            }
            if (line.contains("SelectedLevel:")) {
                line = line.replaceAll("SelectedLevel:","");
                LevelSelector.setSelectedLevel(Integer.parseInt(line));
            }
            if (line.contains("Coins:")) {
                line = line.replaceAll("Coins:","");
                Globals.totalCoinsCollected = Integer.parseInt(line);
            }
            if (line.contains("Score:")) {
                line = line.replaceAll("Score:","");
                Globals.totalScore = Integer.parseInt(line);
            }
        }
        dataReader.close();
    }
}
