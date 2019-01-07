package info.anwesha2k18.iitp.adapters;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

import info.anwesha2k18.iitp.data.LecturesData;
import info.anwesha2k18.iitp.data.WorkshopData;
import info.anwesha2k18.iitp.utils.ExtractWorkshop;

public class LectureLoader extends AsyncTaskLoader<List<LecturesData>> {

    private String mUrl;

    public LectureLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<LecturesData> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<LecturesData> lecturesDataList = ExtractWorkshop.extLectures(mUrl);
        return  lecturesDataList;
    }

}
