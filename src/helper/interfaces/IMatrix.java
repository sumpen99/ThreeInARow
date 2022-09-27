package helper.interfaces;

import helper.enums.Direction;

public interface IMatrix {
    int getValue(int row,int col);
    void setValue(int row,int col,int value);
    void setValue(int index,int value);
    int getIndex(int row,int col);
    void drawToScreen();
    void resetMatrix();
    int searchDirection(int row, int col, int searchNum, int sum, Direction dir);
    boolean searchMatrix(int row,int col,int searchNum);
    boolean findPatter(int value);
    boolean validRowCol(int row,int col);
    boolean validIndex(int index);
    boolean freeIndex(int index);
    int getFreeIndex();
    boolean hasSpace();
}
