
/**
 * Tile class, there are 7 tile objects made
 * Each tile holds its position, current letter and whether is been drawn on the board or not
 * @author Thomas Edwards
 */

import ecs100.*;
import java.awt.Color;
import java.util.ArrayList;
public class Tile
{
    // tile Num is the tile number that the tile is ie 1'st 2'nd etc.. note tile Num 0 is the first tile

    private int left = 70;
    private int top = 70;
    public boolean reDrawn = false;//whether or not the tile has been redrawn on the board
    private int xPos = ScrabbleBoard.tileSize+10; //space between each tile
    public String currentLetter;//the current letter that this tile is
    private Color myBrown = new Color(244,164,96);
    private int tileNumber;
    private Color myGreen = new Color (0,102,51);
    public int xCoord;
    public int yCoord;

    /**
     * CONSTRUCTOR
     * Takes a new letter from the bag array, sets it as the current letter
     * Calls draw tile method to draw the tile in the default position
     */
    public Tile(int tileNum)
    {
            int num = (int)(Math.random()*ScrabbleGame.tilesLeft);//creates a random int between 0 and tilesLeft
            currentLetter = ScrabbleGame.bag.get(num);//takes a letter from the bag array and sets current letter to it
            ScrabbleGame.bag.remove(currentLetter);//removes the letter taken from bag array
            ScrabbleGame.tilesLeft = ScrabbleGame.tilesLeft-1;//subtracts one from tiles left
            tileNumber = tileNum;
            this.drawTile();//draws tile 
    }

    /**
     * Draws the tile in its tileNumber (default) position
     */
    public void drawTile (){
        String letter = currentLetter;//takes a letter from current letter array and uses it
        UI.setColor(myBrown);
        UI.fillRect(left+xPos*tileNumber, top+ScrabbleBoard.boardSize+10,ScrabbleBoard.tileSize,ScrabbleBoard.tileSize);
        UI.setColor(Color.black);
        UI.setFontSize(20);
        if (!letter.equals("blank")){UI.drawString(letter,left+xPos*tileNumber+12,top+ScrabbleBoard.boardSize+35);}
        double ls = BoardChecker.letterScore(currentLetter);
        int letterscore = (int) ls;
        String letterScore = String.valueOf(letterscore);
        UI.setFontSize(12);
        UI.drawString(letterScore,left+xPos*tileNumber+25,top+ScrabbleBoard.boardSize+45);
        xCoord=0;
        yCoord=0;
    }

    /**
     * Changes the x and y co ordinates so that the tile is drawn in the box which the x and y co ordinates were in
     * Draws the tile in a box which the mouse cursor is in
     * Will not draw if there is already a tile in that box
     */
    public void drawTilePosition( double x, double y){
        x = boxTileX(x);//also sets the x and y coordinates of the tile
        y = boxTileY(y);
        if(!this.boardFillChecker(xCoord,yCoord)){
            String letter = currentLetter;//takes a letter from current letter array and uses it
            UI.setColor(myBrown);
            UI.fillRect(x,y,ScrabbleBoard.tileSize,ScrabbleBoard.tileSize);
            UI.setColor(Color.black);
            UI.setFontSize(20);
            if (!letter.equals("blank")){UI.drawString(letter,x+12,y+25);}
            double ls = BoardChecker.letterScore(currentLetter);
            int letterscore = (int) ls;
            String letterScore = String.valueOf(letterscore);
            UI.setFontSize(12);
            UI.drawString(letterScore,x+25,y+35);
            reDrawn = true;
        }
        else {
            this.drawTile();
        }
    }

