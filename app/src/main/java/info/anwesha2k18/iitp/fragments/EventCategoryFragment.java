package info.anwesha2k18.iitp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.activities.EventInfoActivityNew;
import info.anwesha2k18.iitp.adapters.EventsAdapter;
import info.anwesha2k18.iitp.data.EventListData;

public class EventCategoryFragment extends Fragment{

    private EventsAdapter eventListAdapter;
    private ListView eventListView;
    private int eventCode;
    private FirebaseDatabase mfirebase;
    private DatabaseReference eventsDatabaseReference;
    private ChildEventListener mChildEventListener;
    ProgressBar eventListProgress;
    Intent eventIntent;

    public static EventCategoryFragment newInstance(int page){
        Bundle args = new Bundle();
        args.putInt("eventCategoryNumber", page);
        EventCategoryFragment fragment = new EventCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mfirebase = FirebaseDatabase.getInstance();
        eventsDatabaseReference = mfirebase.getReference().child("events");

        switch (getArguments().getInt("eventCategoryNumber")){

            case 1:
                eventCode = 0;
                break;
            case 2:
                eventCode = 1;
                break;
            case 3:
                eventCode = 2;
                break;
            case 4:
                eventCode = 3;
                break;
            default:

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.event_list, container, false);

        eventListView = rootView.findViewById(R.id.event_list_view);
        eventListProgress = rootView.findViewById(R.id.event_list_progress_bar);

        List<EventListData> singleDayList = new ArrayList<EventListData>();

        eventListAdapter = new EventsAdapter(getActivity(), R.layout.card_view, singleDayList);

        eventListView.setAdapter(eventListAdapter);

        attachDatabaseReadListener(eventCode);

        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                eventIntent = new Intent(((Activity) getContext()), EventInfoActivityNew.class);

                Bundle bundle = new Bundle();
                bundle.putString("eveName", eventListAdapter.getItem(i).geteveName());
                bundle.putString("date", eventListAdapter.getItem(i).getdate());
                bundle.putString("long_desc", eventListAdapter.getItem(i).getlong_desc());
                bundle.putString("short_desc", eventListAdapter.getItem(i).getshort_desc());
                bundle.putString("organisers", eventListAdapter.getItem(i).getorganisers());
                bundle.putString("reg_url", eventListAdapter.getItem(i).getreg_url());
                bundle.putString("rules_url", eventListAdapter.getItem(i).getrules_url());
                bundle.putString("time", eventListAdapter.getItem(i).gettime());
                bundle.putString("venue", eventListAdapter.getItem(i).getvenue());
                bundle.putString("cover_url", eventListAdapter.getItem(i).getcover_url());

                eventIntent.putExtras(bundle);

                startActivity(eventIntent);
            }
        });

        return rootView;
    }

    private void attachDatabaseReadListener(int eventCode){
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    int getEventCode = Integer.parseInt(dataSnapshot.child("code").getValue().toString());

                    if (eventCode == 0 && getEventCode != 0){
                        EventListData eventListData = dataSnapshot.getValue(EventListData.class);
                        eventListAdapter.add(eventListData);
                    } else if (eventCode != 0 && getEventCode !=0 && getEventCode == eventCode) {
                        EventListData eventListData = dataSnapshot.getValue(EventListData.class);
                        eventListAdapter.add(eventListData);
                    }
                    eventListProgress.setVisibility(View.INVISIBLE);
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

    private void detachDatabaseReadListener() {
        if (mChildEventListener != null) {
            eventsDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        detachDatabaseReadListener();
        eventListAdapter.clear();
    }
}
