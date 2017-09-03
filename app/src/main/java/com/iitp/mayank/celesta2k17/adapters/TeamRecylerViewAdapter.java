package com.iitp.mayank.celesta2k17.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iitp.mayank.celesta2k17.R;
import com.iitp.mayank.celesta2k17.data.TeamData;

import java.util.ArrayList;

/**
 * Created by manish on 22/8/17.
 */

public class TeamRecylerViewAdapter extends RecyclerView.Adapter<TeamRecylerViewAdapter.TeamViewHolder> {
    private String Name[] ;
    private String POR[] ;
    private  String PhoneNumber[] ;
     Context context;
    private PhoneCLick phoneCLickListener;

    //setting a contructor to set the values of parameters inside the class
    public TeamRecylerViewAdapter (Context context , PhoneCLick phoneCLickListener ,String NameValue[],String PORvalue[] ,String PhoneNumber[])
    {
        Name=NameValue;
        POR=PORvalue;
        this.context=context ;
        this.PhoneNumber=PhoneNumber;
        this.phoneCLickListener = phoneCLickListener;

    }

    public interface PhoneCLick{
        void onPhoneClick(String phone);
    }
// when a new view is created in the recylerView
    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext() ;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean attachToParent = false;
        //inlating the orignal view
        View view = layoutInflater.inflate(R.layout.team_cardview , parent , attachToParent);
        //tossing the orignal view to constructor to cache the view
        TeamViewHolder teamViewHolder = new TeamViewHolder(view) ;

        return  teamViewHolder ;
    }


    // to attach data to the view


    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        //binding view with the data
        holder.phoneNumberTextView.setText(PhoneNumber[position]);
        holder.nameTextView.setText(Name[position]);
        holder.porTextView.setText(POR[position]);
    }


    @Override
    public int getItemCount() {
        return Name.length;
    }

    //view holder to cache the Views
    class TeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView porTextView ;
        TextView nameTextView ;
        TextView phoneNumberTextView ;
        ImageView picImageView ;

//caching the view on the listeners
        public TeamViewHolder(View itemView)
        {
            super(itemView) ;
            porTextView=(TextView)itemView.findViewById(R.id.team_porTextView);
            nameTextView=(TextView)itemView.findViewById(R.id.team_nameTextView) ;
            phoneNumberTextView=(TextView)itemView.findViewById(R.id.team_phoneTextView);
            picImageView=(ImageView)itemView.findViewById(R.id.team_imageNameView);
            phoneNumberTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            phoneCLickListener.onPhoneClick(phoneNumberTextView.getText().toString());
        }
    }
}
