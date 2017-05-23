package com.example.poul.bloodykeras.Model;

import java.sql.Timestamp;

/**
 * Created by poul on 17/3/2017.
 */
public class Bloodbag {


    //region Properties
    private int Id;
    private Timestamp Date;
    private String Bagtype;
    private String Anticoagulant;
    private String Volume;
    private String Kind;
    private String Bloodtype;
    private String Rh;
    private int IdSession;
    private String Checked;
    private String TagRfid;
    private String available;
        //endregion

    //region Constructors
    public Bloodbag(int id, Timestamp date, String bagtype, String anticoagulant, String volume, String kind, String bloodtype, String rh, int idSession, String checked, String tagRfid, String available) {
        Id = id;
        Date = date;
        Bagtype = bagtype;
        Anticoagulant = anticoagulant;
        Volume = volume;
        Kind = kind;
        Bloodtype = bloodtype;
        Rh = rh;
        IdSession = idSession;
        Checked = checked;
        TagRfid = tagRfid;
        this.available = available;
    }

    public Bloodbag() {
    }


    //endregion


    //region Getters-Setters
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Timestamp getDate() {
        return Date;
    }

    public void setDate(Timestamp date) {
        Date = date;
    }

    public String getBagtype() {
        return Bagtype;
    }

    public void setBagtype(String bagtype) {
        Bagtype = bagtype;
    }

    public String getAnticoagulant() {
        return Anticoagulant;
    }

    public void setAnticoagulant(String anticoagulant) {
        Anticoagulant = anticoagulant;
    }

    public String getVolume() {
        return Volume;
    }

    public void setVolume(String volume) {
        Volume = volume;
    }

    public String getKind() {
        return Kind;
    }

    public void setKind(String kind) {
        Kind = kind;
    }

    public String getBloodtype() {
        return Bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        Bloodtype = bloodtype;
    }

    public String getRh() {
        return Rh;
    }

    public void setRh(String rh) {
        Rh = rh;
    }

    public int getIdSession() {
        return IdSession;
    }

    public void setIdSession(int idSession) {
        IdSession = idSession;
    }

    public String getTagRfid() {
        return TagRfid;
    }

    public void setTagRfid(String tagRfid) {
        TagRfid = tagRfid;
    }

    public String getChecked() {
        return Checked;
    }

    public void setChecked(String checked) {
        Checked = checked;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }


    //endregion
}
