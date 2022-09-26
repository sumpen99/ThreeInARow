package helper.methods;

public class CommonMethods {

    public static int stringIsInt(String s){
        try{
            int num = Integer.parseInt(s);
            return num;
        }
        catch( NumberFormatException err){
            return -1;
        }
    }
}
