package com.hippie.trackitcoach.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.hippie.trackitcoach.R;
import com.hippie.trackitcoach.database.MyDataSource;
import com.hippie.trackitcoach.models.DB_Athlete;
import com.hippie.trackitcoach.models.DB_Event;
import com.hippie.trackitcoach.utilities.Millitimer;
import com.hippie.trackitcoach.utilities.WeatherCollector;

import java.util.Arrays;

public class RunEventActivity extends AppCompatActivity {
    private long[] eventKeys;

    // Array of Chosen Atheletes
    DB_Athlete[]Athes;
    // Array of event information
    DB_Event[] EventInfo;
    //CheckBoxes
    CheckBox Form;
    CheckBox Breathing;
    CheckBox Hunger;
    CheckBox Tired;

    public TextView AtheName;
    public TextView ClockTimer;
    TextView Eventname;
    TextView Report;

    EditText Comments;
    EditText Dis;

    boolean Resetter=true;
    public PowerManager pm ;
    public  PowerManager.WakeLock wl;
    boolean Timed;

    Button AddNote;
    Button EndEvent;
    Button PrevBut;
    Button NextBut;

    Button StartTime;
    Button AddLap;
    Button EndTime;
    Button ResetTime;

    Button EnterHeight;

    Millitimer milTimer;
    Thread ClockingThread;

    int  AtheIndex =0;
    String event;

