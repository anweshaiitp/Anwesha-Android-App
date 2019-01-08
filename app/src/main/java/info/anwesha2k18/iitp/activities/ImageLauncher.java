package info.anwesha2k18.iitp.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;


import java.io.File;

import info.anwesha2k18.iitp.R;
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
