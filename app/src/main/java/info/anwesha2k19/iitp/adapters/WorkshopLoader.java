package info.anwesha2k19.iitp.adapters;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

import info.anwesha2k19.iitp.data.WorkshopData;
import info.anwesha2k19.iitp.utils.ExtractWorkshop;

public class WorkshopLoader extends AsyncTaskLoader<List<WorkshopData>> {

    private String mUrl;

    public WorkshopLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<WorkshopData> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<WorkshopData> workshopDataList = ExtractWorkshop.doEverything(mUrl);
        return  workshopDataList;
    }
}
