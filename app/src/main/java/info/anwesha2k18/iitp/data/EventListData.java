package info.anwesha2k18.iitp.data;

public class EventListData {
    private String eveName;
    private String short_desc;
    private String cover_url;
    private String date;
    private String long_desc;
    private String organisers;
    private String reg_url;
    private String rules_url;
    private String time;
    private String venue;

    public EventListData(){


    }
    public EventListData(String eveName, String short_desc){
        this.eveName=eveName;
        this.short_desc=short_desc;
    }

    public EventListData(String eveName, String short_desc, String cover_url, String date, String long_desc, String organisers, String reg_url, String rules_url, String time, String venue){

        this.eveName = eveName;
        this.short_desc = short_desc;
        this.cover_url = cover_url;
        this.date = date;
        this.long_desc = long_desc;
        this.organisers = organisers;
        this.reg_url = reg_url;
        this.rules_url = rules_url;
        this.time = time;
        this.venue = venue;

    }

    public String getshort_desc() {
        return short_desc;
    }

    public String geteveName() {
        return eveName;
    }

    public String getcover_url() {
        return cover_url;
    }

    public String getdate() {
        return date;
    }

    public String getlong_desc() {
        return long_desc;
    }

    public String getorganisers() {
        return organisers;
    }

    public String getreg_url() {
        return reg_url;
    }

    public String rules_url() {
        return rules_url;
    }

    public String gettime() {
        return time;
    }

    public String getvenue() {
        return venue;
    }


}
