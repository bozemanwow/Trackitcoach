package com.hippie.trackitcoach.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.hippie.trackitcoach.models.DB_Athlete;
import com.hippie.trackitcoach.models.DB_Event;
import com.hippie.trackitcoach.models.DB_Report;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Hippie on 8/22/2015.
 */
public class MyDataSource {

    private Context mContext;
    private MySQLHelper mMySQLHelper;

    public MyDataSource(Context context) {
        mContext = context;
        mMySQLHelper = new MySQLHelper(context);
    }


    private SQLiteDatabase open() {
       return mMySQLHelper.getWritableDatabase();
    }

    private void close(SQLiteDatabase database) {
        database.close();
    }

    public long addReport(DB_Athlete DBAthlete, DB_Event DBEvent) {
        long keyID = -5;
        SQLiteDatabase database = open();
        database.beginTransaction();

        ContentValues reportValues = new ContentValues();
        reportValues.put(MySQLHelper.KEY_FID, DBAthlete.getId());
        reportValues.put(MySQLHelper.COLUMN_EVENT_NAME, DBEvent.getEventName());
        reportValues.put(MySQLHelper.COLUMN_DATE, DBEvent.getDate());
        reportValues.put(MySQLHelper.COLUMN_RESULT, DBEvent.getResult());
        reportValues.put(MySQLHelper.COLUMN_COMMENTS, DBEvent.getComments());
        reportValues.put(MySQLHelper.COLUMN_CITY, DBEvent.getCity());
        reportValues.put(MySQLHelper.COLUMN_COUNTRY, DBEvent.getCountry());
        reportValues.put(MySQLHelper.COLUMN_SUMMARY, DBEvent.getSummary());
        reportValues.put(MySQLHelper.COLUMN_TEMP_IN_F, DBEvent.getTempInF());
        reportValues.put(MySQLHelper.COLUMN_HUMIDITY, DBEvent.getHumidity());
        reportValues.put(MySQLHelper.COLUMN_WIND_SPEED, DBEvent.getWindSpeed());
        reportValues.put(MySQLHelper.COLUMN_WIND_DIRECTION, DBEvent.getWindDirection());
        reportValues.put(MySQLHelper.COLUMN_DOES_BREATHING_NEED_IMPROVEMENT, DBEvent.isDoesBreathingNeedImprovement());
        reportValues.put(MySQLHelper.COLUMN_DOES_FORM_NEED_IMPROVEMENT, DBEvent.isDoesFormNeedImprovement());
        reportValues.put(MySQLHelper.COLUMN_IS_ATHLETE_HUNGRY, DBEvent.isAthleteHungry());
        reportValues.put(MySQLHelper.COLUMN_IS_ATHLETE_TIRED, DBEvent.isAthleteTired());

        keyID = database.insert(MySQLHelper.TABLE_EVENTS, null, reportValues);

        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);

