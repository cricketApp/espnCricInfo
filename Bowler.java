package com.mobileblog.user.mobileblogapp.cricket;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by root on 9/22/18.
 */

public class Bowler implements Parcelable {

    private String bowlerName;
    private String dots;
    private String medain;
    private String fours;
    private String sixs;
    private String wickets;
    private String runs;
    private String economy;

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public Bowler(String bowlerName, String dots, String medain, String wickets, String runs, String economy, String over) {

        this.bowlerName = bowlerName;
        this.dots = dots;
        this.medain = medain;
        this.wickets = wickets;
        this.runs = runs;
        this.economy = economy;
        this.over = over;
    }

    private String over;

    public Bowler(String bowlerName, String dots, String medain, String fours, String sixs, String wickets, String runs, String economy) {
        this.bowlerName = bowlerName;
        this.dots = dots;
        this.medain = medain;
        this.fours = fours;
        this.sixs = sixs;
        this.wickets = wickets;
        this.runs = runs;
        this.economy = economy;
    }

    protected Bowler(Parcel in) {
        bowlerName = in.readString();
        dots = in.readString();
        medain = in.readString();
        fours = in.readString();
        sixs = in.readString();
        wickets = in.readString();
        runs = in.readString();
        economy = in.readString();
    }

    public static final Creator<Bowler> CREATOR = new Creator<Bowler>() {
        @Override
        public Bowler createFromParcel(Parcel in) {
            return new Bowler(in);
        }

        @Override
        public Bowler[] newArray(int size) {
            return new Bowler[size];
        }
    };

    public String getBowlerName() {
        return bowlerName;
    }

    public void setBowlerName(String bowlerName) {
        this.bowlerName = bowlerName;
    }

    public String getDots() {
        return dots;
    }

    public void setDots(String dots) {
        this.dots = dots;
    }

    public String getMedain() {
        return medain;
    }

    public void setMedain(String medain) {
        this.medain = medain;
    }

    public String getFours() {
        return fours;
    }

    public void setFours(String fours) {
        this.fours = fours;
    }

    public String getSixs() {
        return sixs;
    }

    public void setSixs(String sixs) {
        this.sixs = sixs;
    }

    public String getWickets() {
        return wickets;
    }

    public void setWickets(String wickets) {
        this.wickets = wickets;
    }

    public String getRuns() {
        return runs;
    }

    public void setRuns(String runs) {
        this.runs = runs;
    }

    public String getEconomy() {
        return economy;
    }

    public void setEconomy(String economy) {
        this.economy = economy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bowlerName);
        dest.writeString(dots);
        dest.writeString(medain);
        dest.writeString(fours);
        dest.writeString(sixs);
        dest.writeString(wickets);
        dest.writeString(runs);
        dest.writeString(economy);
    }
}
