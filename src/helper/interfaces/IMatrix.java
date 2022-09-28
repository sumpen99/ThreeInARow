package helper.interfaces;

import helper.enums.Direction;

public interface IMatrix {
    int getValue(int row,int col);
    void setValue(int row,int col,int value);
    void setValue(int index,int value);
    int getIndex(int row,int col);
    void resetMatrix();
    boolean validRowCol(int row,int col);
    boolean validIndex(int index);
    boolean freeIndex(int index);
    int getColFromIndex(int index);
    int getRowFromIndex(int index);

}
