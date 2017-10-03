package in.org.celesta2k17.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import in.org.celesta2k17.R;
import in.org.celesta2k17.adapters.TeamRecylerViewAdapter;

/**
 * Created by manish on 22/8/17.
 */

public class TeamActivity extends AppCompatActivity implements TeamRecylerViewAdapter.PhoneCLick {

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
                this,
                resources.getStringArray(R.array.array_team_names),
                resources.getStringArray(R.array.array_team_pors),
                resources.getStringArray(R.array.array_team_committees),
                resources.getStringArray(R.array.array_team_number),
                resources.obtainTypedArray(R.array.array_team_images));

        //setting adapter on the recyler view
        recyclerView.setAdapter(teamRecylerViewAdapter);

    }

    @Override
    public void onPhoneClick(String phone) {
        if (!phone.equals("-1")) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phone));
            startActivity(intent);
        }
    }
}
