package helper.struct;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Global struct to get some time and date.
 * Nice for logging
 * */
public class SMDateTime {
    static SMDateTime self;
    static boolean isSet;

    public SMDateTime(){
        assert !SMDateTime.isSet :"SMDateTime is already set!";
        SMDateTime.setInstance();
    }

    private static void setInstance(){
        SMDateTime.isSet = true;
    }

    public static void initSMDateTime(){
        if(self == null){
            self = new SMDateTime();
        }
    }

    public static String getTime(){return self.servePattern("HH:mm:ss");}

    public static String getDate(){return self.servePattern("yyyy-MM-dd");}

    public static String getDateTime(){return self.servePattern("yyyy-MM-dd HH:mm:ss");}

    private String servePattern(String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        ZoneId z = ZoneId.of("Europe/Stockholm");
        ZonedDateTime zdt = ZonedDateTime.now(z);
        return zdt.format(formatter);
    }
}