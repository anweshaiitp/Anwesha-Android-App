package info.anwesha2k18.iitp.data;

public class multiCityData {
    public String headers;
    public String datetime;
    public String venue;
    public String description;
    public int img;
    public multiCityData(String header,String datetime,String venue,String description,int img) {
        this.headers=header;
        this.img = img;
        this.description=description;
        this.datetime = datetime;
        this.venue = venue;
    }
}
