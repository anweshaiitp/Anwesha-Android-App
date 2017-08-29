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

    private FirebaseDatabase mfirebaseDatabase ;
    ArrayList<HighlightsData> mData = new ArrayList<>() ;
    private DatabaseReference mhighlightsDatabaseReference;
    private String LOG_TAG=getClass().toString() ;
    private ChildEventListener mchildEventListener ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_highlights,container,false);

        //opens connection with the database
        mfirebaseDatabase=FirebaseDatabase.getInstance() ;
        //getting reference of the child
        mhighlightsDatabaseReference=mfirebaseDatabase.getReference("Highlights");

        //defining an event listener
        mchildEventListener= new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                HighlightsData highlightsData =dataSnapshot.getValue(HighlightsData.class);
                mData.add(highlightsData);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        //adding an event listeners
        mhighlightsDatabaseReference.addChildEventListener(mchildEventListener);


        return rootView;
    }
}
