package com.iitp.mayank.celesta2k17.activities;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.iitp.mayank.celesta2k17.R;

import static com.iitp.mayank.celesta2k17.R.drawable.fb;

/**
 * Created by manish on 2/10/17.
 */

public class SocialActivity extends AppCompatActivity {

    private ImageView twitterImageView;
    private ImageView fbImageView;
    final String facebookUrl = "https://www.facebook.com/CelestaIITP/";
    final String twitterUrl = "https://twitter.com/celesta_iitp";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_main);

        twitterImageView = (ImageView) findViewById(R.id.imageViewtwt);
        twitterImageView.setOnClickListener(new View.OnClickListener() {
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


    }
}
