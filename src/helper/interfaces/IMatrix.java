package helper.interfaces;

public interface IMatrix {
    int getValue(int row,int col);
    void setValue(int row,int col,int value);
    void setValue(int index,int value);
    int getIndex(int row,int col);
    void drawToScreen();
    boolean validRowCol(int row,int col);
    boolean validIndex(int index);
    boolean freeIndex(int index);
    int getFreeIndex();
    boolean hasSpace();
}
