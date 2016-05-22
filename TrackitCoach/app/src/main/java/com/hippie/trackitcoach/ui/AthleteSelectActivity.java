package com.hippie.trackitcoach.ui;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.hippie.trackitcoach.R;
import com.hippie.trackitcoach.database.MyDataSource;
import com.hippie.trackitcoach.models.DB_Athlete;

import java.util.LinkedList;
import java.util.List;

public class AthleteSelectActivity extends ListActivity {
    public static final List<DB_Athlete> mSelectedAthletesList = new LinkedList<DB_Athlete>();
    public static final String CHOSEN_ATHLETES = "CHOSEN_ATHLETES";
    DB_Athlete[] mAthletes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athlete_select);


        Intent intent = getIntent();
        final String eventName = intent.getStringExtra("event_name");
        mAthletes = getAthleteNames(this);
        List<String> names = new LinkedList<String>();
        for (DB_Athlete athlete : mAthletes) {
            if (doesAthleteParticpateInEvent(athlete, eventName)) {
                String name = athlete.getFirstName() + " " + athlete.getLastName();
                names.add(name);
            }
        }

        String[] namesArray = names.toArray(new String[names.size()]);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, namesArray);
        setListAdapter(adapter);

        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        final ListView list = getListView();

        Button startButton = (Button) findViewById(R.id.start_event_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getCheckAthletes(list);

                Intent intent = new Intent(AthleteSelectActivity.this, RunEventActivity.class);
                intent.putExtra("event_name", eventName);
                DB_Athlete[] chosenAthletesArray = mSelectedAthletesList.toArray(new DB_Athlete[mSelectedAthletesList.size()]);
                intent.putExtra(CHOSEN_ATHLETES, chosenAthletesArray);

                startActivity(intent);
                //   finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSelectedAthletesList.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_athlete_select, menu);
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

    public DB_Athlete[] getAthleteNames(Context context) {
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

    private void getCheckAthletes(ListView listview) {
        int count = listview.getCount();
        for (int i = 0; i < count; i++) {
            if (listview.isItemChecked(i)) {
                mSelectedAthletesList.add(mAthletes[i]);
            }
        }
    }

    private boolean doesAthleteParticpateInEvent(DB_Athlete athlete, String event) {
        if (event.equals("60 Meters") && athlete.doesCompete60meters())
            return true;
        else if (event.equals("100 Meters") && athlete.doesCompete100meters())
            return true;
        else if (event.equals("200 Meters") && athlete.doesCompete200meters())
            return true;
        else if (event.equals("400 Meters") && athlete.doesCompete400meters())
            return true;
        else if (event.equals("800 Meters") && athlete.doesCompete800meters())
            return true;
        else if (event.equals("1500 Meters") && athlete.doesCompete1500meters())
            return true;
        else if (event.equals("3000 Meters") && athlete.doesCompete3000meters())
            return true;
        else if (event.equals("5000 Meters") && athlete.doesCompete5000meters())
            return true;
        else if (event.equals("10000 Meters") && athlete.doesCompete10000meters())
            return true;
        else if (event.equals("60 Meters Hurdles") && athlete.doesCompete60mHurdles())
            return true;
        else if (event.equals("100 Meters Hurdles") && athlete.doesCompete100mHurdles())
            return true;
        else if (event.equals("110 Meters Hurdles") && athlete.doesCompete110mHurdles())
            return true;
        else if (event.equals("400  Meters Hurdles") && athlete.doesCompete400mHurdles())
            return true;
        else if (event.equals("3000 Meters Steeplechase") && athlete.doesCompete3000mSteeplechase())
            return true;
        else if (event.equals("4X100 Meters") && athlete.doesCompete4X100mRelay())
            return true;
        else if (event.equals("4X400 Meters") && athlete.doesCompete4X400mRelay())
            return true;
        else if (event.equals("Long Jump") && athlete.doesCompeteLongJump())
            return true;
        else if (event.equals("Triple Jump") && athlete.doesCompeteTripleJump())
            return true;
        else if (event.equals("High Jump") && athlete.doesCompeteHighJump())
            return true;
        else if (event.equals("Pole Vault") && athlete.doesCompetePoleVault())
            return true;
        else if (event.equals("Shot Put") && athlete.doesCompeteShotPut())
            return true;
        else if (event.equals("Discus Throw") && athlete.doesCompeteDiscus())
            return true;
        else if (event.equals("Hammer Throw") && athlete.doesCompeteHammerThrow())
            return true;
        else if (event.equals("Javelin Throw") && athlete.doesCompeteJavelin())
            return true;
        else
            return false;

    }
}
