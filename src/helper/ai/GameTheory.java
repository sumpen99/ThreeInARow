package helper.ai;
import helper.interfaces.IGameTheory;
import helper.interfaces.IThreading;
import helper.io.IOHandler;
import helper.matrix.GameBoard;
import helper.struct.BoardPosition;
import helper.threading.ThreadHandler;

public abstract class GameTheory implements IGameTheory, IThreading {
    GameBoard board;
    BoardPosition bestPos;
    int emptyCell,markerValueAI,markerValueOpp,markersInARow,newIndex;
    String computerName;
    boolean runAiLoop;
    public GameTheory(GameBoard gameBoard,int emptycell, int markervalueai, int markervalueopp, int markersinarow,String cmpName){
        board = gameBoard;
        bestPos = new BoardPosition();
        emptyCell = emptycell;
        markerValueAI = markervalueai;
        markerValueOpp = markervalueopp;
        markersInARow = markersinarow;
        computerName = cmpName;
    }

    public void runAlgorithm(){
        int cnt = 0;
        IOHandler.askComputerForValue(computerName);
        ThreadHandler.executeNewThread(this);
        while(runAiLoop || cnt++ < 15){IOHandler.printDot();}
        try{ThreadHandler.t1.join();}
        catch(InterruptedException err){IOHandler.logToFile(err.getMessage());}
        IOHandler.printString("\n");
    }

    public void heavyDuty(){newIndex = findBestMove();}

    public void startLoop(){runAiLoop = true;}

    public void closeLoop(){runAiLoop = false;}

    public int getNewIndex(){return newIndex;}

}
