package helper.io;
import helper.matrix.GameBoard;
import helper.player.GamePlayer;
import helper.struct.SMDateTime;
import program.ThreeInARow;

import java.io.*;
import java.util.Scanner;

public class IOHandler {
    public static Scanner scannerIn = new Scanner(System.in);

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

    public static void logToFile(String msg){
        PrintWriter writer;
        String out = "%s -> %s".formatted(SMDateTime.getDateTime(),msg);
        try{
            writer = new PrintWriter(new BufferedWriter(new FileWriter("./resources/files/log/error/error.log", true)));
            writer.println(out);
            writer.close();

        }
        catch(IOException err){

        }
    }

    public static boolean testPathToLogFile(){
        logToFile("Start Of Program");
        return ThreeInARow.funcToCheck.passed;
    }

    public static void printString(String str){
        System.out.printf("%s",str);
    }

    public static void printChar(char c){
        System.out.printf("%c",c);
    }

    public static void printInt(int num){
        System.out.printf("%d", num);
    }

    public static void printFloat(float num){
        System.out.printf("%f", num);
    }

    public static void clearScreen(){
        //System.out.flush();
        //System.out.print("\033\143");
        for(int clear = 0; clear < 10; clear++) {
            System.out.println("\b") ;
        }
    }

    public static String printGameMenu(){
        System.out.println("Welcome for A Game Of Three In A Row!");
        System.out.println("Do We Fancy (1) SinglePlayer (2) MultiPlayer or (q) Exit");
        System.out.print("Enter: ");
        return scannerIn.nextLine();
    }

    public static void welcomePlayers(String nameOne,String nameTwo){
        System.out.println("Our Contestants Are:");
        System.out.println(nameOne);
        System.out.println(nameTwo);
        printString("\n");
    }

    public static String askForPlayerName(int index){
        System.out.println("Please Enter Name For Player %d".formatted(index));
        System.out.print("Val: ");
        return scannerIn.nextLine();
    }

    public static String askForNewPosition(GamePlayer player){
        System.out.println("Your Move %s".formatted(player.name));
        System.out.print("Enter (Row Col): ");
        return scannerIn.nextLine();
    }

    public static void askComputerForValue(String name){
        int cnt = 0;
        System.out.println("Your Move %s".formatted(name));
        do{
            printChar('.');
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException err){
                break;
            }

        }while(cnt++< 40);
        printString("\n");
    }

    public static String askForBoardSize(){
        System.out.println("Please Enter The Size Of Board You Would Like To Play (min 3 max 64)");
        System.out.print("Enter: ");
        return scannerIn.nextLine();
    }

    public static void printCurrentBoard(GameBoard m){
        clearScreen();
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
}
