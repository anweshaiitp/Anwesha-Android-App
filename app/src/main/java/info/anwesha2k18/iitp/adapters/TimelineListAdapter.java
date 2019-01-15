package info.anwesha2k18.iitp.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

        if(position %2 == 1) {
            LinearLayout linearLayoutLeft = (LinearLayout) convertView.findViewById(R.id.timeline_linear_layout_left);
            LinearLayout linearLayoutRight = (LinearLayout) convertView.findViewById(R.id.timeline_linear_layout_right);
            TextView timelineEventName = (TextView) convertView.findViewById(R.id.timeline_eveName_left);
            TextView timelineTime = (TextView) convertView.findViewById(R.id.timeline_time_left);
            TextView timelineVenue = (TextView) convertView.findViewById(R.id.timeline_venue_left);
            ImageView timelineImageView = (ImageView) convertView.findViewById(R.id.timeline_image_view_left);

            linearLayoutLeft.setVisibility(View.VISIBLE);
            linearLayoutRight.setVisibility(View.GONE);
            timelineEventName.setText(currentEvent.geteveName());
            timelineTime.setText(currentEvent.gettime());
            timelineVenue.setText(currentEvent.getvenue());
       /* Glide.with(getContext())
                .load(currentEvent.getcover_url())
                .apply(new RequestOptions().error(R.drawable.anwesha_placeholder))
                .into(timelineImageView);
                */
        } else {
            LinearLayout linearLayoutLeft = (LinearLayout) convertView.findViewById(R.id.timeline_linear_layout_left);
            LinearLayout linearLayoutRight = (LinearLayout) convertView.findViewById(R.id.timeline_linear_layout_right);
            TextView timelineEventName = (TextView) convertView.findViewById(R.id.timeline_eveName_right);
            TextView timelineTime = (TextView) convertView.findViewById(R.id.timeline_time_right);
            TextView timelineVenue = (TextView) convertView.findViewById(R.id.timeline_venue_right);
            ImageView timelineImageView = (ImageView) convertView.findViewById(R.id.timeline_image_view_right);

            timelineVenue.setText(currentEvent.getvenue());
            linearLayoutRight.setVisibility(View.VISIBLE);
            linearLayoutLeft.setVisibility(View.GONE);
            timelineEventName.setText(currentEvent.geteveName());
            timelineTime.setText(currentEvent.gettime());
        }


        return  convertView;
    }

}
