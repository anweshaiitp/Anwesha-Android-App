package com.iitp.mayank.celesta2k17.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iitp.mayank.celesta2k17.R;
import com.iitp.mayank.celesta2k17.activities.MyProfile;
import com.iitp.mayank.celesta2k17.adapters.EventsAdapter;

/**
 * Created by mayank on 26/5/17.
 */

public class HomePage extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.events_pager);
        EventsAdapter eventsAdapter = new EventsAdapter(getContext());

        viewPager.setAdapter(eventsAdapter);
        return rootView;
    }
}
