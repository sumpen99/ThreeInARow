package helper.struct;

/**
 * Simple class to time stuff
 * Like for ex. the running time of a new game
 * */
public class SMTimer {
    boolean running;
    long clockBegin,clockEnd,timePassed;

    public void startClock(){
        running = true;
        clockBegin = System.currentTimeMillis();
    }

    public void stopClock(){
        clockEnd = System.currentTimeMillis();
        timePassed = clockEnd-clockBegin;
        running = false;
    }

    public float getTimePassed(){
        float fTime = 0.0f;
        if(running){
            stopClock();
            if(validateTimePassed()){fTime = timePassed/1000.0f;}
        }
        if(validateTimePassed()){fTime = timePassed/1000.0f;}
        return fTime;
    }

    public String getTimePassedString(){
        float fTime = getTimePassed();
        return "%s".formatted(fTime);
    }

    boolean validateTimePassed(){
        return timePassed != 0.0f;
    }
}
