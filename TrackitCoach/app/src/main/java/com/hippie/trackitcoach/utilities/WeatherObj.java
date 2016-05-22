package com.hippie.trackitcoach.utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Todd on 8/10/2015.
 */
public class WeatherObj {
    String City ="Loc: ",Country=", ",Sky="Weather: ";
    double Temp;
    double Humidity;

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getSky() {
        return Sky;
    }

    public void setSky(String sky) {
        Sky = sky;
    }

    public double getTemp() {
        return Temp;
    }

    public void setTemp(double temp) {
        Temp = temp;
    }

    public double getHumidity() {
        return Humidity;
    }

    public void setHumidity(double humidity) {
        Humidity = humidity;
    }

    public double getWindSpeed() {
        return WindSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        WindSpeed = windSpeed;
    }

    public double getWindDir() {
        return WindDir;
    }

    public void setWindDir(double windDir) {
        WindDir = windDir;
    }

    double WindSpeed;
    double WindDir;

    public WeatherObj(String jsondata) {
        // Todo Update to fit gps json- this will crash if not updated with GPS format
        JSONObject jobj;
        try {
            // get string into Json Object
            jobj    = new JSONObject(jsondata);
            City    += jobj.getString("name");

            JSONObject WindObj = getObject("wind", jobj);
            WindSpeed =  WindObj.getDouble("speed");
            WindDir =  WindObj.getDouble("deg");

            // Weather obj
            JSONArray WeaObj =  jobj.getJSONArray("weather");
            JSONObject WeaObjtwo = WeaObj.getJSONObject(0);
            Sky += WeaObjtwo.getString("description");

          //  Sky += WeaObj.getString("description");
            JSONObject MainInfo = getObject("main", jobj);
            Temp = Math.round((MainInfo.getDouble("temp") - 273.15)* 1.8000 + 32.00);
            Humidity = MainInfo.getDouble("humidity");

            JSONObject Sysobj = getObject("sys",jobj);
            Country += Sysobj.getString("country");

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private JSONObject getObject(String tagName, JSONObject jobj) {
        JSONObject subObj = null;
        try {
            subObj = jobj.getJSONObject(tagName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return subObj;

    }

    public String MakeReport()
    {

        return City+Country+"\n"+Sky+"\n"+
                String.valueOf(Temp)+"F"+"\n"+"Humidity "+String.valueOf(Humidity)+"%"+"\n"+"WindSpeed: "+String.valueOf(WindSpeed)+"m/s"+"\n"+
                "WindDir: "+WindDirection(WindDir);
    }
    String WindDirection(double dir)
    {
        String SDir="North";

        if((dir > 350 && dir < 360) || (dir > -1 && dir < 11))
            SDir="North";
        else if( dir <60)
            SDir = "North East";
        else if( dir < 120)
            SDir =" East";
        else if( dir < 170)
            SDir = "South East";
        else if( dir < 190)
            SDir = "South";
        else if( dir < 210)
            SDir = "South";
        else if( dir < 240)
            SDir = "South West";
        else if( dir < 300)
            SDir = "West";
        else if( dir < 350)
            SDir = "North West";
            return SDir;
    }
}
