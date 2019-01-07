package info.anwesha2k18.iitp.activities;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.adapters.WorkshopAdapter;
import info.anwesha2k18.iitp.adapters.WorkshopLoader;
import info.anwesha2k18.iitp.data.WorkshopData;

public class WorkshopActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<WorkshopData>> {

    private ListView workshopListView;
    private WorkshopAdapter mAdapter;
    private ProgressBar mProgressBar;
    private Button registerButton;

    private final String WORKSHOP_URL = "https://firebasestorage.googleapis.com/v0/b/anwesha-2k19.appspot.com/o/workshop%2Fworkshop.json?alt=media";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop);

        mProgressBar = findViewById(R.id.workshop_progress);

        workshopListView = findViewById(R.id.workshop_list_view);

        registerButton =(Button) findViewById(R.id.workshop_register_button);

        List<WorkshopData> workshopDataList = new ArrayList<WorkshopData>();
        mAdapter = new WorkshopAdapter(WorkshopActivity.this, R.layout.card_workshop, workshopDataList);

        workshopListView.setAdapter(mAdapter);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.instamojo.com/techo/anwesha19-iit-patna-techno-workshop-series/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

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
        mProgressBar.setVisibility(View.GONE);
        if (data != null && !data.isEmpty()){
            mAdapter.addAll(data);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<WorkshopData>> loader) {
        mAdapter.clear();
    }
}