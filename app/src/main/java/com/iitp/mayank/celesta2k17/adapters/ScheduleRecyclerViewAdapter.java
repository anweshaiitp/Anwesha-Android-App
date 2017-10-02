package com.iitp.mayank.celesta2k17.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iitp.mayank.celesta2k17.R;
import com.iitp.mayank.celesta2k17.data.EventsData;

import java.util.ArrayList;

/**
 * Created by mayank on 23/8/17.
 */

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<ScheduleRecyclerViewAdapter.ScheduleViewHolder> {

    private final ScheduleRecyclerViewAdapter.ListCardClick mOnClickListener;
    ArrayList<EventsData> dataList = new ArrayList<>();
    String eventHeader[];
    String eventText[];
    String time[];
    String date[];
    String venue[];
    TypedArray images;
    Context context;

    public ScheduleRecyclerViewAdapter(Context context, ScheduleRecyclerViewAdapter.ListCardClick listCardClick, String eventHeader[], String eventText[], String time[], String date[], String venue[], TypedArray images) {
        this.context = context;
        mOnClickListener = listCardClick;
        this.eventHeader = eventHeader;
        this.eventText = eventText;
        this.time = time;
        this.date = date;
        this.venue = venue;
        this.images = images;
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean attachToParent = false;
        View view = layoutInflater.inflate(R.layout.card_schedule, parent, attachToParent);
        ScheduleViewHolder scheduleViewHolder = new ScheduleViewHolder(view);
        return scheduleViewHolder;
    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int position) {
        holder.header.setText(eventHeader[position]);
        holder.location.setText(venue[position]);
        holder.time.setText(time[position]);

        EventsData eventsData = new EventsData();
        eventsData.setHeader(eventHeader[position]);
        eventsData.setText(eventText[position]);
        eventsData.setVenue(venue[position]);
        eventsData.setImageId(images.getResourceId(position, -1));
        eventsData.setDateTime(date[position] + " \nTime:" + time[position]);

        dataList.add(eventsData);
    }

    @Override
    public int getItemCount() {
        return eventHeader.length;
    }

    public interface ListCardClick {
        void onListClick(EventsData eventsData, View view) throws ClassNotFoundException;
    }

    class ScheduleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView header;
        TextView time;
        TextView location;

        public ScheduleViewHolder(View itemView) {
            super(itemView);
            header = (TextView) itemView.findViewById(R.id.schedule_card_header);
            time = (TextView) itemView.findViewById(R.id.time_text_view);
            location = (TextView) itemView.findViewById(R.id.location_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            try {
                mOnClickListener.onListClick(dataList.get(position), v);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
