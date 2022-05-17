package de.victor.eventadvisor.service.publicApiResponse;

public class Properties {
    private String title;
    private PropertiesData data;
    private String pubDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PropertiesData getData() {
        return data;
    }

    public void setData(PropertiesData data) {
        this.data = data;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
