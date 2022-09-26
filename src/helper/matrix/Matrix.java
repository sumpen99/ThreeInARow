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

    public int getValue(int row,int col){return m[getIndex(row,col)];}

    public void setValue(int row,int col,int value){
        m[getIndex(row,col)] = value;
    }

    public int getIndex(int row,int col){
        return (row*columns)+col;
    }

}