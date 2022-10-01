package helper.struct;

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
