
/**
 * Scrabble Game
 * 
 * Thomas Edwards 
 * 1.0
 * 
 * Board covers 50 + boardSize  in both x and y directions (so 50-650 for default)
 * Each box is 40 wide and there are 15 tiles between 50 and 650
 * The tiles start at 70(constant) and each tile is 40 wide and there is a 10(constant) pixel gap between each tile.
 * Note atm changing the boardSize is NOT a good idea.
 * 
 * Currently getting a bug where taking a token off the board and putting it down another tiles spot will swap the tiles and redraw other tile which is still on the board
 */
import ecs100.*;
import java.awt.Color;
import java.util.ArrayList;
public class ScrabbleGame
{
    // instance variables - replace the example below with your own
    private double startX;
    private double startY;
    public static final int left = 50;
    public static final int top = 50;
    private String selectedTile=null;
    public static ArrayList<String> bag = new ArrayList<String>(100);
    public static int tilesLeft;
    private Tile tile1;
    private Tile tile2;
    private Tile tile3;
    private Tile tile4;
    private Tile tile5;
    private Tile tile6;
    private Tile tile7;
    private String tileToSwap;
    private BoardChecker bc1;
    private ScrabbleBoard sb1;

    /**
     * Constructor for objects of class ScrabbleRack
     * Creates a bag array which contains all the letters
     */
    public ScrabbleGame()
    {
        sb1 = new ScrabbleBoard();//draws the board
        bc1 = new BoardChecker ();
        this.addBag();
        tilesLeft = 100;
        this.startGame();
        UI.addButton("End Turn", this::endTurn);
        UI.setLineWidth(3);
        UI.drawRect(left+640,top-10,ScrabbleBoard.tileSize+40,ScrabbleBoard.tileSize+40);
        UI.setLineWidth(1);
        UI.setFontSize(18);
        UI.drawString("Selected Tile",left+630,top+ScrabbleBoard.tileSize+60);
        UI.setFontSize(12);
    }

    public void startGame (){//will draw the first 7 tiles
        tile1 = new Tile (0);//tile 1 in position 0;
        tile2 = new Tile (1);
        tile3 = new Tile (2);
        tile4 = new Tile (3);
        tile5 = new Tile (4);
        tile6 = new Tile (5);
        tile7 = new Tile (6);
        UI.setMouseListener(this::doMouse);
    }

    public void endTurn (){
        if(!BoardChecker.checkStart()){//checks that the first turn has initial 
            UI.println("Invalid move");
            UI.println("Please place a token on star");
            return;
        }
        if (!bc1.checkValidity()){//checks that a tile is touching a previously placed tile
            UI.println("Invalid move");
            return;
        }
        if (!bc1.checkValidity2()){//checks that all tiles placed are in the same row and column
            UI.println("Invalid move");
            return;
        }
        bc1.valid=false;//resets the valid to false
        int num = 0;//number which counts how many tiles were placed that turn
        if (tile1.reDrawnTrue()&& tilesLeft>0){tile1= new Tile (0);num++;}
        if (tile2.reDrawnTrue()&& tilesLeft>0){tile2= new Tile (1);num++;}
        if (tile3.reDrawnTrue()&& tilesLeft>0){tile3= new Tile (2);num++;}
        if (tile4.reDrawnTrue()&& tilesLeft>0){tile4= new Tile (3);num++;}
        if (tile5.reDrawnTrue()&& tilesLeft>0){tile5= new Tile (4);num++;}
        if (tile6.reDrawnTrue()&& tilesLeft>0){tile6= new Tile (5);num++;}
        if (tile7.reDrawnTrue()&& tilesLeft>0){tile7= new Tile (6);num++;}

        if(num==7){bc1.score+=50;}//if you use all 7 letters, add 50 to the score
        bc1.updateScore();
        //loop which goes through the entire board and makes any tile numbers 10 which effectivly locks them in place
        for (int j = 0; j<BoardChecker.boardTileNumbers.length; j++){
            for (int i = 0; i<BoardChecker.boardTileNumbers[j].length; i++){
                if (BoardChecker.boardTileNumbers[i][j]!=0){
                    BoardChecker.boardTileNumbers[i][j]=10;
                }
            }
        }
    }

