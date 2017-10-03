package in.org.celesta2k17.data;

/**
 * Created by mayank on 12/8/17.
 */

public class ClubsData {
    private String header;
    private String text;
    private String intentClass;
    private int imageId;

    public ClubsData()

    {

    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIntentClass() {
        return intentClass;
    }

    public void setIntentClass(String intentClass) {
        this.intentClass = intentClass;
    }

    public String getHeader() {
        return header;

    }

    public void setHeader(String header) {
        this.header = header;
    }
}
