package com.iitp.mayank.celesta2k17.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iitp.mayank.celesta2k17.R;
import com.iitp.mayank.celesta2k17.data.EventsData;

import java.util.ArrayList;


public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<EventsRecyclerViewAdapter.EventViewHolder>
{
    ArrayList<EventsData> dataList = new ArrayList<>();
    String eventHeader[];
    String eventText[];
    String intent[];

    private final ListCardClick mOnClickListener;

    public EventsRecyclerViewAdapter(ListCardClick listCardClick , String eventHeader[], String eventText[] ,String intent[])
    {
        this.eventHeader = eventHeader;
        this.eventText = eventText;
        this.intent = intent;
        mOnClickListener = listCardClick;
    }

    public interface ListCardClick
    {
        void onListClick(String intent) throws ClassNotFoundException;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean attachToParent = false;
        View view = layoutInflater.inflate(R.layout.card_view , parent , attachToParent);
        EventViewHolder eventViewHolder = new EventViewHolder(view);
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        EventsData eventsData = new EventsData();
        eventsData.setHeader(eventHeader[position]);
        eventsData.setText(eventText[position]);
        eventsData.setIntentClass(intent[position]);

        dataList.add(eventsData);
        holder.textViewHeader.setText(eventHeader[position]);
        holder.textViewData.setText(eventText[position]);
    }

    @Override
    public int getItemCount() {
        return eventHeader.length;
    }

    class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView textViewHeader;
        TextView textViewData;
        public EventViewHolder(View itemView) {
            super(itemView);
            textViewHeader = (TextView)itemView.findViewById(R.id.card_header);
            textViewData = (TextView)itemView.findViewById(R.id.card_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            try {
                mOnClickListener.onListClick(dataList.get(position).getIntentClass());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
