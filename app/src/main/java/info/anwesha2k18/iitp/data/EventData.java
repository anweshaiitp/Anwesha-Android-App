package info.anwesha2k18.iitp.data;

/**
 * Created by mayank on 26/11/17.
 */

public class EventData {
    public int fee;
    public int day;
    public int size;
    public int id;
    public int code;
    public String name;
    public String tagline;
    public String date;
    public String time;
    public String venue;
    public String organisers;
    public String short_desc;
    public String long_desc;
    public String toDisplay_short, toDisplay_long;
    public String imageURL;
    public String rules;
    public String regURL;

    public EventData(int id, String name, int fee, int day, int size, int code, String tagline, String date, String time, String venue, String organisers, String short_desc, String long_desc, String imageURL, String ruleURL, String regURL) {
        this.id = id;
        this.name = name;
        this.short_desc = short_desc;
        this.fee = fee;
        this.day = day;
        this.code = code;
        this.size = size;
        this.tagline = tagline;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.organisers = organisers;
        this.long_desc = long_desc;
        toDisplay_short = filterLongDesc((short_desc));
        toDisplay_long = filterLongDesc((long_desc));
        this.imageURL = imageURL;
        this.rules = ruleURL;
        this.regURL = regURL;
    }

    public String filterLongDesc(String longdesc) {
        longdesc = longdesc.replaceAll("<[^>]*>", "");
        longdesc = longdesc.replaceAll("&nbsp;", "");
        longdesc = longdesc.replaceAll("&ldquo;", "\n");
        longdesc = longdesc.replaceAll("\r", "\n");
        longdesc = longdesc.replaceAll("\n{2,}", "\n\n");
        longdesc = longdesc.trim();
        //longdesc = longdesc.substring(longdesc.indexOf("\n"));
        //longdesc = longdesc.trim();

        return longdesc;
    }
}
