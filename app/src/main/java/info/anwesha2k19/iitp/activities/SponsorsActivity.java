package info.anwesha2k19.iitp.activities;

import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

import info.anwesha2k19.iitp.R;

public class SponsorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);
        HashMap<Integer ,String> map = new HashMap<>();
        map.put(R.id.airtel,"https://www.airtel.in/");
        map.put(R.id.ascspons1,"http://www.startup.bihar.gov.in/");
        map.put(R.id.sbi,"https://www.onlinesbi.com/");
        map.put(R.id.ntpc,"https://www.ntpc.co.in/");
        map.put(R.id.ruban,"http://www.rubanpatliputrahospital.com/");
        for(Map.Entry<Integer,String> pair: map.entrySet()){
            ImageView imageView = findViewById(pair.getKey());
//            imageView.setOnClickListener(v -> customTabsIntent.launchUrl(SponsorsActivity.this, Uri.parse(pair.getValue())));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                    builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));

                    CustomTabsIntent customTabsIntent = builder.build();
                    customTabsIntent.launchUrl(SponsorsActivity.this, Uri.parse(pair.getValue()));
                }
            });
        }
    }
}
