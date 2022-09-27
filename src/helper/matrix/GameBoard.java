package helper.matrix;
import helper.enums.Token;
import helper.io.IOHandler;

import static helper.methods.CommonMethods.getRandomInt;

public class GameBoard extends Matrix{
    Token[] tokens = Token.values();
    int indexTaken;
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

    @Override
    public void resetMatrix(){
        for(int i = 0;i < size;i++){
            m[i] = 0;
        }
    }

    @Override
    public boolean hasSpace(){
        return indexTaken < size;
    }

    @Override
    public void setValue(int row,int col,int value){
        m[getIndex(row,col)] = value;
        indexTaken++;
    }

    @Override
    public void setValue(int index,int value){
        m[index] = value;
        indexTaken++;
    }

    @Override
    public boolean freeIndex(int index){
        return m[index] == 0;
    }

    @Override
    public int getFreeIndex(){
        int index;
        if(!hasSpace())return -1;
        while((!freeIndex(index=getRandomInt(size))));
        return index;
    }
}
