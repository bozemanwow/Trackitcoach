package com.hippie.trackitcoach.ui;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hippie.trackitcoach.R;
import com.hippie.trackitcoach.adapters.EventListAdapter;
import com.hippie.trackitcoach.adapters.ReportListAdapter;
import com.hippie.trackitcoach.database.MyDataSource;
import com.hippie.trackitcoach.models.DB_Athlete;
import com.hippie.trackitcoach.models.DB_Report;
import com.hippie.trackitcoach.models.ReportListItem;

import java.util.LinkedList;
import java.util.List;

public class SearchRecordsActivity extends ListActivity implements AdapterView.OnItemSelectedListener{



    public static final String REVIEW_REPORT = "REVIEW_REPORT";
    public static final String ATHLETE_ID = "ATHLETE_ID";
    public static final String EVENT_NAME = "EVENT_NAME";

    private String reportType;
    private List<DB_Report> mDBReports;
    private DB_Athlete[] mAthletes;
    private String[] namesArray;
    private int mId;
    private String[] eventNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_records);


        Spinner spins = (Spinner) findViewById(R.id.search_type_spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.searchType, android.R.layout.simple_spinner_item);
        spins.setAdapter(adapter);
        spins.setOnItemSelectedListener(this);


        // GETS REQUESTED REPORT FROM LIST TO VIEW
        final ListView listView = getListView();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (reportType.equals("All Reports")) {
                    displayAllReports();
                    DB_Report report = mDBReports.get(position);
                    Intent intent = new Intent(SearchRecordsActivity.this, ViewReportActivity.class);
                    intent.putExtra(REVIEW_REPORT, report);
                    startActivity(intent);
                } else if (reportType.equals("By Reports")) {

                } else if (reportType.equals("By Athlete")) {
                    DB_Athlete athlete = mAthletes[position];
                    mId = athlete.getId();
                    Intent intent = new Intent(SearchRecordsActivity.this, ReviewByAthleteSelectActivity.class);
                    intent.putExtra(ATHLETE_ID, mId);
                    startActivity(intent);


                } else if (reportType.equals("By Event")) {
                    Intent intent = new Intent(SearchRecordsActivity.this, ReviewByEventActivity.class);
                    ListView listView = getListView();


                            TextView textView = (TextView) view.findViewById(R.id.event_list_view);
                            String event = textView.getText().toString();


                            intent.putExtra(EVENT_NAME, event);
                            startActivity(intent);



                } else {

                }


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_records, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void displayAllReports() {

        MyDataSource dataSource = new MyDataSource(SearchRecordsActivity.this);
        mDBReports = dataSource.getAllReports();
        int size = mDBReports.size();
        ReportListItem mReportItems[] = new ReportListItem[size];
        for(int i = 0; i < size; i++) {
            ReportListItem item = new ReportListItem(mDBReports.get(i));
            mReportItems[i] = item;
        }

        ReportListAdapter adapter = new ReportListAdapter(SearchRecordsActivity.this, mReportItems );
        setListAdapter(adapter);
    }

    public void displayByAthlete(int id) {
        MyDataSource dataSource = new MyDataSource(SearchRecordsActivity.this);
        mDBReports = dataSource.getAllReportsByAthlete(id);
        int size = mDBReports.size();
        ReportListItem mReportItems[] = new ReportListItem[size];
        for(int i = 0; i < size; i++) {
            ReportListItem item = new ReportListItem(mDBReports.get(i));
            mReportItems[i] = item;
        }
        ReportListAdapter adapter = new ReportListAdapter(SearchRecordsActivity.this, mReportItems );
        setListAdapter(adapter);
    }




    public void showAthleteNames() {
        mAthletes = getAthleteNames(this);
        List<String> names = new LinkedList<String>();
        for(DB_Athlete athlete : mAthletes){
            String name = athlete.getFirstName() + " " + athlete.getLastName();
            names.add(name);
        }

        namesArray = names.toArray(new String[names.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,namesArray );
        setListAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView myText = (TextView) view;
        reportType = myText.getText().toString();
        Toast.makeText(this, "You Selected " + reportType, Toast.LENGTH_SHORT).show();
        if (reportType.equals("All Reports")) {
            displayAllReports();
        }
        else if (reportType.equals("By Reports")) {

        } else if (reportType.equals("By Athlete")) {
            showAthleteNames();

        }
        else if (reportType.equals("By Event")) {
            eventNames = getResources().getStringArray(R.array.eventList);
            EventListAdapter adapter = new EventListAdapter(this, eventNames);
            setListAdapter(adapter);
        }

        else Toast.makeText(getApplicationContext(), "Must Select Type",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public DB_Athlete [] getAthleteNames(Context context) {
        MyDataSource dataSource = new MyDataSource(this);
        List<DB_Athlete> mDB_athletes;
        mDB_athletes = dataSource.getListOfAllAthletes();
        DB_Athlete[] athleteNames = AthleteListToArray(mDB_athletes);

        return athleteNames;
    }

    public DB_Athlete[] AthleteListToArray(List<DB_Athlete> _list) {
        int size = _list.size();
        DB_Athlete[] mArray = _list.toArray(new DB_Athlete[size]);

        return mArray;
    }
}