    Thread Timey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_event);



        AssignVeiwsandButtons();


        SetInformation();
        eventKeys = new long[Athes.length];
        for(int i = 0; i < eventKeys.length; i++){
            eventKeys[i] = -5;
        }
        Button saveButton = (Button) findViewById(R.id.save_report_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReport();
            }
        });



        SetLayout();




        if(Timed)
        {
            milTimer = new Millitimer(this,ClockTimer);
            Timey = new Thread(milTimer);
            Timey.start();
            Timey.setPriority(1);
        }
        new WeatherCollector(getApplicationContext(),this,Eventname,EventInfo);
    }
    public boolean IsTimed()
    {
        if(event.contains("throw")||event.contains("Jump"))
            return false;
        else
            return  true;


    }
    void AssignVeiwsandButtons(){
        Eventname = (TextView) findViewById(R.id.textViewEventName);
        Form = (CheckBox) findViewById(R.id.checkBoxForm);
        Breathing = (CheckBox) findViewById(R.id.checkBoxBreath);
        Hunger = (CheckBox) findViewById(R.id.checkBoxHunger);
        Tired = (CheckBox) findViewById(R.id.checkBoxTired);
        AtheName = (TextView) findViewById(R.id.textViewAthlete);
        Comments = (EditText)findViewById(R.id.editTextDataLog);
        Report = (TextView) findViewById(R.id.textViewResults);

        Dis = (EditText) findViewById(R.id.editTextHeight);
        ClockTimer = (TextView)findViewById(R.id.chronometerTimer);
        Comments.setText("");

        //  AddNote = (Button) findViewById(R.id.no);
        EndEvent = (Button) findViewById(R.id.buttonEndEvent);
        PrevBut = (Button) findViewById(R.id.buttonPrev);
        NextBut = (Button) findViewById(R.id.buttonNext);

        StartTime = (Button) findViewById(R.id.buttonStartTIme);
        AddLap    = (Button) findViewById(R.id.buttonLap);
        EndTime   = (Button) findViewById(R.id.buttonEndTime);
        ResetTime = (Button) findViewById(R.id.buttonResetTimer);

        //   AddNote = (Button) findViewById(R.id.buttonAddNote);
        EndEvent = (Button) findViewById(R.id.buttonEndEvent);
        PrevBut = (Button) findViewById(R.id.buttonPrev);
        NextBut = (Button) findViewById(R.id.buttonNext);

        EnterHeight = (Button) findViewById(R.id.buttonHeight);


    }
    private void SetInformation() {
        Intent intent = getIntent();
        // Name of Event
        event = intent.getStringExtra("event_name");

        //Sets up array of atheletes
        Parcelable[] parcelables = intent.getParcelableArrayExtra(AthleteSelectActivity.CHOSEN_ATHLETES);
        Athes= Arrays.copyOf(parcelables, parcelables.length, DB_Athlete[].class);

        EventInfo = new DB_Event[Athes.length];

        for (int i = 0; i < EventInfo.length; i++) {
            EventInfo[i]= new DB_Event();
            EventInfo[i].setEventName(event);
        }
        Timed = IsTimed();
        Eventname.setText(event);
        AtheIndex =0;
        // setup intital information
        AtheName.setText(Athes[AtheIndex].getLastName()+ " "+ Athes[AtheIndex].getFirstName());
        Form.setChecked(false);
        Breathing.setChecked(false);
        Hunger.setChecked(false);
        Tired.setChecked(false);


    }
    private void SetLayout()
    {
        if(Timed)
        {
            pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyWakelockTag");

            // this will turn off veiws that are not needed for now
            Dis.setVisibility(View.GONE);

            EnterHeight.setVisibility(View.GONE);
            // disabled veiw if not needed
            EndTime.setEnabled(false);

            // setup buttons for timed event
            StartTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Settup Timer
                    milTimer.StartWatch();
                    if(!wl.isHeld())
                        wl.acquire();
// Switch enabled for the buttons
                    StartTime.setEnabled(false);
                    EndTime.setEnabled(true);
                }
            });
            // Stops time to be continued later
            EndTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Resetter = false;
                    // Pauses the timer
                    milTimer.PauseWatch();
                    if(wl.isHeld())
                        wl.release();

                    StartTime.setEnabled(true);
                    EndTime.setEnabled(false);
                }
            });
            // Puts current timer info into the report section for the athlete
            AddLap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Information = "Lap: " + ClockTimer.getText() + " ";
                    Report.setText(Report.getText() + Information);
                    if(milTimer.isTicking())
                        addValueForEvent();
                }
            });
            // meant to reset the timer to zero and stop the timer
            ResetTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Resetter = true;
                    //Makes timer reset to 00;
                    milTimer.ResetWatch();

                    StartTime.setEnabled(true);
                    EndTime.setEnabled(false);

                    if(wl.isHeld())
                        wl.release();
                }
            });

        }else {
            //Disabled Unused
            ClockTimer.setVisibility(View.GONE);
            StartTime.setVisibility(View.GONE);
            AddLap.setVisibility(View.GONE);
            EndTime.setVisibility(View.GONE);
            ResetTime.setVisibility(View.GONE);
// Meant to input information about the distance as needed in the report box
            EnterHeight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Dis.getText().toString().compareTo("") != 0) {
                        String Information = Comments.getText().toString();
                        addValueForEvent();
                        Information += "Distance: " + Dis.getText() + "\n";
                        Dis.setText("");
                        Comments.setText(Information);
                    }

                }
            });
        }

        // Generic Setting cycles though athletes as needed
        NextBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextPageInformationSetUp(1);
            }
        });
        PrevBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextPageInformationSetUp(-1);
            }
        });


    }
    void addValueForEvent()
    {
        EventInfo[AtheIndex].setResult(Report.getText().toString() + " " + EventInfo[AtheIndex].getResult());
    }
    void NextPageInformationSetUp(int count)
    {
//set current athlestes according screen information
        EventInfo[AtheIndex].setDoesFormNeedImprovement(Form.isChecked());
        EventInfo[AtheIndex].setDoesBreathingNeedImprovement(Breathing.isChecked());
        EventInfo[AtheIndex].setIsAthleteHungry(Hunger.isChecked());
        EventInfo[AtheIndex].setIsAthleteTired(Tired.isChecked());
        EventInfo[AtheIndex].setComments(Comments.getText().toString());
        EventInfo[AtheIndex].setResult(Report.getText().toString());
        // cycle left or right based on count-
        AtheIndex+= count;
        // make sure always in range of the array
        if(AtheIndex>= Athes.length)
            AtheIndex=0;
        else if(AtheIndex < 0)
            AtheIndex = Athes.length-1;
        // assigin page information accodring the new athelete selected
        //Setting Up information for next person
        AtheName.setText(Athes[AtheIndex].getFirstName() + " " + Athes[AtheIndex].getLastName());
        Form.setChecked( EventInfo[AtheIndex].isAthleteTired());
        Breathing.setChecked( EventInfo[AtheIndex].isDoesBreathingNeedImprovement());
        Hunger.setChecked( EventInfo[AtheIndex].isAthleteHungry());
        Tired.setChecked( EventInfo[AtheIndex].isAthleteTired());
        Comments.setText(EventInfo[AtheIndex].getComments());
        Report.setText(EventInfo[AtheIndex].getResult());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_run_event, menu);
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
    public void EndandFinsihEvent(View Ve)
    {
        // If Timed Release CP Awake
        EndThreadoFTimer();
        //To Cover if only one person was there or nothing else was moved
        NextPageInformationSetUp(0);

        // Get Back the Athletes
        //  ManApp.Eve.Athe = Athes;
        // setup intent
//        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        String Report = event +"\n"+ EventInfo[0].GetWeatherOverView();
        MyDataSource dataSource = new MyDataSource(this);
        for(int i=0; i < EventInfo.length; i++ )
        {
            Report += Athes[i].ReportCall()+"\n"+ EventInfo[i].GenerateReport()+"\n";

            EventInfo[i].setFID_Athletes(Athes[i].getId());
            dataSource.addReport(Athes[i], EventInfo[i]);

        }


 //       emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Report From Event " +event + ": ");
        // makes a report to pass as a string
//        emailIntent.putExtra(Intent.EXTRA_TEXT,Report);

 //       emailIntent.setType("message/rfc822");





//        startActivityForResult(Intent.createChooser(emailIntent, "Choose mail..."), 0);
        Intent intent = new Intent(this, ChooseActivity.class);
        startActivity(intent);


    }
    private void EndThreadoFTimer()
    {
        if( Timed) {
            if (wl.isHeld())
                wl.release();
            milTimer.FinsihThread();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //if(requestCode == Activity.RESULT_OK)
        finish();
    }



    public void saveReport() {
        MyDataSource dataSource = new MyDataSource(this);
        if (eventKeys[AtheIndex] < 0) {
            NextPageInformationSetUp(0);
           eventKeys[AtheIndex] = dataSource.addReport(Athes[AtheIndex], EventInfo[AtheIndex]);
        }
        else {
            dataSource.updateEvent(eventKeys[AtheIndex], Athes[AtheIndex], EventInfo[AtheIndex]);
        }





    }
}
