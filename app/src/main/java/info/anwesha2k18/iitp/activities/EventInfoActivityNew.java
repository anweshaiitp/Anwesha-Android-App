package info.anwesha2k18.iitp.activities;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import info.anwesha2k18.iitp.R;

public class EventInfoActivityNew extends AppCompatActivity {

    private String eveName;
    private String date;
    private String long_desc;
    private String short_desc;
    private String organisers;
    private String reg_url;
    private String rules_url;
    private String time;
    private String venue;
    private String cover_url;

    TextView eventNameDisplay;
    TextView eventDate_TimeDisplay;
    TextView eventVenueDisplay;
    TextView eventRegLinkDisplay;
    TextView eventInfoDisplay;
    TextView eventRulesDisplay;
    TextView eventOrganizersDisplay;
    ImageView eventCoverDisplay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info_new);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        eveName = bundle.getString("eveName", "hello");
        date = bundle.getString("date");
        time = bundle.getString("time");
        venue = bundle.getString("venue");
        long_desc = bundle.getString("long_desc");
        organisers = bundle.getString("organisers");
        reg_url = bundle.getString("reg_url");
        rules_url = bundle.getString("rules_url");
        cover_url = bundle.getString("cover_url");
        short_desc = bundle.getString("short_desc");

        eventNameDisplay=(TextView) findViewById(R.id.event_info_name);
        eventDate_TimeDisplay=(TextView) findViewById(R.id.event_date_time);
        eventVenueDisplay=(TextView) findViewById(R.id.event_venue);
        eventRegLinkDisplay=(TextView) findViewById(R.id.event_reg_link);
        eventInfoDisplay=(TextView) findViewById(R.id.event_info_textview);
        eventRulesDisplay=(TextView) findViewById(R.id.event_rules_textview);
        eventOrganizersDisplay=(TextView) findViewById(R.id.event_organizers);

        eventCoverDisplay=(ImageView) findViewById(R.id.event_cover_display) ;

        displayData();

    }

    private void displayData(){
        eventNameDisplay.setText(eveName);
        eventDate_TimeDisplay.setText(date+" "+time);
        eventVenueDisplay.setText(venue);
        eventRegLinkDisplay.setText(reg_url);
        eventInfoDisplay.setText(long_desc);
        eventRulesDisplay.setText(rules_url);
        eventOrganizersDisplay.setText(organisers);

        Glide.with(this)
                .load(cover_url)
                .apply(new RequestOptions().error(R.drawable.anwesha_placeholder))
                .into(eventCoverDisplay);

    }

}

