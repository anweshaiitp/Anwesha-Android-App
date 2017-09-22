package com.iitp.mayank.celesta2k17.fragments;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.iitp.mayank.celesta2k17.utils.NetworkUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import static android.R.attr.action;

public class GalleryFragment extends android.support.v4.app.Fragment {


    RecyclerView imageRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    final private String LOG_TAG = getClass().toString();
    File images[];
    View view;
    GalleryRecylerViewAdapter galleryRecylerViewAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        //to get the context of the root view
        view = rootView;
        //loading the previous data from the file
        setImages();

        //adding swipe refresh layout in the gallery fragment
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh_gallery);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new ReloadImagesAysncTask().execute(new ContextWrapper(getContext()), getContext());
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });


        imageRecyclerView = (RecyclerView) rootView.findViewById(R.id.galleryRecyclerView);
        imageRecyclerView.addOnItemTouchListener(new RecylerItemListener(getContext(), imageRecyclerView,
                new RecylerItemListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent(getActivity(), ImageLauncher.class);
                        intent.putExtra("imageV", getImageFile(position));
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

        galleryRecylerViewAdapter = new GalleryRecylerViewAdapter(rootView.getContext(), images);
        imageRecyclerView.setAdapter(galleryRecylerViewAdapter);

        return rootView;
    }

    //for getting the file from main class in a inner class
    //helper method to set a variable in inner class
    private File getImageFile(int position) {
        File file = images[position];
        return file;
    }

    //to set the data in the file
    private void setImages() {
        ContextWrapper contextWrapper = new ContextWrapper(view.getContext());
        File directory = contextWrapper.getDir("images_thumbnails", Context.MODE_PRIVATE);
        images = directory.listFiles();

    }

    //to remove the data from the list
    private void clearImages() {   //setting the images to null
        images = null;
    }


    // to trigger download task in background thread on swipe refresh
    private class ReloadImagesAysncTask extends AsyncTask<Object, Void, Boolean> {
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                clearImages();
                galleryRecylerViewAdapter.notifyDataSetChanged();
                setImages();
                galleryRecylerViewAdapter.notifyDataSetChanged();

                Toast.makeText(getContext(), "Gallery Updated", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Download failed. Please try again later", Toast.LENGTH_LONG).show();
            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Object... params) {
            return new NetworkUtils().downloadImages((ContextWrapper) params[0], (Context) params[1]);
        }
    }


}

