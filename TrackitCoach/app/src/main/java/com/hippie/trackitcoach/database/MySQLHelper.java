package com.hippie.trackitcoach.database;

/**
 * Created by Hippie on 8/20/2015.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class MySQLHelper extends SQLiteOpenHelper {

    // Database details

    private static final String DATABASE_NAME = "reports.db";
    private static final int DATABASE_VERSION = 1;


    // reports table name
    public static final String TABLE_ATHLETES = "table_athletes";
    public static final String TABLE_EVENTS = "table_events";


    // reports Table Columns names
    public static final String KEY_FID = "foreign_id_athletes";
    public static final String COLUMN_EVENT_NAME = "event_name";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_RESULT = "result";
    public static final String COLUMN_HEIGHT = "height";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_COMMENTS = "comments";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_SUMMARY = "summary";
    public static final String COLUMN_TEMP_IN_F = "temp_in_f";
    public static final String COLUMN_HUMIDITY = "humidity";
    public static final String COLUMN_WIND_SPEED = "wind_speed";
    public static final String COLUMN_WIND_DIRECTION = "wind_direction";
    public static final String COLUMN_DOES_BREATHING_NEED_IMPROVEMENT = "breathing";
    public static final String COLUMN_DOES_FORM_NEED_IMPROVEMENT = "form";
    public static final String COLUMN_IS_ATHLETE_HUNGRY = "hungry";
    public static final String COLUMN_IS_ATHLETE_TIRED = "tired";
    private static String CREATE_EVENTS = "CREATE TABLE table_events ( " +
            BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "foreign_id_athletes INTEGER, " +
            "event_name TEXT, " +
            "date TEXT, " +
            "result TEXT, " +
            "comments TEXT, " +
            "city TEXT, " +
            "country TEXT, " +
            "summary TEXT, " +
            "temp_in_f REAL, " +
            "humidity REAL, " +
            "wind_speed REAL, " +
            "wind_direction TEXT, " +
            "breathing INTEGER, " +
            "form INTEGER, " +
            "hungry INTEGER, " +
            "tired INTEGER )";


    // Athletes table

    // Athletes Table Columns names
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_SEX = "sex";
    public static final String COLUMN_60M = "run_60m";
    public static final String COLUMN_100M = "run_100m";
    public static final String COLUMN_200M = "run_200m";
    public static final String COLUMN_400M = "run_400m";
    public static final String COLUMN_800M = "run_800m";
    public static final String COLUMN_1500M = "run_1500m";
    public static final String COLUMN_3000M = "run_3000m";
    public static final String COLUMN_5000M = "run_5000m";
    public static final String COLUMN_10000M = "run_10000m";
    public static final String COLUMN_60M_HURDLES = "run_60m_hurdles";
    public static final String COLUMN_100M_HURDLES = "run_100m_hurdles";
    public static final String COLUMN_110M_HURDLES = "run_110m_hurdles";
    public static final String COLUMN_400M_HURDLES = "run_400m_hurdles";
    public static final String COLUMN_3000M_STEEPLECHASE = "steeple_chase";
    public static final String COLUMN_4X100M_RELAY = "relay_4x100m";
    public static final String COLUMN_4X400M_RELAY = "relay_4x400m";
    public static final String COLUMN_LONG_JUMP = "long_jump";
    public static final String COLUMN_TRIPLE_JUMP = "triple_jump";
    public static final String COLUMN_HIGH_JUMP = "high_jump";
    public static final String COLUMN_POLE_VAULT = "pole_vault";
    public static final String COLUMN_SHOT_PUT = "shot_put";
    public static final String COLUMN_DISCUS = "discus";
    public static final String COLUMN_HAMMER_THROW = "hammer_throw";
    public static final String COLUMN_JAVELIN = "javelin";
    private static String CREATE_ATHLETES = "CREATE TABLE table_athletes ( " +
            BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "first_name INTEGER, " +
            "last_name TEXT, " +
            "email TEXT, " +
            "sex TEXT, " +
            "run_60m INTEGER, " +
            "run_100m INTEGER, " +
            "run_200m INTEGER, " +
            "run_400m INTEGER, " +
            "run_800m INTEGER, " +
            "run_1500m INTEGER, " +
            "run_3000m INTEGER, " +
            "run_5000m INTEGER, " +
            "run_10000m INTEGER, " +
            "run_60m_hurdles INTEGER, " +
            "run_100m_hurdles INTEGER, " +
            "run_110m_hurdles INTEGER, " +
            "run_400m_hurdles INTEGER, " +
            "steeple_chase INTEGER, " +
            "relay_4x100m INTEGER, " +
            "relay_4x400m INTEGER, " +
            "long_jump INTEGER, " +
            "triple_jump INTEGER, " +
            "high_jump INTEGER, " +
            "pole_vault INTEGER, " +
            "shot_put INTEGER, " +
            "discus INTEGER, " +
            "hammer_throw INTEGER, " +
            "javelin INTEGER)";



    // DB_Event Table functionality
    public MySQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
      sqLiteDatabase.execSQL(CREATE_EVENTS);
      sqLiteDatabase.execSQL(CREATE_ATHLETES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Drop older reports table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS table_reports");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS table_athletes");

        // addReport fresh report table
        this.onCreate(sqLiteDatabase);
    }



}
