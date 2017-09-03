package com.iitp.mayank.celesta2k17.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.iitp.mayank.celesta2k17.data.GalleryPics;
import com.iitp.mayank.celesta2k17.data.HighlightsData;
import com.iitp.mayank.celesta2k17.fragments.HighlightsPage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by mayank on 26/8/17.
 */

public class NetworkUtils {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUrlDatabaseReference;
    ArrayList<GalleryPics> mpicUrl = new ArrayList<>();
    private ChildEventListener mChildEventListener;
    private FirebaseStorage mStorage;
    private ArrayList<FileDownloadTask> tasks = new ArrayList<FileDownloadTask>();
    private  ArrayList<HighlightsData> highlightsDatas= new ArrayList<>();
    private DatabaseReference mhighlightsDatabaseReference;
    private StorageReference mStorageReference;
    private File localFile;
    private StorageReference islandRef;
    int loop = 0;
    final private String LOG_TAG = getClass().toString();
    private ChildEventListener mhighlightsChildeventlistener;

    public boolean downloadImages(ContextWrapper c, Context context) {
        if (!hasNetwork(context))
            return false;

        else {
            // this is the main accessing point of the data base
            mFirebaseDatabase = FirebaseDatabase.getInstance();

            //getting reference to message part of the app
            mUrlDatabaseReference = mFirebaseDatabase.getReference().child("Url");

            //creating an instance of Firebase storage
            mStorage = FirebaseStorage.getInstance();

            // Create a storage reference from our app
            mStorageReference = mStorage.getReference();


            //get the context where app data is saved
            ContextWrapper contextWrapper = new ContextWrapper(c);

            //creates  if needed, a new directory in which the application can place custom images data  files
            final File directory = contextWrapper.getDir("images_thumbnails", Context.MODE_PRIVATE);


            // adding a listener attached to the firebase to sync with any changes made with the firebase

            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    GalleryPics galleryPics = dataSnapshot.getValue(GalleryPics.class);
                    // adding the url to data list
                    mpicUrl.add(galleryPics);

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

            // adding a event listener to sync with the firebase
            mUrlDatabaseReference.addChildEventListener(mChildEventListener);

            //to notify when all the previous dataSnapshot is downloaded
            mUrlDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    // iterate on the array list
                    // create a new file in that directory with this name
                    /**
                     * @param directory accepts the directory where you want to save the file
                     * @param #name accepts the name of the file
                     * */

                    for (GalleryPics galleryPics : mpicUrl) {

                        //try creating a local file with the image name
                        try {
                            localFile = new File(directory, galleryPics.getmPicName());
//                            Toast.makeText(getApplicationContext(), localFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {

                            Log.e(LOG_TAG, e.getMessage());
                        }
                        islandRef = mStorageReference.child(galleryPics.getmPhotoUrl());

                        tasks.add(islandRef.getFile(localFile));
                        tasks.get(loop).addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
//                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

                            }
                        });
                        tasks.get(loop).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(LOG_TAG,  e.getMessage());
                            }
                        });
                        ++loop;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e(LOG_TAG, databaseError.toString());
                }
            });

        }
        return true;
    }

    //to extract the data from the  firebase
    public  boolean extractHighlights(ContextWrapper contextWrapper, Context context )
    {
        Log.v("ASASAS","AA");
        // this return false if there is no interet connection
        if (!hasNetwork(context))
        {  return false; }
        else
        {
            final File directory = contextWrapper.getDir("highlights", Context.MODE_PRIVATE);

            //opens connection with the database
            mFirebaseDatabase=FirebaseDatabase.getInstance() ;
            //getting reference of the child
            mhighlightsDatabaseReference=mFirebaseDatabase.getReference().child("Highlights");

            mhighlightsChildeventlistener= new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    // reference to the object of the class where we should add the data
                    HighlightsData highlightsData = dataSnapshot.getValue(HighlightsData.class);
                    //adding the data to the array list
                    highlightsDatas.add(highlightsData);
//                    Log.e(LOG_TAG,"this is addddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
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
            mhighlightsDatabaseReference.addChildEventListener(mhighlightsChildeventlistener);

            //to download the previously added data in the string
            mhighlightsDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ArrayList<String> data = new ArrayList<>();
                    for(HighlightsData h : highlightsDatas)
                    {
                        data.add(h.getmHighlights());
                    }
                    File highlightsFile = new File(directory , "highlight.txt");
                    try {
                        PrintWriter printWriter = new PrintWriter(highlightsFile);
                        for(String txt : data)
                        {
                            printWriter.println(txt);
                        }
                        printWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    //printing the stack trace
                }
            });

        }
        return  true;
    }

    private  boolean hasNetwork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
