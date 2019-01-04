package info.anwesha2k18.iitp.activities;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.adapters.EventsAdapter;
import info.anwesha2k18.iitp.data.EventData;
import info.anwesha2k18.iitp.data.EventListData;
import info.anwesha2k18.iitp.database.WebSyncDB;
import info.anwesha2k18.iitp.fragments.EventCategoryFragment;

public class EventsActivityNew extends AppCompatActivity {

    private FirebaseDatabase mfirebase;
    private DatabaseReference eventsDatabaseReference;
    private ChildEventListener mChildEventListener;
    private List<EventData> AllEvents = new ArrayList<>();
    private EventsAdapter mEventsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_tabs);
        mfirebase = FirebaseDatabase.getInstance();
        eventsDatabaseReference = mfirebase.getReference().child("events");
        ListView eventListView =(ListView) findViewById(R.id.eventListView);

        List<EventListData> mEventListData= new ArrayList<>();
        mEventsAdapter= new EventsAdapter(this,R.layout.card_view,mEventListData);
        eventListView.setAdapter(mEventsAdapter);
        attachDatabaseReadListener();
    }

    private void attachDatabaseReadListener(){
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    EventListData eventListData = dataSnapshot.getValue(EventListData.class);
                    mEventsAdapter.add(eventListData);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            eventsDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

}
