package info.anwesha2k19.iitp.utils;

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
import info.anwesha2k19.iitp.data.GalleryPics;
import info.anwesha2k19.iitp.data.HighlightsData;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by mayank on 26/8/17.
 */

public class NetworkUtils {

    final private String LOG_TAG = getClass().toString();
    private FirebaseDatabase mFirebaseDatabase;
    private ArrayList<HighlightsData> highlightsDatas = new ArrayList<>();
    private DatabaseReference mhighlightsDatabaseReference;
    private ChildEventListener mhighlightsChildeventlistener;

    public boolean extractHighlights(ContextWrapper contextWrapper, Context context) {
        if (!hasNetwork(context)) {
            return false;
        } else {
            final File directory = contextWrapper.getDir("highlights", Context.MODE_PRIVATE);

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mhighlightsDatabaseReference = mFirebaseDatabase.getReference().child("highlights");

            mhighlightsChildeventlistener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    HighlightsData highlightsData = dataSnapshot.getValue(HighlightsData.class);
                    highlightsDatas.add(highlightsData);
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

            mhighlightsDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ArrayList<String> data = new ArrayList<>();
                    for (HighlightsData h : highlightsDatas) {
                        data.add(h.getmHighlights());
                    }
                    File highlightsFile = new File(directory, "highlight.txt");
                    try {
                        PrintWriter printWriter = new PrintWriter(highlightsFile);
                        for (String txt : data) {
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
        return true;
    }

    private boolean hasNetwork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
