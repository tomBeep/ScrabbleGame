
/**
 * 
 * This creates the board for my scrabble game
 * Thomas Edwards 
 * Version 1
 * It also contains the redrawing methods that will redraw tiles of the board when called
 */

import ecs100.*;
import java.awt.Color;

public class ScrabbleBoard
{

    public static final int left = 50;
    public static final int top = 50;
    public static final int boardSize=600;//constant try not to change as fucks everything up
    public static final int tileSize = boardSize/15;

    private Color myGreen = new Color (0,153,100);//the green of the board
    private Color myDarkGreen = new Color (0,102,51);//the green of the background
    private Color myPink = new Color (255,153,204);
    private Color myLightBlue = new Color (0,204,204);
    private Color myBrown = new Color(244,164,96);

    /**
     * Constructor 
     */
    public ScrabbleBoard()
    {
        UI.setDivider(0.0);
        UI.clearPanes();
        this.startMethod();
    }

    public void drawTile (double x, double y, String letter){
        UI.setColor(myBrown);
        UI.fillRect(x,y,tileSize,tileSize);
        UI.setColor(Color.black);
        UI.drawString(letter,x+12,y+35);
    }

    public void startMethod()
    {
        this.drawOutline();
        this.drawPlain();
        this.drawDoubleWord();
        this.drawTripleWord();
        this.drawDoubleLetter();
        this.drawTripleLetter();
        this.drawStar();
    }
    
    /**
     * The following methods are called when the scrabble board object is first made
     * They run through loops and draw the correct board tiles in the correct locations
     */
    public void drawPlain(){//draws the plain tiles colored blue-green with a white outline
        int row = 0;
        int col = 0;
        while (col<15){
            while(row<15){
                UI.setColor(myGreen);
                UI.fillRect(left+tileSize*row,top+tileSize*col,tileSize,tileSize);
                UI.setColor(Color.white);
                UI.drawRect(left+tileSize*row,top+tileSize*col,tileSize,tileSize);
                row++;
            }
            row=0;
            col++;
        }
        UI.setColor(Color.black);
    }

    public void drawDoubleWord(){
        int row = 0;
        int col = 0;
        int box1=0;
        int box2=14;
        while (col<15){
            while(row<15){
                if (row==box1 || row ==box2){
                    UI.setColor(myPink);
                    UI.fillRect(left+tileSize*row,top+tileSize*col,tileSize,tileSize);
                    UI.setColor(Color.white);
                    UI.drawRect(left+tileSize*row,top+tileSize*col,tileSize,tileSize);
                    UI.setColor(Color.black);
                    UI.drawString("Double",left+tileSize*row,top+tileSize*col+tileSize/2);
                    UI.drawString("  Word ",left+tileSize*row,top+tileSize*col+tileSize/2+tileSize/3);
                }
                row++;
            }
            box1++;
            box2=box2-1;
            row=0;
            col++;
        }
    }

    public void drawTripleWord(){
        int row = 0;
        int col = 0;
        int box1=0;
        int box2=14;
        int box3=7;
        while (col<15){
            while(row<15){
                if ((row==box1 || row ==box2 || row ==box3) && (col ==0 || col ==7 || col ==14)){
                    UI.setColor(Color.red);
                    UI.fillRect(left+tileSize*row,top+tileSize*col,tileSize,tileSize);
                    UI.setColor(Color.white);
                    UI.drawRect(left+tileSize*row,top+tileSize*col,tileSize,tileSize);
                    UI.setColor(Color.black);
                    UI.drawString(" Triple",left+tileSize*row,top+tileSize*col+tileSize/2);
                    UI.drawString("  Word",left+tileSize*row,top+tileSize*col+tileSize/2+tileSize/3);
                }
                row++;
            }
            row=0;
            col++;
        }
    }

    public void drawDoubleLetter(){
        int row = 0;
        int col = 0;
        int box = 1;
        boolean draw = false;
        int [] dlBoxes = {4,12,37,39,46,53,60,93,97,99,103,109,117,123,127,129,133,166,173,180,187,189,214,222};
        while (col<15){
            while(row<15){
                for (int item : dlBoxes){
                    if (item==box){draw=true;}
                }
                if (draw){
                    UI.setColor(myLightBlue);
                    UI.fillRect(left+tileSize*row,top+tileSize*col,tileSize,tileSize);
                    UI.setColor(Color.white);
                    UI.drawRect(left+tileSize*row,top+tileSize*col,tileSize,tileSize);
                    UI.setColor(Color.black);
                    UI.drawString("Double",left+tileSize*row,top+tileSize*col+tileSize/2);
                    UI.drawString(" Letter",left+tileSize*row,top+tileSize*col+tileSize/2+tileSize/3);
                    draw=false;
                }
                row++;
                box++;
            }
            row=0;
            col++;
        }
    }

