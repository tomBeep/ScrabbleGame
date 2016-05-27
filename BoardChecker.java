
/**
 * Should check the board in both x and y directions, calculate all the word scores of that turn and then add it to the total score
 * @author Thomas Edwards 
 * 5/05/2016
 */

import ecs100.*;
import java.awt.Color;
import java.util.ArrayList;
public class BoardChecker
{
    // instance variables - replace the example below with your own
    public static double [][] boardLetterScores = new double [15] [15];
    public static int [][] boardTileNumbers = new int [15][15]; //which tile has been moved into which things
    public static String [][] boardLetter = new String [15][15];
    public double score = 0;
    String writeScore="0";
    private int down = 0; //moves down each turn
    public boolean valid = true;

    /**
     * Constructor
     */
    public BoardChecker()
    {
        UI.setFontSize(20);
        UI.drawString("Player 1",680,200);
        UI.setFontSize(12);
        UI.drawRect(665,170,120,550);
        //UI.drawRect(785,170,120,550); //the rect for two players
        UI.drawLine(665,210,785,210);
    }

    /**
     * This method is given a letter and will return its letter score
     */
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

    /**
     * Calls the checkX and checkY methods to check the scores in both directions
     * then draws the new score 
     */
    public void updateScore (){
        double x =checkX();
        double y =checkY();
        score = score+y+x;
        writeScore=String.valueOf(score);
        UI.setFontSize(20);
        UI.drawString(writeScore,690,240+down);
        UI.setFontSize(12);
        down=down+25;
    }

    /**
     * Goes horizontally across the board, checking for new words and scores
     * i is my y value (row), j is my x values (column)
     */
    public double checkX (){
        double word = 0;
        double scoreAdd=0;
        int letterCount=0;//counts the number of letters in the word
        boolean flag = false;//atm this will flag the word if it contains a new letter added that turn
        boolean flag2 = false;//checks that the word is at least 2 letters long
        boolean TW = false;
        boolean DW = false;
        boolean TW2 = false;//a second variable in case there are two triple/double words gone over by the same word
        boolean DW2 = false;
        for (int i=0;i<15;i++){
            for (int j=0; j<15;j++){
                int num =  boardTileNumbers[i][j];
                if (num==10){
                    double add = this.letterScore(boardLetter[i][j]);
                    letterCount++;
                    word=word+add;
                }
                if(1<=num && 7>=num){
                    flag=true;
                    double add = this.letterScore(boardLetter[i][j]);
                    if (j==0 && i==0 || j==7 && i==0 || j==15 && i==0 || j==0 && i==7 || j==14 && i==7 || j==0 && i==14 || j==7 && i==14 || j==14 && i==14){
                        if(TW){TW2=true;}
                        TW = true;
                    }
                    else if (j==1 && i==1 || j==2 && i==2 || j==3 && i==3 || j==4 && i==4 || j==13 && i==1 || j==12 && i==2 || j==11 && i==3 || j==10 && i==4 || j==7 && i==7
                    || j==1 && i==13 || j==2 && i==12 || j==3 && i==11 || j==4 && i==10 || j==13 && i==13 || j==12 && i==12 || j==11 && i==11 || j==10 && i==10){
                        if(DW){DW2=true;}
                        DW = true;
                    }
                    else if (j==3 && i==0 || j==11 && i==0 || j==6 && i==2 || j==8 && i==2 || j==0 && i==3 || j==7 && i==3 || j==14 && i==3 || j==2 && i==6
                    || j==6 && i==6 || j==8 && i==6 || j==12 && i==6 || j==3 && i==7 || j==11 && i==7 || j==2 && i==8 || j==6 && i==8 || j==8 && i==8
                    || j==12 && i==8 || j==0 && i==11 || j==7 && i==11 || j==14 && i==11 || j==6 && i==12 || j==8 && i==12 || j==3 && i==14 || j==11 && i==14){
                        add=add*2;
                    }
                    else if (j==5 && i==1 || j==9 && i==1 || j==1 && i==5 || j==5 && i==5 || j==9 && i==5 || j==13 && i==5 || j==1 && i==9 || j==5 && i==9
                    || j==9 && i==9 || j==13 && i==9 || j==5 && i==13 || j==9 && i==13){
                        add=add*3;
                    }
                    letterCount++;
                    word=word+add;
                }
                if(num==0){
                    if(DW){word=word*2;}
                    if(TW){word=word*3;}
                    if(flag && letterCount!=1){scoreAdd=scoreAdd+word;}
                    word=0;
                    DW=false;
                    TW=false;
                    flag = false;
                    letterCount=0;
                }
            }
            if(DW){word=word*2;}
            if(TW){word=word*3;}
            if(flag && letterCount!=1){scoreAdd=scoreAdd+word;}
            word=0;
            DW=false;
            TW=false;
            flag = false;
            letterCount=0;
        }
        return scoreAdd;
    }

