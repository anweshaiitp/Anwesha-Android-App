package com.iitp.mayank.celesta2k17.data;

/**
 * Created by manish on 22/8/17.
 */

public class TeamData {

private String mPor;
private  String mphoneNumber;
private int mPhotoId ;
private  String mName ;

    public TeamData()
    {

    }

    public String getmName() {
        return mName;
    }

    public int getmPhotoId() {
        return mPhotoId;
    }

    public String getMphoneNumber() {
        return mphoneNumber;
    }

    public String getmPor() {
        return mPor;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmPor(String mPor) {
        this.mPor = mPor;
    }

    public void setMphoneNumber(String mphoneNumber) {
        this.mphoneNumber = mphoneNumber;
    }

    public void setmPhotoId(int mPhotoId) {
        this.mPhotoId = mPhotoId;
    }
}
