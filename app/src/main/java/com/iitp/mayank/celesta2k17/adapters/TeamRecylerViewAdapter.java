package com.iitp.mayank.celesta2k17.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iitp.mayank.celesta2k17.R;

/**
 * Created by manish on 22/8/17.
 */

public class TeamRecylerViewAdapter extends RecyclerView.Adapter<TeamRecylerViewAdapter.TeamViewHolder> {
    TypedArray images;
    Context context;
    private String Name[];
    private String Committee[];
    private String POR[];
    private String PhoneNumber[];
    private PhoneCLick phoneCLickListener;

    //setting a contructor to set the values of parameters inside the class
    public TeamRecylerViewAdapter(Context context, PhoneCLick phoneCLickListener, String NameValue[], String PORValue[], String CommitteeValue[], String PhoneNumber[], TypedArray images) {
        Name = NameValue;
        Committee = CommitteeValue;
        POR = PORValue;
        this.context = context;
        this.PhoneNumber = PhoneNumber;
        this.phoneCLickListener = phoneCLickListener;
        this.images = images;
    }

    // when a new view is created in the recylerView
    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean attachToParent = false;
        //inlating the orignal view
        View view = layoutInflater.inflate(R.layout.team_cardview, parent, attachToParent);
        //tossing the orignal view to constructor to cache the view
        TeamViewHolder teamViewHolder = new TeamViewHolder(view);

        return teamViewHolder;
    }

    // to attach data to the view
    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        //binding view with the data
        if (!PhoneNumber[position].equals("-1")) {
            holder.phoneIcon.setVisibility(View.VISIBLE);
            holder.phoneNumberTextView.setVisibility(View.VISIBLE);
            holder.phoneNumberTextView.setText(PhoneNumber[position]);
        } else {
            holder.phoneIcon.setVisibility(View.GONE);
            holder.phoneNumberTextView.setVisibility(View.GONE);
        }
        holder.nameTextView.setText(Name[position]);
        if (!POR[position].equals("-1")) {
            holder.porTextView.setVisibility(View.VISIBLE);
            holder.porTextView.setText(POR[position]);
        } else {
            holder.porTextView.setVisibility(View.GONE);
        }
        holder.committeeTextView.setText(Committee[position]);
        Glide.with(context).clear(holder.picImageView);

        Glide.with(context)
                .load(images.getResourceId(position, -1))
                .thumbnail(0.5f)
                .into(holder.picImageView);
    }

    @Override
    public int getItemCount() {
        return Name.length;
    }


    public interface PhoneCLick {
        void onPhoneClick(String phone);
    }

    //view holder to cache the Views
    class TeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView committeeTextView;
        TextView porTextView;
        TextView nameTextView;
        ImageView phoneIcon;
        TextView phoneNumberTextView;
        ImageView picImageView;

        //caching the view on the listeners
        public TeamViewHolder(View itemView) {
            super(itemView);
            committeeTextView = (TextView) itemView.findViewById(R.id.team_committee_textview);
            porTextView = (TextView) itemView.findViewById(R.id.team_por_textview);
            nameTextView = (TextView) itemView.findViewById(R.id.team_nameTextView);
            phoneIcon = (ImageView) itemView.findViewById(R.id.team_phone_icon);
            phoneNumberTextView = (TextView) itemView.findViewById(R.id.team_phoneTextView);
            picImageView = (ImageView) itemView.findViewById(R.id.team_imageNameView);
            phoneNumberTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            phoneCLickListener.onPhoneClick(phoneNumberTextView.getText().toString());
        }
    }
}