    public void drawTripleLetter(){
        int row = 0;
        int col = 0;
        int box = 1;
        boolean draw = false;
        int [] dlBoxes = {21,25,77,81,85,89,137,141,145,149,201,205};
        while (col<15){
            while(row<15){
                for (int item : dlBoxes){
                    if (item==box){draw=true;}
                }
                if (draw){
                    UI.setColor(Color.blue);
                    UI.fillRect(left+tileSize*row,top+tileSize*col,tileSize,tileSize);
                    UI.setColor(Color.white);
                    UI.drawRect(left+tileSize*row,top+tileSize*col,tileSize,tileSize);
                    UI.setColor(Color.white);
                    UI.drawString(" Triple",left+tileSize*row,top+tileSize*col+tileSize/2);
                    UI.drawString(" Letter",left+tileSize*row,top+tileSize*col+tileSize/2+tileSize/3);
                    draw=false;
                }
                row++;
                box++;
            }
            row=0;
            col++;
        }
        UI.setColor(Color.black);
    }

    public void drawStar(){//draws the plain tiles colored blue-green with a white outline
        UI.setColor(myPink);
        UI.fillRect(left+tileSize*7,top+tileSize*7,tileSize,tileSize);
        UI.setColor(Color.white);
        UI.drawRect(left+tileSize*7,top+tileSize*7,tileSize,tileSize);
        UI.setColor(Color.black);
        double [] x = {left+tileSize*7.5, left+tileSize*7.6, left+tileSize*7.9, left+tileSize*7.6,left+tileSize*7.5,left+tileSize*7.4,left+tileSize*7.1,left+tileSize*7.4};
        double [] y = {top+tileSize*7.1, top+tileSize*7.4, top+tileSize*7.5, top+tileSize*7.6,top+tileSize*7.9,top+tileSize*7.6,top+tileSize*7.5,top+tileSize*7.4};
        UI.fillPolygon(x,y,8);
    }

    public void drawOutline () {
        UI.setColor(myDarkGreen);
        UI.fillRect(0,0,boardSize*2,boardSize*2);
        UI.setColor(Color.white);
        int num = 0;
        String [] letters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};
        for (String item : letters){
            UI.drawString(item,left+tileSize*num+tileSize/2-2,top-4);
            num++;
        }
        num = 0;
        String [] numbers = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
        for (String item : numbers){
            UI.drawString(item,left-20,top+tileSize*num+tileSize/2+2);
            num++;
        }
    }
    
    /**
     * These following methods will only redraw one tile and will redraw it at the grid coordinates specified in the parameters
     */

    public static void drawTL (double row, double col){//x and y coordinates, not actual pixel points
        row = row-1;
        col = col-1;
        UI.setColor(Color.blue);
        UI.fillRect(left+tileSize*row,top+tileSize*col,tileSize,tileSize);
        UI.setColor(Color.white);
        UI.drawRect(left+tileSize*row,top+tileSize*col,tileSize,tileSize);
        UI.setColor(Color.white);
        UI.drawString(" Triple",left+tileSize*row,top+tileSize*col+tileSize/2);
        UI.drawString(" Letter",left+tileSize*row,top+tileSize*col+tileSize/2+tileSize/3);
    }

    public void drawDL (double row, double col){//x and y coordinates, not actual pixel points
        row = row-1;
        col = col-1;
        UI.setColor(myLightBlue);
        UI.fillRect(left+tileSize*row,top+tileSize*col,tileSize,tileSize);
        UI.setColor(Color.white);
        UI.drawRect(left+tileSize*row,top+tileSize*col,tileSize,tileSize);
        UI.setColor(Color.black);
        UI.drawString("Double",left+tileSize*row,top+tileSize*col+tileSize/2);
        UI.drawString(" Letter",left+tileSize*row,top+tileSize*col+tileSize/2+tileSize/3);
    }

    public void drawDW (double row, double col){//x and y coordinates, not actual pixel points
        row = row-1;
        col = col-1;
        UI.setColor(myPink);
        UI.fillRect(left+tileSize*row,top+tileSize*col,tileSize,tileSize);
        UI.setColor(Color.white);
        UI.drawRect(left+tileSize*row,top+tileSize*col,tileSize,tileSize);
        UI.setColor(Color.black);
        UI.drawString("Double",left+tileSize*row,top+tileSize*col+tileSize/2);
        UI.drawString("  Word ",left+tileSize*row,top+tileSize*col+tileSize/2+tileSize/3);
    }

    public void drawTW (double row, double col){//x and y coordinates, not actual pixel points
        row = row-1;
        col = col-1;
        UI.setColor(Color.red);
        UI.fillRect(left+tileSize*row,top+tileSize*col,tileSize,tileSize);
        UI.setColor(Color.white);
        UI.drawRect(left+tileSize*row,top+tileSize*col,tileSize,tileSize);
        UI.setColor(Color.black);
        UI.drawString(" Triple",left+tileSize*row,top+tileSize*col+tileSize/2);
        UI.drawString("  Word",left+tileSize*row,top+tileSize*col+tileSize/2+tileSize/3);
    }

    public void drawNormal (double row, double col){//x and y coordinates, not actual pixel points
        row = row-1;
        col = col-1;
        UI.setColor(myGreen);
        UI.fillRect(left+tileSize*row,top+tileSize*col,tileSize,tileSize);
        UI.setColor(Color.white);
        UI.drawRect(left+tileSize*row,top+tileSize*col,tileSize,tileSize);
    }
}
