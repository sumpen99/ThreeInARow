package helper.io;
import helper.matrix.GameBoard;
import helper.player.GamePlayer;
import helper.struct.GameInfo;
import helper.struct.MinMaxPos;
import helper.struct.SMDateTime;
import java.io.*;
import java.util.Scanner;
import static helper.methods.CommonMethods.evaluateInput;

/**
 *
 * All input/output is handled by this class
 * clear screen function does not work in the terminal of ide
 *
 * */
public class IOHandler {
    static IOHandler self;
    static boolean isSet;
    Scanner scannerIn;

    public IOHandler(){
        assert !IOHandler.isSet :"IOHandler is already set!";
        IOHandler.setInstance();
    }

    private static void setInstance(){
        IOHandler.isSet = true;
    }

    /**
     * This is the only reason we are setting a Global Instance
     * This way we can use it everywhere but we only haft to
     * initialize one instance of the Scanner class
     * I think thats pretty neat but maybe its unnecessary
     * */
    public static void initIOHandler(){
        if(self == null){
            self = new IOHandler();
            self.scannerIn = new Scanner(System.in);
        }
    }

    public static void removeFilesFromFolder(String folder){
        File dir = new File(folder);
        for(File file: dir.listFiles()){
            if(file == null)printString("File is null Error");
            else if(!file.delete())printString("Unable To Remove File %s".formatted(file.toString()));
        }
    }

    public static void removeFile(String fileName){
        File file = new File(fileName);
        if(!file.delete()){printString("Unable To Remove File %s".formatted(file.toString()));}

    }

    /**
     * used to log errors inside ./resources/files/log/error/error.log
     * */
    public static void logToFile(String msg){
        PrintWriter writer;
        String out = "%s -> %s".formatted(SMDateTime.getDateTime(),msg);
        try{
            writer = new PrintWriter(new BufferedWriter(new FileWriter("./resources/files/log/error/error.log", true)));
            writer.println(out);
            writer.close();

        }
        catch(IOException err){
            printString(err.getMessage());
        }
    }

    /**
     * used for testing
     * */
    public static void testPathToLogFile(){
        logToFile("Start Of Program");
    }

    /**
     * I do realize this is not needed but i like to have
     * different print methods for int/string/float/boolean etc
     * If you want to do something different with a int before printing you can just look this method
     * up and dont infect other printCalls
     * */
    public static void printInt(int num){
        System.out.printf("%d", num);
    }

    public static void printBoolean(boolean b){
        System.out.printf("%b",b);
    }

    public static void printString(String str){
        System.out.printf("%s",str);
    }

    public static void printChar(char c){
        System.out.printf("%c",c);
    }

    public static void printFloat(float num){
        System.out.printf("%f", num);
    }

    public static void clearScreen(){
        /*try{
            if(runningOS.contains("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else{
                Runtime.getRuntime().exec("clear");
            }

        }
        catch(Exception err){
            logToFile(err.getMessage());
        }*/
    }

    /**
     * prints result of last game
     * */
    public static void printGameInfo(GameInfo gameInfo){
        if(gameInfo.winner){
            printString("Game Over And We Have A Winner  :: %s\n".formatted(gameInfo.lastWinner.name));
        }
        else printString("Game Over And We Have A Draw\n");
        printString("Running Time For Last Game      :: %d sec\n".formatted((int)gameInfo.runningTime));
        printString("Games Played For This Session   :: %d\n".formatted(gameInfo.gamesPlayed));
        printString("Standings:\n%s :: %d\n%s :: %d\n\n".formatted(gameInfo.players[0].name,gameInfo.players[0].winStreak,gameInfo.players[1].name,gameInfo.players[1].winStreak));


    }

    /**
     * prints gameMenu
     * */
    public static String printGameMenu(){
        System.out.println("Welcome for A Game Of Three In A Row!");
        System.out.println("Enter (1) SinglePlayer (2) MultiPlayer or (q) Exit");
        System.out.print("Enter: ");
        return self.scannerIn.nextLine();
    }

