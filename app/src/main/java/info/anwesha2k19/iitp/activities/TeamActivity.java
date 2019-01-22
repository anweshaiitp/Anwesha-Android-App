package info.anwesha2k19.iitp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import info.anwesha2k19.iitp.R;
import info.anwesha2k19.iitp.adapters.TeamRecylerViewAdapter;

/**
 * Created by manish on 22/8/17.
 */

public class TeamActivity extends AppCompatActivity implements TeamRecylerViewAdapter.PhoneCLick {

    RecyclerView recyclerView;
    TeamRecylerViewAdapter teamRecylerViewAdapter;
    String url;
    String url2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_team);
    }

    @Override
    public void onPhoneClick(String phone) {
        if (!phone.equals("-1")) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phone));
            startActivity(intent);
        }
    }

    public void go_to_fb() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String fburl = getFacebookPageUrl(this);
        intent.setData(Uri.parse(fburl));
        this.url = "";
        this.url2 = "";
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public String getFacebookPageUrl(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) {
                return "fb://facewebmodal/f?href=" + url;
            } else
                return "fb://page/" + url2;
        } catch (PackageManager.NameNotFoundException e) {
            return url;
        }
    }

    public void mayank_wadhwani(View v) {
        this.url = "https://www.facebook.com/mayank.wadhwani";
        this.url2 = "mayank.wadhwani";
        go_to_fb();
    }

    public void yash(View v) {
        this.url = "https://www.facebook.com/yash.choudhary.3994";
        this.url2 = "yash.choudhary.3994";
        go_to_fb();
    }

    public void ayush(View v) {
        this.url = "https://www.facebook.com/ayushinani1";
        this.url2 = "ayushinani1";
        go_to_fb();
    }

    public void milan(View v) {
        this.url = "https://www.facebook.com/milan.jolly.7";
        this.url2 = "milan.jolly.7";
        go_to_fb();
    }

    public void mayank_tripathi(View v) {
        this.url = "https://www.facebook.com/mayank.tripathi.507";
        this.url2 = "mayank.tripathi.507";
        go_to_fb();
    }

    public void balija(View v) {
        this.url = "https://www.facebook.com/shashank.balija";
        this.url2 = "shashank.balija";
        go_to_fb();
    }

    public void naveen(View v) {
        this.url = "https://www.facebook.com/tnaveen1998";
        this.url2 = "tnaveen1998";
        go_to_fb();
    }

    public void krishan(View v) {
        this.url = "https://www.facebook.com/krishan.kaushik.52643";
        this.url2 = "krishan.kaushik.52643";
        go_to_fb();
    }

    public void sandeep(View v) {
        this.url = "https://www.facebook.com/sandeep.tripathy.775";
        this.url2 = "sandeep.tripathy.775";
        go_to_fb();
    }

    public void shashank_singh(View v) {
        this.url = "https://www.facebook.com/shashank.don.3";
        this.url2 = "shashank.don.3";
        go_to_fb();
    }

    public void darsini(View v) {
        this.url = "https://www.facebook.com/GSahiDarsini";
        this.url2 = "GSahiDarsini";
        go_to_fb();
    }

    public void dhanush(View v) {
        this.url = "https://www.facebook.com/dhanushsr1998";
        this.url2 = "dhanushsr1998";
        go_to_fb();
    }

    public void abhinav_gyan(View v) {
        this.url = "https://www.facebook.com/theabhinavgyan";
        this.url2 = "theabhinavgyan";
        go_to_fb();
    }

    public void parth(View v) {
        Toast.makeText(this, "Facebook not found", Toast.LENGTH_SHORT).show();
    }

    public void abhinav_sharma(View v) {
        this.url = "https://www.facebook.com/abhinav.sharma.79656921";
        this.url2 = "abhinav.sharma.79656921";
        go_to_fb();
    }

    public void kundan(View v) {
        Toast.makeText(this, "Facebook not found", Toast.LENGTH_SHORT).show();
    }

    public void nilendu(View v) {
        this.url = "https://www.facebook.com/nilendu.shubham";
        this.url2 = "nilendu.shubham";
        go_to_fb();
    }

    public void abhishek(View v) {
        this.url = "https://www.facebook.com/aBkMOURYA";
        this.url2 = "aBkMOURYA";
        go_to_fb();
    }

    public void nookesh(View v) {
        this.url = "https://www.facebook.com/pilli.nookesh";
        this.url2 = "pilli.nookesh";
        go_to_fb();
    }

    public void fazle(View v) {
        this.url = "https://www.facebook.com/Fazle.Rahman.Ejazi";
        this.url2 = "Fazle.Rahman.Ejazi";
        go_to_fb();
    }

    public void tameesh(View v) {
        this.url = "https://www.facebook.com/tameeshB";
        this.url2 = "tameeshB";
        go_to_fb();
    }

    public void manish(View v) {
        this.url = "https://www.facebook.com/imanishk16";
        this.url2 = "imanishk16";
        go_to_fb();
    }

}
