package in.org.celesta2k17.activities;

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

import in.org.celesta2k17.R;
import in.org.celesta2k17.adapters.EventsRecyclerViewAdapter;
import in.org.celesta2k17.data.EventsData;

import static in.org.celesta2k17.activities.EventInfoActivity.EXTRA_CONTACTS;
import static in.org.celesta2k17.activities.EventInfoActivity.EXTRA_ORGANIZERS;

public class RoboticsEvents extends AppCompatActivity implements EventsRecyclerViewAdapter.ListCardClick {

    RecyclerView recyclerView;
    EventsRecyclerViewAdapter eventsRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_robotics);

        recyclerView = (RecyclerView) findViewById(R.id.rv_events);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);
        Resources resources = getResources();

        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorEvents)));

        eventsRecyclerViewAdapter = new EventsRecyclerViewAdapter(getApplicationContext(), this,
                resources.getStringArray(R.array.array_robotics_event_headers),
                resources.getStringArray(R.array.array_robotics_event_text),
                resources.getStringArray(R.array.array_robotics_event_rules),
                resources.getStringArray(R.array.array_robotics_event_date_time),
                resources.getStringArray(R.array.array_robotics_event_venue),
                resources.obtainTypedArray(R.array.array_robotics_event_images),
                resources.getStringArray(R.array.array_robotics_organizers),
                resources.getStringArray(R.array.array_robotics_contacts));
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

        Intent intentNew = new Intent(this, Class.forName("in.org.celesta2k17.activities.EventInfoActivity"));
        intentNew.putExtra(EventInfoActivity.EXTRA_HEADER, eventsData.getHeader());
        intentNew.putExtra(EventInfoActivity.EXTRA_DESCRIPTION, eventsData.getText());
        intentNew.putExtra(EventInfoActivity.EXTRA_RULES, eventsData.getRules());
        intentNew.putExtra(EventInfoActivity.EXTRA_DATE_TIME, eventsData.getDateTime());
        intentNew.putExtra(EventInfoActivity.EXTRA_VENUE, eventsData.getVenue());
        intentNew.putExtra(EventInfoActivity.EXTRA_IMAGE_ID, eventsData.getImageId());
        intentNew.putExtra(EXTRA_ORGANIZERS, eventsData.getOrganizers());
        intentNew.putExtra(EXTRA_CONTACTS, eventsData.getContacts());
        startActivity(intentNew, options.toBundle());
    }
}
