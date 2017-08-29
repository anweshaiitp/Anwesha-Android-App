package com.iitp.mayank.celesta2k17.data;

/**
 * Created by manish on 17/8/17.
 */

public class GalleryPics {

    private String mPhotoUrl;
    private  String mPicName ;
// making an empty constructor for firebase
    public GalleryPics() {
    }
// to set the photo url
    public GalleryPics(String url ,String picName) {

        mPhotoUrl = url;
        mPicName=picName;
    }

    //returns the photo url
    public String getmPhotoUrl() {
        return mPhotoUrl;
    }

    // returns the pic name
    public String getmPicName() {
        return mPicName;
    }

}
