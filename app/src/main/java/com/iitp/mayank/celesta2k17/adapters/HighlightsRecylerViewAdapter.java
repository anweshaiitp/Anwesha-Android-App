package com.iitp.mayank.celesta2k17.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iitp.mayank.celesta2k17.R;

/**
 * Created by manish on 30/8/17.
 */

public class HighlightsRecylerViewAdapter extends RecyclerView.Adapter<HighlightsRecylerViewAdapter.HighlightsViewHolder> {


    @Override
    public HighlightsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onBindViewHolder(HighlightsViewHolder holder, int position) {


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
