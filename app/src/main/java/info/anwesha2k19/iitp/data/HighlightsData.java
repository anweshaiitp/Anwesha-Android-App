package info.anwesha2k19.iitp.data;

/**
 * Created by manish on 29/8/17.
 */

public class HighlightsData {
    private String mHighlights;

    public HighlightsData(String mHighlights) {
        this.mHighlights = mHighlights;
    }

    // empty constructor to add functionality from firebase
    public HighlightsData() {

    }

    public String getmHighlights() {
        return mHighlights;
    }
}
