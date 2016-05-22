package com.hippie.trackitcoach.utilities;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.TextView;


import com.hippie.trackitcoach.models.DB_Event;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Todd on 8/7/2015.
 */
public class WeatherCollector {
    //   /*
    Activity act;
    Context Appcon;
    // ToDo Will change when doing GPS update so Json Object will be differnt
    String jsonStr = null;
    WeatherObj Wobj;
    TextView InfoHold;
    DB_Event[] Info;
    public WeatherCollector(Context con, Activity _act, final TextView INfoHolder, DB_Event[] _Info) {
        Info = _Info;
        InfoHold = INfoHolder;
        GPSTracker Gps_Track = new GPSTracker(con);
        double Lon = Gps_Track.getLongitude();
        double Lat = Gps_Track.getLatitude();
        // account for openweather is bozemanwow@gmail.com password is Tricia12
        String url = "http://api.openweathermap.org/data/2.5/weather?lat="+String.valueOf(Lat)+"&lon="+String.valueOf(Lon)+"=524901&APPID=f285ecbf2a897701f0a7dc2a06939b3c";
        act = _act;
        Appcon = con;
        if (isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            Request req = new Request.Builder().url(url).build();
            Call call = client.newCall(req);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    FailedToRetreive();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    //    Response response = call.execute();
                    try {
                        jsonStr = response.body().string();
                        //   Message(response.body().string());

                        if (response.isSuccessful()) {

                            makeweather();
                           SetUpRunEventWeather();

                        } else {
                            FailedToRetreive();
                        }
                    } catch (IOException e) {
                        FailedToRetreive();
                    }
                }
            });
        } else
            FailedToRetreive();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager man = (ConnectivityManager) Appcon.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = man.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return networkInfo.isAvailable();
        else
            return false;
    }


    public void SetUpRunEventWeather() {

        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SetWeather();

            }

            private void SetWeather() {

                TextView Input = (TextView) act.findViewById(InfoHold.getId());
                Input.setText((CharSequence) Wobj.MakeReport() + "\n" + Input.getText());

            }
        });
    }

    


    public void makeweather() {

        Wobj = new WeatherObj(jsonStr);
        for(int i = 0; i< Info.length; i++) {
            Info[i].setCity(Wobj.getCity());
            Info[i].setWindDirection(Wobj.WindDirection(Wobj.getWindDir()));
            Info[i].setTempInF(Wobj.getTemp());
            Info[i].setHumidity(Wobj.getHumidity());
            Info[i].setCountry(Wobj.getCountry());
            Info[i].setWindSpeed(Wobj.getWindSpeed());
        }
    }

    ///*
    public WeatherObj getWeatherObj() {
        return Wobj;
    }

    //   */
    void Message(String mes) {
        Log.v("Weather Check", mes);
    }
//*/
    void FailedToRetreive()
    {
        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView Input = (TextView) act.findViewById(InfoHold.getId());
                Input.setText((CharSequence)"Failed to Retreive The Weather Report");
            }

         });
    }
}


