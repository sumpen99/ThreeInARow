package helper.methods;

import helper.struct.PassedCheck;

/**
 * Class to handle some methods
 * we can use on different places in the program
 * */
public class CommonMethods {

    /**
     * returns random int with a maxValue
     * */
    public static int getRandomInt(int maxValue){
        double val = Math.random()*1000;
        return (int)val%maxValue;
    }

    /**
     * returns parsed int if its a valid
     * number else -1.
     * -1 is also a correct int but not where we use it
     * */
    public static int stringIsInt(String s){
        try{
            int num = Integer.parseInt(s);
            return num;
        }
        catch( Exception err){
            return -1;
        }
    }

    /**
     * parse string with a ' ' seperator
     * returns int[] if it check out else none
     * if its not valid we pass the string to PassedCeck struct and sets passed as false
     * */
    public static int[] verifyNewPos(String s, PassedCheck result){
        try{
            int row,col;
            String[] pos = s.split(" ");
            row = Integer.parseInt(pos[0]);
            col = Integer.parseInt(pos[1]);
            return new int[]{row,col};
        }
        catch( Exception err){
            result.passed = false;
            result.message = s;
            return null;
        }
    }

    /**
     * trims string down and returns first char
     * if length is 0 we return 0
     * */
    public static char evaluateInput(String str){
        if((str = str.trim()).length() == 0)return 0;
        return str.charAt(0);
    }
}
