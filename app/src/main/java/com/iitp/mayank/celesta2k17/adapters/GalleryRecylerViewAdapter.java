package com.iitp.mayank.celesta2k17.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.iitp.mayank.celesta2k17.R;
import com.iitp.mayank.celesta2k17.data.GalleryPics;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by manish on 16/8/17.
 */

public class GalleryRecylerViewAdapter extends RecyclerView.Adapter<GalleryRecylerViewAdapter.galleryViewHolder> {

    List<File> images;
    Context context;
    public GalleryRecylerViewAdapter(Context context , File file[])
    {
        this.context = context;
        if(file == null)
            this.images = null;
        else
            this.images = new LinkedList<>(Arrays.asList(file));
        Log.v("TAGAGAGAAG" , Arrays.toString(file));
    }

    public void swap(File file[])
    {
        if(images != null){
            images.clear();
            if(file == null)
                images = null;
            else
                images.addAll(Arrays.asList(file));
        }
        else {
            if(file == null)
                images = null;
            else
                images = new LinkedList<>(Arrays.asList(file));
        }
        notifyDataSetChanged();
    }

    @Override
    public galleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // to get the context where we are going to inflate the values
        Context context = parent.getContext();
        int layoutIdForItemList = R.layout.gallery_image_view;

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(layoutIdForItemList, parent, false);

        galleryViewHolder viewHolder = new galleryViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(galleryViewHolder holder, int position) {
        Glide.with(context).clear(holder.imageView);
        Glide.with(context)
                .load(images.get(position))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return images == null ? 0 : images.size();
    }

    // this class is used to cache the view when OncreateView inflates a view and toss this view to this constructor
    class galleryViewHolder extends RecyclerView.ViewHolder {

        //getting a image view to cache the data
        ImageView imageView;

        public galleryViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.galleryImageItem);

        }
    }

}
