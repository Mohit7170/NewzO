package com.app.newzo;

public class Modals {

    private String heading, desc, source, date, Url;

    public Modals(String heading, String desc, String source, String date, String url) {
        this.heading = heading;
        this.desc = desc;
        this.source = source;
        this.date = date;
        Url = url;
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
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
