package helper.matrix;
import helper.interfaces.IMatrix;

public abstract class Matrix implements IMatrix {
    public int size,rows,columns;
    public int[] m;
    public Matrix(int type){
        rows = type;
        columns = type;
        size = type*type;
        m = new int[size];
    }

    public boolean freeIndex(int index){
        return m[index] == 0;
    }

    public int getColFromIndex(int index){
        return index%columns;
    }

    public int getRowFromIndex(int index){
        return index/columns;
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

}