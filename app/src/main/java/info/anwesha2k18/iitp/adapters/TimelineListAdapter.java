package info.anwesha2k18.iitp.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.data.TimelineData;

public class TimelineListAdapter extends ArrayAdapter<TimelineData> {

    public TimelineListAdapter(Context context, int resource, List<TimelineData> objects){
        super(context,resource,objects);

    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            convertView=((Activity) getContext()).getLayoutInflater().inflate(R.layout.card_timeline,parent,false);
        }
        TimelineData currentEvent=getItem(position);

        TextView timelineEventName=(TextView) convertView.findViewById(R.id.timeline_eveName);
        TextView timelineTime=(TextView) convertView.findViewById(R.id.timeline_time);
        TextView timelineVenue = (TextView) convertView.findViewById(R.id.timeline_venue);
        ImageView timelineImageView = (ImageView) convertView.findViewById(R.id.timeline_image_view);


        timelineEventName.setText(currentEvent.geteveName());
        timelineTime.setText(currentEvent.gettime());
        timelineVenue.setText(currentEvent.getvenue());
        Glide.with(getContext())
                .load(currentEvent.getcover_url())
                .apply(new RequestOptions().error(R.drawable.anwesha_placeholder))
                .into(timelineImageView);


        return  convertView;
    }

}
