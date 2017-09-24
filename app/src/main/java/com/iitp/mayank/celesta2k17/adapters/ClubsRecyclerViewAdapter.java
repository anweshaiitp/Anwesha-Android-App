package com.iitp.mayank.celesta2k17.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iitp.mayank.celesta2k17.R;
import com.iitp.mayank.celesta2k17.data.ClubsData;

import java.util.ArrayList;


public class ClubsRecyclerViewAdapter extends RecyclerView.Adapter<ClubsRecyclerViewAdapter.ClubsViewHolder>
{
    private final ListCardClick mOnClickListener;
    ArrayList<ClubsData> dataList = new ArrayList<>();
    String eventHeader[];
    String eventText[];
    String intent[];
    TypedArray images;
    Context context;

    public ClubsRecyclerViewAdapter(Context context, ListCardClick listCardClick, String eventHeader[], String eventText[], String intent[], TypedArray img)
    {
        this.eventHeader = eventHeader;
        this.eventText = eventText;
        this.intent = intent;
        mOnClickListener = listCardClick;
        images = img;
        this.context = context;
    }

    @Override
    public ClubsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean attachToParent = false;
        View view = layoutInflater.inflate(R.layout.card_view , parent , attachToParent);
        ClubsViewHolder clubsViewHolder = new ClubsViewHolder(view);
        return clubsViewHolder;
    }

             @Override
    public void onBindViewHolder(ClubsViewHolder holder, int position) {
        ClubsData clubsData = new ClubsData();
        clubsData.setHeader(eventHeader[position]);
        clubsData.setText(eventText[position]);
        clubsData.setIntentClass(intent[position]);
        clubsData.setImageId(images.getResourceId(position, -1));

        dataList.add(clubsData);

        holder.textViewHeader.setText(eventHeader[position]);

        if(!eventText[position].equals("-1"))
            holder.textViewData.setText(eventText[position]);
        else
        {
            holder.textViewData.setVisibility(View.GONE);
            holder.textViewHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP , 16);
        }
        holder.textViewData.setVisibility(View.GONE);
        Glide.with(context).clear(holder.imageView);
        Glide.with(context).load(clubsData.getImageId()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return eventHeader.length;
    }

    public interface ListCardClick {
        void onListClick(String intent) throws ClassNotFoundException;
    }

    class ClubsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView textViewHeader;
        TextView textViewData;
        ImageView imageView;

        public ClubsViewHolder(View itemView) {
            super(itemView);
            textViewHeader = (TextView)itemView.findViewById(R.id.card_header);
            textViewData = (TextView)itemView.findViewById(R.id.card_text);
            imageView = (ImageView)itemView.findViewById(R.id.card_cardimage);
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
