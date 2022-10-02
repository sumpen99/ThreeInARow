package helper.struct;

/**
 * Struct used by GameMode classes to validate and get a new pos
 * to put a marker on the board
 * */
public class BoardPosition {
    public int row,col;
    public boolean validMove;

    public void setValue(int rowValue,int colValue){
        row = rowValue;
        col = colValue;
    }

    public void setValue(int rowValue,int colValue,boolean validpos){
        row = rowValue;
        col = colValue;
        validMove = validpos;
    }
}
