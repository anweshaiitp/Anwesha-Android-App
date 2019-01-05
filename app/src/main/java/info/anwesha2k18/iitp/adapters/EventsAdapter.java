package info.anwesha2k18.iitp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.data.EventListData;


public class EventsAdapter extends ArrayAdapter<EventListData> {
    Context context;
    TypedArray images;

 public EventsAdapter(Context context, int resource, List<EventListData> objects){
     super(context,resource,objects);

 }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            convertView=((Activity) getContext()).getLayoutInflater().inflate(R.layout.card_view,parent,false);
        }
        EventListData currentEvent=getItem(position);
        
        TextView eventDescDisplay=(TextView) convertView.findViewById(R.id.card_text);
        TextView eventTextDisplay=(TextView) convertView.findViewById(R.id.card_header);


        eventTextDisplay.setText(currentEvent.geteveName());

        eventDescDisplay.setText(currentEvent.getshort_desc());



        return  convertView;
    }
}
