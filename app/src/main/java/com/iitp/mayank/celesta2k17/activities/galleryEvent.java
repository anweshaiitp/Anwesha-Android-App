package com.iitp.mayank.celesta2k17.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.iitp.mayank.celesta2k17.R;
import com.iitp.mayank.celesta2k17.data.GalleryPics;

import java.io.File;
import java.util.ArrayList;

public class galleryEvent extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUrlDatabaseReference;
    ArrayList<GalleryPics> mpicUrl = new ArrayList<GalleryPics>();
    private ChildEventListener mChildEventListener;
    private  FirebaseStorage mStorage ;

    private StorageReference mStorageReference;
    private File localFile ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_event);

        // this is the main accessing point of the data base
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        //getting reference to message part of the app
        mUrlDatabaseReference = mFirebaseDatabase.getReference().child("Url");
        //creating an instance of Firebase storage
        mStorage= FirebaseStorage.getInstance();

        // Create a storage reference from our app
       mStorageReference= mStorage.getReference();

        //create a local file
        try {
            localFile = File.createTempFile("Celesta", "images");
            Toast.makeText(getApplicationContext(),localFile.getAbsolutePath(),Toast.LENGTH_LONG).show();
            Log.e("Fooooo",localFile.getAbsolutePath());
        }
        catch ( Exception e )
        {
            Toast.makeText(getApplicationContext(),"Can't create file ",Toast.LENGTH_LONG).show();
            Log.e("sdsddsd",e.getMessage()) ;
        }


        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                GalleryPics galleryPics = dataSnapshot.getValue(GalleryPics.class);
                // adding the url to data list
                mpicUrl.add(galleryPics);
                Toast.makeText(getApplicationContext(),mpicUrl.get(0).getmPhotoUrl().trim(),Toast.LENGTH_LONG).show();

                StorageReference islandRef = mStorageReference.child(mpicUrl.get(0).getmPhotoUrl());



                islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(),"Wohoooo",Toast.LENGTH_LONG).show();

                        // Local temp file has been created
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {

                        Toast.makeText(getApplicationContext(),"Not Wohoooo",Toast.LENGTH_LONG).show();

                        // Handle any errors
                    }
                });




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

        mUrlDatabaseReference.addChildEventListener(mChildEventListener);


    }

}
