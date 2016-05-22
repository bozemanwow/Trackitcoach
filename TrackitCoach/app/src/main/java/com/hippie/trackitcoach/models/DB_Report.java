package com.hippie.trackitcoach.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hippie on 8/23/2015.
 */
public class DB_Report implements Parcelable{

    // =============== +++ Member Variables +++ ===============

    private DB_Athlete mDBAthlete;
    private DB_Event mDBEvent;


    protected DB_Report(Parcel in) {
        mDBAthlete = in.readParcelable(DB_Athlete.class.getClassLoader());
        mDBEvent = in.readParcelable(DB_Event.class.getClassLoader());
    }

    public static final Creator<DB_Report> CREATOR = new Creator<DB_Report>() {
        @Override
        public DB_Report createFromParcel(Parcel in) {
            return new DB_Report(in);
        }

        @Override
        public DB_Report[] newArray(int size) {
            return new DB_Report[size];
        }
    };

    // =============== +++ Setters & Getters +++ ===============
    public DB_Event getDBEvent() {
        return mDBEvent;
    }


    public DB_Athlete getDBAthlete() {
        return mDBAthlete;
    }

    public void setDBAthlete(DB_Athlete DBAthlete) {
        mDBAthlete = DBAthlete;
    }


    public void setDBEvent(DB_Event DBEvent) {
        mDBEvent = DBEvent;
    }
    // =============== +++ Member Functions +++ ===============
    public DB_Report() {}

    public DB_Report(DB_Athlete mDBAthlete, DB_Event DBEvent) {
        this.mDBAthlete = mDBAthlete;
        mDBEvent = DBEvent;
    }

    public void addAthlete(DB_Athlete DBAthlete) {
        mDBAthlete = DBAthlete;
    }

    public void addEvent(DB_Event DBEvent){
        mDBEvent = DBEvent;
    }

    public String showReport() {
         String firstName = this.getDBAthlete().getFirstName();
         String lastName = this.getDBAthlete().getLastName();
         String email = this.getDBAthlete().getEmail();
         String sex = this.getDBAthlete().getSex();
        String eventName = this.getDBEvent().getEventName();
        String date = this.getDBEvent().getDate();
        String result = this.getDBEvent().getResult();
        String comments = this.getDBEvent().getComments();
        String city = this.getDBEvent().getCity();
        String country = this.getDBEvent().getCountry();
        String summary = this.getDBEvent().getSummary();
        double tempInF = this.getDBEvent().getTempInF();
        double humidity = this.getDBEvent().getHumidity();
        double windSpeed = this.getDBEvent().getWindSpeed();
        String windDirection = this.getDBEvent().getWindDirection();
        boolean doesBreathingNeedImprovement = this.getDBEvent().isDoesBreathingNeedImprovement();
        boolean doesFormNeedImprovement = this.getDBEvent().isDoesFormNeedImprovement();
        boolean isAthleteHungry = this.getDBEvent().isAthleteHungry();
        boolean isAthleteTired = this.getDBEvent().isAthleteTired();
        String myRport = "Ahtlete Name: " + firstName + " " + lastName + "\n" +
                "Email: " + email + "\n" +
                "Sex: " + sex + "\n" +
                "Event Name: " + eventName + "\n" +
                "Date: " + date  + "\n" +
                "Result: " + result  + "\n" +
                "City: " + city + "\n" +
                "Country: " + country + "\n" +
                "Weather: " + summary + "\n" +
                "Temperature: " + tempInF + "\n" +
                "Humidity: " + humidity + "\n" +
                "Wind Speed: " + windSpeed + "\n" +
                "Wind Direction: " + windDirection + "\n" +
                "Breathing OK? " + doesBreathingNeedImprovement + "\n" +
                "Form OK? " + doesFormNeedImprovement  + "\n" +
                "Athlete hungry? " + isAthleteHungry + "\n" +
                "Athlete tired? " + isAthleteTired + "\n" +
                "Comments: " + comments;

        return myRport;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mDBAthlete, flags);
        dest.writeParcelable(mDBEvent, flags);
    }
}
