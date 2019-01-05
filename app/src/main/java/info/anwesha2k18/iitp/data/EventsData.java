package info.anwesha2k18.iitp.data;

/**
 * Created by mukuntha on 19/8/17.
 */

public class EventsData {
    private String header;
    private String text;
    private String rules;
    private String dateTime;
    private String venue;
    private String organizers;
    private String contacts;
    private int imageId;


    public EventsData()

    {

    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getHeader() {
        return header;

    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getOrganizers() {
        return organizers;
    }

    public void setOrganizers(String organizers) {
        this.organizers = organizers;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}