    /**
     * Working well
     */
    public void doMouse(String action, double x, double y){
        if (action.equals("pressed")){
            if(this.touchingTile(x,y)){
                if (selectedTile.equals("tile1")){tile1.deleteTile();}
                if (selectedTile.equals("tile2")){tile2.deleteTile();}
                if (selectedTile.equals("tile3")){tile3.deleteTile();}
                if (selectedTile.equals("tile4")){tile4.deleteTile();}
                if (selectedTile.equals("tile5")){tile5.deleteTile();}
                if (selectedTile.equals("tile6")){tile6.deleteTile();}
                if (selectedTile.equals("tile7")){tile7.deleteTile();}
            }
            else if (x>=left && x<=left+ScrabbleBoard.boardSize && y>=top && y<=top+ScrabbleBoard.boardSize){
                double idX = this.getX(x);//the double numbers 1-15 indicating the row clicked
                double idY = this.getY(y);//the double numbers 1-15 indicating the column clicked
                int gridX = (int)idX-1;//changes double coordinates into int elements of an array 0-14
                int gridY = (int)idY-1;
                if (BoardChecker.boardTileNumbers[gridY][gridX]!=0 && BoardChecker.boardTileNumbers[gridY][gridX]!=10){
                    if(BoardChecker.boardTileNumbers[gridY][gridX]==1){selectedTile="tile1"; tile1.deleteTilePosition(idX,idY);} 
                    else if(BoardChecker.boardTileNumbers[gridY][gridX]==2){selectedTile="tile2"; tile2.deleteTilePosition(idX,idY);} 
                    else if(BoardChecker.boardTileNumbers[gridY][gridX]==3){selectedTile="tile3"; tile3.deleteTilePosition(idX,idY);} 
                    else if(BoardChecker.boardTileNumbers[gridY][gridX]==4){selectedTile="tile4"; tile4.deleteTilePosition(idX,idY);} 
                    else if(BoardChecker.boardTileNumbers[gridY][gridX]==5){selectedTile="tile5"; tile5.deleteTilePosition(idX,idY);} 
                    else if(BoardChecker.boardTileNumbers[gridY][gridX]==6){selectedTile="tile6"; tile6.deleteTilePosition(idX,idY);} 
                    else if(BoardChecker.boardTileNumbers[gridY][gridX]==7){selectedTile="tile7"; tile7.deleteTilePosition(idX,idY);} 
                    if (idX==1 && idY==1 || idX==8 && idY==1 || idX==15 && idY==1 || idX==1 && idY==8 || idX==15 && idY==8 || idX==1 && idY==15 || idX==8 && idY==15 || idX==15 && idY==15){
                        sb1.drawTW(idX,idY);
                    }
                    else if (idX==2 && idY==2 || idX==3 && idY==3 || idX==4 && idY==4 || idX==5 && idY==5 || idX==14 && idY==2 || idX==13 && idY==3 || idX==12 && idY==4 || idX==11 && idY==5
                    || idX==2 && idY==14 || idX==3 && idY==13 || idX==4 && idY==12 || idX==5 && idY==11 || idX==14 && idY==14 || idX==13 && idY==13 || idX==12 && idY==12 || idX==11 && idY==11){
                        sb1.drawDW(idX,idY);
                    }
                    else if (idX==4 && idY==1 || idX==12 && idY==1 || idX==7 && idY==3 || idX==9 && idY==3 || idX==1 && idY==4 || idX==8 && idY==4 || idX==15 && idY==4 || idX==3 && idY==7
                    || idX==7 && idY==7 || idX==9 && idY==7 || idX==13 && idY==7 || idX==4 && idY==8 || idX==12 && idY==8 || idX==3 && idY==9 || idX==7 && idY==9 || idX==9 && idY==9
                    || idX==13 && idY==9 || idX==1 && idY==12 || idX==8 && idY==12 || idX==15 && idY==12 || idX==7 && idY==13 || idX==9 && idY==13 || idX==4 && idY==15 || idX==12 && idY==15){
                        sb1.drawDL(idX,idY);
                    }
                    else if (idX==6 && idY==2 || idX==10 && idY==2 || idX==2 && idY==6 || idX==6 && idY==6 || idX==10 && idY==6 || idX==14 && idY==6 || idX==2 && idY==10 || idX==6 && idY==10
                    || idX==10 && idY==10 || idX==14 && idY==10 || idX==6 && idY==14 || idX==10 && idY==14){
                        sb1.drawTL(idX,idY);
                    }
                    else if (idX==8 && idY==8){
                        sb1.drawStar();
                    }
                    else {
                        sb1.drawNormal(idX,idY);
                    }
                }
            }
            else{
                selectedTile= null;
            }
            if (selectedTile!=null){this.drawSelectedTile();}
        }
        if (action.equals("released")){
            if (x>=left && x<=left+ScrabbleBoard.boardSize && y>=top && y<=top+ScrabbleBoard.boardSize && selectedTile!=null){
                if (selectedTile.equals("tile1") && !tile1.reDrawnTrue()){
                    tile1.drawTilePosition(x,y);
                    if (tile1.reDrawn){BoardChecker.boardTileNumbers[tile1.yCoord-1][tile1.xCoord-1] = 1;}
                }
                if (selectedTile.equals("tile2") && !tile2.reDrawnTrue()){
                    tile2.drawTilePosition(x,y);
                    if (tile2.reDrawn){BoardChecker.boardTileNumbers[tile2.yCoord-1][tile2.xCoord-1] = 2;}
                }
                if (selectedTile.equals("tile3") && !tile3.reDrawnTrue()){
                    tile3.drawTilePosition(x,y);
                    if (tile3.reDrawn){BoardChecker.boardTileNumbers[tile3.yCoord-1][tile3.xCoord-1] = 3;}
                }
                if (selectedTile.equals("tile4") && !tile4.reDrawnTrue()){
                    tile4.drawTilePosition(x,y);
                    if (tile4.reDrawn){BoardChecker.boardTileNumbers[tile4.yCoord-1][tile4.xCoord-1] = 4;}
                }
                if (selectedTile.equals("tile5") && !tile5.reDrawnTrue()){
                    tile5.drawTilePosition(x,y);
                    if (tile5.reDrawn){BoardChecker.boardTileNumbers[tile5.yCoord-1][tile5.xCoord-1] = 5;}
                }
                if (selectedTile.equals("tile6") && !tile6.reDrawnTrue()){
                    tile6.drawTilePosition(x,y);
                    if (tile6.reDrawn){BoardChecker.boardTileNumbers[tile6.yCoord-1][tile6.xCoord-1] = 6;}
                }
                if (selectedTile.equals("tile7") && !tile7.reDrawnTrue()){
                    tile7.drawTilePosition(x,y);
                    if (tile7.reDrawn){BoardChecker.boardTileNumbers[tile7.yCoord-1][tile7.xCoord-1] = 7;}
                }
            }
            else if (x>=70 && x<=410 && y>=70+ScrabbleBoard.boardSize+10 && y<=70+ScrabbleBoard.boardSize+10+ScrabbleBoard.tileSize){
                this.swapTiles(x,y);
            }
            else if (selectedTile!=null) {
                this.drawSelected();
            }
            selectedTile= null;
            this.eraseSelectedTile();
        }
    }

