package helper.matrix;
import helper.enums.Direction;
import helper.enums.Token;
import helper.io.IOHandler;
import helper.struct.BoardPosition;
import helper.struct.MinMaxPos;
import java.util.Arrays;

/**
 * Class whose responsibility is too keeping track of the current state of
 * the game in terms of markers on the board and whos winning or not
 * extends matrix
 * */
public class GameBoard extends Matrix{
    Token[] tokens = Token.values();
    int indexTaken;
    BoardPosition newPos;
    public GameBoard(int size){
        super(size);
        newPos = new BoardPosition();
    }

    /**
     * Prints current state of the matrix to the console
     * */
    public void drawToScreen(){
        IOHandler.printCurrentBoard(this);
    }

    /**
     * Returns the char value corresponding to the value put into the matrix (0 = " ", 1 = O, 2 = X)
     * */
    public char getChar(int y,int x){
        int v = getValue(y,x);
        return tokens[v].getChar();
    }

    /**
     * If the matrix has free space,
     * returns true or false
     * */
    public boolean hasSpace(){
        return indexTaken < size;
    }

    /**
     * Search method to find a end game
     * Calls searchDirection wich is a recursive function thet keeps going
     * until a number of different criteria is met.
     * If we find the amount of markers in a row we are looking for it
     * return true. else false
     * */
    public boolean searchMatrix(int row,int col,int markerValue,int markersInARow){
        int sum;
        sum = searchDirection(row,col,markerValue,0, Direction.NORTH,markersInARow);
        if(sum == markersInARow)return true;
        sum = searchDirection(row,col,markerValue,0,Direction.SOUTH,markersInARow);
        if(sum == markersInARow)return true;
        sum = searchDirection(row,col,markerValue,0,Direction.EAST,markersInARow);
        if(sum == markersInARow)return true;
        sum = searchDirection(row,col,markerValue,0,Direction.WEST,markersInARow);
        if(sum == markersInARow)return true;
        sum = searchDirection(row,col,markerValue,0,Direction.NORTH_EAST,markersInARow);
        if(sum == markersInARow)return true;
        sum = searchDirection(row,col,markerValue,0,Direction.NORTH_WEST,markersInARow);
        if(sum == markersInARow)return true;
        sum = searchDirection(row,col,markerValue,0,Direction.SOUTH_EAST,markersInARow);
        if(sum == markersInARow)return true;
        sum = searchDirection(row,col,markerValue,0,Direction.SOUTH_WEST,markersInARow);
        if(sum == markersInARow)return true;
        return false;
    }


    /**
     * Indeed it looks alot like searchMatrix and with some modifications you could probably
     * remove this one. But for now its easier to have both
     * returns true if found else false
     * */
    public boolean searchValidMoves(int row,int col,int markerValue,int markersInARow){
        int sum;
        sum = searchDirection(row,col,markerValue,0, Direction.NORTH,markersInARow);
        if((sum == markersInARow && newPos.validMove) && freeIndex(getIndex(newPos.row,newPos.col)))return true;
        sum = searchDirection(row,col,markerValue,0,Direction.SOUTH,markersInARow);
        if((sum == markersInARow && newPos.validMove) && freeIndex(getIndex(newPos.row,newPos.col)))return true;
        sum = searchDirection(row,col,markerValue,0,Direction.EAST,markersInARow);
        if((sum == markersInARow && newPos.validMove) && freeIndex(getIndex(newPos.row,newPos.col)))return true;
        sum = searchDirection(row,col,markerValue,0,Direction.WEST,markersInARow);
        if((sum == markersInARow && newPos.validMove) && freeIndex(getIndex(newPos.row,newPos.col)))return true;
        sum = searchDirection(row,col,markerValue,0,Direction.NORTH_EAST,markersInARow);
        if((sum == markersInARow && newPos.validMove) && freeIndex(getIndex(newPos.row,newPos.col)))return true;
        sum = searchDirection(row,col,markerValue,0,Direction.NORTH_WEST,markersInARow);
        if((sum == markersInARow && newPos.validMove) && freeIndex(getIndex(newPos.row,newPos.col)))return true;
        sum = searchDirection(row,col,markerValue,0,Direction.SOUTH_EAST,markersInARow);
        if((sum == markersInARow && newPos.validMove) && freeIndex(getIndex(newPos.row,newPos.col)))return true;
        sum = searchDirection(row,col,markerValue,0,Direction.SOUTH_WEST,markersInARow);
        if((sum == markersInARow && newPos.validMove) && freeIndex(getIndex(newPos.row,newPos.col)))return true;
        return false;
    }

