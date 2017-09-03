package com.iitp.mayank.celesta2k17.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iitp.mayank.celesta2k17.R;

public class EventInfoActivity extends AppCompatActivity {

    public static final String EXTRA_HEADER = "Header",
            EXTRA_TEXT = "Text",
            EXTRA_VENUE = "Venue",
            EXTRA_DATE_TIME = "DateTime",
            EXTRA_IMAGE_ID = "ImageId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);

        // Get the Intent that started this activity and extract the strings needed
        Intent intent = getIntent();
        final String header = intent.getStringExtra(EXTRA_HEADER);
        String text = intent.getStringExtra(EXTRA_TEXT);
        final String dateTime = intent.getStringExtra(EXTRA_DATE_TIME);
        String venue = intent.getStringExtra(EXTRA_VENUE);
        int imageId = intent.getIntExtra(EXTRA_IMAGE_ID, -1);

        ((TextView) findViewById(R.id.event_info_name)).setText(header);
        if (imageId != -1)
            ((ImageView) findViewById(R.id.event_info_imageview)).setImageResource(imageId);
        if (text == "-1")
            text = "No Information Available";
        final String finalText = text;
        ((TextView) findViewById(R.id.event_info_textview)).setText(text);
        ((TextView) findViewById(R.id.event_date_time)).setText(dateTime);
        ((TextView) findViewById(R.id.event_venue)).setText(venue);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_share_event);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resources resources = getResources();
                String shareString = (String) resources.getText(R.string.share_message) + "\n"
                        + (String) resources.getText(R.string.name) + ": " + header + "\n"
                        + (String) resources.getText(R.string.date_time) + ": " + dateTime + "\n"
                        + finalText;
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareString);
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, resources.getText(R.string.share_to)));
            }
        });


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
