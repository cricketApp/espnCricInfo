package com.mobileblog.user.mobileblogapp.cricket;

/**
 * Created by root on 9/25/18.
 */

public class NewMatch {

    private String name,date;

    public NewMatch(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
