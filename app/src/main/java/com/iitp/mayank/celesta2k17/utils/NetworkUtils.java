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

import java.io.File;
import java.util.ArrayList;

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

    private StorageReference mStorageReference;
    private File localFile;
    private StorageReference islandRef;

    final private String LOG_TAG = getClass().toString();

    public boolean downloadImages(ContextWrapper c , Context context)
    {
        if(!hasNetwork(context))
            return false;
        else
        {
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
            final File directory = contextWrapper.getDir("images", Context.MODE_PRIVATE);


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

            //loading the data when the files are downloaded
            mUrlDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for ( GalleryPics galleryPics:mpicUrl)
                    {
                        Log.e(LOG_TAG,galleryPics.getmPhotoUrl());
                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            //to notify when all the previous dataSnapshot is downloaded
            mUrlDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    Log.e(LOG_TAG, "" + mpicUrl.size());
                    // iterate on the array list


                    int loop = 0;

                    for (GalleryPics galleryPics : mpicUrl) {


                        //try creating a local file with the image name
                        try {
                            // create a new file in that directory with this name
                            /**
                             * @param directory accepts the directory where you want to save the file
                             * @param #name accepts the name of the file
                             * */

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
                                Log.e(LOG_TAG, "successssssssssssssssssssssssssssssssssssssssssssss");
                            }
                        });

                        tasks.get(loop).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(LOG_TAG, "failllllllllllllllllllese" + e.getMessage());
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

    boolean hasNetwork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
