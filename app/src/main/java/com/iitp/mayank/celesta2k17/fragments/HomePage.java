package com.iitp.mayank.celesta2k17.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.iitp.mayank.celesta2k17.R;
import com.iitp.mayank.celesta2k17.activities.AboutActivity;
import com.iitp.mayank.celesta2k17.activities.DevelopersActivity;
import com.iitp.mayank.celesta2k17.activities.EventsActivity;
import com.iitp.mayank.celesta2k17.activities.ScheduleActivity;
import com.iitp.mayank.celesta2k17.activities.TeamActivity;
import com.iitp.mayank.celesta2k17.adapters.EventsAdapter;
import com.iitp.mayank.celesta2k17.listeners.ViewPagerCustomDuration;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mayank on 26/5/17.
 */

public class HomePage extends android.support.v4.app.Fragment {

    LinearLayout eventsLinearLayout;
//    LinearLayout galleryLinearLayout;
    LinearLayout aboutFrameLayout;
    LinearLayout scheduleLinearLayout;
    LinearLayout teamLinearLayout ;
    LinearLayout devLinearLayout;
    private int currentPage=0 ;
    Timer timer ;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 4000; // time in milliseconds between successive task executions.
    private int NUM_PAGES=4 ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);


        final ViewPagerCustomDuration viewPagerCustomDuration=(ViewPagerCustomDuration)rootView.findViewById(R.id.events_pager) ;
        viewPagerCustomDuration.setScrollDuration(900);
        EventsAdapter eventsAdapter = new EventsAdapter(getContext());

        viewPagerCustomDuration.setAdapter(eventsAdapter);
        /*Adding automatic swap to the images
        * */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPagerCustomDuration.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer .schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);





        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab_maps);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "https://www.google.com/maps/@?api=1&map_action=map&center=25.535752,84.851065&zoom=16&basemap=satellite";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                getContext().startActivity(intent);
            }
        });

        eventsLinearLayout = (LinearLayout) rootView.findViewById(R.id.events);
        eventsLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext(), EventsActivity.class);
                startActivity(intent);

            }
        });

        aboutFrameLayout = (LinearLayout) rootView.findViewById(R.id.about);
        aboutFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext(), AboutActivity.class);
                startActivity(intent);
            }
        });

        scheduleLinearLayout = (LinearLayout) rootView.findViewById(R.id.schedule);
        scheduleLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext(), ScheduleActivity.class);
                startActivity(intent);
            }
        });

        devLinearLayout = (LinearLayout) rootView.findViewById(R.id.developers);
        devLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext(), DevelopersActivity.class);
                startActivity(intent);
            }
        });

        teamLinearLayout=(LinearLayout)rootView.findViewById(R.id.team) ;
        teamLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( rootView.getContext(), TeamActivity.class) ;
                startActivity(intent);

            }
        });
        return rootView;
    }
}
