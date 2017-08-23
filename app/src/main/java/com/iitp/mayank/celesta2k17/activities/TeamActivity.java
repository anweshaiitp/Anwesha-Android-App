package com.iitp.mayank.celesta2k17.activities;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.iitp.mayank.celesta2k17.R;
import com.iitp.mayank.celesta2k17.adapters.GalleryRecylerViewAdapter;
import com.iitp.mayank.celesta2k17.adapters.TeamRecylerViewAdapter;

/**
 * Created by manish on 22/8/17.
 */

public class TeamActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TeamRecylerViewAdapter teamRecylerViewAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_team);

        recyclerView = (RecyclerView) findViewById(R.id.teamRecylerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);
        Resources resources = getResources();

        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimaryDark)));
        //populating the array with the data
        teamRecylerViewAdapter = new TeamRecylerViewAdapter(getApplicationContext(),
                                resources.getStringArray(R.array.array_name_view),
                                resources.getStringArray(R.array.array_por_view),
                                resources.getStringArray(R.array.array_team_number_view));

        //setting adapter on the recyler view
        recyclerView.setAdapter(teamRecylerViewAdapter);

    }
}
