package com.iitp.mayank.celesta2k17.fragments;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.iitp.mayank.celesta2k17.R;
import com.iitp.mayank.celesta2k17.activities.ImageLauncher;
import com.iitp.mayank.celesta2k17.adapters.GalleryRecylerViewAdapter;
import com.iitp.mayank.celesta2k17.data.GalleryPics;
import com.iitp.mayank.celesta2k17.listeners.RecylerItemListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class GalleryFragment extends android.support.v4.app.Fragment {


    RecyclerView imageRecyclerView;
    final private String LOG_TAG = getClass().toString();


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        ContextWrapper contextWrapper = new ContextWrapper(rootView.getContext());
        File directory = contextWrapper.getDir("images_thumbnails", Context.MODE_PRIVATE);
        final File images[] = directory.listFiles();

        imageRecyclerView = (RecyclerView) rootView.findViewById(R.id.galleryRecyclerView);
        imageRecyclerView.addOnItemTouchListener(new RecylerItemListener(getContext(), imageRecyclerView,
                new RecylerItemListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent(getActivity(), ImageLauncher.class);
                        intent.putExtra("imageV", images[position]);
                        getActivity().startActivity(intent);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }
        ));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(rootView.getContext(), 2);
        imageRecyclerView.setLayoutManager(gridLayoutManager);


        imageRecyclerView.setHasFixedSize(true);

        GalleryRecylerViewAdapter galleryRecylerViewAdapter = new GalleryRecylerViewAdapter(rootView.getContext(), images);
        imageRecyclerView.setAdapter(galleryRecylerViewAdapter);

        return rootView;
    }
}

