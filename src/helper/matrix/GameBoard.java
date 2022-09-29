package helper.matrix;
import helper.enums.Direction;
import helper.enums.Token;
import helper.io.IOHandler;
import java.util.Arrays;

import static helper.methods.CommonMethods.getRandomInt;


public class GameBoard extends Matrix{
    Token[] tokens = Token.values();
    int indexTaken;
    int[] moveToIndex;
    public GameBoard(int size){
        super(size);
        moveToIndex = new int[8];
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
        return sum == markersInARow;
    }

    public int searchDirection(int row, int col, int markerValue, int sum, Direction dir,int markersInARow){
        if(!validRowCol(row,col) || sum == markersInARow || getValue(row,col)!=markerValue)return sum;
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

    public int lookAtEachDirection(int index){
        int newIndex,row,col,cnt = 0;
        row = getRowFromIndex(index);
        col = getColFromIndex(index);
        if(validIndex(newIndex = getIndex(row-1,col)))if(m[newIndex] == 0)moveToIndex[cnt++] = newIndex;    // NORTH
        if(validIndex(newIndex = getIndex(row+1,col)))if(m[newIndex] == 0)moveToIndex[cnt++] =  newIndex;   // SOUTH
        if(validIndex(newIndex = getIndex(row,col+1)))if(m[newIndex] == 0)moveToIndex[cnt++] =  newIndex;   // EAST
        if(validIndex(newIndex = getIndex(row,col-1)))if(m[newIndex] == 0)moveToIndex[cnt++] =  newIndex;   // WEST
        if(validIndex(newIndex = getIndex(row-1,col+1)))if(m[newIndex] == 0)moveToIndex[cnt++] =  newIndex; // NORTH EAST
        if(validIndex(newIndex = getIndex(row-1,col-1)))if(m[newIndex] == 0)moveToIndex[cnt++] =  newIndex; // NORTH WEST
        if(validIndex(newIndex = getIndex(row+1,col+1)))if(m[newIndex] == 0)moveToIndex[cnt++] =  newIndex; // SOUTH EAST
        if(validIndex(newIndex = getIndex(row+1,col-1)))if(m[newIndex] == 0)moveToIndex[cnt++] =  newIndex; // SOUTH WEST
        return cnt;
    }

    public int lookForNewPosition(int index){
        int newIndex;
        while((newIndex=lookAtEachDirection(index)) == 0){
            ++index;
            index%=size;
        }
        return moveToIndex[getRandomInt(newIndex)];
    }

    public int getFreeIndex(){
        int index;
        while((!freeIndex(index=getRandomInt(size))));
        return index;
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
