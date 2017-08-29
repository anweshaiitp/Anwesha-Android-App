package com.iitp.mayank.celesta2k17.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.events_pager);
        EventsAdapter eventsAdapter = new EventsAdapter(getContext());

        viewPager.setAdapter(eventsAdapter);
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

//        galleryLinearLayout = (LinearLayout) rootView.findViewById(R.id.gallery);
//        galleryLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(rootView.getContext(), GalleryFragment.class);
//                startActivity(intent);
//
//
//            }
//        });

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
