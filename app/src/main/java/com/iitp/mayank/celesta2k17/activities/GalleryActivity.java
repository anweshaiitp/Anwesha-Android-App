package com.iitp.mayank.celesta2k17.activities;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.iitp.mayank.celesta2k17.R;
import com.iitp.mayank.celesta2k17.adapters.GalleryRecylerViewAdapter;
import com.iitp.mayank.celesta2k17.data.GalleryPics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.R.attr.data;

public class GalleryActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUrlDatabaseReference;
    ArrayList<GalleryPics> mpicUrl = new ArrayList<GalleryPics>();
    private ChildEventListener mChildEventListener;
    private FirebaseStorage mStorage;

    private StorageReference mStorageReference;
    private File localFile;
    private StorageReference islandRef;
    private ArrayList<FileDownloadTask> tasks = new ArrayList<FileDownloadTask>();
    Random rand = new Random();

    RecyclerView imageRecyclerView;
    final private String LOG_TAG = getClass().toString();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_event);

        imageRecyclerView = (RecyclerView) findViewById(R.id.galleryRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this , 2);
        imageRecyclerView.setLayoutManager(gridLayoutManager);

        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        imageRecyclerView.setHasFixedSize(true);

        File directory = contextWrapper.getDir("images_thumbnails", Context.MODE_PRIVATE);
        File images[] = directory.listFiles();

        GalleryRecylerViewAdapter galleryRecylerViewAdapter = new GalleryRecylerViewAdapter(this , images);
        imageRecyclerView.setAdapter(galleryRecylerViewAdapter);
    }
}

