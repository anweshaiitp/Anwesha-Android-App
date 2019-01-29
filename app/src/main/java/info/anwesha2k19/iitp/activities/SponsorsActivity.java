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
        map.put(R.id.yac,"http://yac.bih.nic.in/");
        map.put(R.id.ntpc,"https://www.ntpc.co.in/");
        map.put(R.id.ruban,"http://www.rubanpatliputrahospital.com/");
        map.put(R.id.beltron,"http://www.bsedc.bihar.gov.in/");
        map.put(R.id.canara,"https://www.canarabank.in/");
        map.put(R.id.ktm_key,"");
        map.put(R.id.incubation,"http://www.iciitp.com/");
        map.put(R.id.godrej,"https://www.anwesha.info/sponsors#");
        map.put(R.id.panache,"https://www.anwesha.info/sponsors#");
        map.put(R.id.summer,"https://www.anwesha.info/sponsors#");
        map.put(R.id.empire,"");
        map.put(R.id.angeethi,"");
        map.put(R.id.bhukkad,"");
        map.put(R.id.pizza,"");
        map.put(R.id.coke,"http://www.coca-cola.com/global/");
        map.put(R.id.amul,"http://www.amul.com/");
        map.put(R.id.choco,"http://www.coca-cola.com/global/");
        map.put(R.id.web,"http://crazy24x7.com/");
        map.put(R.id.radio,"http://www.radiomirchi.com/");
        map.put(R.id.forex,"http://patnaforex.com/");
        map.put(R.id.bvg,"http://bvgindia.in/");
        map.put(R.id.pge,"http://www.patnaglobal.com/");
        map.put(R.id.bss,"https://www.facebook.com/bssbihta/");
        map.put(R.id.vlcc,"");
        map.put(R.id.chs,"");
        map.put(R.id.can,"http://www.canjuice.in/");
        map.put(R.id.toppr,"https://www.toppr.com/");
        map.put(R.id.pbeats,"http://www.patnabeats.com/");
        map.put(R.id.plikes,"https://www.facebook.com/patnalikes/");
        map.put(R.id.patnabr,"https://www.facebook.com/PATNAbr/");
        map.put(R.id.eye,"https://www.facebook.com/hawkeyedphotographers/");
        map.put(R.id.zeb,"");
        map.put(R.id.ktm_race,"");

        map.put(R.id.ruban,"http://www.rubanpatliputrahospital.com/");
        for(Map.Entry<Integer,String> pair: map.entrySet()){
            ImageView imageView = findViewById(pair.getKey());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (pair.getValue() != null && !pair.getValue().equals("")) {
                        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                        builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));

                        CustomTabsIntent customTabsIntent = builder.build();
                        customTabsIntent.launchUrl(SponsorsActivity.this, Uri.parse(pair.getValue()));
                    }
                }
            });
        }
    }
}
