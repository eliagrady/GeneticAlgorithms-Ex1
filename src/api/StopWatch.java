package api;

/**
 * The StopWatch class.
 * @author Omer
 *
 */
public class StopWatch
{

    // the class' variable
    private long startTime = System.currentTimeMillis();
    
    
    
    /**
     * the class' constructr.
     */
    public StopWatch()
    {
        
        super();
        this.startTime = System.currentTimeMillis();
    }
    
    /**
     * restarts the stop watch
     */
    public void restart()
    {
        
        this.startTime = System.currentTimeMillis();
    }
    
    /**
     * converts milliseconds to time.
     * @param milliseconds - the given milliseconds to converts
     * @return the time
     */
    private String millisecondsToTime(long milliseconds)
    {
        
        // converts the milliseconds to time
        long second = (milliseconds / 1000) % 60;
        long minute = (milliseconds / (1000 * 60)) % 60;
        long hour = (milliseconds / (1000 * 60 * 60)) % 24;

        String time = String.format("%02d:%02d:%02d:%d", hour, minute, second, milliseconds);
        
        // returns the time string
        return time;
    }
    
    /**
     * @return the time passed
     */
    public String getTime()
    {
        
        return this.millisecondsToTime(System.currentTimeMillis() - this.startTime);
    }
}
