package com.hippie.trackitcoach.ui;

import android.app.ListActivity;
import android.content.Context;
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

public class RemoveAthleteActivity extends ListActivity {
    public static final List<DB_Athlete> mSelectedAthletesList = new LinkedList<DB_Athlete>();
    DB_Athlete[] mAthletes;
    public static final String CHOSEN_ATHLETES = "CHOSEN_ATHLETES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_athlete);


        mAthletes = getAthleteNames(this);
        final List<String> names = new LinkedList<String>();
        for(DB_Athlete athlete : mAthletes){
            String name = athlete.getFirstName() + " " + athlete.getLastName();
            names.add(name);
        }

        String[] namesArray = names.toArray(new String[names.size()]);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked,namesArray );
        setListAdapter(adapter);

        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);




        Button removeButton = (Button) findViewById(R.id.delete_button);
        final ListView list = getListView();
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataSource dataSource = new MyDataSource(RemoveAthleteActivity.this);
                getCheckAthletes(list);

                for (int i = 0; i < mSelectedAthletesList.size(); i++) {
                    int athlete_id = mSelectedAthletesList.get(i).getId();

                    dataSource.delete_byID(athlete_id);

                    names.remove(i);

                }
                finish();


            }
        });

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


    private void getCheckAthletes(ListView listview) {
        int count = listview.getCount();
        for (int i = 0; i < count; i++) {
            if (listview.isItemChecked(i)) {
                mSelectedAthletesList.add(mAthletes[i]);
            }
        }
    }
}
