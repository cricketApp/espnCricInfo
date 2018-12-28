package com.mobileblog.user.mobileblogapp.cricket;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by root on 9/22/18.
 */

public class Batsman implements Parcelable{

    private String batsmanName;
    private String sr;
    private String fours;
    private String dismisalInfo;
    private String balls;
    private String runs;
    private String sixs;

    public Batsman(String batsmanName, String sr, String fours, String sixs, String balls, String runs, String dismisalInfo) {
        this.batsmanName = batsmanName;
        this.sr = sr;
        this.fours = fours;
        this.sixs = sixs;
        this.balls = balls;
        this.runs = runs;
        this.dismisalInfo = dismisalInfo;
    }

    protected Batsman(Parcel in) {
        batsmanName = in.readString();
        sr = in.readString();
        fours = in.readString();
        dismisalInfo = in.readString();
        balls = in.readString();
        runs = in.readString();
        sixs = in.readString();
    }

    public static final Creator<Batsman> CREATOR = new Creator<Batsman>() {
        @Override
        public Batsman createFromParcel(Parcel in) {
            return new Batsman(in);
        }

        @Override
        public Batsman[] newArray(int size) {
            return new Batsman[size];
        }
    };

    public String getBatsmanName() {
        return batsmanName;
    }

    public void setBatsmanName(String batsmanName) {
        this.batsmanName = batsmanName;
    }

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
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

    public String getBalls() {
        return balls;
    }

    public void setBalls(String balls) {
        this.balls = balls;
    }

    public String getRuns() {
        return runs;
    }

    public void setRuns(String runs) {
        this.runs = runs;
    }

    public String getDismisalInfo() {
        return dismisalInfo;
    }

    public void setDismisalInfo(String dismisalInfo) {
        this.dismisalInfo = dismisalInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(batsmanName);
        dest.writeString(sr);
        dest.writeString(fours);
        dest.writeString(dismisalInfo);
        dest.writeString(balls);
        dest.writeString(runs);
        dest.writeString(sixs);
    }
}
