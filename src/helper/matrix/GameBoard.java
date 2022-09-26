package helper.matrix;

import helper.enums.Token;
import helper.io.IOHandler;

public class GameBoard extends Matrix{
    Token[] tokens = Token.values();
    public GameBoard(int size){
        super(size);
    }

    public void drawToScreen(){
        IOHandler.printCurrentBoard(this);
    }

    public char getChar(int y,int x){
        int v = getValue(y,x);
        return tokens[v].getChar();
    }
}
