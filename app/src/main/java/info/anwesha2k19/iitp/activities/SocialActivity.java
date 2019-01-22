package info.anwesha2k19.iitp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


import info.anwesha2k19.iitp.R;

/**
 * Created by manish on 2/10/17.
 */

public class SocialActivity extends AppCompatActivity {

    private ImageView instaImageView;
    private ImageView fbImageView;
    private ImageView gmailImageView;
    final String facebookUrl = "https://www.facebook.com/anwesha.iitpatna/";
    final String twitterUrl = "https://www.instagram.com/anwesha.iitp/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_main);

        instaImageView = (ImageView) findViewById(R.id.imageViewInst);
        instaImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(twitterUrl));
                startActivity(intent);
            }
        });

        fbImageView = (ImageView) findViewById(R.id.imageViewfb);
        fbImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(facebookUrl));
                startActivity(intent);

            }
        });

        gmailImageView = (ImageView) findViewById(R.id.image_view_mail);
        gmailImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, "anweshaiitp@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Query Regarding ANWESHA 2k19");
                startActivity(intent);
            }
        });
    }
}
