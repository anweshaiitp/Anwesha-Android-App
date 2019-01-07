package info.anwesha2k18.iitp.data;

public class TimelineData {

    private String eveName;
    private String cover_url;
    private String time;
    private String venue;

    public TimelineData(){}

    public TimelineData(String eveName, String cover_url, String time, String venue){
        this.eveName = eveName;
        this.cover_url = cover_url;
        this.time = time;
        this.venue = venue;
    }

    public String geteveName() {
        return eveName;
    }

    public String getcover_url() {
        return cover_url;
    }

    public String gettime() {
        return time;
    }

    public String getvenue() {
        return venue;
    }

    public void settime(String time) {
        this.time = time;
    }

    public void setcover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public void seteveName(String eveName) {
        this.eveName = eveName;
    }

    public void setvenue(String venue) {
        this.venue = venue;
    }

}