    public void drawSelected (){
        if (selectedTile.equals("tile1")){tile1.drawTile();}
        if (selectedTile.equals("tile2")){tile2.drawTile();}
        if (selectedTile.equals("tile3")){tile3.drawTile();}
        if (selectedTile.equals("tile4")){tile4.drawTile();}
        if (selectedTile.equals("tile5")){tile5.drawTile();}
        if (selectedTile.equals("tile6")){tile6.drawTile();}
        if (selectedTile.equals("tile7")){tile7.drawTile();}
    }

    public boolean touchingTile(double x, double y){//is the clicker touching a tile on the rack
        if (x>=70 && x<=110 && y>=70+ScrabbleBoard.boardSize+10 && y<=70+ScrabbleBoard.boardSize+10+ScrabbleBoard.tileSize){
            selectedTile = "tile1";
            return true;
        }
        else if (x>=120 && x<=160 && y>=70+ScrabbleBoard.boardSize+10 && y<=70+ScrabbleBoard.boardSize+10+ScrabbleBoard.tileSize){
            selectedTile = "tile2";
            return true;
        }
        else if (x>=170 && x<=210 && y>=70+ScrabbleBoard.boardSize+10 && y<=70+ScrabbleBoard.boardSize+10+ScrabbleBoard.tileSize){
            selectedTile = "tile3";
            return true;
        }
        else if (x>=220 && x<=260 && y>=70+ScrabbleBoard.boardSize+10 && y<=70+ScrabbleBoard.boardSize+10+ScrabbleBoard.tileSize){
            selectedTile = "tile4";
            return true;
        }
        else if (x>=270 && x<=310 && y>=70+ScrabbleBoard.boardSize+10 && y<=70+ScrabbleBoard.boardSize+10+ScrabbleBoard.tileSize){
            selectedTile = "tile5";
            return true;
        }
        else if (x>=320 && x<=360 && y>=70+ScrabbleBoard.boardSize+10 && y<=70+ScrabbleBoard.boardSize+10+ScrabbleBoard.tileSize){
            selectedTile = "tile6";
            return true;
        }
        else if (x>=370 && x<=410 && y>=70+ScrabbleBoard.boardSize+10 && y<=70+ScrabbleBoard.boardSize+10+ScrabbleBoard.tileSize){
            selectedTile = "tile7";
            return true;
        }
        else {return false;}
    }

