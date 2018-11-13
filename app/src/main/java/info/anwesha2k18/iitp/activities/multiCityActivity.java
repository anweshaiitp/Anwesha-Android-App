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
import info.anwesha2k18.iitp.adapters.multiCityRecyclerViewAdapter;
import info.anwesha2k18.iitp.data.multiCityData;

import static info.anwesha2k18.iitp.activities.multiCityInfo.EXTRA_DATE_TIME;
import static info.anwesha2k18.iitp.activities.multiCityInfo.EXTRA_HEADER;
import static info.anwesha2k18.iitp.activities.multiCityInfo.EXTRA_IMAGE_ID;
import static info.anwesha2k18.iitp.activities.multiCityInfo.EXTRA_RULES;
import static info.anwesha2k18.iitp.activities.multiCityInfo.EXTRA_VENUE;

public class multiCityActivity extends AppCompatActivity implements multiCityRecyclerViewAdapter.ListCardClick {
    RecyclerView recyclerView;
    multiCityRecyclerViewAdapter multiCityRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_multicity);
        recyclerView = findViewById(R.id.rv_mul);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        Resources resources = getResources();

        android.support.v7.app.ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimary)));

        multiCityRecyclerViewAdapter = new multiCityRecyclerViewAdapter(getApplicationContext(), this,
                resources.getStringArray(R.array.array_multicity_event_headers),
                resources.getStringArray(R.array.array_multicity_event_date_time),
                resources.getStringArray(R.array.array_multicity_event_venue),
                resources.getStringArray(R.array.array_multicity_event_rules),
                resources.obtainTypedArray(R.array.array_multicity_event_images)
        );
        recyclerView.setAdapter(multiCityRecyclerViewAdapter);
    }

    @Override
    public void onListClick(multiCityData eventsData, View view) throws ClassNotFoundException {

        ImageView imageView = view.findViewById(R.id.cardimage);
        TextView textViewHeader = view.findViewById(R.id.cardhead);
        Pair<View, String> p1 = Pair.create(imageView, "event_image_view_transition");
        Pair<View, String> p2 = Pair.create(textViewHeader, "event_text_header_transition");

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, p1,p2);

        Intent intentNew = new Intent(this, Class.forName("info.anwesha2k18.iitp.activities.multiCityInfo"));
        intentNew.putExtra(EXTRA_HEADER, eventsData.headers);
        intentNew.putExtra(EXTRA_RULES, eventsData.description);
        intentNew.putExtra(EXTRA_DATE_TIME, eventsData.datetime);
        intentNew.putExtra(EXTRA_VENUE, eventsData.venue);
        intentNew.putExtra(EXTRA_IMAGE_ID, eventsData.img);
        startActivity(intentNew, options.toBundle());
    }
}
