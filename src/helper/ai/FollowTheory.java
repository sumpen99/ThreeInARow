package helper.ai;
import helper.matrix.GameBoard;
import helper.struct.MinMaxPos;

/**
 * NOT PARTICULARY SMART BUT ATLEAST IT PROVIDES THE POSSIBILITY
 * FOR PLAYER VS COMPUTER WITH PLAYERS CHOICE OF GRIDSIZE AND MARKERS-IN-A-ROW
 * It will win if the user is even dumber
 * */
public class FollowTheory extends GameTheory {
    MinMaxPos bestPos;
    public FollowTheory(GameBoard gameBoard, int emptycell, int markervalueai, int markervalueopp, int markersinarow,String cmpName){
        super(gameBoard,emptycell,markervalueai,markervalueopp,markersinarow,cmpName);
        bestPos = new MinMaxPos();
    }

    /**
     * Starts of by searching the top result for the computer. if the user
     * cant bet that it will choose the computers next move. Else it will follow user
     * */
    @Override
    public int findBestMove(){
        bestPos.reset();
        if(board.getCurrentMarkersCount() == 0){return board.getIndex(board.rows/2,board.columns/2);}
        board.lookForNewPosition(markersInARow,markerValueAI,0,bestPos);
        board.lookForNewPosition(markersInARow,markerValueOpp,bestPos.valueHigh,bestPos);
        return bestPos.index;
    }
}
