package com.hippie.trackitcoach.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hippie.trackitcoach.R;
import com.hippie.trackitcoach.adapters.ReportListAdapter;
import com.hippie.trackitcoach.database.MyDataSource;
import com.hippie.trackitcoach.models.DB_Report;
import com.hippie.trackitcoach.models.ReportListItem;

import java.util.List;

public class ReviewByAthleteSelectActivity extends ListActivity {
    public final static String ATHLETE_REPORT = "ATHLETE_REPORT";
    private List<DB_Report> mDBReports;
    private int mId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_by_athlete_select);
        Intent intent = getIntent();
        mId = intent.getIntExtra("ATHLETE_ID", 0);

        displayByAthlete(mId);



        ListView listView = getListView();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DB_Report report = mDBReports.get(position);
                Intent intent = new Intent(ReviewByAthleteSelectActivity.this, Athlete_Report.class);
                intent.putExtra(ATHLETE_REPORT, report);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_review_by_athlete_select, menu);
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


    public void displayByAthlete(int id) {
        MyDataSource dataSource = new MyDataSource(ReviewByAthleteSelectActivity.this);
        mDBReports = dataSource.getAllReportsByAthlete(id);
        int size = mDBReports.size();
        ReportListItem mReportItems[] = new ReportListItem[size];
        for(int i = 0; i < size; i++) {
            ReportListItem item = new ReportListItem(mDBReports.get(i));
            mReportItems[i] = item;
        }
        ReportListAdapter adapter = new ReportListAdapter(ReviewByAthleteSelectActivity.this, mReportItems );
        setListAdapter(adapter);

    }
}
