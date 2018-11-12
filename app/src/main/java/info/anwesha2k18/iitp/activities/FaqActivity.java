package info.anwesha2k18.iitp.activities;

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.activities.ExpandableListAdapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FaqActivity extends AppCompatActivity {

    private info.anwesha2k18.iitp.activities.ExpandableListAdapter mListAdapter;
    private ExpandableListView mExpandableListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.faq_color));

        mExpandableListView = findViewById(R.id.lvExp);
        initData();
        mListAdapter = new ExpandableListAdapter(this, listDataHeader, listHash);
        mExpandableListView.setAdapter(mListAdapter);

    }

    public void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("What is Anwesha?");
        listDataHeader.add("When was Anwesha started?");
        listDataHeader.add("When Anwesha will take place?");
        listDataHeader.add("How to reach?");

        List<String> o = new ArrayList<>();
        o.add("Anwesha is a Techno-Cultural extravaganza held every year in spring at IIT Patna");

        List<String> t = new ArrayList<>();
        t.add("Anwesha started in 2010");

        List<String> th = new ArrayList<>();
        th.add("The dates will be notified soon");
        List<String> f = new ArrayList<>();
        f.add("Anwesha will take place at Bihta campus of IIT Patna." +
                "Check Map for more details");

        listHash.put(listDataHeader.get(0), o);
        listHash.put(listDataHeader.get(1), t);
        listHash.put(listDataHeader.get(2), th);
        listHash.put(listDataHeader.get(3), f);
    }
}



