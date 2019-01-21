package info.anwesha2k18.iitp.activities;


import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.adapters.LectureAdapter;
import info.anwesha2k18.iitp.adapters.LectureLoader;
import info.anwesha2k18.iitp.adapters.WorkshopAdapter;
import info.anwesha2k18.iitp.data.LecturesData;
import info.anwesha2k18.iitp.data.WorkshopData;

public class LectureActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<LecturesData>>{

    private ListView lecturesListView;
    private LectureAdapter mAdapter;
    private ProgressBar mProgressBar;
    private final String LECTURES_URL = "https://firebasestorage.googleapis.com/v0/b/anwesha-2k19.appspot.com/o/lectures%2Flectures.json?alt=media";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop);

        Button registerButton = (Button) findViewById(R.id.workshop_register_button);
        registerButton.setVisibility(View.GONE);

        mProgressBar = findViewById(R.id.workshop_progress);
        lecturesListView = findViewById(R.id.workshop_list_view);

        List<LecturesData> lecturesDataList = new ArrayList<LecturesData>();
        mAdapter = new LectureAdapter(LectureActivity.this, R.layout.card_lectures, lecturesDataList);

        lecturesListView.setAdapter(mAdapter);

        loadLectures();

    }

    public void loadLectures(){
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(2, null, this);
    }

    @Override
    public Loader<List<LecturesData>> onCreateLoader(int i, Bundle bundle) {
        return new LectureLoader(this, LECTURES_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<LecturesData>> loader, List<LecturesData> lecturesData) {
        mAdapter.clear();
        mProgressBar.setVisibility(View.GONE);
        if (lecturesData != null && !lecturesData.isEmpty()){
            mAdapter.addAll(lecturesData);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<LecturesData>> loader) {

    }
}
