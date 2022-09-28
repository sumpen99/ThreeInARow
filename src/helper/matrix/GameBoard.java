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

    public boolean searchMatrix(int row,int col,int pattern,int keyValue){
        int sum;
        sum = searchDirection(row,col,pattern,0, Direction.NORTH,keyValue);
        if(sum == keyValue)return true;
        sum = searchDirection(row,col,pattern,0,Direction.SOUTH,keyValue);
        if(sum == keyValue)return true;
        sum = searchDirection(row,col,pattern,0,Direction.EAST,keyValue);
        if(sum == keyValue)return true;
        sum = searchDirection(row,col,pattern,0,Direction.WEST,keyValue);
        if(sum == keyValue)return true;
        sum = searchDirection(row,col,pattern,0,Direction.NORTH_EAST,keyValue);
        if(sum == keyValue)return true;
        sum = searchDirection(row,col,pattern,0,Direction.NORTH_WEST,keyValue);
        if(sum == keyValue)return true;
        sum = searchDirection(row,col,pattern,0,Direction.SOUTH_EAST,keyValue);
        if(sum == keyValue)return true;
        sum = searchDirection(row,col,pattern,0,Direction.SOUTH_WEST,keyValue);
        return sum == keyValue;
    }

    public int searchDirection(int row, int col, int searchNum, int sum, Direction dir,int keyValue){
        if(!validRowCol(row,col) || sum == keyValue || getValue(row,col)!=searchNum)return sum;
        if(dir == Direction.NORTH)sum = searchDirection(row-1,col,searchNum,sum+1,dir,keyValue);             // NORTH
        else if(dir == Direction.SOUTH)sum = searchDirection(row+1,col,searchNum,sum+1,dir,keyValue);        // SOUTH
        else if(dir == Direction.EAST)sum = searchDirection(row,col+1,searchNum,sum+1,dir,keyValue);         // EAST
        else if(dir == Direction.WEST)sum = searchDirection(row,col-1,searchNum,sum+1,dir,keyValue);         // WEST
        else if(dir == Direction.NORTH_EAST)sum = searchDirection(row-1,col+1,searchNum,sum+1,dir,keyValue); // NORTH EAST
        else if(dir == Direction.NORTH_WEST)sum = searchDirection(row-1,col-1,searchNum,sum+1,dir,keyValue); // NORTH WEST
        else if(dir == Direction.SOUTH_EAST)sum = searchDirection(row+1,col+1,searchNum,sum+1,dir,keyValue); // SOUTH EAST
        else if(dir == Direction.SOUTH_WEST)sum = searchDirection(row+1,col-1,searchNum,sum+1,dir,keyValue); // SOUTH WEST
        return sum;
    }

    public boolean findWinningPatter(int pattern,int keyValue){
        int row = 0,col;
        for(;row<rows;row++){
            for(col = 0;col < columns;col++){
                if(m[getIndex(row,col)] == pattern){
                    if(searchMatrix(row,col,pattern,keyValue))return true;
                }
            }
        }
        return false;
    }

    public int lookAtEachDirection(int index){
        int newIndex,row,col,cnt = 0;
        row = getRowFromIndex(index);
        col = getColFromIndex(index);
        if(validIndex(newIndex = getIndex(row-1,col)))if(m[newIndex] == 0)moveToIndex[cnt++] = newIndex;      // NORTH
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

    public boolean hasSpace(){
        return indexTaken < size;
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
