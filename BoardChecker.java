
/**
 * Write a description of class BoardChecker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import ecs100.*;
import java.awt.Color;
import java.util.ArrayList;
public class BoardChecker
{
    // instance variables - replace the example below with your own
    public static double [][] boardLetterScores = new double [15] [15];
    public static int [][] boardTileNumbers = new int [15][15];
    public static String [][] boardLetter = new String [15][15];
    private double score = 0;

    /**
     * Constructor for objects of class BoardChecker
     */
    public BoardChecker()
    {
        
    }

    public static double letterScore (String letter){
        if (letter.equals("A") || letter.equals("E") || letter.equals("I") || letter.equals("O") || letter.equals("U")
        || letter.equals("L") || letter.equals("N") || letter.equals("S") || letter.equals("T") || letter.equals("R")){
            return 1;
        }
        else if (letter.equals("D") || letter.equals("G")){
            return 2;
        }
        else if (letter.equals("B") || letter.equals("C") || letter.equals("M") || letter.equals("P")){
            return 3;
        }
        else if (letter.equals("F") || letter.equals("H") || letter.equals("V") || letter.equals("W") || letter.equals("Y")){
            return 4;
        }
        else if (letter.equals("K")){
            return 5;
        }
        else if (letter.equals("J") || letter.equals("X")){
            return 8;
        }
        else if (letter.equals("blank")){
            return 0;
        }
        else {
            return 10;
        }
    }
}
