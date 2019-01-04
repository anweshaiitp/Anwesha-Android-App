package info.anwesha2k18.iitp.data;

public class WorkshopData {

    private String workshopName;
    private String workshopDescription;
    private String workshopDate;
    private String workshopTime;
    private String workshopVenue;
    private String workshopImageUrl;

    public WorkshopData(){}

    public WorkshopData(String workshopName, String workshopDescription, String workshopDate, String workshopTime, String workshopVenue, String workshopImageUrl){
        this.workshopDate = workshopDate;
        this.workshopVenue = workshopVenue;
        this.workshopTime = workshopTime;
        this.workshopName = workshopName;
        this.workshopImageUrl = workshopImageUrl;
        this.workshopDescription = workshopDescription;
    }

    public String getWorkshopDate() {
        return workshopDate;
    }

    public String getWorkshopDescription() {
        return workshopDescription;
    }

    public String getWorkshopImageUrl() {
        return workshopImageUrl;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public String getWorkshopTime() {
        return workshopTime;
    }

    public String getWorkshopVenue() {
        return workshopVenue;
    }

    public void setWorkshopDate(String workshopDate) {
        this.workshopDate = workshopDate;
    }

    public void setWorkshopDescription(String workshopDescription) {
        this.workshopDescription = workshopDescription;
    }

    public void setWorkshopImageUrl(String workshopImageUrl) {
        this.workshopImageUrl = workshopImageUrl;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public void setWorkshopTime(String workshopTime) {
        this.workshopTime = workshopTime;
    }

    public void setWorkshopVenue(String workshopVenue) {
        this.workshopVenue = workshopVenue;
    }
}
