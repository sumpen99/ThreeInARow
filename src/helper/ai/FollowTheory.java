package helper.ai;
import helper.io.IOHandler;
import helper.matrix.GameBoard;
import helper.struct.MinMaxPos;

/**
 * NOT PARTICULARY SMART BUT ATLEAST IT PROVIDES THE POSSIBILITY
 * FOR PLAYER VS COMPUTER WITH PLAYERS CHOICE OF GRIDSIZE AND MARKERS-IN-A-ROW
 * */
public class FollowTheory extends GameTheory {
    MinMaxPos bestPos;
    public FollowTheory(GameBoard gameBoard, int emptycell, int markervalueai, int markervalueopp, int markersinarow,String cmpName){
        super(gameBoard,emptycell,markervalueai,markervalueopp,markersinarow,cmpName);
        bestPos = new MinMaxPos();
    }

    @Override
    public int findBestMove(){
        bestPos.reset();
        board.lookForNewPosition(markersInARow,markerValueAI,0,bestPos);
        board.lookForNewPosition(markersInARow,markerValueOpp,bestPos.valueHigh,bestPos);
        return bestPos.index;
    }
}
