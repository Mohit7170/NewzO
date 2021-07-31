package com.app.newzo;

public class Modals {

    private String heading, desc, source, date, url,imgUrl;

    public Modals(String heading, String desc, String source, String date, String url, String imgUrl) {
        this.heading = heading;
        this.desc = desc;
        this.source = source;
        this.date = date;
        this.url = url;
        this.imgUrl = imgUrl;
    }


    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
