package info.anwesha2k19.iitp.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import info.anwesha2k19.iitp.R;
import info.anwesha2k19.iitp.activities.ImageLauncher;

public class GalleryFragmentNew extends android.support.v4.app.Fragment {

    final private String LOG_TAG = getClass().toString();
    private FirebaseDatabase mfirebase;
    private DatabaseReference galleryDatabaseReference;
    private ValueEventListener mChildEventListener;

    private GalleryAdapter galleryAdapter;
    private ListView galleryListView;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        mfirebase = FirebaseDatabase.getInstance();
        galleryDatabaseReference = mfirebase.getReference().child("imageurl");
        galleryDatabaseReference.keepSynced(true);

        TextView emptyGallery = (TextView) rootView.findViewById(R.id.empty_gallery_text_view);

        galleryListView = (ListView) rootView.findViewById(R.id.galleryListView);
        galleryListView.setEmptyView(emptyGallery);

        List<String> galleryListStr = new ArrayList<>();

        galleryAdapter = new GalleryAdapter(getContext(), R.layout.card_view, galleryListStr);

        galleryListView.setAdapter(galleryAdapter);

        attachDatabaseReadListener();

        return rootView;
    }

    private void attachDatabaseReadListener(){

        if (mChildEventListener == null) {
            mChildEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    galleryAdapter.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        String imageUrl = dataSnapshot1.child("url").getValue().toString();
                        galleryAdapter.add(imageUrl);
                    }
                    galleryAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            galleryDatabaseReference.addValueEventListener(mChildEventListener);
        }
    }

    private void detachDatabaseReadListener() {
        if (mChildEventListener != null) {
            galleryDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        detachDatabaseReadListener();
        galleryAdapter.clear();
    }

    @Override
    public void onStop() {
        detachDatabaseReadListener();
        galleryAdapter.clear();
        super.onStop();
    }

    @Override
    public void onStart() {
        attachDatabaseReadListener();
        super.onStart();
    }

    private class GalleryAdapter extends ArrayAdapter<String> {
        public GalleryAdapter(@NonNull Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.gallery_image_view, parent, false);
            }

            String currentUrl = getItem(position);

            ImageView galleryImageItem = (ImageView) convertView.findViewById(R.id.galleryImageItem);

            Glide.with(getContext())
                    .load(currentUrl)
                    .apply(new RequestOptions().error(R.drawable.anwesha_placeholder))
                    .into(galleryImageItem);

            galleryImageItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (currentUrl != null){
                        Intent intent = new Intent(getContext(), ImageLauncher.class);
                        intent.putExtra("image_url", currentUrl);
                        startActivity(intent);
                    }
                }
            });

            return convertView;
        }
    }
}

