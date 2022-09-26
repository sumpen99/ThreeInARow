package helper.io;

import helper.matrix.GameBoard;
import helper.matrix.Matrix;

import java.util.Scanner;

public class IOHandler {
    public static Scanner scannerIn = new Scanner(System.in);

    public static void printString(String str){
        System.out.print(str);
    }

    public static void printChar(char c){
        System.out.print(c);
    }

    public static String printGameMenu(){
        System.out.println("Welcome for A Game Of Three In A Row!");
        System.out.println("Do We Fancy SinglePlayer Mode (type 1) or MultiPlayer Mode (type 2) (type q to exit)");
        System.out.print("Val: ");
        return scannerIn.nextLine();
    }

    public static void welcomePlayers(String nameOne,String nameTwo){
        System.out.println("Ok Our Contestants Are:");
        System.out.println(nameOne);
        System.out.println(nameTwo);
    }

    public static String askForPlayerName(int index){
        System.out.println("Please Enter Name For Player %d".formatted(index));
        System.out.print("Val: ");
        return scannerIn.nextLine();
    }

    public static String askForBoardSize(){
        System.out.println("Please Enter The Size Of Board You Would Like To Play At");
        System.out.println("ex. 3");
        System.out.println(" [] [] []");
        System.out.println(" [] [] []");
        System.out.println(" [] [] []");
        System.out.print("Val: ");
        return scannerIn.nextLine();
    }

    public static void printCurrentBoard(GameBoard m){
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
