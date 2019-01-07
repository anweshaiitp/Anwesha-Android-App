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
import info.anwesha2k18.iitp.data.LecturesData;
import info.anwesha2k18.iitp.data.WorkshopData;

public class LectureAdapter extends ArrayAdapter<LecturesData> {

    Context mContext;

    public LectureAdapter(@NonNull Context context, int resource, List<LecturesData> objects) {
        super(context, resource, objects);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.card_lectures, parent, false);
        }

        TextView lectureName = (TextView) convertView.findViewById(R.id.lecture_name_display);
        TextView lectureDate = (TextView) convertView.findViewById(R.id.lecture_date_display);
        TextView lectureTime = (TextView) convertView.findViewById(R.id.lecture_time_display);
        TextView lectureVenue = (TextView) convertView.findViewById(R.id.lecture_venue_display);
        TextView lectureDescription = (TextView) convertView.findViewById(R.id.lecture_description_display);
        ImageView lectureImage = (ImageView) convertView.findViewById(R.id.lecture_image_display);

        LecturesData currentLecture = getItem(position);

        lectureName.setText(currentLecture.getWorkshopName());
        lectureDate.setText(currentLecture.getWorkshopDate());
        lectureTime.setText(currentLecture.getWorkshopTime());
        lectureVenue.setText(currentLecture.getWorkshopVenue());
        lectureDescription.setText(currentLecture.getWorkshopDescription());

        Glide.with(getContext())
                .load(currentLecture.getWorkshopImageUrl())
                .apply(new RequestOptions().error(R.drawable.anwesha_placeholder))
                .into(lectureImage);


        return convertView;
    }

}
