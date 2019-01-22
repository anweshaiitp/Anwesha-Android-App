package info.anwesha2k19.iitp.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import info.anwesha2k19.iitp.R;
import info.anwesha2k19.iitp.adapters.TimelineListAdapter;
import info.anwesha2k19.iitp.data.TimelineData;

public class TimelineFragment extends Fragment {

    private TimelineListAdapter timelineListAdapter;
    private ListView timelineListView;
    private int day;
    private FirebaseDatabase mfirebase;
    private DatabaseReference eventsDatabaseReference;
    private ChildEventListener mChildEventListener;
    ProgressBar timelineProgressBar;

    public static TimelineFragment newInstance(int page){
        Bundle args = new Bundle();
        args.putInt("day", page);
        TimelineFragment fragment = new TimelineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mfirebase = FirebaseDatabase.getInstance();
        eventsDatabaseReference = mfirebase.getReference().child("schedule");
        eventsDatabaseReference.keepSynced(true);
       // eventsDatabaseReference.orderByChild("time");

        switch (getArguments().getInt("day")){

            case 1:
                day = 1;
                break;
            case 2:
                day = 2;
                break;
            case 3:
                day = 3;
                break;
            default:

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_timeline, container, false);

        timelineListView = rootView.findViewById(R.id.timeline_list_view);

        List<TimelineData> singleDayList = new ArrayList<TimelineData>();

        timelineListAdapter = new TimelineListAdapter(getActivity(), R.layout.card_timeline, singleDayList);

        timelineListView.setAdapter(timelineListAdapter);
        timelineProgressBar = rootView.findViewById(R.id.timeline_progress_bar);

        attachDatabaseReadListener(day);

        return rootView;
    }

    private void attachDatabaseReadListener(int day){
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    int dayInt = 0;
                   // int getEventCode = Integer.parseInt(dataSnapshot.child("code").getValue().toString());
                    if (dataSnapshot.child("day").getValue() != null) {
                        dayInt = Integer.parseInt(dataSnapshot.child("day").getValue().toString());
                    }

                    if (dayInt != 0 && dayInt == day){
                        TimelineData eventListData = dataSnapshot.getValue(TimelineData.class);
                        timelineListAdapter.add(eventListData);
                    }
                    timelineProgressBar.setVisibility(View.INVISIBLE);
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
        timelineListAdapter.clear();
    }


}