    /**
     * Recursive search method to look in the direction specified
     * */
    public int searchDirection(int row, int col, int markerValue, int sum, Direction dir,int markersInARow){
        newPos.setValue(row,col,validRowCol(row,col));
        if(!newPos.validMove || sum == markersInARow || getValue(row,col)!=markerValue)return sum;
        if(dir == Direction.NORTH)sum = searchDirection(row-1,col,markerValue,sum+1,dir,markersInARow);             // NORTH
        else if(dir == Direction.SOUTH)sum = searchDirection(row+1,col,markerValue,sum+1,dir,markersInARow);        // SOUTH
        else if(dir == Direction.EAST)sum = searchDirection(row,col+1,markerValue,sum+1,dir,markersInARow);         // EAST
        else if(dir == Direction.WEST)sum = searchDirection(row,col-1,markerValue,sum+1,dir,markersInARow);         // WEST
        else if(dir == Direction.NORTH_EAST)sum = searchDirection(row-1,col+1,markerValue,sum+1,dir,markersInARow); // NORTH EAST
        else if(dir == Direction.NORTH_WEST)sum = searchDirection(row-1,col-1,markerValue,sum+1,dir,markersInARow); // NORTH WEST
        else if(dir == Direction.SOUTH_EAST)sum = searchDirection(row+1,col+1,markerValue,sum+1,dir,markersInARow); // SOUTH EAST
        else if(dir == Direction.SOUTH_WEST)sum = searchDirection(row+1,col-1,markerValue,sum+1,dir,markersInARow); // SOUTH WEST
        return sum;
    }

    /**
     * Starting point for minimaxtheory
     * returns true if found else false
     * */
    public boolean findWinningPatter(int markerValue,int markersInARow){
        int row = 0,col;
        for(;row<rows;row++){
            for(col = 0;col < columns;col++){
                if(m[getIndex(row,col)] == markerValue){
                    if(searchMatrix(row,col,markerValue,markersInARow))return true;
                }
            }
        }
        return false;
    }

    /**
     * Starting point for the followtheory algorithm
     * Its simplistic and searches for the pattern with
     * most markers in a row/line/diagonal. If player scores higher
     * then computer it follows player otherwise it will extend
     * self
     * */
    public void lookForNewPosition(int markersInARow, int markerToCheck, int bestMove, MinMaxPos bestPos){
        int markersToWin = markersInARow-1,marker;
        outer: while(markersToWin >= 1 && markersToWin > bestMove){
            for(int row = 0;row<rows;row++){
                for(int col = 0;col < columns;col++){
                    marker = getValue(row,col);
                    if(marker == markerToCheck){
                        if(searchValidMoves(row,col,marker,markersToWin)){
                            bestPos.index = getIndex(newPos.row,newPos.col);
                            bestPos.valueHigh = markersToWin;
                            break outer;
                        }
                    }
                }
            }
            markersToWin--;
        }
    }

    /**
     * Returns count of current markers put on gameboard
     * */
    public int getCurrentMarkersCount(){
        return indexTaken;
    }

    @Override
    public void resetMatrix(){
        Arrays.fill(m,0);
        indexTaken = 0;
    }

    @Override
    public void setValue(int index,int value){
        m[index] = value;
        indexTaken++;
    }

}
