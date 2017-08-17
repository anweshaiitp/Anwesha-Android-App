package com.iitp.mayank.celesta2k17.data;

/**
 * Created by manish on 17/8/17.
 */

public class GalleryPics {

    private String mPhotoUrl;
// making an empty constructor so that it can't be instantizied
    public GalleryPics() {
    }
// to set the photo url
    public GalleryPics(String url) {

        mPhotoUrl = url;
    }

    //returns the photo url
    public String getmPhotoUrl() {
        return mPhotoUrl;
    }


}
