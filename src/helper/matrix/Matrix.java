package helper.matrix;
import helper.interfaces.IMatrix;

import java.util.Arrays;

/**
 * Abstract class Matrix
 *  */
public abstract class Matrix implements IMatrix {
    public int size,rows,columns,indexTaken;
    public int[] m;
    public Matrix(int type){
        rows = type;
        columns = type;
        size = type*type;
        m = new int[size];
    }

    /**
     * returns true if any value is set to anything other the 0
     * false otherwise
     * */
    public boolean hasFreeSpace(){
        int cnt = 0;
        while(cnt<size)if(m[cnt++] == 0)return true;
        return false;
    }

    /**
     * returns true index of array equals 0
     * false otherwise
     * */
    public boolean freeIndex(int index){
        return m[index] == 0;
    }

    /**
     * returns the column corresponding to index supplied
     * */
    public int getColFromIndex(int index){
        return index%columns;
    }

    /**
     * returns the row corresponding to index supplied
     * */
    public int getRowFromIndex(int index){
        return index/columns;
    }

    /**
     * returns the value of the index corresponding to row/col
     * */
    public int getValue(int row,int col){return m[getIndex(row,col)];}

    /**
     * sets the value of the index corresponding to row/col
     * */
    public void setValue(int row,int col,int value){
        m[getIndex(row,col)] = value;
    }

    /**
     * sets the value of the index supplied
     * */
    public void setValue(int index,int value){
        m[index] = value;
        indexTaken++;
    }

    /**
     * resets the matrix to 0 on all indexes
     * */
    public void resetMatrix(){
        Arrays.fill(m,0);
        indexTaken = 0;
    }

    /**
     * returns the index of the matrix based on row col
     * */
    public int getIndex(int row,int col){
        if(!validRowCol(row,col))return -1;
        return (row*columns)+col;
    }

    /**
     * returns true if 0 <= row <= rows and 0 <= col <= columns
     * false otherwise
     * */
    public boolean validRowCol(int row,int col){
        return (row >= 0 && row < rows) && (col >= 0 && col < columns);
    }

    /**
     * returns true if 0 <= index < size of matrix
     * false otherwise
     * */
    public boolean validIndex(int index){
        return index >= 0 && index < size;
    }

}