        return keyID;
    }

    public void addAthlete(DB_Athlete DBAthlete) {
        SQLiteDatabase database = open();
        database.beginTransaction();

        // Save DBAthlete
        ContentValues athleteValues = new ContentValues();
        athleteValues.put(MySQLHelper.COLUMN_FIRST_NAME, DBAthlete.getFirstName());
        athleteValues.put(MySQLHelper.COLUMN_LAST_NAME, DBAthlete.getLastName());
        athleteValues.put(MySQLHelper.COLUMN_EMAIL, DBAthlete.getEmail());
        athleteValues.put(MySQLHelper.COLUMN_SEX, DBAthlete.getSex());
        athleteValues.put(MySQLHelper.COLUMN_60M, DBAthlete.doesCompete60meters());
        athleteValues.put(MySQLHelper.COLUMN_100M, DBAthlete.doesCompete100meters());
        athleteValues.put(MySQLHelper.COLUMN_200M, DBAthlete.doesCompete200meters());
        athleteValues.put(MySQLHelper.COLUMN_400M, DBAthlete.doesCompete400meters());
        athleteValues.put(MySQLHelper.COLUMN_800M, DBAthlete.doesCompete800meters());
        athleteValues.put(MySQLHelper.COLUMN_1500M, DBAthlete.doesCompete1500meters());
        athleteValues.put(MySQLHelper.COLUMN_3000M, DBAthlete.doesCompete3000meters());
        athleteValues.put(MySQLHelper.COLUMN_5000M, DBAthlete.doesCompete5000meters());
        athleteValues.put(MySQLHelper.COLUMN_10000M, DBAthlete.doesCompete10000meters());
        athleteValues.put(MySQLHelper.COLUMN_60M_HURDLES, DBAthlete.doesCompete60mHurdles());
        athleteValues.put(MySQLHelper.COLUMN_100M_HURDLES, DBAthlete.doesCompete100mHurdles());
        athleteValues.put(MySQLHelper.COLUMN_110M_HURDLES, DBAthlete.doesCompete110mHurdles());
        athleteValues.put(MySQLHelper.COLUMN_400M_HURDLES, DBAthlete.doesCompete400mHurdles());
        athleteValues.put(MySQLHelper.COLUMN_3000M_STEEPLECHASE, DBAthlete.doesCompete3000mSteeplechase());
        athleteValues.put(MySQLHelper.COLUMN_4X100M_RELAY, DBAthlete.doesCompete4X100mRelay());
        athleteValues.put(MySQLHelper.COLUMN_4X400M_RELAY, DBAthlete.doesCompete4X400mRelay());
        athleteValues.put(MySQLHelper.COLUMN_LONG_JUMP, DBAthlete.doesCompeteLongJump());
        athleteValues.put(MySQLHelper.COLUMN_TRIPLE_JUMP, DBAthlete.doesCompeteTripleJump());
        athleteValues.put(MySQLHelper.COLUMN_HIGH_JUMP, DBAthlete.doesCompeteHighJump());
        athleteValues.put(MySQLHelper.COLUMN_POLE_VAULT, DBAthlete.doesCompetePoleVault());
        athleteValues.put(MySQLHelper.COLUMN_SHOT_PUT, DBAthlete.doesCompeteShotPut());
        athleteValues.put(MySQLHelper.COLUMN_DISCUS, DBAthlete.doesCompeteDiscus());
        athleteValues.put(MySQLHelper.COLUMN_HAMMER_THROW, DBAthlete.doesCompeteHammerThrow());
        athleteValues.put(MySQLHelper.COLUMN_JAVELIN, DBAthlete.doesCompeteJavelin());
        database.insert(MySQLHelper.TABLE_ATHLETES, null,  athleteValues);

        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);

    }




    // GET ALL ATHLETES
    public List<DB_Athlete> getListOfAllAthletes() {
        SQLiteDatabase database = open();

        Cursor cursor = database.query(
                MySQLHelper.TABLE_ATHLETES,
                new String[]{
                        BaseColumns._ID,
                        MySQLHelper.COLUMN_FIRST_NAME,
                        MySQLHelper.COLUMN_LAST_NAME,
                        MySQLHelper.COLUMN_EMAIL,
                        MySQLHelper.COLUMN_SEX,
                        MySQLHelper.COLUMN_60M,
                        MySQLHelper.COLUMN_100M,
                        MySQLHelper.COLUMN_200M,
                        MySQLHelper.COLUMN_400M,
                        MySQLHelper.COLUMN_800M,
                        MySQLHelper.COLUMN_1500M,
                        MySQLHelper.COLUMN_3000M,
                        MySQLHelper.COLUMN_5000M,
                        MySQLHelper.COLUMN_10000M,
                        MySQLHelper.COLUMN_60M_HURDLES,
                        MySQLHelper.COLUMN_100M_HURDLES,
                        MySQLHelper.COLUMN_110M_HURDLES,
                        MySQLHelper.COLUMN_400M_HURDLES,
                        MySQLHelper.COLUMN_3000M_STEEPLECHASE,
                        MySQLHelper.COLUMN_4X100M_RELAY,
                        MySQLHelper.COLUMN_4X400M_RELAY,
                        MySQLHelper.COLUMN_LONG_JUMP,
                        MySQLHelper.COLUMN_TRIPLE_JUMP,
                        MySQLHelper.COLUMN_HIGH_JUMP,
                        MySQLHelper.COLUMN_POLE_VAULT,
                        MySQLHelper.COLUMN_SHOT_PUT,
                        MySQLHelper.COLUMN_DISCUS,
                        MySQLHelper.COLUMN_HAMMER_THROW,
                        MySQLHelper.COLUMN_JAVELIN},
                null,
                null,
                null,
                null,
                null);
                List<DB_Athlete> DBAthletes = new LinkedList<DB_Athlete>();
                if(cursor.moveToFirst()) {
                    do {
                            DB_Athlete DBAthlete = new DB_Athlete(
                                getIntFromColumnName(cursor, BaseColumns._ID),
                                getStringFromColumnName(cursor, MySQLHelper.COLUMN_FIRST_NAME),
                                getStringFromColumnName(cursor, MySQLHelper.COLUMN_LAST_NAME),
                                getStringFromColumnName(cursor, MySQLHelper.COLUMN_EMAIL),
                                getStringFromColumnName(cursor, MySQLHelper.COLUMN_SEX),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_60M)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_100M)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_200M)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_400M)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_800M)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_1500M)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_3000M)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_5000M)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_10000M)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_60M_HURDLES)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_100M_HURDLES)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_110M_HURDLES)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_400M_HURDLES)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_3000M_STEEPLECHASE)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_4X100M_RELAY)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_4X400M_RELAY)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_LONG_JUMP)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_TRIPLE_JUMP)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_HIGH_JUMP)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_POLE_VAULT)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_SHOT_PUT)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_DISCUS)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_HAMMER_THROW)),
                                    getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_JAVELIN)));
                        DBAthletes.add(DBAthlete);
                    }while(cursor.moveToNext());
                }
        cursor.close();
        close(database);
        return DBAthletes;
    }

    // GET ALL REPORTS
    public List<DB_Report> getAllReports() {
        List<DB_Report> DBReports = new LinkedList<>();
        List<DB_Event> DBEvents = getAllEvents();
        for(DB_Event DBEvent : DBEvents) {
            DB_Report DBReport = new DB_Report();
            DBReports.add(DBReport);
            DBReport.addEvent(DBEvent);
        }

        DBReports = addAthletesToReport(DBReports);

        return DBReports;
    }

    public List<DB_Report> addAthletesToReport(List<DB_Report> DBReports){
        SQLiteDatabase database = open();

        for(DB_Report DBReport : DBReports) {
            Cursor cursor = database.rawQuery(
                    "SELECT * FROM " + MySQLHelper.TABLE_ATHLETES +
                            " WHERE " + BaseColumns._ID + "= " + DBReport.getDBEvent().getFID_Athletes(), null);

            if(cursor.moveToFirst()) {
                do {
                    DB_Athlete DBAthlete = new DB_Athlete(
                            getIntFromColumnName(cursor, BaseColumns._ID),
                            getStringFromColumnName(cursor, MySQLHelper.COLUMN_FIRST_NAME),
                            getStringFromColumnName(cursor, MySQLHelper.COLUMN_LAST_NAME),
                            getStringFromColumnName(cursor, MySQLHelper.COLUMN_EMAIL),
                            getStringFromColumnName(cursor, MySQLHelper.COLUMN_SEX),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_60M)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_100M)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_200M)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_400M)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_800M)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_1500M)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_3000M)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_5000M)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_10000M)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_60M_HURDLES)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_100M_HURDLES)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_110M_HURDLES)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_400M_HURDLES)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_3000M_STEEPLECHASE)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_4X100M_RELAY)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_4X400M_RELAY)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_LONG_JUMP)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_TRIPLE_JUMP)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_HIGH_JUMP)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_POLE_VAULT)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_SHOT_PUT)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_DISCUS)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_HAMMER_THROW)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_JAVELIN)));
                    DBReport.addAthlete(DBAthlete);
                }while(cursor.moveToNext());

            }

        }
        close(database);
        return DBReports;
    }

    private boolean getBoolean(int num) {
        if(num == 0)
            return false;
        else
            return true;
    }



    private int getIntFromColumnName(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getInt(columnIndex);
    }

    private String getStringFromColumnName(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getString(columnIndex);
    }

    private double getDoubleFromColumnName(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getDouble(columnIndex);
    }


    // GET ALL REPORTS
    public List<DB_Event> getAllEvents() {
        List<DB_Event> DBEvents = new LinkedList<DB_Event>();
        SQLiteDatabase database = open();



            Cursor cursor = database.rawQuery(
                    "SELECT * FROM " + MySQLHelper.TABLE_EVENTS, null);

            if (cursor.moveToFirst()) {
                do {
                    DB_Event newDBEvent = new DB_Event(
                            getIntFromColumnName(cursor, BaseColumns._ID),
                            getIntFromColumnName(cursor, MySQLHelper.KEY_FID),
                            getStringFromColumnName(cursor, MySQLHelper.COLUMN_EVENT_NAME),
                            getStringFromColumnName(cursor, MySQLHelper.COLUMN_DATE),
                            getStringFromColumnName(cursor, MySQLHelper.COLUMN_RESULT),
                            getStringFromColumnName(cursor, MySQLHelper.COLUMN_COMMENTS),
                            getStringFromColumnName(cursor, MySQLHelper.COLUMN_CITY),
                            getStringFromColumnName(cursor, MySQLHelper.COLUMN_COUNTRY),
                            getStringFromColumnName(cursor, MySQLHelper.COLUMN_SUMMARY),
                            getDoubleFromColumnName(cursor, MySQLHelper.COLUMN_TEMP_IN_F),
                            getDoubleFromColumnName(cursor, MySQLHelper.COLUMN_HUMIDITY),
                            getDoubleFromColumnName(cursor, MySQLHelper.COLUMN_WIND_SPEED),
                            getStringFromColumnName(cursor, MySQLHelper.COLUMN_WIND_DIRECTION),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_DOES_BREATHING_NEED_IMPROVEMENT)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_DOES_FORM_NEED_IMPROVEMENT)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_IS_ATHLETE_HUNGRY)),
                            getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_IS_ATHLETE_TIRED)));
                    DBEvents.add(newDBEvent);
                } while (cursor.moveToNext());
            }


        return DBEvents;
    }


    public List<DB_Event> getReportsByAthleteID(int athlete_id) {
        List<DB_Event> DBEvents = new LinkedList<DB_Event>();
        SQLiteDatabase database = open();



        Cursor cursor = database.rawQuery(

                "SELECT * FROM table_events WHERE table_events.foreign_id_athletes = " + athlete_id, null);

        if (cursor.moveToFirst()) {
            do {
                DB_Event newDBEvent = new DB_Event(
                        getIntFromColumnName(cursor, BaseColumns._ID),
                        getIntFromColumnName(cursor, MySQLHelper.KEY_FID),
                        getStringFromColumnName(cursor, MySQLHelper.COLUMN_EVENT_NAME),
                        getStringFromColumnName(cursor, MySQLHelper.COLUMN_DATE),
                        getStringFromColumnName(cursor, MySQLHelper.COLUMN_RESULT),
                        getStringFromColumnName(cursor, MySQLHelper.COLUMN_COMMENTS),
                        getStringFromColumnName(cursor, MySQLHelper.COLUMN_CITY),
                        getStringFromColumnName(cursor, MySQLHelper.COLUMN_COUNTRY),
                        getStringFromColumnName(cursor, MySQLHelper.COLUMN_SUMMARY),
                        getDoubleFromColumnName(cursor, MySQLHelper.COLUMN_TEMP_IN_F),
                        getDoubleFromColumnName(cursor, MySQLHelper.COLUMN_HUMIDITY),
                        getDoubleFromColumnName(cursor, MySQLHelper.COLUMN_WIND_SPEED),
                        getStringFromColumnName(cursor, MySQLHelper.COLUMN_WIND_DIRECTION),
                        getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_DOES_BREATHING_NEED_IMPROVEMENT)),
                        getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_DOES_FORM_NEED_IMPROVEMENT)),
                        getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_IS_ATHLETE_HUNGRY)),
                        getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_IS_ATHLETE_TIRED)));
                DBEvents.add(newDBEvent);
            } while (cursor.moveToNext());
        }


        return DBEvents;

    }


    // GET ALL REPORTS by athelte
    public List<DB_Report> getAllReportsByAthlete(int athlete_id) {
        List<DB_Report> DBReports = new LinkedList<>();
        List<DB_Event> DBEvents = getReportsByAthleteID(athlete_id);
        for (DB_Event DBEvent : DBEvents) {
            DB_Report DBReport = new DB_Report();
            DBReports.add(DBReport);
            DBReport.addEvent(DBEvent);
        }
        DBReports = addAthletesToReport(DBReports);

        return DBReports;
    }
    ///////////

    public List<DB_Event> getReportsByEventName(String eventName) {
        List<DB_Event> DBEvents = new LinkedList<DB_Event>();
        SQLiteDatabase database = open();



        Cursor cursor = database.rawQuery(

                "SELECT * FROM table_events WHERE event_name = " + "\"" + eventName + "\"", null);

        if (cursor.moveToFirst()) {
            do {
                DB_Event newDBEvent = new DB_Event(
                        getIntFromColumnName(cursor, BaseColumns._ID),
                        getIntFromColumnName(cursor, MySQLHelper.KEY_FID),
                        getStringFromColumnName(cursor, MySQLHelper.COLUMN_EVENT_NAME),
                        getStringFromColumnName(cursor, MySQLHelper.COLUMN_DATE),
                        getStringFromColumnName(cursor, MySQLHelper.COLUMN_RESULT),
                        getStringFromColumnName(cursor, MySQLHelper.COLUMN_COMMENTS),
                        getStringFromColumnName(cursor, MySQLHelper.COLUMN_CITY),
                        getStringFromColumnName(cursor, MySQLHelper.COLUMN_COUNTRY),
                        getStringFromColumnName(cursor, MySQLHelper.COLUMN_SUMMARY),
                        getDoubleFromColumnName(cursor, MySQLHelper.COLUMN_TEMP_IN_F),
                        getDoubleFromColumnName(cursor, MySQLHelper.COLUMN_HUMIDITY),
                        getDoubleFromColumnName(cursor, MySQLHelper.COLUMN_WIND_SPEED),
                        getStringFromColumnName(cursor, MySQLHelper.COLUMN_WIND_DIRECTION),
                        getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_DOES_BREATHING_NEED_IMPROVEMENT)),
                        getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_DOES_FORM_NEED_IMPROVEMENT)),
                        getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_IS_ATHLETE_HUNGRY)),
                        getBoolean(getIntFromColumnName(cursor, MySQLHelper.COLUMN_IS_ATHLETE_TIRED)));
                DBEvents.add(newDBEvent);
            } while (cursor.moveToNext());
        }


        return DBEvents;

    }


    ///////////



    // GET ALL REPORTS by athelte
    public List<DB_Report> getAllReportsByEvent(String eventName) {
        List<DB_Report> DBReports = new LinkedList<>();
        List<DB_Event> DBEvents = getReportsByEventName(eventName);
        for (DB_Event DBEvent : DBEvents) {
            DB_Report DBReport = new DB_Report();
            DBReports.add(DBReport);
            DBReport.addEvent(DBEvent);
        }
        DBReports = addAthletesToReport(DBReports);

        return DBReports;
    }


    public void delete_byID(int id){
        SQLiteDatabase database = open();
        database.delete(MySQLHelper.TABLE_ATHLETES, BaseColumns._ID + "=" + "'"+id+"'", null);
    }





    public void updateEvent (long id, DB_Athlete athlete, DB_Event event)
    {
        SQLiteDatabase database = open();

        ContentValues reportValues = new ContentValues();
        reportValues.put(MySQLHelper.KEY_FID, athlete.getId());
        reportValues.put(MySQLHelper.COLUMN_EVENT_NAME, event.getEventName());
        reportValues.put(MySQLHelper.COLUMN_DATE, event.getDate());
        reportValues.put(MySQLHelper.COLUMN_RESULT, event.getResult());
        reportValues.put(MySQLHelper.COLUMN_COMMENTS, event.getComments());
        reportValues.put(MySQLHelper.COLUMN_CITY, event.getCity());
        reportValues.put(MySQLHelper.COLUMN_COUNTRY, event.getCountry());
        reportValues.put(MySQLHelper.COLUMN_SUMMARY, event.getSummary());
        reportValues.put(MySQLHelper.COLUMN_TEMP_IN_F, event.getTempInF());
        reportValues.put(MySQLHelper.COLUMN_HUMIDITY, event.getHumidity());
        reportValues.put(MySQLHelper.COLUMN_WIND_SPEED, event.getWindSpeed());
        reportValues.put(MySQLHelper.COLUMN_WIND_DIRECTION, event.getWindDirection());
        reportValues.put(MySQLHelper.COLUMN_DOES_BREATHING_NEED_IMPROVEMENT, event.isDoesBreathingNeedImprovement());
        reportValues.put(MySQLHelper.COLUMN_DOES_FORM_NEED_IMPROVEMENT, event.isDoesFormNeedImprovement());
        reportValues.put(MySQLHelper.COLUMN_IS_ATHLETE_HUNGRY, event.isAthleteHungry());
        reportValues.put(MySQLHelper.COLUMN_IS_ATHLETE_TIRED, event.isAthleteTired());

        database.update(MySQLHelper.TABLE_EVENTS, reportValues, BaseColumns._ID + " = ? ", new String[]{Integer.toString((int)id)});

    }
}



