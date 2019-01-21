package info.anwesha2k18.iitp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import info.anwesha2k18.iitp.R;

public class ImageLauncher extends AppCompatActivity {

    File picture;
    private String LOG_TAG = getClass().toString();
    ImageView myImage;

    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageview);

        Intent intent = getIntent();
        myImage = (ImageView) findViewById(R.id.launcher);
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        if (intent.getExtras().get("imageV") != null) {
            picture = (File) getIntent().getExtras().get("imageV");

            if (picture.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(picture.getAbsolutePath());
                myImage.setImageBitmap(myBitmap);
            }
        } else if (intent.getExtras().get("image_url") != null){
            Glide.with(this)
                    .load(intent.getExtras().get("image_url"))
                    .apply(new RequestOptions().error(R.drawable.anwesha_placeholder))
                    .into(myImage);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScaleGestureDetector.onTouchEvent(event);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.75f,
                    Math.min(mScaleFactor, 10.0f));
            myImage.setScaleX(mScaleFactor);
            myImage.setScaleY(mScaleFactor);
            return true;
        }
    }
}