    /**
     * These two methods take an x/y value and round it to the correct box and return the rounded number
     */
    public double boxTileX(double x){
        double left1 = ScrabbleGame.left;
        if (x>=left1 && x<=left1+ScrabbleBoard.tileSize*1){
            x = left1 + ScrabbleBoard.tileSize*0;
            xCoord = 1;}
        if (x>=left1+ScrabbleBoard.tileSize*1 && x<=left1+ScrabbleBoard.tileSize*2){
            x = left1 + ScrabbleBoard.tileSize*1;
            xCoord = 2;}
        if (x>=left1 +ScrabbleBoard.tileSize*2 && x<=left1+ScrabbleBoard.tileSize*3){
            x = left1 + ScrabbleBoard.tileSize*2;
            xCoord = 3;}
        if (x>=left1+ScrabbleBoard.tileSize*3 && x<=left1+ScrabbleBoard.tileSize*4){
            x = left1 + ScrabbleBoard.tileSize*3;
            xCoord = 4;}
        if (x>=left1+ScrabbleBoard.tileSize*4 && x<=left1+ScrabbleBoard.tileSize*5){
            x = left1 + ScrabbleBoard.tileSize*4;
            xCoord = 5;}
        if (x>=left1+ScrabbleBoard.tileSize*5 && x<=left1+ScrabbleBoard.tileSize*6){
            x = left1 + ScrabbleBoard.tileSize*5;
            xCoord = 6;}
        if (x>=left1+ScrabbleBoard.tileSize*6 && x<=left1+ScrabbleBoard.tileSize*7){
            x = left1 + ScrabbleBoard.tileSize*6;
            xCoord = 7;}
        if (x>=left1+ScrabbleBoard.tileSize*7 && x<=left1+ScrabbleBoard.tileSize*8){
            x = left1 + ScrabbleBoard.tileSize*7;
            xCoord = 8;}
        if (x>=left1+ScrabbleBoard.tileSize*8 && x<=left1+ScrabbleBoard.tileSize*9){
            x = left1 + ScrabbleBoard.tileSize*8;
            xCoord =9;}
        if (x>=left1+ScrabbleBoard.tileSize*9 && x<=left1+ScrabbleBoard.tileSize*10){
            x = left1 + ScrabbleBoard.tileSize*9;
            xCoord = 10;}
        if (x>=left1+ScrabbleBoard.tileSize*10 && x<=left1+ScrabbleBoard.tileSize*11){
            x = left1 + ScrabbleBoard.tileSize*10;
            xCoord = 11;}
        if (x>=left1+ScrabbleBoard.tileSize*11 && x<=left1+ScrabbleBoard.tileSize*12){
            x = left1 + ScrabbleBoard.tileSize*11;
            xCoord = 12;}
        if (x>=left1+ScrabbleBoard.tileSize*12 && x<=left1+ScrabbleBoard.tileSize*13){
            x = left1 + ScrabbleBoard.tileSize*12;
            xCoord = 13;}
        if (x>=left1+ScrabbleBoard.tileSize*13 && x<=left1+ScrabbleBoard.tileSize*14){
            x = left1 + ScrabbleBoard.tileSize*13;
            xCoord = 14;}
        if (x>=left1+ScrabbleBoard.tileSize*14 && x<=left1+ScrabbleBoard.tileSize*15){
            x = left1 + ScrabbleBoard.tileSize*14;
            xCoord = 15;}
        return x;
    }

    public double boxTileY(double x){
        double left1 = ScrabbleGame.left;
        if (x>=left1 && x<=left1+ScrabbleBoard.tileSize*1){
            x = left1 + ScrabbleBoard.tileSize*0;
            yCoord = 1;}
        if (x>=left1+ScrabbleBoard.tileSize*1 && x<=left1+ScrabbleBoard.tileSize*2){
            x = left1 + ScrabbleBoard.tileSize*1;
            yCoord = 2;}
        if (x>=left1 +ScrabbleBoard.tileSize*2 && x<=left1+ScrabbleBoard.tileSize*3){
            x = left1 + ScrabbleBoard.tileSize*2;
            yCoord = 3;}
        if (x>=left1+ScrabbleBoard.tileSize*3 && x<=left1+ScrabbleBoard.tileSize*4){
            x = left1 + ScrabbleBoard.tileSize*3;
            yCoord = 4;}
        if (x>=left1+ScrabbleBoard.tileSize*4 && x<=left1+ScrabbleBoard.tileSize*5){
            x = left1 + ScrabbleBoard.tileSize*4;
            yCoord = 5;}
        if (x>=left1+ScrabbleBoard.tileSize*5 && x<=left1+ScrabbleBoard.tileSize*6){
            x = left1 + ScrabbleBoard.tileSize*5;
            yCoord = 6;}
        if (x>=left1+ScrabbleBoard.tileSize*6 && x<=left1+ScrabbleBoard.tileSize*7){
            x = left1 + ScrabbleBoard.tileSize*6;
            yCoord = 7;}
        if (x>=left1+ScrabbleBoard.tileSize*7 && x<=left1+ScrabbleBoard.tileSize*8){
            x = left1 + ScrabbleBoard.tileSize*7;
            yCoord = 8;}
        if (x>=left1+ScrabbleBoard.tileSize*8 && x<=left1+ScrabbleBoard.tileSize*9){
            x = left1 + ScrabbleBoard.tileSize*8;
            yCoord =9;}
        if (x>=left1+ScrabbleBoard.tileSize*9 && x<=left1+ScrabbleBoard.tileSize*10){
            x = left1 + ScrabbleBoard.tileSize*9;
            yCoord = 10;}
        if (x>=left1+ScrabbleBoard.tileSize*10 && x<=left1+ScrabbleBoard.tileSize*11){
            x = left1 + ScrabbleBoard.tileSize*10;
            yCoord = 11;}
        if (x>=left1+ScrabbleBoard.tileSize*11 && x<=left1+ScrabbleBoard.tileSize*12){
            x = left1 + ScrabbleBoard.tileSize*11;
            yCoord = 12;}
        if (x>=left1+ScrabbleBoard.tileSize*12 && x<=left1+ScrabbleBoard.tileSize*13){
            x = left1 + ScrabbleBoard.tileSize*12;
            yCoord = 13;}
        if (x>=left1+ScrabbleBoard.tileSize*13 && x<=left1+ScrabbleBoard.tileSize*14){
            x = left1 + ScrabbleBoard.tileSize*13;
            yCoord = 14;}
        if (x>=left1+ScrabbleBoard.tileSize*14 && x<=left1+ScrabbleBoard.tileSize*15){
            x = left1 + ScrabbleBoard.tileSize*14;
            yCoord = 15;}
        return x;
    }

