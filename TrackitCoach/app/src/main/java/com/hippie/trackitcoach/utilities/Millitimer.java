package com.hippie.trackitcoach.utilities;


import android.app.Activity;
import android.widget.TextView;

/**
 * Created by Todd on 8/26/2015.
 */
public class Millitimer implements Runnable {
    public boolean isTicking() {
        return Ticking;
    }

    boolean Ticking;
    int milicount, secondcount, mincount, increment;
    Activity act;
    TextView ClockTimer;

    boolean Go = true;


    public Millitimer(Activity runt, TextView Clock) {
        milicount = secondcount = mincount = 0;
        increment = 1;
        act = runt;
        ClockTimer = Clock;

        Ticking = false;

    }

    public void ResetWatch() {
        milicount = secondcount = mincount = 0;

        Ticking = false;
        SetWatch();
    }

    public void StartWatch() {

        Ticking = true;

    }

    public void PauseWatch() {

        Ticking = false;
        SetWatch();
    }

    public void SetWatch() {
        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ClockTimer.setText(TimeSum());

            }
        });
    }

    public String TimeSum() {
        String Mos = "", Sos = "", Mios = "";

        if (mincount < 10)
            Mos = "0";
        if (secondcount < 10)
            Sos = "0";
        if (milicount < 10)
            Mios = "0";
        String value =     Mos + String.valueOf(mincount) + ":" + Sos + String.valueOf(secondcount) + ":" + Mios + String.valueOf(milicount);

        return (value);

    }
public void FinsihThread()
{
    Go = false;
}

    @Override
    public void run() {
        while (Go) {
            SetWatch();
            if (Ticking) {

                milicount += increment;

                if (milicount > 100) {
                    milicount = 0;
                    secondcount++;

                }
                if (secondcount > 59) {
                    secondcount = 0;
                    mincount++;

                }


            }
            try {

                Thread.sleep(10);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
