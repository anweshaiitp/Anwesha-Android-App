package info.anwesha2k18.iitp.data;

public class LecturesData {

    private String lectureName;
    private String lectureDetail;
    private String lectureImageUrl;

    public LecturesData(){}

    public LecturesData(String lectureName, String lectureDetail, String lectureImageUrl){

        this.lectureName = lectureName;
        this.lectureDetail = lectureDetail;
        this.lectureImageUrl = lectureImageUrl;

    }

    public String getLectureDetail() {
        return lectureDetail;
    }

    public String getLectureImageUrl() {
        return lectureImageUrl;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureDetail(String lectureDetail) {
        this.lectureDetail = lectureDetail;
    }

    public void setLectureImageUrl(String lectureImageUrl) {
        this.lectureImageUrl = lectureImageUrl;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }
}
