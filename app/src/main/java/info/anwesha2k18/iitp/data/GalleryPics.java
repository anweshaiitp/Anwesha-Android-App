package info.anwesha2k18.iitp.data;

/**
 * Created by manish on 17/8/17.
 */

public class GalleryPics {

    private String url;
    private String picName;

    // making an empty constructor for firebase
    public GalleryPics() {
    }

    // to set the photo url
    public GalleryPics(String url, String picName) {

        this.url = url;
        this.picName = picName;
    }

    //returns the photo url
    public String geturl() {
        return url;
    }

    // returns the pic name
    public String getpicName() {
        return picName;
    }

    public void seturl(String url) {
        this.url = url;
    }

    public void setpicName(String picName) {
        this.picName = picName;
    }
}
