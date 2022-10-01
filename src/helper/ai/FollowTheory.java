package helper.ai;
import helper.matrix.GameBoard;

/**
 *  
 *
 * */
public class FollowTheory extends GameTheory {
    public FollowTheory(GameBoard gameBoard, int emptycell, int markervalueai, int markervalueopp, int markersinarow){
        super(gameBoard,emptycell,markervalueai,markervalueopp,markersinarow);
    }

    @Override
    public int findBestMove(){
        return board.lookForNewPosition(markersInARow);
    }
}
