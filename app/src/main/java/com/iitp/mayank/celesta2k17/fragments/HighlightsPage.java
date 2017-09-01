package com.iitp.mayank.celesta2k17.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iitp.mayank.celesta2k17.R;
import com.iitp.mayank.celesta2k17.data.HighlightsData;

import java.util.ArrayList;

/**
 * Created by mayank on 26/5/17.
 */

public class HighlightsPage extends android.support.v4.app.Fragment {


    private String LOG_TAG = getClass().toString();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_highlights, container, false);


        return rootView;
    }
}
