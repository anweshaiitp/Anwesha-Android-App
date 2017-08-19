package com.iitp.mayank.celesta2k17.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.iitp.mayank.celesta2k17.R;

import static com.iitp.mayank.celesta2k17.activities.NjackEvents.EXTRA_DATE_TIME;
import static com.iitp.mayank.celesta2k17.activities.NjackEvents.EXTRA_HEADER;
import static com.iitp.mayank.celesta2k17.activities.NjackEvents.EXTRA_IMAGE_ID;
import static com.iitp.mayank.celesta2k17.activities.NjackEvents.EXTRA_TEXT;
import static com.iitp.mayank.celesta2k17.activities.NjackEvents.EXTRA_VENUE;

public class EventInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);

        // Get the Intent that started this activity and extract the strings needed
        Intent intent = getIntent();
        String header = intent.getStringExtra(EXTRA_HEADER);
        String text = intent.getStringExtra(EXTRA_TEXT);
        String dateTime = intent.getStringExtra(EXTRA_DATE_TIME);
        String venue = intent.getStringExtra(EXTRA_VENUE);
        int imageId = intent.getIntExtra(EXTRA_IMAGE_ID, -1);

        ((TextView) findViewById(R.id.event_info_name)).setText(header);
        if (imageId != -1)
            ((ImageView) findViewById(R.id.event_info_imageview)).setImageResource(imageId);
        if (text == "-1")
            text = "No Information Available";
        ((TextView) findViewById(R.id.event_info_textview)).setText(text);
        ((TextView) findViewById(R.id.event_date_time)).setText(dateTime);
        ((TextView) findViewById(R.id.event_venue)).setText(venue);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
