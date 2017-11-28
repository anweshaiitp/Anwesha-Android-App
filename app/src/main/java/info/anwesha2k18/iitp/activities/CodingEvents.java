package info.anwesha2k18.iitp.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.adapters.EventsRecyclerViewAdapter;
import info.anwesha2k18.iitp.data.EventsData;

import static info.anwesha2k18.iitp.activities.EventInfoActivity.EXTRA_CONTACTS;
import static info.anwesha2k18.iitp.activities.EventInfoActivity.EXTRA_TIME;
import static info.anwesha2k18.iitp.activities.EventInfoActivity.EXTRA_LONG_DESCRIPTION;
import static info.anwesha2k18.iitp.activities.EventInfoActivity.EXTRA_HEADER;
import static info.anwesha2k18.iitp.activities.EventInfoActivity.EXTRA_IMAGE_ID;
import static info.anwesha2k18.iitp.activities.EventInfoActivity.EXTRA_ORGANIZERS;
import static info.anwesha2k18.iitp.activities.EventInfoActivity.EXTRA_RULES;
import static info.anwesha2k18.iitp.activities.EventInfoActivity.EXTRA_VENUE;

public class CodingEvents extends AppCompatActivity implements EventsRecyclerViewAdapter.ListCardClick {

    RecyclerView recyclerView;
    EventsRecyclerViewAdapter eventsRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_coding_events);

        recyclerView = (RecyclerView) findViewById(R.id.rv_events);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);
        Resources resources = getResources();

        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorEvents)));

        eventsRecyclerViewAdapter = new EventsRecyclerViewAdapter(getApplicationContext(), this,
                resources.getStringArray(R.array.array_coding_event_headers),
                resources.getStringArray(R.array.array_coding_event_text),
                resources.getStringArray(R.array.array_coding_event_rules),
                resources.getStringArray(R.array.array_coding_event_date_time),
                resources.getStringArray(R.array.array_coding_event_venue),
                resources.obtainTypedArray(R.array.array_coding_event_images),
                resources.getStringArray(R.array.array_coding_organizers),
                resources.getStringArray(R.array.array_coding_contacts));
        recyclerView.setAdapter(eventsRecyclerViewAdapter);
    }

    @Override
    public void onListClick(EventsData eventsData, View view) throws ClassNotFoundException {

        ImageView imageView = (ImageView) view.findViewById(R.id.card_cardimage);
        TextView textViewHeader = (TextView) view.findViewById(R.id.card_header);
        Pair<View, String> p1 = Pair.create((View) imageView, "event_image_view_transition");
        Pair<View, String> p2 = Pair.create((View) textViewHeader, "event_text_header_transition");

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, p1, p2);

        Intent intentNew = new Intent(this, Class.forName("info.anwesha2k18.iitp.activities.EventInfoActivity"));
        intentNew.putExtra(EXTRA_HEADER, eventsData.getHeader());
        intentNew.putExtra(EXTRA_LONG_DESCRIPTION, eventsData.getText());
        intentNew.putExtra(EXTRA_RULES, eventsData.getRules());
        intentNew.putExtra(EXTRA_TIME, eventsData.getDateTime());
        intentNew.putExtra(EXTRA_VENUE, eventsData.getVenue());
        intentNew.putExtra(EXTRA_IMAGE_ID, eventsData.getImageId());
        intentNew.putExtra(EXTRA_ORGANIZERS, eventsData.getOrganizers());
        intentNew.putExtra(EXTRA_CONTACTS, eventsData.getContacts());
        startActivity(intentNew, options.toBundle());
    }
}
