package com.hippie.trackitcoach.utilities;

/**
 * Created by Hippie on 8/28/2015.
 */
import android.text.format.Time;

/**
 * Clock utility.
 */
public class Clock {

    /**
     * Get current time in human-readable form.
     * @return current time as a string.
     */
    public static String getNow() {
        Time now = new Time();
        now.setToNow();
        String sTime = now.format("%Y_%m_%d %T");
        return sTime;
    }
    /**
     * Get current time in human-readable form without spaces and special characters.
     * The returned value may be used to compose a file name.
     * @return current time as a string.
     */
    public static String getTimeStamp() {
        Time now = new Time();
        now.setToNow();
        String sTime = now.format("%m/%d/%Y");
        return sTime;
    }

}