package com.iitp.mayank.celesta2k17.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iitp.mayank.celesta2k17.R;

public class galleryEvent extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase ;
    private DatabaseReference mUrlDatabaseReference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_event);

        // this is the main accessing point of the data base
        mFirebaseDatabase= FirebaseDatabase.getInstance() ;
        //getting reference to message part of the app
        mUrlDatabaseReference=mFirebaseDatabase.getReference().child("Url") ;

    }

}
