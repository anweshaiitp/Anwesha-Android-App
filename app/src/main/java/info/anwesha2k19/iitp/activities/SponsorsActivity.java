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
        map.put(R.id.beltron,"http://www.bsedc.bihar.gov.in/");
        map.put(R.id.ntpc,"https://www.ntpc.co.in/");
        map.put(R.id.ruban,"http://www.rubanpatliputrahospital.com/");
        map.put(R.id.canara,"https://www.canarabank.in/");
        map.put(R.id.nic,"https://nationalinsuranceindia.nic.co.in/");
        map.put(R.id.ncc,"http://ncclimited.com/");
        map.put(R.id.lic,"https://www.licindia.in/");
        map.put(R.id.incubation,"http://www.iciitp.com/");
        map.put(R.id.godrej,"http://www.godrej.com/");
        map.put(R.id.boi,"https://www.bankofindia.co.in/");
        map.put(R.id.hdfc,"https://www.hdfcbank.com/");
        map.put(R.id.kamla,"https://www.zaubacorp.com/company/KAMLADITYYA-CONSTRUCTION-PRIVATE-LIMITED/U45203JH2010PTC013945");
        map.put(R.id.bseidc,"http://www.bseidc.in/");
        map.put(R.id.lemontree,"https://www.lemontreehotels.com/");
        map.put(R.id.empire,"https://14-empire.business.site/");
        map.put(R.id.bhukkad,"https://bhukkad-baba.business.site/");
        map.put(R.id.pizza,"https://www.pizzahut.co.in/");
        map.put(R.id.waffle,"https://www.facebook.com/pages/category/Food---Beverage/The-Belgian-Waffle-Patna-1561906433920546/");
        map.put(R.id.biharsehai,"http://www.biharsehai.in/?fbclid=IwAR1oio464M8DI_VHvE9Uked5Ifw5ZtedIywyxzWcGu0wqJk32hbFRip-9AM");
        map.put(R.id.biharbytes,"http://www.biharbytes.com/");
        map.put(R.id.bizarre,"https://www.instagram.com/bizarre.india/");
        map.put(R.id.varta,"https://www.campusvarta.com/");
        map.put(R.id.erstudio,"https://www.instagram.com/er.studio_/");
        map.put(R.id.indiabeats,"https://www.instagram.com/India.beats");
        map.put(R.id.realbihar,"http://www.realbihar.in/");
        map.put(R.id.coke,"http://www.coca-cola.com/global/");
        map.put(R.id.amul,"http://www.amul.com/");
        map.put(R.id.choco,"http://www.coca-cola.com/global/");
        map.put(R.id.web,"http://crazy24x7.com/");
        map.put(R.id.radio,"http://www.radiomirchi.com/");
        map.put(R.id.ktm_race,"https://www.ktm.com/in/");
        map.put(R.id.vlcc,"https://www.vlccpersonalcare.com/");
        map.put(R.id.classmate,"https://www.itcportal.com/");
        map.put(R.id.zeb,"https://zebronics.com/");
        map.put(R.id.trendydice,"https://www.facebook.com/trendydice/");
        map.put(R.id.saalt,"https://beta.anwesha.info/sponsors#");
        map.put(R.id.zoutons,"https://zoutons.com/in");
        map.put(R.id.maxfashion,"https://www.maxfashion.in/");
        map.put(R.id.pge,"http://www.patnaglobal.com/");
        map.put(R.id.eye,"https://www.facebook.com/hawkeyedphotographers/");

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
