package com.hippie.trackitcoach.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hippie.trackitcoach.R;
import com.hippie.trackitcoach.models.DB_Report;

public class ViewReportActivity extends AppCompatActivity {
    private DB_Report report;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);

        Bundle myReport = getIntent().getExtras();
        report = (DB_Report) myReport.getParcelable(SearchRecordsActivity.REVIEW_REPORT);

        TextView textView = (TextView) findViewById(R.id.review_report_textView);
        textView.setText(report.showReport());

        Button sendEmailButton = (Button) findViewById(R.id.send_email_button);
        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] TO = {report.getDBAthlete().getEmail()};
                String[] CC = {""};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, report.showReport());

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();

                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ViewReportActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_report, menu);
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
}
