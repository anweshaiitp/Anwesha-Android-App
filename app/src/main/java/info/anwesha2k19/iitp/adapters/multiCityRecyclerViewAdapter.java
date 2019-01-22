package info.anwesha2k19.iitp.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import info.anwesha2k19.iitp.R;
import info.anwesha2k19.iitp.activities.MainActivity;
import info.anwesha2k19.iitp.activities.multiCityActivity;
import info.anwesha2k19.iitp.data.multiCityData;

public class multiCityRecyclerViewAdapter  extends RecyclerView.Adapter<multiCityRecyclerViewAdapter.multiCityViewHolder>  {

    ArrayList<multiCityData> dataList = new ArrayList<>();
    String eventHeader[];
    String eventDateTime[];
    String eventVenue[];
    TypedArray images;
    String eventDescription[];
    Context context;
    private final ListCardClick mOnClickListener;
    public multiCityRecyclerViewAdapter(Context context, ListCardClick listCardClick, String eventHeader[], String eventDateTime[],String eventVenue[],String eventDescription[],TypedArray img) {
        this.eventHeader = eventHeader;
        this.eventDescription = eventDescription;
        this.eventDateTime = eventDateTime;
        this.eventVenue= eventVenue;
        mOnClickListener = listCardClick;
        images = img;
        this.context = context;
    }
    @Override
    public multiCityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean attachToParent = false;
        View view = layoutInflater.inflate(R.layout.card_multicity, parent, attachToParent);
        multiCityViewHolder lecturesViewHolder = new multiCityViewHolder(view);
        return lecturesViewHolder;
    }
    @Override
    public void onBindViewHolder(multiCityViewHolder holder, int position) {
        multiCityData clubsData = new multiCityData(eventHeader[position],eventDateTime[position],eventVenue[position],eventDescription[position],images.getResourceId(position, -1));
        dataList.add(clubsData);
        holder.imageView.setImageResource(images.getResourceId(position, -1));
    }
    @Override
    public int getItemCount() {
        return eventHeader.length;
    }
    public interface ListCardClick {
        void onListClick(multiCityData eventsData, View view) throws ClassNotFoundException;
    }
    class multiCityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        public multiCityViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.cardimage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                int position = getAdapterPosition();
            if((dataList.get(position).headers.equals("-1"))) {
                Toast.makeText(context, "Coming Soon!", Toast.LENGTH_SHORT).show();
            } else{
            try {
                mOnClickListener.onListClick(dataList.get(position), v);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
      }
    }
}
