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

        LecturesData currentLecture = getItem(position);

        TextView lecturesNameView = (TextView) convertView.findViewById(R.id.lecture_name_view);
        TextView lectureDetailView = (TextView) convertView.findViewById(R.id.lecture_detail_view);
        ImageView lectureImageView = (ImageView) convertView.findViewById(R.id.lecture_image_view);

        lecturesNameView.setText(currentLecture.getLectureName());
        lectureDetailView.setText(currentLecture.getLectureDetail());

        Glide.with(getContext())
                .load(currentLecture.getLectureImageUrl())
                .apply(new RequestOptions().error(R.drawable.anwesha_placeholder))
                .into(lectureImageView);

        return convertView;
    }

}
