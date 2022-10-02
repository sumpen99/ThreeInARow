package helper.ai;
import helper.interfaces.IGameTheory;
import helper.interfaces.IThreading;
import helper.io.IOHandler;
import helper.matrix.GameBoard;
import helper.struct.BoardPosition;
import helper.threading.ThreadHandler;

/**
 * Abstract class to steer the computer. Extended by MiniMaxTheory and FollowTheory
 * Implements Ithreading which runs findbestmove in a seperate thread
 * */
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

    /**
     * Incase the algorithm implemented runs slow
     * we do it in a second thread whilst printing thinking
     * process to the output. If it finish fast the function
     * simulates some thinking
     * */
    public void runAlgorithm(){
        ThreadHandler.executeNewThread(this);
        startThinkingProcess();
        try{
            ThreadHandler.t1.join();
        }
        catch(InterruptedException err){
            IOHandler.logToFile(err.getMessage());
        }
    }

    /**
     * Printing dots to the terminal until the algorithm is done
     * or until we count to 15 (* thread.sleep(100))
     * */
    void startThinkingProcess(){
        int cnt = 0;
        IOHandler.askComputerForValue(computerName);
        while(runAiLoop || cnt++ < 15){IOHandler.printDot();}
        IOHandler.printString("\n");
    }

    /**
     *  the function executed from threadhandler and
     *  by classes implementing IThreading
     * */
    public void heavyDuty(){
        newIndex = findBestMove();
    }

    /**
     * sets one condition inside startThinkingProcess to true
     * */
    public void startLoop(){runAiLoop = true;}

    /**
     * sets one condition inside startThinkingProcess to false
     * if the algorithm runs fast the loop keep going until we hit 15 counts
     * */
    public void closeLoop(){runAiLoop = false;}

    /**
     * returens the index findBestMove got for us
     * */
    public int getNewIndex(){return newIndex;}

}