    /**
     * Welcomes player
     * */
    public static void welcomePlayers(GameInfo gameInfo){
        System.out.println("Our Contestants Are:");
        System.out.println(gameInfo.players[0].name);
        System.out.println(gameInfo.players[1].name);
        System.out.println("(If yout want to end game type quit instead of new pos)");
        printString("\n");
    }

    /**
     * Asks for player name and return a string
     * If its not valid we come back here quickly
     * */
    public static String askForPlayerName(int index){
        System.out.printf("Enter Name For Player %d%n", index);
        System.out.print("Enter: ");
        return self.scannerIn.nextLine();
    }

    /**
     * Asks for new game
     * */
    public static char askForNewGame(){
        System.out.printf("New Game? (y) (n)\n");
        System.out.print("Enter: ");
        return evaluateInput(self.scannerIn.nextLine());
    }

    /**
     * Asks for new position
     * */
    public static String askForNewPosition(GamePlayer player){
        System.out.printf("Your Move %s%n", player.name);
        System.out.print("Enter (Row Col): ");
        return self.scannerIn.nextLine();
    }

    /**
     * Symbolic print to ask computer for a value
     * */
    public static void askComputerForValue(String name) {
        System.out.printf("Your Move %s%n", name);
    }

    /**
     * When the computer is thinking we are printing dots
     * with some sleeping time in between
     * */
    public static void printDot(){
        printChar('.');
        try{
            Thread.sleep(100);
        }
        catch(InterruptedException err){
            logToFile(err.getMessage());
        }
    }

    /**
     * Asks for boardsize
     * */
    public static String askForBoardSize(){
        System.out.println("Enter The Size Of Board You Would Like To Play (min 3 max 11)");
        System.out.print("Enter: ");
        return self.scannerIn.nextLine();
    }

    /**
     * Asks for markers-in-a-row to win
     * */
    public static String askForKeyValue(int maxValue){
        System.out.println("You Can Increase The Game From A Min Of 3 In A Row Up To A Max of %d Markers In A Row".formatted(maxValue));
        System.out.print("Enter: ");
        return self.scannerIn.nextLine();
    }

    /**
     * prints the current state of the board
     * col 0 -> maxColumns
     * row 0 -> maxRows
     * Input from the user becomes index inside matrix
     * */
    public static void printCurrentBoard(GameBoard m){
        //clearScreen();
        int x,y = 0;
        printCellStart();
        for(x = 0;x<m.columns;x++){printColBase(x);}
        printString("\n");
        for(;y < m.rows;y++){
            printRowIndex(y);
            for(x = 0;x<m.columns;x++){
                char value = m.getChar(y,x);
                if(x == m.columns-1)printCellEnd(value);
                else printCellBase(value);
            }
            if(y < m.rows-1){
                printRow(m.columns*4);
            }
            else printString("\n");
        }
    }

    /**
     * Prints row index
     * If we have double digits we shift a bit
     * */
    public static void printRowIndex(int value){
        if(value < 10)printString(" %d  ".formatted(value));
        else printString(" %d ".formatted(value));
    }

    public static void printCellStart(){
        printString("    ");
    }

    public static void printRowStart(){
        printString("   |");
    }

    public static void printColBase(int value){
        if(value < 10)printString(" %d  ".formatted(value));
        else printString(" %d ".formatted(value));
    }

    public static void printCellBase(int value){
        printString(" %d  ".formatted(value));
    }

    public static void printCellBase(char value){
        printString(" %c |".formatted(value));
    }

    public static void printCellEnd(char value){
        printString(" %c ".formatted(value));
    }

    public static void printRow(int size){
        int cnt = 0;
        printString("\n");
        printCellStart();
        while(cnt++ < size){printChar('-');}
        printString("\n");
    }

    public static void printMinMaxPos(MinMaxPos pos){
        printString("Index: %d HighestValue: %d".formatted(pos.index,pos.valueHigh));
    }
}
