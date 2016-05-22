package com.hippie.trackitcoach.models;

/**
 * Created by Hippie on 8/23/2015.
 */
public class ReportListItem {

    private String mEventName;
    private String mAthleteName;
    private String mDate;


    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getEventName() {
        return mEventName;
    }

    public void setEventName(String eventName) {
        mEventName = eventName;
    }

    public String getAthleteName() {
        return mAthleteName;
    }

    public void setAthleteName(String athleteName) {
        mAthleteName = athleteName;
    }

    public ReportListItem() {}


    public ReportListItem(DB_Report DBReport) {
        mEventName = DBReport.getDBEvent().getEventName();
        mAthleteName = DBReport.getDBAthlete().getFirstName() + DBReport.getDBAthlete().getLastName();
        mDate = DBReport.getDBEvent().getDate();
    }
}
