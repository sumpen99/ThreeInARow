package helper.ai;
import helper.matrix.GameBoard;
import helper.struct.MinMaxPos;

/**
 * To support player vs computer on grids > 3*3 and markers in a row > 3 I choose
 * this implementation of an "ai". The only smart move it takes is to follow
 * the path with most markers in a row/line/diagonal with a free space next to it.
 * Hence the name FollowTheory...
 * */
public class FollowTheory extends GameTheory {
    MinMaxPos bestPos;
    public FollowTheory(GameBoard gameBoard, int emptycell, int markervalueai, int markervalueopp, int markersinarow,String cmpName){
        super(gameBoard,emptycell,markervalueai,markervalueopp,markersinarow,cmpName);
        bestPos = new MinMaxPos();
    }

    /**
     * Starts of by searching the top result for the computer. if the user
     * cant bet that value it will be the computers next move, else it follows user path
     * if no marker is on the board it places it in the middle
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
