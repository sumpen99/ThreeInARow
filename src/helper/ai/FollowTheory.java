package helper.ai;
import helper.matrix.GameBoard;

/**
 *
 *
 * */
public class FollowTheory extends GameTheory {
    public FollowTheory(GameBoard gameBoard, int emptycell, int markervalueai, int markervalueopp, int markersinarow,String cmpName){
        super(gameBoard,emptycell,markervalueai,markervalueopp,markersinarow,cmpName);
    }

    @Override
    public int findBestMove(){
        return board.lookForNewPosition(markersInARow);
    }
}
