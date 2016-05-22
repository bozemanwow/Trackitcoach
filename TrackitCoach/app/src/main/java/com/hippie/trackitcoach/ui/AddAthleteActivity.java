package com.hippie.trackitcoach.ui;

import android.app.ListActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.hippie.trackitcoach.R;
import com.hippie.trackitcoach.database.MyDataSource;
import com.hippie.trackitcoach.models.DB_Athlete;

import java.util.LinkedList;
import java.util.List;

public class AddAthleteActivity extends ListActivity {


    private String[] eventList;
    private List participateInEventsList = new LinkedList();
    private DB_Athlete DBAthlete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_athlete);

        // View Setup
        final TextView firstName = (TextView) findViewById(R.id.firstName_editText);
        final TextView lastName = (TextView) findViewById(R.id.lastName_editText);
        final TextView email = (TextView) findViewById(R.id.email_editText);
        final CheckBox male = (CheckBox) findViewById(R.id.male_check_box);
        final CheckBox female = (CheckBox) findViewById(R.id.female_check_box);


        Button saveAthleteButton = (Button) findViewById(R.id.save_athlete_button);


        Resources res = getResources();
       eventList = res.getStringArray(R.array.eventList);


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddAthleteActivity.this, android.R.layout.simple_list_item_checked, eventList );
        setListAdapter(adapter);

        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        final ListView listView = getListView();



        saveAthleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBAthlete = new DB_Athlete();
                int count = eventList.length;
                for(int i = 0; i < count; i++) {
                    if(listView.isItemChecked(i)){
                        String eventName = eventList[i];
                        setCompeteEvents(DBAthlete, eventName);
                    }
                }
                MyDataSource dataSource = new MyDataSource(AddAthleteActivity.this);


                DBAthlete.setFirstName(firstName.getText().toString());
                DBAthlete.setLastName(lastName.getText().toString());
                DBAthlete.setEmail(email.getText().toString());



                dataSource.addAthlete(DBAthlete);
                finish();


            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_and_remove_athlete, menu);
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



    private void setCompeteEvents(DB_Athlete athlete, String event) {


            if(event.equals("60 Meters"))
                athlete.setCompete60meters(true);
            else if(event.equals("100 Meters"))
                athlete.setCompete100meters(true);
            else if(event.equals("200 Meters"))
                athlete.setCompete200meters(true);
            else if(event.equals("400 Meters"))
                athlete.setCompete400meters(true);
            else if(event.equals("800 Meters"))
                athlete.setCompete800meters(true);
            else if(event.equals("1500 Meters"))
                athlete.setCompete1500meters(true);
            else if(event.equals("3000 Meters"))
                athlete.setCompete3000meters(true);
            else if(event.equals("5000 Meters"))
                athlete.setCompete5000meters(true);
            else if(event.equals("10000 Meters"))
                athlete.setCompete10000meters(true);
            else if(event.equals("60 Meters Hurdles"))
                athlete.setCompete60mHurdles(true);
            else if(event.equals("100 Meters Hurdles"))
                athlete.setCompete100mHurdles(true);
            else if(event.equals("110 Meters Hurdles"))
                athlete.setCompete110mHurdles(true);
            else if(event.equals("400  Meters Hurdles"))
                athlete.setCompete400mHurdles(true);
            else if(event.equals("3000 Meters Steeplechase"))
                athlete.setCompete3000mSteeplechase(true);
            else if(event.equals("4X100 Meters"))
                athlete.setCompete4X100mRelay(true);
            else if(event.equals("4X400 Meters"))
                athlete.setCompete4X400mRelay(true);
            else if(event.equals("Long Jump"))
                athlete.setCompeteLongJump(true);
            else if(event.equals("Triple Jump"))
                athlete.setCompeteTripleJump(true);
            else if(event.equals("High Jump"))
                athlete.setCompeteHighJump(true);
            else if(event.equals("Pole Vault"))
                athlete.setCompetePoleVault(true);
            else if(event.equals("Shot Put"))
                athlete.setCompeteShotPut(true);
            else if(event.equals("Discus Throw"))
                athlete.setCompeteDiscus(true);
            else if(event.equals("Hammer Throw"))
                athlete.setCompeteHammerThrow(true);
            else if(event.equals("Javelin Throw"))
                athlete.setCompeteJavelin(true);
        }


    private void getCheckedEvents(ListView listview) {
        int count = listview.getCount();
        for (int i = 0; i < count; i++) {
            if (listview.isItemChecked(i)) {
                String eventName = listview.getAdapter().getItem(i).toString();
                participateInEventsList.add(eventName);
            }
        }
    }
}















