package com.iitp.mayank.celesta2k17.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iitp.mayank.celesta2k17.R;
import com.iitp.mayank.celesta2k17.adapters.ScheduleRecyclerViewAdapter;
import com.iitp.mayank.celesta2k17.data.EventsData;

/**

 */
public class ScheduleDay1 extends android.support.v4.app.Fragment implements ScheduleRecyclerViewAdapter.ListCardClick {

    RecyclerView recyclerView;
    ScheduleRecyclerViewAdapter scheduleRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.fragment_schedule_day1, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_schedule_day1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        Resources resources = getResources();

        scheduleRecyclerView = new ScheduleRecyclerViewAdapter(getActivity() ,
                                                        this,
                                                        resources.getStringArray(R.array.array_day1_event_header),
                                                        resources.getStringArray(R.array.array_day1_event_text),
                                                        resources.getStringArray(R.array.array_day1_event_time),
                                                        resources.getStringArray(R.array.array_day1_event_dates),
                                                        resources.getStringArray(R.array.array_ace_event_venue),
                                                        resources.obtainTypedArray(R.array.array_day1_event_image));
        recyclerView.setAdapter(scheduleRecyclerView);
        return rootView;
    }

    @Override
    public void onListClick(EventsData eventsData, View view) {

    }
}
