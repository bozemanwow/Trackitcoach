package com.hippie.trackitcoach.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hippie.trackitcoach.R;
import com.hippie.trackitcoach.adapters.ReportListAdapter;
import com.hippie.trackitcoach.database.MyDataSource;
import com.hippie.trackitcoach.models.DB_Report;
import com.hippie.trackitcoach.models.ReportListItem;

import java.util.List;

public class ReviewByEventActivity extends ListActivity {
    private List<DB_Report> mDBReports;
    public static final String EVENT_REPORT = "EVENT_REPORT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_by_event);

        Bundle extras = getIntent().getExtras();
        String eventName = extras.getString(SearchRecordsActivity.EVENT_NAME);

        MyDataSource dataSource = new MyDataSource(this);
        mDBReports = dataSource.getAllReportsByEvent(getEventTitle(eventName));

        int size = mDBReports.size();
        ReportListItem mReportItems[] = new ReportListItem[size];
        for(int i = 0; i < size; i++) {
            ReportListItem item = new ReportListItem(mDBReports.get(i));
            mReportItems[i] = item;
        }
        ReportListAdapter adapter = new ReportListAdapter(ReviewByEventActivity.this, mReportItems );
        setListAdapter(adapter);





        ListView listView = getListView();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DB_Report report = mDBReports.get(position);
                Intent intent = new Intent(ReviewByEventActivity.this, EventReportActivity.class);
                intent.putExtra(EVENT_REPORT, report);
                startActivity(intent);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_report_view, menu);
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

    public String getEventTitle(String name) {
        String eventTitle = "";

        if(name.equals("60 meters")){
            eventTitle = "60m";
        }
        else if(name.equals("100 meters")){
            eventTitle = "100m";
        }
        else if(name.equals("200 meters")){
            eventTitle = "200m";
        }
        else if(name.equals("400 meters")){
            eventTitle = "400m";
        }
        else if(name.equals("800 meters")){
            eventTitle = "800m";
        }
        else if(name.equals("1500 meters")){
            eventTitle = "1500m";
        }
        else if(name.equals("3000 meters")){
            eventTitle = "3000m";
        }
        else if(name.equals("5000 meters")){
            eventTitle = "5000m";
        }
        else if(name.equals("10000 meters")){
            eventTitle = "10000m";
        }
        else if(name.equals("60 meters Hurdles")){
            eventTitle = "60m_hurdles";
        }
        else if(name.equals("100 meters hurdles")){
            eventTitle = "100m_hurdles";
        }
        else if(name.equals("110 meters hurdles")){
            eventTitle = "110m_hurdles";
        }
        else if(name.equals("400  meters hurdles")){
            eventTitle = "400m_hurdles";
        }
        else if(name.equals("3000 meters steeplechase")){
            eventTitle = "steeple_chase";
        }
        else if(name.equals("4X100 meters")){
            eventTitle = "4x100m";
        }
        else if(name.equals("4X400 meters")){
            eventTitle = "4x400m";
        }
        else if(name.equals("Long Jump")){
            eventTitle = "long_jump";
        }
        else if(name.equals("Triple Jump")){
            eventTitle = "triple_jump";
        }
        else if(name.equals("High Jump")){
            eventTitle = "high_jump";
        }
        else if(name.equals("Pole vault Jump")){
            eventTitle = "pole_vault";
        }
        else if(name.equals("Shot put throw")){
            eventTitle = "shot_put";
        }
        else if(name.equals("Discus throw")){
            eventTitle = "discus";
        }
        else if(name.equals("Hammer throw")){
            eventTitle = "hammer_throw";
        }
        else if (name.equals("Javelin throw")){
            eventTitle = "javelin";
        }
        else
            Toast.makeText(getApplicationContext(), "No Event By That Name", Toast.LENGTH_SHORT).show();


        return eventTitle;
    }
}
