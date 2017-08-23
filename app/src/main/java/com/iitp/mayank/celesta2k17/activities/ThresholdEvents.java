package com.iitp.mayank.celesta2k17.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.iitp.mayank.celesta2k17.R;
import com.iitp.mayank.celesta2k17.adapters.EventsRecyclerViewAdapter;
import com.iitp.mayank.celesta2k17.data.EventsData;

import static com.iitp.mayank.celesta2k17.activities.EventInfoActivity.EXTRA_DATE_TIME;
import static com.iitp.mayank.celesta2k17.activities.EventInfoActivity.EXTRA_HEADER;
import static com.iitp.mayank.celesta2k17.activities.EventInfoActivity.EXTRA_IMAGE_ID;
import static com.iitp.mayank.celesta2k17.activities.EventInfoActivity.EXTRA_TEXT;
import static com.iitp.mayank.celesta2k17.activities.EventInfoActivity.EXTRA_VENUE;

public class ThresholdEvents extends AppCompatActivity implements EventsRecyclerViewAdapter.ListCardClick {

    RecyclerView recyclerView;
    EventsRecyclerViewAdapter eventsRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_threshold_events);

        recyclerView = (RecyclerView)findViewById(R.id.rv_events);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this , 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setHasFixedSize(true);
        Resources resources = getResources();

        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorEvents)));

        eventsRecyclerViewAdapter = new EventsRecyclerViewAdapter(getApplicationContext(), this, resources.getStringArray(R.array.array_threshold_event_headers),
                resources.getStringArray(R.array.array_threshold_event_text),
                resources.getStringArray(R.array.array_threshold_event_date_time),
                resources.getStringArray(R.array.array_threshold_event_venue),
                resources.obtainTypedArray(R.array.array_threshold_event_images));
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

        Intent intentNew = new Intent(this, Class.forName("com.iitp.mayank.celesta2k17.activities.EventInfoActivity"));
        intentNew.putExtra(EXTRA_HEADER, eventsData.getHeader());
        intentNew.putExtra(EXTRA_TEXT, eventsData.getText());
        intentNew.putExtra(EXTRA_DATE_TIME, eventsData.getDateTime());
        intentNew.putExtra(EXTRA_VENUE, eventsData.getVenue());
        intentNew.putExtra(EXTRA_IMAGE_ID, eventsData.getImageId());
        startActivity(intentNew, options.toBundle());
    }
}
