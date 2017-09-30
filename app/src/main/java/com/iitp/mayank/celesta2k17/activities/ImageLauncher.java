package com.iitp.mayank.celesta2k17.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.iitp.mayank.celesta2k17.R;

import java.io.File;

/**
 * Created by manish on 6/9/17.
 */

public class ImageLauncher extends AppCompatActivity {

    File picture;
    private String LOG_TAG = getClass().toString();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageview);


        picture = (File) getIntent().getExtras().get("imageV");

        if (picture.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(picture.getAbsolutePath());
            ImageView myImage = (ImageView) findViewById(R.id.launcher);
            myImage.setImageBitmap(myBitmap);
        }


    }
}
