package info.anwesha2k18.iitp.activities;

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

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.adapters.TeamRecylerViewAdapter;

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
