package places.wiki;

public class Article extends Category {
    private String geoData;

    public Article(String lang, String name, String reference, String geoData) {
        super(lang, name, reference);
        this.geoData = geoData;
    }

    public String getGeoData() {
        return geoData;
    }

    public void setGeoData(String geoData) {
        this.geoData = geoData;
    }

    @Override
    public String toString() {
        return "Article{" +
                "geoData='" + geoData + '\'' +
                "} " + super.toString();
    }
}