    public void addBag()
    {
        bag = new ArrayList<String> (100);
        int num = 0;
        bag.add("blank");
        bag.add("blank");
        while (num<12){
            bag.add("E");
            num++;
        }
        num = 0;
        while (num<9){
            bag.add("A");
            num++;
        }
        num = 0;
        while (num<9){
            bag.add("I");
            num++;
        }
        num = 0;
        while (num<8){
            bag.add("O");
            num++;
        }
        num = 0;
        while (num<6){
            bag.add("N");
            num++;
        }
        num = 0;
        while (num<6){
            bag.add("R");
            num++;
        }
        num = 0;
        while (num<6){
            bag.add("T");
            num++;
        }
        num = 0;
        while (num<4){
            bag.add("L");
            num++;
        }
        num = 0;
        while (num<4){
            bag.add("S");
            num++;
        }
        num = 0;
        while (num<4){
            bag.add("U");
            num++;
        }
        num = 0;
        while (num<4){
            bag.add("D");
            num++;
        }
        num = 0;
        while (num<3){
            bag.add("G");
            num++;
        }
        num = 0;
        bag.add("B");
        bag.add("B");
        bag.add("C");
        bag.add("C");
        bag.add("M");
        bag.add("M");
        bag.add("P");
        bag.add("P");
        bag.add("F");
        bag.add("F");
        bag.add("H");
        bag.add("H");
        bag.add("V");
        bag.add("V");
        bag.add("W");
        bag.add("W");
        bag.add("Y");
        bag.add("Y");
        bag.add("K");
        bag.add("J");
        bag.add("X");
        bag.add("Q");
        bag.add("Z");
    }

    //these two methods take the x coordinate of where things were clicked and try to match them to cordinates on the scrabble board

