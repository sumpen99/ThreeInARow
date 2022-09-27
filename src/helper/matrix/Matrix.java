package helper.matrix;
import  helper.enums.Direction;
import helper.interfaces.IMatrix;

public abstract class Matrix implements IMatrix {
    private final int keyValue = 3;
    public int size,rows,columns;
    public int[] m;
    public Matrix(int type){
        rows = type;
        columns = type;
        size = type*type;
        m = new int[size];
    }

    public int getValue(int row,int col){return m[getIndex(row,col)];}

    public void setValue(int row,int col,int value){
        m[getIndex(row,col)] = value;
    }

    public int getIndex(int row,int col){
        if(!validRowCol(row,col))return -1;
        return (row*columns)+col;
    }

    public boolean validRowCol(int row,int col){
        return (row >= 0 && row < rows) && (col >= 0 && col < columns);
    }

    public boolean validIndex(int index){
        return index >= 0 && index < size;
    }

    public boolean searchMatrix(int row,int col,int value){
        int sum;
        sum = searchDirection(row,col,value,0,Direction.NORTH);
        if(sum == keyValue)return true;
        sum = searchDirection(row,col,value,0,Direction.SOUTH);
        if(sum == keyValue)return true;
        sum = searchDirection(row,col,value,0,Direction.EAST);
        if(sum == keyValue)return true;
        sum = searchDirection(row,col,value,0,Direction.WEST);
        if(sum == keyValue)return true;
        sum = searchDirection(row,col,value,0,Direction.NORTH_EAST);
        if(sum == keyValue)return true;
        sum = searchDirection(row,col,value,0,Direction.NORTH_WEST);
        if(sum == keyValue)return true;
        sum = searchDirection(row,col,value,0,Direction.SOUTH_EAST);
        if(sum == keyValue)return true;
        sum = searchDirection(row,col,value,0,Direction.SOUTH_WEST);
        return sum == keyValue;
    }

    public int searchDirection(int row, int col, int searchNum, int sum, Direction dir){
        if(!validRowCol(row,col) || sum == keyValue || getValue(row,col)!=searchNum)return sum;
        if(dir == Direction.NORTH)sum = searchDirection(row-1,col,searchNum,sum+1,dir); // NORTH
        else if(dir == Direction.SOUTH)sum = searchDirection(row+1,col,searchNum,sum+1,dir); // SOUTH
        else if(dir == Direction.EAST)sum = searchDirection(row,col+1,searchNum,sum+1,dir); // EAST
        else if(dir == Direction.WEST)sum = searchDirection(row,col-1,searchNum,sum+1,dir); // WEST
        else if(dir == Direction.NORTH_EAST)sum = searchDirection(row+1,col+1,searchNum,sum+1,dir); // NORTH EAST
        else if(dir == Direction.NORTH_WEST)sum = searchDirection(row-1,col-1,searchNum,sum+1,dir); // NORTH WEST
        else if(dir == Direction.SOUTH_EAST)sum = searchDirection(row-1,col+1,searchNum,sum+1,dir); // SOUTH EAST
        else if(dir == Direction.SOUTH_WEST)sum = searchDirection(row-1,col-1,searchNum,sum+1,dir); // SOUTH WEST
        return sum;
    }

    public boolean findPatter(int value){
        int row = 0,col;
        for(;row<rows;row++){
            for(col = 0;col < columns;col++){
                if(m[getIndex(row,col)] == value){
                    if(searchMatrix(row,col,value))return true;
                }
            }
        }
        return false;
    }

}