    /**
     * Checks whether the spot you are trying to fill is already taken or not
     * If it is then it returns true
     * If its not then it fills out the appropriate arrays and returns false
     * Also adds the letter, letter score and tiledrawn values to the 3 arrays in boardchecker
     */

    public boolean boardFillChecker (int x, int y){
        if (BoardChecker.boardLetter[y-1][x-1]!=null){
            return true;
        }
        else{
            //the following changes 2 arrays adding the letter to the correct spot in each array
            //the thrid array is added to in scrabble game when this method returns false
            double letterScore = BoardChecker.letterScore(currentLetter);//gets the leter score using the letterscore method
            BoardChecker.boardLetterScores[yCoord-1][xCoord-1]=letterScore;//adds the letter score to the correct spot in the letter score array
            BoardChecker.boardLetter [yCoord-1][xCoord-1]= currentLetter;//adds the letter to the board letters array
            return false;
        }
    }

    /**
     * Calls deleteTile method
     * Chooses new letter from bag array and sets it as current letter
     * Calls drawTile methos
     */
    public void replaceTile()
    {
        if(ScrabbleGame.tilesLeft != 0){
            this.deleteTile();
            int num = (int)(Math.random()*ScrabbleGame.tilesLeft);//creates a random int between 0 and tilesLeft
            currentLetter = ScrabbleGame.bag.get(num);//takes a letter from the bag array and sets current letter to it
            ScrabbleGame.bag.remove(currentLetter);//removes the letter taken from bag array
            ScrabbleGame.tilesLeft = ScrabbleGame.tilesLeft-1;//subtracts one from tiles left
            this.drawTile();
            xCoord=0;
            yCoord=0;
            reDrawn = false;
        }
    }

    /**
     * Erases a tile when its in its default position and its letter doesnt erase object though
     * Then draws background rectangle
     */
    public void deleteTile() {
        String letter = currentLetter;
        UI.eraseRect(left+xPos*tileNumber, top+ScrabbleBoard.boardSize+10,ScrabbleBoard.tileSize,ScrabbleBoard.tileSize);
        UI.eraseString(letter,left+xPos*tileNumber+12,top+ScrabbleBoard.boardSize+35); //erases the tile and letter
        UI.setColor(myGreen);
        UI.fillRect(left+xPos*tileNumber, top+ScrabbleBoard.boardSize+10,ScrabbleBoard.tileSize,ScrabbleBoard.tileSize);
        UI.drawRect(left+xPos*tileNumber, top+ScrabbleBoard.boardSize+10,ScrabbleBoard.tileSize,ScrabbleBoard.tileSize);
        UI.setColor(Color.black);
    }

    //take coordinates from the mouse, checks which box its in, then either erases that box if mouse was in a box or draws tile in orginal place
    public void deleteTilePosition(double x, double y){
        if(this.boardFillChecker(xCoord,yCoord)){
            UI.eraseRect(50+(x-1)*ScrabbleBoard.tileSize,50+(y-1)*ScrabbleBoard.tileSize,ScrabbleBoard.tileSize,ScrabbleBoard.tileSize);
            reDrawn = false;
            BoardChecker.boardTileNumbers[yCoord-1][xCoord-1]=0;
            BoardChecker.boardLetter[yCoord-1][xCoord-1]=null;
        }
        else {
            this.drawTile();
        }
    }

    public boolean reDrawnTrue (){
        return reDrawn;
    }
}
