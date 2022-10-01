package helper.matrix;
import helper.enums.Direction;
import helper.enums.Token;
import helper.io.IOHandler;
import helper.struct.BoardPosition;
import java.util.Arrays;

/**
 *
 *  THE NEED FOR A ABSTRACT CLASS MATRIX IS NOT NEEDED
 *  BUT YOU GET RID OF ALOT OF FUNCTIONS AND YOU NEVER NOW
 *  IN WICH DIRECTION A PROGRAM FOLLOWS
 *
 * */
public class GameBoard extends Matrix{
    Token[] tokens = Token.values();
    int indexTaken;
    int[] moveToIndex;
    BoardPosition newPos;
    public GameBoard(int size){
        super(size);
        moveToIndex = new int[8];
        newPos = new BoardPosition();
    }

    public void drawToScreen(){
        IOHandler.printCurrentBoard(this);
    }

    public char getChar(int y,int x){
        int v = getValue(y,x);
        return tokens[v].getChar();
    }

    public boolean hasSpace(){
        return indexTaken < size;
    }

    /**
     * SEARCH-METHOD TO FIND A END-GAME
     * CALLS searchDirection WICH IS A RECURSIVE FUNCTION THAT KEEPS GOING
     * UNTIL A NUMBER OF DIFFERENT CRITERIA IS MET
     * IF WE FIND THE AMOUNT OF MARKERS-IN-A-ROW WE ARE LOOKING FOR
     * IT RETURNS TRUE. ELSE FALSE
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
     * INDEED IT LOOKS ALOT LIKE searchMatrix AND WITH SOME MODIFICATIONS YOU COULD PROBABLY
     * REMOVE THIS ONE. BUT FOR NOW ITS EASIER TO HAVE JUST BOTH
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
     * RECURSIVE SEARCH METHOD TO LOOK IN THE DIRECTION SPECIFIED
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
     * STARTING POINT FOR MINIMAXTHEORY
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
     * STARTING POINT FOR FOLLOWTHEORY
     * */
    public int lookForNewPosition(int markersInARow){
        int markersToWin = markersInARow-1,marker;
        while(markersToWin >= 1){
            for(int row = 0;row<rows;row++){
                for(int col = 0;col < columns;col++){
                    if(!freeIndex(getIndex(row,col))){
                        marker = getValue(row,col);
                        if(searchValidMoves(row,col,marker,markersToWin)){
                            return getIndex(newPos.row,newPos.col);
                        }
                    }
                }
            }
            markersToWin--;
        }
        return fallbackOption();
    }

    /**
     * WE SHOULD NEVER HIT THIS ONE
     * BUT IF WE DO, THIS KEEPS THE PROGRAM FROM CRASHING
     * */
    public int fallbackOption(){
        for(int row = 0;row<rows;row++){
            for(int col = 0;col < columns;col++){
                if(freeIndex(getIndex(row,col)))return getIndex(row,col);
            }
        }
        return 0;
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
