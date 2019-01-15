package info.anwesha2k18.iitp.activities;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.adapters.EventFragmentAdapter;
import info.anwesha2k18.iitp.adapters.TimelineFragmentAdapter;

public class TimelineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        getSupportActionBar().setElevation(0);

        ViewPager viewPager = (ViewPager) findViewById(R.id.timeline_viewpager);
        TimelineFragmentAdapter pagerAdapter = new TimelineFragmentAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.timeline_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
