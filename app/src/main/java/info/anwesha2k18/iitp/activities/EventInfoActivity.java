package info.anwesha2k18.iitp.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import info.anwesha2k18.iitp.R;

public class EventInfoActivity extends AppCompatActivity {

    public static final String EXTRA_HEADER = "Header",
            EXTRA_DESCRIPTION = "Text",
            EXTRA_RULES = "Rules",
            EXTRA_VENUE = "Venue",
            EXTRA_DATE_TIME = "DateTime",
            EXTRA_IMAGE_ID = "ImageId",
            EXTRA_ORGANIZERS = "Organizers",
            EXTRA_CONTACTS = "Contacts";

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(getApplicationContext()).clearMemory();
        Glide.get(getApplicationContext()).trimMemory(TRIM_MEMORY_COMPLETE);
        System.gc();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);

        // Get the Intent that started this activity and extract the strings needed
        Intent intent = getIntent();
        final String header = intent.getStringExtra(EXTRA_HEADER);
        String text = intent.getStringExtra(EXTRA_DESCRIPTION);
        String rules = intent.getStringExtra(EXTRA_RULES);
        final String dateTime = intent.getStringExtra(EXTRA_DATE_TIME);
        String venue = intent.getStringExtra(EXTRA_VENUE);
        int imageId = intent.getIntExtra(EXTRA_IMAGE_ID, -1);

        ((TextView) findViewById(R.id.event_info_name)).setText(header);
        if (imageId != -1)
            Glide.with(this)
                    .load(imageId)
                    .into((ImageView) findViewById(R.id.event_info_imageview));

        if (text.equals("-1"))
            text = "No Information Available";
        final String finalText = text;
        ((TextView) findViewById(R.id.event_info_textview)).setText(text);

        TextView rulesTextView = (TextView) findViewById(R.id.event_rules_textview);
        if (rules.equals("-1")) {
            rulesTextView.setVisibility(View.GONE);
            (findViewById(R.id.rules_header)).setVisibility(View.GONE);
        } else {
            rulesTextView.setText(rules);
        }
        String organizers = intent.getStringExtra(EXTRA_ORGANIZERS);
        final String contacts = intent.getStringExtra(EXTRA_CONTACTS);
        if (organizers.equals("-1"))
            organizers = "No information available";
        ((TextView) findViewById(R.id.event_organizers)).setText(organizers);
        ((TextView) findViewById(R.id.event_contact)).setText(contacts);

        ((TextView) findViewById(R.id.event_date_time)).setText(dateTime);
        ((TextView) findViewById(R.id.event_venue)).setText(venue);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_share_event);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resources resources = getResources();
                String shareString = resources.getText(R.string.share_message) + "\n"
                        + resources.getText(R.string.name) + ": " + header + "\n"
                        + resources.getText(R.string.date_time) + ": " + dateTime + "\n"
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