    /**
     * Goes vertically across the board, checking for new words and scores
     * adds up all the words and returns the total value of all added words
     */
    public double checkY (){
        double word = 0;
        double scoreAdd=0;
        int letterCount=0;//counts the number of letters in the word
        boolean flag = false;//atm this will flag the word if it contains a new letter added that turn
        boolean flag2 = false;//checks that the word is at least 2 letters long
        boolean TW = false;
        boolean DW = false;
        boolean TW2 =false;
        boolean DW2 = false;
        for (int j=0;j<15;j++){
            for (int i=0; i<15;i++){
                int num =  boardTileNumbers[i][j];
                if (num==10){
                    double add = this.letterScore(boardLetter[i][j]);
                    letterCount++;
                    word=word+add;
                }
                if(1<=num && 7>=num){
                    flag=true;
                    double add = this.letterScore(boardLetter[i][j]);
                    if (j==0 && i==0 || j==7 && i==0 || j==15 && i==0 || j==0 && i==7 || j==14 && i==7 || j==0 && i==14 || j==7 && i==14 || j==14 && i==14){
                        if(TW){TW2=true;}
                        TW = true;
                    }
                    else if (j==1 && i==1 || j==2 && i==2 || j==3 && i==3 || j==4 && i==4 || j==13 && i==1 || j==12 && i==2 || j==11 && i==3 || j==10 && i==4 || j==7 && i==7
                    || j==1 && i==13 || j==2 && i==12 || j==3 && i==11 || j==4 && i==10 || j==13 && i==13 || j==12 && i==12 || j==11 && i==11 || j==10 && i==10){
                        if(DW){DW2=true;}
                        DW = true;//need to add the centr tile (8,8) i think?
                    }
                    else if (j==3 && i==0 || j==11 && i==0 || j==6 && i==2 || j==8 && i==2 || j==0 && i==3 || j==7 && i==3 || j==14 && i==3 || j==2 && i==6
                    || j==6 && i==6 || j==8 && i==6 || j==12 && i==6 || j==3 && i==7 || j==11 && i==7 || j==2 && i==8 || j==6 && i==8 || j==8 && i==8
                    || j==12 && i==8 || j==0 && i==11 || j==7 && i==11 || j==14 && i==11 || j==6 && i==12 || j==8 && i==12 || j==3 && i==14 || j==11 && i==14){
                        add=add*2;
                    }
                    else if (j==5 && i==1 || j==9 && i==1 || j==1 && i==5 || j==5 && i==5 || j==9 && i==5 || j==13 && i==5 || j==1 && i==9 || j==5 && i==9
                    || j==9 && i==9 || j==13 && i==9 || j==5 && i==13 || j==9 && i==13){
                        add=add*3;
                    }
                    letterCount++;
                    word=word+add;
                }
                if(num==0){
                    if(DW){word=word*2;}
                    if(TW){word=word*3;}
                    if(DW2){word=word*2;}
                    if(TW2){word=word*3;}
                    if(flag && letterCount!=1){scoreAdd=scoreAdd+word;}
                    word=0;
                    DW=false;
                    TW=false;
                    flag = false;
                    letterCount=0;
                }
            }
            if(DW){word=word*2;}
            if(TW){word=word*3;}
            if(flag && letterCount!=1){scoreAdd=scoreAdd+word;}
            word=0;
            DW=false;
            TW=false;
            flag = false;
            letterCount=0;
        }
        return scoreAdd;
    }

    /**
     * This method checks that the starting tile is not empty
     */
    public static boolean checkStart (){
        if (boardTileNumbers[7][7]!=0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * This method checks whether one of the tiles you placed that turn is touching an already placed tile, or is the middle tile
     */
    public static boolean checkValidity (){
        for (int i=0;i<15;i++){
            for (int j=0; j<15;j++){
                int num =  boardTileNumbers[i][j];
                if(1<=num && 7>=num){
                    if(boardTileNumbers[7][7]!=0 && boardTileNumbers[7][7]!=10){
                        return true;
                    }
                    if(j!=14){
                        if(boardTileNumbers[i][j+1]==10){
                            return true;
                        }
                    }
                    if(j!=0){
                        if(boardTileNumbers[i][j-1]==10){
                            return true;
                        }
                    }
                }
            }
        }
        for (int j=0;j<15;j++){
            for (int i=0; i<15;i++){
                int num =  boardTileNumbers[i][j];
                if(1<=num && 7>=num){
                    if(boardTileNumbers[7][7]!=0 && boardTileNumbers[7][7]!=10){
                        return true;
                    }
                    if(i!=14){
                        if(boardTileNumbers[i+1][j]==10){
                            return true;
                        }
                    }
                    if(i!=0){
                        if(boardTileNumbers[i-1][j]==10){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method should check that all of the tiles placed were in one column or row and that there are no empty gaps between tiles placed
     */
    public static boolean checkValidity2 (){
        boolean flagged = false;
        boolean over = false;
        boolean xfine = true;
        boolean nogaps =false;
        for (int i=0;i<15;i++){
            for (int j=0; j<15;j++){
                int num =  boardTileNumbers[i][j];
                if(1<=num && 7>=num){
                    flagged = true;
                    nogaps =true;
                }
                if(num==0 && nogaps== true){
                    over=true;
                }
                if(1<=num && 7>=num && over){
                    xfine = false;
                }
            }
            if(flagged){
                over=true;
            }
        }
        flagged = false;
        over = false;
        nogaps=false;
        boolean yfine = true;
        for (int j=0;j<15;j++){
            for (int i=0; i<15;i++){
                int num =  boardTileNumbers[i][j];
                if(1<=num && 7>=num){
                    flagged = true;
                    nogaps = true;
                }
                if(num==0 && nogaps== true){
                    over=true;
                }
                if(1<=num && 7>=num && over){
                    yfine = false;
                }
            }
            if(flagged){
                over=true;
            }
        }
        if(yfine || xfine){return true;}
        else {return false;}
    }	
}