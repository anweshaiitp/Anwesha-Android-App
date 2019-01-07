package info.anwesha2k18.iitp.data;

public class SliderEventListData {

    private String eveName;
    private String short_desc;
    //Default constructor
    public SliderEventListData(){


    }
    public SliderEventListData(String eveName, String short_desc){
        this.eveName=eveName;
        this.short_desc=short_desc;
    }

    public String getshort_desc() {
        return short_desc;
    }

    public String geteveName() {
        return eveName;
    }

    public void setShortDesc(String short_desc) {
        this.short_desc = short_desc;
    }

    public void seteveName(String eventname) {
        this.eveName = eventname;
    }
}
