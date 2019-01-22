package info.anwesha2k19.iitp.data;

public class WorkshopData {

    private String workshopName;
    private String workshopInfoUrl;
    private String workshopDate;
    private String workshopVenue;
    private String workshopImageUrl;

    public WorkshopData(){}

    public WorkshopData(String workshopName, String workshopInfoUrl, String workshopDate, String workshopVenue, String workshopImageUrl){
        this.workshopDate = workshopDate;
        this.workshopVenue = workshopVenue;
        this.workshopName = workshopName;
        this.workshopImageUrl = workshopImageUrl;
        this.workshopInfoUrl = workshopInfoUrl;
    }

    public String getWorkshopDate() {
        return workshopDate;
    }

    public String getWorkshopInfoUrl() {
        return workshopInfoUrl;
    }

    public String getWorkshopImageUrl() {
        return workshopImageUrl;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public String getWorkshopVenue() {
        return workshopVenue;
    }

    public void setWorkshopDate(String workshopDate) {
        this.workshopDate = workshopDate;
    }

    public void setWorkshopInfoUrl(String workshopInfoUrl) {
        this.workshopInfoUrl = workshopInfoUrl;
    }

    public void setWorkshopImageUrl(String workshopImageUrl) {
        this.workshopImageUrl = workshopImageUrl;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public void setWorkshopVenue(String workshopVenue) {
        this.workshopVenue = workshopVenue;
    }
}
