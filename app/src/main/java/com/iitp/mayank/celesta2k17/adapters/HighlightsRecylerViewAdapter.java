package com.iitp.mayank.celesta2k17.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iitp.mayank.celesta2k17.R;

import java.util.ArrayList;

/**
 * Created by manish on 30/8/17.
 */

public class HighlightsRecylerViewAdapter extends RecyclerView.Adapter<HighlightsRecylerViewAdapter.HighlightsViewHolder> {

    Context context;
    ArrayList<String> header;
    ArrayList<String> details;
    //to set the values tossed from network splash background task
    public HighlightsRecylerViewAdapter (Context context , ArrayList<String> header , ArrayList<String> details)
    {
        this.context = context;
        this.header = header;
        this.details = details;
    }



    @Override
    public HighlightsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext() ;

        LayoutInflater layoutInflater = LayoutInflater.from(context) ;
        View view = layoutInflater.inflate(R.layout.highlights_card_view,parent,false);
        //tossing the view for caching
        HighlightsViewHolder highlightsViewHolder = new HighlightsViewHolder(view) ;

        return  highlightsViewHolder ;

    }

    @Override
    public int getItemCount() {
        return header.size();
    }

    @Override
    public void onBindViewHolder(HighlightsViewHolder holder, int position) {
        holder.header.setText(header.get(position));
        holder.content.setText(details.get(position));
    }

    //making a view holder to cache the value of the view
    class HighlightsViewHolder extends RecyclerView.ViewHolder {
        TextView header;
        TextView content;

        public HighlightsViewHolder(View itemView) {
            super(itemView);
            header = (TextView) itemView.findViewById(R.id.highlightsheader);
            content = (TextView) itemView.findViewById(R.id.highlightscontent);
        }
    }
}
