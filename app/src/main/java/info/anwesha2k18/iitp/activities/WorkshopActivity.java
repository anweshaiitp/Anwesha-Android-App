package info.anwesha2k18.iitp.activities;


import android.os.Bundle;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.adapters.WorkshopAdapter;
import info.anwesha2k18.iitp.adapters.WorkshopLoader;
import info.anwesha2k18.iitp.data.WorkshopData;

public class WorkshopActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<WorkshopData>> {

    private ListView workshopListView;
    private WorkshopAdapter mAdapter;
    private final String WORKSHOP_URL = "https://firebasestorage.googleapis.com/v0/b/anwesha2k19-grobo.appspot.com/o/workshop.json?alt=media";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop);

        workshopListView = findViewById(R.id.workshop_list_view);

        List<WorkshopData> workshopDataList = new ArrayList<WorkshopData>();
        mAdapter = new WorkshopAdapter(WorkshopActivity.this, R.layout.card_workshop, workshopDataList);

        workshopListView.setAdapter(mAdapter);

        loadWorkshop();

    }

    public void loadWorkshop(){
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(1, null, this);
    }

    @Override
    public Loader<List<WorkshopData>> onCreateLoader(int id, Bundle args) {
        return new WorkshopLoader(this, WORKSHOP_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<WorkshopData>> loader, List<WorkshopData> data) {

        mAdapter.clear();
        if (data != null && !data.isEmpty()){
            mAdapter.addAll(data);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<WorkshopData>> loader) {
        mAdapter.clear();
    }
}