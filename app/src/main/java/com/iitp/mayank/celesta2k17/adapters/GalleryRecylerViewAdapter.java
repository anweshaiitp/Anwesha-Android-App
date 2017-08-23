package com.iitp.mayank.celesta2k17.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.iitp.mayank.celesta2k17.R;
import com.iitp.mayank.celesta2k17.activities.GalleryEvent;
import com.iitp.mayank.celesta2k17.data.GalleryPics;

import java.util.ArrayList;

/**
 * Created by manish on 16/8/17.
 */

public class GalleryRecylerViewAdapter extends RecyclerView.Adapter<GalleryRecylerViewAdapter.galleryViewHolder> {

    ArrayList<GalleryPics> galleryPics = new ArrayList<>();
    Context context;
    public GalleryRecylerViewAdapter(Context context , ArrayList<GalleryPics> galleryPics)
    {
        this.context = context;
        this.galleryPics = galleryPics;
        Log.v("TAGAGAGAAG" , galleryPics.toString());
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
        Glide.with(context)
                .load(galleryPics.get(position))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return galleryPics.size();
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
