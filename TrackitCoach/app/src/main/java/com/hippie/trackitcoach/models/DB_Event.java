package com.hippie.trackitcoach.models;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hippie on 8/12/2015.
 */
public class DB_Event implements Parcelable{

    // =============== +++ Member Variables +++ ===============

    private int mID;
    private int mFID_Athletes;
    private String mEventName;
    private String mDate;
    private String mResult;
    private String mComments;
    private String mCity;
    private String mCountry;
    private String mSummary;
    private double mTempInF;
    private double mHumidity;
    private double mWindSpeed;
    private String mWindDirection;
    private boolean mDoesBreathingNeedImprovement;
    private boolean mDoesFormNeedImprovement;
    private boolean mIsAthleteHungry;
    private boolean mIsAthleteTired;


    // =============== +++ Setters & Getters +++ ===============

    public int getFID_Athletes() {
        return mFID_Athletes;
    }

    public void setFID_Athletes(int FID_Athletes) {
        mFID_Athletes = FID_Athletes;
    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public String getEventName() {
        return mEventName;
    }

    public void setEventName(String eventName) {
        mEventName = eventName;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getResult() {
        return mResult;
    }

    public void setResult(String result) {
        mResult = result;
    }


    public String getComments() {
        return mComments;
    }

    public void setComments(String comments) {
        mComments = comments;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public double getTempInF() {
        return mTempInF;
    }

    public void setTempInF(double tempInF) {
        mTempInF = tempInF;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(double humidity) {
        mHumidity = humidity;
    }

    public double getWindSpeed() {
        return mWindSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        mWindSpeed = windSpeed;
    }

    public String getWindDirection() {
        return mWindDirection;
    }

    public void setWindDirection(String windDirection) {
        mWindDirection = windDirection;
    }

    public boolean isDoesBreathingNeedImprovement() {
        return mDoesBreathingNeedImprovement;
    }

    public void setDoesBreathingNeedImprovement(boolean doesBreathingNeedImprovement) {
        mDoesBreathingNeedImprovement = doesBreathingNeedImprovement;
    }

    public boolean isDoesFormNeedImprovement() {
        return mDoesFormNeedImprovement;
    }

    public void setDoesFormNeedImprovement(boolean doesFormNeedImprovement) {
        mDoesFormNeedImprovement = doesFormNeedImprovement;
    }

    public boolean isAthleteHungry() {
        return mIsAthleteHungry;
    }

    public void setIsAthleteHungry(boolean isAthleteHungry) {
        mIsAthleteHungry = isAthleteHungry;
    }

    public boolean isAthleteTired() {
        return mIsAthleteTired;
    }

    public void setIsAthleteTired(boolean isAthleteTired) {
        mIsAthleteTired = isAthleteTired;
    }

    // =============== +++ Member Functions +++ ===============
    public DB_Event() {}

    public DB_Event(int ID, int FID_Athletes, String eventName,
                    String date, String result, String comments,
                    String city, String country, String summary, double tempInF, double humidity,
                    double windSpeed, String windDirection, boolean breathing, boolean form,
                    boolean hungry, boolean tired) {
        mID = ID;
        mFID_Athletes = FID_Athletes;
        mEventName = eventName;
        mDate = date;
        mResult = result;
        mComments = comments;
        mCity = city;
        mCountry = country;
        mSummary = summary;
        mTempInF = tempInF;
        mHumidity = humidity;
        mWindSpeed = windSpeed;
        mWindDirection = windDirection;
        mDoesBreathingNeedImprovement = breathing;
        mDoesFormNeedImprovement = form;
        mIsAthleteHungry = hungry;
        mIsAthleteTired = tired;

    }





    public int boolToInt(Boolean bool) {
        if(bool == false)
            return 0;
        else
            return 1;

    }

    public boolean intToBool(int myInt) {
        if(myInt == 0)
            return false;
        else
            return true;
    }

// =============== +++ Parcelable Functions +++ ===============

    protected DB_Event(Parcel in) {
        mID = in.readInt();
        mFID_Athletes = in.readInt();
        mEventName = in.readString();
        mDate = in.readString();
        mResult = in.readString();
        mComments = in.readString();
        mCity = in.readString();
        mCountry = in.readString();
        mSummary = in.readString();
        mTempInF = in.readDouble();
        mHumidity = in.readDouble();
        mWindSpeed = in.readDouble();
        mWindDirection = in.readString();
        mDoesBreathingNeedImprovement = intToBool(in.readInt());
        mDoesFormNeedImprovement = intToBool(in.readInt());
        mIsAthleteHungry = intToBool(in.readInt());
        mIsAthleteTired = intToBool(in.readInt());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mID);
        dest.writeInt(mFID_Athletes);
 //       dest.writeTypedList(mDBAthletes);
        dest.writeString(mEventName);
        dest.writeString(mDate);
        dest.writeString(mResult);
        dest.writeString(mComments);
        dest.writeString(mCity);
        dest.writeString(mCountry);
        dest.writeString(mSummary);
        dest.writeDouble(mTempInF);
        dest.writeDouble(mHumidity);
        dest.writeDouble(mWindSpeed);
        dest.writeString(mWindDirection);
        dest.writeInt(boolToInt(mDoesBreathingNeedImprovement));
        dest.writeInt(boolToInt(mDoesFormNeedImprovement));
        dest.writeInt(boolToInt(mIsAthleteHungry));
        dest.writeInt(boolToInt(mIsAthleteTired));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DB_Event> CREATOR = new Creator<DB_Event>() {
        @Override
        public DB_Event createFromParcel(Parcel in) {
            return new DB_Event(in);
        }

        @Override
        public DB_Event[] newArray(int size) {
            return new DB_Event[size];
        }
    };


    //-- Todd Code
    public String GenerateReport()
    {
        return getResult()+"\n"+GetNeedsWorkString()+"\n"+getComments();
    }
    public String GetWeatherOverView()
    {
        return     mCity+mCountry+"\n"+
                String.valueOf(getTempInF())+"F"+"\n"+"Humidity "+String.valueOf(mHumidity)+"%"+"\n"+"WindSpeed: "+String.valueOf(mWindSpeed)+"m/s"+"\n"+
                "WindDir: "+getWindDirection();
    }
    private String GetNeedsWorkString()
    {
        String Report = "";
        if(isDoesBreathingNeedImprovement()|| isDoesFormNeedImprovement()|| isAthleteHungry()|| isAthleteTired()) {
            Report += "Needs to:";
            if (isDoesBreathingNeedImprovement()) {
                Report += "Work On Breathing, ";
            }
            if (isDoesFormNeedImprovement()) {
                Report += "Work On Form, ";
            }
            if (isAthleteHungry()) {
                Report +="Work On Eating Habits, ";
            }
            if (isAthleteTired()) {
                Report +="Get Better Sleep";
            }
            Report += "...";
        }
        return Report;
    }


}