    public double getX(double x){
        int x1 = 0;
        if (x>=left && x<=left+ScrabbleBoard.tileSize*1){
            x = left + ScrabbleBoard.tileSize*0;
            x1=1;}
        if (x>=left+ScrabbleBoard.tileSize*1 && x<=left+ScrabbleBoard.tileSize*2){
            x = left + ScrabbleBoard.tileSize*1;
            x1 = 2;}
        if (x>=left +ScrabbleBoard.tileSize*2 && x<=left+ScrabbleBoard.tileSize*3){
            x = left + ScrabbleBoard.tileSize*2;
            x1 = 3;}
        if (x>=left+ScrabbleBoard.tileSize*3 && x<=left+ScrabbleBoard.tileSize*4){
            x = left + ScrabbleBoard.tileSize*3;
            x1= 4;}
        if (x>=left+ScrabbleBoard.tileSize*4 && x<=left+ScrabbleBoard.tileSize*5){
            x = left + ScrabbleBoard.tileSize*4;
            x1 = 5;}
        if (x>=left+ScrabbleBoard.tileSize*5 && x<=left+ScrabbleBoard.tileSize*6){
            x = left + ScrabbleBoard.tileSize*5;
            x1= 6;}
        if (x>=left+ScrabbleBoard.tileSize*6 && x<=left+ScrabbleBoard.tileSize*7){
            x = left + ScrabbleBoard.tileSize*6;
            x1 = 7;}
        if (x>=left+ScrabbleBoard.tileSize*7 && x<=left+ScrabbleBoard.tileSize*8){
            x = left + ScrabbleBoard.tileSize*7;
            x1 = 8;}
        if (x>=left+ScrabbleBoard.tileSize*8 && x<=left+ScrabbleBoard.tileSize*9){
            x = left + ScrabbleBoard.tileSize*8;
            x1 =9;}
        if (x>=left+ScrabbleBoard.tileSize*9 && x<=left+ScrabbleBoard.tileSize*10){
            x = left + ScrabbleBoard.tileSize*9;
            x1 = 10;}
        if (x>=left+ScrabbleBoard.tileSize*10 && x<=left+ScrabbleBoard.tileSize*11){
            x = left + ScrabbleBoard.tileSize*10;
            x1 = 11;}
        if (x>=left+ScrabbleBoard.tileSize*11 && x<=left+ScrabbleBoard.tileSize*12){
            x = left + ScrabbleBoard.tileSize*11;
            x1 = 12;}
        if (x>=left+ScrabbleBoard.tileSize*12 && x<=left+ScrabbleBoard.tileSize*13){
            x = left + ScrabbleBoard.tileSize*12;
            x1 = 13;}
        if (x>=left+ScrabbleBoard.tileSize*13 && x<=left+ScrabbleBoard.tileSize*14){
            x = left + ScrabbleBoard.tileSize*13;
            x1 = 14;}
        if (x>=left+ScrabbleBoard.tileSize*14 && x<=left+ScrabbleBoard.tileSize*15){
            x = left + ScrabbleBoard.tileSize*14;
            x1 = 15;}
        return x1;
    }

