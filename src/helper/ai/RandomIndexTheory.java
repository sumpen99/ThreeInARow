package helper.ai;
import helper.matrix.GameBoard;


public class RandomIndexTheory extends GameTheory {
    int lastIndex;
    public RandomIndexTheory(GameBoard gameBoard){
        super(gameBoard);
        lastIndex = -1;
    }

    @Override
    public void reset(){
        lastIndex = -1;
    }

    @Override
    public int findBestMove(){
        int index;
        if(lastIndex == -1){index=board.getFreeIndex();}
        else index = board.lookForNewPosition(lastIndex);
        lastIndex = index;
        return index;
    }
}
