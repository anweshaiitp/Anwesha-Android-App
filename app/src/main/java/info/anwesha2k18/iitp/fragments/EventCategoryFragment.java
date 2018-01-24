package info.anwesha2k18.iitp.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.activities.EventInfoActivity;
import info.anwesha2k18.iitp.adapters.EventCategoryRecyclerViewAdapter;
import info.anwesha2k18.iitp.data.EventData;
import info.anwesha2k18.iitp.database.WebSyncDB;

public class EventCategoryFragment extends Fragment implements EventCategoryRecyclerViewAdapter.CardClick{

    public List<EventData> AllEvents = new ArrayList<>();
    public List<EventData> CulturalEvents = new ArrayList<>();

    public java.util.Map< Integer ,List<EventData>> m1 = new HashMap<>();


    WebSyncDB db;
    int id;

    public EventCategoryFragment() {
    }

    public static EventCategoryFragment newInstance(int eveid) {
        EventCategoryFragment fragment = new EventCategoryFragment();
        Bundle args = new Bundle();
        args.putInt("id", eveid);
        fragment.setArguments(args);
        return fragment;
    }

    private void getAllEvents(){
        CulturalEvents.clear();
        db = new WebSyncDB(getContext());
        Cursor cursor = db.getAllEvents();
        int c = 0;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            AllEvents.add(new EventData(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15)));
            cursor.moveToNext();
            c++;
            if (c > 200) break;
        }

        for(int i=0;i<AllEvents.size();i++){
            if(AllEvents.get(i).code == id){
                int temp = AllEvents.get(i).id;
                int x=0;
                for(int j=0;j<AllEvents.size();j++){
                    if(temp == AllEvents.get(j).code){
                        CulturalEvents.add(AllEvents.get(j));
                        x++;
                    }
                }
                if(x==0){
                    CulturalEvents.add(AllEvents.get(i));
                }
            }
        }
        m1.put(id,CulturalEvents);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt("id", 0);
        getAllEvents();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_event_category, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new EventCategoryRecyclerViewAdapter(m1.get(id), getContext(), this));
        return view;
    }

    @Override
    public void onListClick(EventData eventData, View view) throws ClassNotFoundException {

        ImageView imageView = view.findViewById(R.id.card_cardimage);
        TextView textViewHeader = view.findViewById(R.id.card_header);
        Pair<View, String> p1 = Pair.create((View) imageView, "event_image_view_transition");
        Pair<View, String> p2 = Pair.create((View) textViewHeader, "event_text_header_transition");

//        ActivityOptionsCompat options = ActivityOptionsCompat.
//                makeSceneTransitionAnimation(this.getActivity(), p1, p2);

        Intent intentNew = new Intent(this.getContext(), EventInfoActivity.class);
        intentNew.putExtra(EventInfoActivity.EXTRA_HEADER, eventData.name);
        intentNew.putExtra(EventInfoActivity.EXTRA_LONG_DESCRIPTION, eventData.toDisplay_short + eventData.toDisplay_long);
//        intentNew.putExtra(EventInfoActivity.EXTRA_RULES, eventsData.getRules());
        intentNew.putExtra(EventInfoActivity.EXTRA_TIME, eventData.time);
        intentNew.putExtra(EventInfoActivity.EXTRA_VENUE, eventData.venue);
//        intentNew.putExtra(EventInfoActivity.EXTRA_IMAGE_ID, eventData.getImageId());
        intentNew.putExtra(EventInfoActivity.EXTRA_ORGANIZERS, eventData.organisers);
//        intentNew.putExtra(EXTRA_CONTACTS, eventsData.getContacts());
        intentNew.putExtra(EventInfoActivity.EXTRA_ID, eventData.id);
        intentNew.putExtra(EventInfoActivity.EXTRA_CODE, eventData.code);
        intentNew.putExtra(EventInfoActivity.EXTRA_DAY, eventData.day);
        intentNew.putExtra(EventInfoActivity.EXTRA_SIZE, eventData.size);
        intentNew.putExtra(EventInfoActivity.EXTRA_FEE, eventData.fee);
        intentNew.putExtra(EventInfoActivity.EXTRA_DATE, eventData.date);
        intentNew.putExtra(EventInfoActivity.EXTRA_RULES, eventData.rules);
        intentNew.putExtra(EventInfoActivity.EXTRA_REG_URL, eventData.regURL);
        intentNew.putExtra(EventInfoActivity.EXTRA_IMAGE_ID, eventData.imageURL);
        intentNew.putExtra(EventInfoActivity.EXTRA_DATE, eventData.date);
        startActivity(intentNew);
    }
}