    public void swapTiles (double x, double y){
        if (x>=70 && x<=110 && y>=70+ScrabbleBoard.boardSize+10 && y<=70+ScrabbleBoard.boardSize+10+ScrabbleBoard.tileSize){
            if(tile1.reDrawn){
                this.drawSelected();
                return;
            }
            tileToSwap = "tile1";
        }
        else if (x>=120 && x<=160 && y>=70+ScrabbleBoard.boardSize+10 && y<=70+ScrabbleBoard.boardSize+10+ScrabbleBoard.tileSize){
            if(tile2.reDrawn){
                this.drawSelected();
                return;
            }
            tileToSwap = "tile2";
        }
        else if (x>=170 && x<=210 && y>=70+ScrabbleBoard.boardSize+10 && y<=70+ScrabbleBoard.boardSize+10+ScrabbleBoard.tileSize){
            if(tile3.reDrawn){
                this.drawSelected();
                return;
            }
            tileToSwap = "tile3";
        }
        else if (x>=220 && x<=260 && y>=70+ScrabbleBoard.boardSize+10 && y<=70+ScrabbleBoard.boardSize+10+ScrabbleBoard.tileSize){
            if(tile4.reDrawn){
                this.drawSelected();
                return;
            }
            tileToSwap = "tile4";
        }
        else if (x>=270 && x<=310 && y>=70+ScrabbleBoard.boardSize+10 && y<=70+ScrabbleBoard.boardSize+10+ScrabbleBoard.tileSize){
            if(tile5.reDrawn){
                this.drawSelected();
                return;
            }
            tileToSwap = "tile5";
        }
        else if (x>=320 && x<=360 && y>=70+ScrabbleBoard.boardSize+10 && y<=70+ScrabbleBoard.boardSize+10+ScrabbleBoard.tileSize){
            if(tile6.reDrawn){
                this.drawSelected();
                return;
            }
            tileToSwap = "tile6";
        }
        else if (x>=370 && x<=410 && y>=70+ScrabbleBoard.boardSize+10 && y<=70+ScrabbleBoard.boardSize+10+ScrabbleBoard.tileSize){
            if(tile7.reDrawn){
                this.drawSelected();
                return;
            }
            tileToSwap = "tile7";
        }
        String letter1 = null;
        if(selectedTile!=null && tileToSwap!=null){
            if (selectedTile.equals("tile1")){letter1=tile1.currentLetter;}
            if (selectedTile.equals("tile2")){letter1=tile2.currentLetter;}
            if (selectedTile.equals("tile3")){letter1=tile3.currentLetter;}
            if (selectedTile.equals("tile4")){letter1=tile4.currentLetter;}
            if (selectedTile.equals("tile5")){letter1=tile5.currentLetter;}
            if (selectedTile.equals("tile6")){letter1=tile6.currentLetter;}
            if (selectedTile.equals("tile7")){letter1=tile7.currentLetter;}
            String letter2=null;
            if (tileToSwap.equals("tile1")){letter2=tile1.currentLetter; tile1.currentLetter=letter1; tile1.drawTile();}
            if (tileToSwap.equals("tile2")){letter2=tile2.currentLetter; tile2.currentLetter=letter1; tile2.drawTile();}
            if (tileToSwap.equals("tile3")){letter2=tile3.currentLetter; tile3.currentLetter=letter1; tile3.drawTile();}
            if (tileToSwap.equals("tile4")){letter2=tile4.currentLetter; tile4.currentLetter=letter1; tile4.drawTile();}
            if (tileToSwap.equals("tile5")){letter2=tile5.currentLetter; tile5.currentLetter=letter1; tile5.drawTile();}
            if (tileToSwap.equals("tile6")){letter2=tile6.currentLetter; tile6.currentLetter=letter1; tile6.drawTile();}
            if (tileToSwap.equals("tile7")){letter2=tile7.currentLetter; tile7.currentLetter=letter1; tile7.drawTile();}

            if (selectedTile.equals("tile1")){tile1.currentLetter=letter2; tile1.drawTile();}
            if (selectedTile.equals("tile2")){tile2.currentLetter=letter2; tile2.drawTile();}
            if (selectedTile.equals("tile3")){tile3.currentLetter=letter2; tile3.drawTile();}
            if (selectedTile.equals("tile4")){tile4.currentLetter=letter2; tile4.drawTile();}
            if (selectedTile.equals("tile5")){tile5.currentLetter=letter2; tile5.drawTile();}
            if (selectedTile.equals("tile6")){tile6.currentLetter=letter2; tile6.drawTile();}
            if (selectedTile.equals("tile7")){tile7.currentLetter=letter2; tile7.drawTile();}
        }
        else if(tileToSwap==null){
            this.drawSelected();
        }
    }

