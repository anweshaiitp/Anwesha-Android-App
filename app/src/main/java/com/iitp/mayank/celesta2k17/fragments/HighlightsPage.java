package com.iitp.mayank.celesta2k17.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iitp.mayank.celesta2k17.R;
import com.iitp.mayank.celesta2k17.adapters.HighlightsRecylerViewAdapter;
import com.iitp.mayank.celesta2k17.data.HighlightsData;

import java.util.ArrayList;

/**
 * Created by mayank on 26/5/17.
 */

public class HighlightsPage extends android.support.v4.app.Fragment {

    RecyclerView highlightsRecylerView ;
    HighlightsRecylerViewAdapter highlightsRecylerViewAdapter ;
    private  static  ArrayList<HighlightsData> data = new ArrayList<>();
    private String LOG_TAG = getClass().toString();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_highlights, container, false);

        highlightsRecylerView=(RecyclerView)rootView.findViewById(R.id.highlightsRecylerView) ;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        highlightsRecylerView.setLayoutManager(linearLayoutManager);

        highlightsRecylerViewAdapter = new HighlightsRecylerViewAdapter(data);
        highlightsRecylerView.setAdapter(highlightsRecylerViewAdapter);

        return rootView;
    }

    public static  void setArray( ArrayList<HighlightsData> hdata)
    {
        data = hdata ;
    }

}
