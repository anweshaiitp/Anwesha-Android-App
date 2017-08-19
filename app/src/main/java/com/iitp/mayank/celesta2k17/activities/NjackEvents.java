package com.iitp.mayank.celesta2k17.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.iitp.mayank.celesta2k17.R;
import com.iitp.mayank.celesta2k17.adapters.EventsRecyclerViewAdapter;
import com.iitp.mayank.celesta2k17.data.EventsData;

public class NjackEvents extends AppCompatActivity implements EventsRecyclerViewAdapter.ListCardClick {

    public static final String EXTRA_HEADER = "Header",
            EXTRA_TEXT = "Text",
            EXTRA_VENUE = "Venuw",
            EXTRA_DATE_TIME = "DateTime",
            EXTRA_IMAGE_ID = "ImageId";
    
    RecyclerView recyclerView;
    EventsRecyclerViewAdapter eventsRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_njack);

        recyclerView = (RecyclerView)findViewById(R.id.rv_events);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this , 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setHasFixedSize(true);
        Resources resources = getResources();

        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorEvents)));

        eventsRecyclerViewAdapter = new EventsRecyclerViewAdapter(getApplicationContext(), this, resources.getStringArray(R.array.array_njack_event_headers),
                resources.getStringArray(R.array.array_njack_event_text) ,
                resources.getStringArray(R.array.array_njack_event_intent),
                resources.getStringArray(R.array.array_njack_event_intent),
                resources.obtainTypedArray(R.array.array_njack_event_images));
        recyclerView.setAdapter(eventsRecyclerViewAdapter);
    }

    @Override
    public void onListClick(EventsData eventsData) throws ClassNotFoundException {
        Intent intentNew = new Intent(this, Class.forName("com.iitp.mayank.celesta2k17.activities.EventInfoActivity"));
        intentNew.putExtra(EXTRA_HEADER, eventsData.getHeader());
        intentNew.putExtra(EXTRA_TEXT, eventsData.getText());
        intentNew.putExtra(EXTRA_DATE_TIME, eventsData.getDateTime());
        intentNew.putExtra(EXTRA_VENUE, eventsData.getVenue());
        intentNew.putExtra(EXTRA_IMAGE_ID, eventsData.getImageId());
        startActivity(intentNew);
    }
}
