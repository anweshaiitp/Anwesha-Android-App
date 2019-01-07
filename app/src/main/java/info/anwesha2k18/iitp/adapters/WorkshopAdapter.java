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
import info.anwesha2k18.iitp.data.WorkshopData;

public class WorkshopAdapter extends ArrayAdapter<WorkshopData> {

    Context mContext;

    public WorkshopAdapter(@NonNull Context context, int resource, List<WorkshopData> objects) {
        super(context, resource, objects);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.card_workshop, parent, false);
        }

        TextView workshopName = (TextView) convertView.findViewById(R.id.workshop_name_display);
        TextView workshopDate = (TextView) convertView.findViewById(R.id.workshop_date_display);
        TextView workshopVenue = (TextView) convertView.findViewById(R.id.workshop_venue_display);
        TextView workshopInfoDisplay = (TextView) convertView.findViewById(R.id.workshop_info_link_display);
        ImageView workshopImage = (ImageView) convertView.findViewById(R.id.workshop_image_display);

        WorkshopData currentWorkshop = getItem(position);

        workshopName.setText(currentWorkshop.getWorkshopName());
        workshopDate.setText(currentWorkshop.getWorkshopDate());
        workshopVenue.setText(currentWorkshop.getWorkshopVenue());
        workshopInfoDisplay.setText(currentWorkshop.getWorkshopInfoUrl());

        Glide.with(getContext())
                .load(currentWorkshop.getWorkshopImageUrl())
                .apply(new RequestOptions().error(R.drawable.anwesha_placeholder))
                .into(workshopImage);

        return convertView;
    }
}