    //takes the y coordinate of the mouse and returns a y grid coordinate
    public double getY(double y){
        int y1 = 0;
        if (y>=left && y<=left+ScrabbleBoard.tileSize*1){
            y = left + ScrabbleBoard.tileSize*0;
            y1=1;}
        if (y>=left+ScrabbleBoard.tileSize*1 && y<=left+ScrabbleBoard.tileSize*2){
            y = left + ScrabbleBoard.tileSize*1;
            y1 = 2;}
        if (y>=left +ScrabbleBoard.tileSize*2 && y<=left+ScrabbleBoard.tileSize*3){
            y = left + ScrabbleBoard.tileSize*2;
            y1 = 3;}
        if (y>=left+ScrabbleBoard.tileSize*3 && y<=left+ScrabbleBoard.tileSize*4){
            y = left + ScrabbleBoard.tileSize*3;
            y1= 4;}
        if (y>=left+ScrabbleBoard.tileSize*4 && y<=left+ScrabbleBoard.tileSize*5){
            y = left + ScrabbleBoard.tileSize*4;
            y1 = 5;}
        if (y>=left+ScrabbleBoard.tileSize*5 && y<=left+ScrabbleBoard.tileSize*6){
            y = left + ScrabbleBoard.tileSize*5;
            y1= 6;}
        if (y>=left+ScrabbleBoard.tileSize*6 && y<=left+ScrabbleBoard.tileSize*7){
            y = left + ScrabbleBoard.tileSize*6;
            y1 = 7;}
        if (y>=left+ScrabbleBoard.tileSize*7 && y<=left+ScrabbleBoard.tileSize*8){
            y = left + ScrabbleBoard.tileSize*7;
            y1 = 8;}
        if (y>=left+ScrabbleBoard.tileSize*8 && y<=left+ScrabbleBoard.tileSize*9){
            y = left + ScrabbleBoard.tileSize*8;
            y1 =9;}
        if (y>=left+ScrabbleBoard.tileSize*9 && y<=left+ScrabbleBoard.tileSize*10){
            y = left + ScrabbleBoard.tileSize*9;
            y1 = 10;}
        if (y>=left+ScrabbleBoard.tileSize*10 && y<=left+ScrabbleBoard.tileSize*11){
            y = left + ScrabbleBoard.tileSize*10;
            y1 = 11;}
        if (y>=left+ScrabbleBoard.tileSize*11 && y<=left+ScrabbleBoard.tileSize*12){
            y = left + ScrabbleBoard.tileSize*11;
            y1 = 12;}
        if (y>=left+ScrabbleBoard.tileSize*12 && y<=left+ScrabbleBoard.tileSize*13){
            y = left + ScrabbleBoard.tileSize*12;
            y1 = 13;}
        if (y>=left+ScrabbleBoard.tileSize*13 && y<=left+ScrabbleBoard.tileSize*14){
            y = left + ScrabbleBoard.tileSize*13;
            y1 = 14;}
        if (y>=left+ScrabbleBoard.tileSize*14 && y<=left+ScrabbleBoard.tileSize*15){
            y = left + ScrabbleBoard.tileSize*14;
            y1 = 15;}
        return y1;
    }
    private Color myBrown = new Color(244,164,96);
    public void drawSelectedTile (){
        String letter = null;
        if (selectedTile.equals("tile1")){letter = tile1.currentLetter;}
        if (selectedTile.equals("tile2")){letter = tile2.currentLetter;}
        if (selectedTile.equals("tile3")){letter = tile3.currentLetter;}
        if (selectedTile.equals("tile4")){letter = tile4.currentLetter;}
        if (selectedTile.equals("tile5")){letter = tile5.currentLetter;}
        if (selectedTile.equals("tile6")){letter = tile6.currentLetter;}
        if (selectedTile.equals("tile7")){letter = tile7.currentLetter;}
        UI.setColor(myBrown);
        UI.fillRect(left+660,top+10,ScrabbleBoard.tileSize,ScrabbleBoard.tileSize);
        UI.setColor(Color.black);
        UI.setFontSize(20);
        if (!letter.equals("blank")){UI.drawString(letter,left+672,top+35);}
        double ls = BoardChecker.letterScore(letter);
        int letterscore = (int) ls;
        String letterScore = String.valueOf(letterscore);
        UI.setFontSize(12);
        UI.drawString(letterScore,left+685,top+45);
    }
    private Color myDarkGreen = new Color (0,102,51);
    public void eraseSelectedTile(){
        UI.eraseRect(left+660,top+10,ScrabbleBoard.tileSize,ScrabbleBoard.tileSize);
        UI.setColor(myDarkGreen);
        UI.fillRect(left+660,top+10,ScrabbleBoard.tileSize,ScrabbleBoard.tileSize);
        UI.drawRect(left+660,top+10,ScrabbleBoard.tileSize,ScrabbleBoard.tileSize);
    }

    public static void main (String [] args){
        ScrabbleGame billy = new ScrabbleGame();
    }
}
