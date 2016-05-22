package com.hippie.trackitcoach.models;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Todd on 7/16/2015.
 */
public class DB_Athlete implements Parcelable {

    // =============== +++ Member Variables +++ ===============

    private int mId;
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mSex;

    private boolean mCompete60meters;
    private boolean mCompete100meters;
    private boolean mCompete200meters;
    private boolean mCompete400meters;
    private boolean mCompete800meters;
    private boolean mCompete1500meters;
    private boolean mCompete3000meters;
    private boolean mCompete5000meters;
    private boolean mCompete10000meters;
    private boolean mCompete60mHurdles;
    private boolean mCompete100mHurdles;
    private boolean mCompete110mHurdles;
    private boolean mCompete400mHurdles;
    private boolean mCompete3000mSteeplechase;
    private boolean mCompete4X100mRelay;
    private boolean mCompete4X400mRelay;
    private boolean mCompeteLongJump;
    private boolean mCompeteTripleJump;
    private boolean mCompeteHighJump;
    private boolean mCompetePoleVault;
    private boolean mCompeteShotPut;
    private boolean mCompeteDiscus;
    private boolean mCompeteHammerThrow;
    private boolean mCompeteJavelin;


    // =============== +++ Setters & Getters +++ ===============

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }


    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getSex() {
        return mSex;
    }

    public void setSex(String sex) {
        mSex = sex;
    }

    public boolean doesCompete60meters() {
        return mCompete60meters;
    }

    public void setCompete60meters(boolean Compete60meters) {
        mCompete60meters = Compete60meters;
    }

    public boolean doesCompete100meters() {
        return mCompete100meters;
    }

    public void setCompete100meters(boolean Compete100meters) {
        mCompete100meters = Compete100meters;
    }

    public boolean doesCompete200meters() {
        return mCompete200meters;
    }

    public void setCompete200meters(boolean Compete200meters) {
        mCompete200meters = Compete200meters;
    }

    public boolean doesCompete400meters() {
        return mCompete400meters;
    }

    public void setCompete400meters(boolean Compete400meters) {
        mCompete400meters = Compete400meters;
    }

    public boolean doesCompete800meters() {
        return mCompete800meters;
    }

    public void setCompete800meters(boolean Compete800meters) {
        mCompete800meters = Compete800meters;
    }

    public boolean doesCompete1500meters() {
        return mCompete1500meters;
    }

    public void setCompete1500meters(boolean Compete1500meters) {
        mCompete1500meters = Compete1500meters;
    }

    public boolean doesCompete3000meters() {
        return mCompete3000meters;
    }

    public void setCompete3000meters(boolean Compete3000meters) {
        mCompete3000meters = Compete3000meters;
    }

    public boolean doesCompete5000meters() {
        return mCompete5000meters;
    }

    public void setCompete5000meters(boolean Compete5000meters) {
        mCompete5000meters = Compete5000meters;
    }

    public boolean doesCompete10000meters() {
        return mCompete10000meters;
    }

    public void setCompete10000meters(boolean Compete10000meters) {
        mCompete10000meters = Compete10000meters;
    }

    public boolean doesCompete60mHurdles() {
        return mCompete60mHurdles;
    }

    public void setCompete60mHurdles(boolean Compete60mHurdles) {
        mCompete60mHurdles = Compete60mHurdles;
    }

    public boolean doesCompete100mHurdles() {
        return mCompete100mHurdles;
    }

    public void setCompete100mHurdles(boolean Compete100mHurdles) {
        mCompete100mHurdles = Compete100mHurdles;
    }

    public boolean doesCompete110mHurdles() {
        return mCompete110mHurdles;
    }

    public void setCompete110mHurdles(boolean Compete110mHurdles) {
        mCompete110mHurdles = Compete110mHurdles;
    }

    public boolean doesCompete400mHurdles() {
        return mCompete400mHurdles;
    }

    public void setCompete400mHurdles(boolean Compete400mHurdles) {
        mCompete400mHurdles = Compete400mHurdles;
    }

    public boolean doesCompete3000mSteeplechase() {
        return mCompete3000mSteeplechase;
    }

    public void setCompete3000mSteeplechase(boolean Compete3000mSteeplechase) {
        mCompete3000mSteeplechase = Compete3000mSteeplechase;
    }

    public boolean doesCompete4X100mRelay() {
        return mCompete4X100mRelay;
    }

    public void setCompete4X100mRelay(boolean Compete4X100mRelay) {
        mCompete4X100mRelay = Compete4X100mRelay;
    }

    public boolean doesCompete4X400mRelay() {
        return mCompete4X400mRelay;
    }

    public void setCompete4X400mRelay(boolean Compete4X400mRelay) {
        mCompete4X400mRelay = Compete4X400mRelay;
    }

    public boolean doesCompeteLongJump() {
        return mCompeteLongJump;
    }

    public void setCompeteLongJump(boolean CompeteLongJump) {
        mCompeteLongJump = CompeteLongJump;
    }

    public boolean doesCompeteTripleJump() {
        return mCompeteTripleJump;
    }

    public void setCompeteTripleJump(boolean CompeteTripleJump) {
        mCompeteTripleJump = CompeteTripleJump;
    }

    public boolean doesCompeteHighJump() {
        return mCompeteHighJump;
    }

    public void setCompeteHighJump(boolean CompeteHighJump) {
        mCompeteHighJump = CompeteHighJump;
    }

    public boolean doesCompetePoleVault() {
        return mCompetePoleVault;
    }

    public void setCompetePoleVault(boolean competePoleVault) {
        mCompetePoleVault = competePoleVault;
    }

    public boolean doesCompeteShotPut() {
        return mCompeteShotPut;
    }

    public void setCompeteShotPut(boolean CompeteShotPut) {
        mCompeteShotPut = CompeteShotPut;
    }

    public boolean doesCompeteDiscus() {
        return mCompeteDiscus;
    }

    public void setCompeteDiscus(boolean CompeteDiscus) {
        mCompeteDiscus = CompeteDiscus;
    }

    public boolean doesCompeteHammerThrow() {
        return mCompeteHammerThrow;
    }

    public void setCompeteHammerThrow(boolean CompeteHammerThrow) {
        mCompeteHammerThrow = CompeteHammerThrow;
    }

    public boolean doesCompeteJavelin() {
        return mCompeteJavelin;
    }

    public void setCompeteJavelin(boolean CompeteJavelin) {
        mCompeteJavelin = CompeteJavelin;
    }


    // =============== +++ Member Functions +++ ===============

    // Default Constructor
    public DB_Athlete() {
        mId = 0;
        mFirstName = "";
        mLastName = "";
        mEmail = "";
        mSex = "";
        mCompete60meters = false;
        mCompete100meters = false;
        mCompete200meters = false;
        mCompete400meters = false;
        mCompete800meters = false;
        mCompete1500meters = false;
        mCompete3000meters = false;
        mCompete5000meters = false;
        mCompete10000meters = false;
        mCompete60mHurdles = false;
        mCompete100mHurdles = false;
        mCompete110mHurdles = false;
        mCompete400mHurdles = false;
        mCompete3000mSteeplechase = false;
        mCompete4X100mRelay = false;
        mCompete4X400mRelay = false;
        mCompeteLongJump = false;
        mCompeteTripleJump = false;
        mCompeteHighJump = false;
        mCompetePoleVault = false;
        mCompeteShotPut = false;
        mCompeteDiscus = false;
        mCompeteHammerThrow = false;
        mCompeteJavelin = false;
    }

    //Overloaded Constructor
    public DB_Athlete(int id,
                      String firstName,
                      String lastName,
                      String email,
                      String sex,
                      boolean Compete60meters,
                      boolean Compete100meters,
                      boolean Compete200meters,
                      boolean Compete400meters,
                      boolean Compete800meters,
                      boolean Compete1500meters,
                      boolean Compete3000meters,
                      boolean Compete5000meters,
                      boolean Compete10000meters,
                      boolean Compete60mHurdles,
                      boolean Compete100mHurdles,
                      boolean Compete110mHurdles,
                      boolean Compete400mHurdles,
                      boolean Compete3000mSteeplechase,
                      boolean Compete4X100mRelay,
                      boolean Compete4X400mRelay,
                      boolean CompeteLongJump,
                      boolean CompeteTripleJump,
                      boolean CompeteHighJump,
                      boolean competePoleVault,
                      boolean CompeteShotPut,
                      boolean CompeteDiscus,
                      boolean CompeteHammerThrow,
                      boolean CompeteJavelin) {
        mId = id;
        mFirstName = firstName;
        mLastName = lastName;
        mEmail = email;
        mSex = sex;
        mCompete60meters = Compete60meters;
        mCompete100meters = Compete100meters;
        mCompete200meters = Compete200meters;
        mCompete400meters = Compete400meters;
        mCompete800meters = Compete800meters;
        mCompete1500meters = Compete1500meters;
        mCompete3000meters = Compete3000meters;
        mCompete5000meters = Compete5000meters;
        mCompete10000meters = Compete10000meters;
        mCompete60mHurdles = Compete60mHurdles;
        mCompete100mHurdles = Compete100mHurdles;
        mCompete110mHurdles = Compete110mHurdles;
        mCompete400mHurdles = Compete400mHurdles;
        mCompete3000mSteeplechase = Compete3000mSteeplechase;
        mCompete4X100mRelay = Compete4X100mRelay;
        mCompete4X400mRelay = Compete4X400mRelay;
        mCompeteLongJump = CompeteLongJump;
        mCompeteTripleJump = CompeteTripleJump;
        mCompeteHighJump = CompeteHighJump;
        mCompetePoleVault = competePoleVault;
        mCompeteShotPut = CompeteShotPut;
        mCompeteDiscus = CompeteDiscus;
        mCompeteHammerThrow = CompeteHammerThrow;
        mCompeteJavelin = CompeteJavelin;
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

    protected DB_Athlete(Parcel in) {
        mId = in.readInt();
        mFirstName = in.readString();
        mLastName = in.readString();
        mEmail = in.readString();
        mSex = in.readString();

        mCompete60meters = intToBool(in.readInt());
        mCompete100meters = intToBool(in.readInt());
        mCompete200meters = intToBool(in.readInt());
        mCompete400meters = intToBool(in.readInt());
        mCompete800meters = intToBool(in.readInt());
        mCompete1500meters = intToBool(in.readInt());
        mCompete3000meters = intToBool(in.readInt());
        mCompete5000meters = intToBool(in.readInt());
        mCompete10000meters = intToBool(in.readInt());
        mCompete60mHurdles = intToBool(in.readInt());
        mCompete100mHurdles = intToBool(in.readInt());
        mCompete110mHurdles = intToBool(in.readInt());
        mCompete400mHurdles = intToBool(in.readInt());
        mCompete3000mSteeplechase = intToBool(in.readInt());
        mCompete4X100mRelay = intToBool(in.readInt());
        mCompete4X400mRelay = intToBool(in.readInt());
        mCompeteLongJump = intToBool(in.readInt());
        mCompeteTripleJump = intToBool(in.readInt());
        mCompeteHighJump = intToBool(in.readInt());
        mCompetePoleVault = intToBool(in.readInt());
        mCompeteShotPut = intToBool(in.readInt());
        mCompeteDiscus = intToBool(in.readInt());
        mCompeteHammerThrow = intToBool(in.readInt());
        mCompeteJavelin = intToBool(in.readInt());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mFirstName);
        dest.writeString(mLastName);
        dest.writeString(mEmail);
        dest.writeString(mSex);

        dest.writeInt(boolToInt(mCompete60meters));
        dest.writeInt(boolToInt(mCompete100meters));
        dest.writeInt(boolToInt(mCompete200meters));
        dest.writeInt(boolToInt(mCompete400meters));
        dest.writeInt(boolToInt(mCompete800meters));
        dest.writeInt(boolToInt(mCompete1500meters));
        dest.writeInt(boolToInt(mCompete3000meters));
        dest.writeInt(boolToInt(mCompete5000meters));
        dest.writeInt(boolToInt(mCompete10000meters));
        dest.writeInt(boolToInt(mCompete60mHurdles));
        dest.writeInt(boolToInt(mCompete100mHurdles));
        dest.writeInt(boolToInt(mCompete110mHurdles));
        dest.writeInt(boolToInt(mCompete400mHurdles));
        dest.writeInt(boolToInt(mCompete3000mSteeplechase));
        dest.writeInt(boolToInt(mCompete4X100mRelay));
        dest.writeInt(boolToInt(mCompete4X400mRelay));
        dest.writeInt(boolToInt(mCompeteLongJump));
        dest.writeInt(boolToInt(mCompeteTripleJump));
        dest.writeInt(boolToInt(mCompeteHighJump));
        dest.writeInt(boolToInt(mCompetePoleVault));
        dest.writeInt(boolToInt(mCompeteShotPut));
        dest.writeInt(boolToInt(mCompeteDiscus));
        dest.writeInt(boolToInt(mCompeteHammerThrow));
        dest.writeInt(boolToInt(mCompeteJavelin));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DB_Athlete> CREATOR = new Creator<DB_Athlete>() {
        @Override
        public DB_Athlete createFromParcel(Parcel in) {
            return new DB_Athlete(in);
        }

        @Override
        public DB_Athlete[] newArray(int size) {
            return new DB_Athlete[size];
        }
    };

    //- Todd  Code
    public String ReportCall()
    {

        return ": "+ getLastName()+", "+getFirstName();

    }

}