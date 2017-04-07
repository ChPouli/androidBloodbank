package com.example.poul.bloodykeras.Model;

import java.sql.Timestamp;

/**
 * Created by poul on 17/3/2017.
 */
public class SessionM {

    //region properties
    private int Id;
    private Timestamp Time;
    private String Pressure;
    private String HbHt;
    private String Status;
    private String Reactions;
    private int Id_user;
    private int Id_donor;
//endregion


    //region Constructors
    public SessionM(int id, Timestamp time, String pressure, String hbHt, String status, String reactions, int id_user, int id_donor) {
        Id = id;
        Time = time;
        Pressure = pressure;
        HbHt = hbHt;
        Status = status;
        Reactions = reactions;
        Id_user = id_user;
        Id_donor = id_donor;
    }

    public SessionM() {
    }
    //endregion

    //region Getters
    public int getId() {
        return Id;
    }

    public Timestamp getTime() {
        return Time;
    }

    public String getPressure() {
        return Pressure;
    }

    public String getHbHt() {
        return HbHt;
    }

    public String getStatus() {
        return Status;
    }

    public String getReactions() {
        return Reactions;
    }

    public int getId_user() {
        return Id_user;
    }

    public int getId_donor() {
        return Id_donor;
    }

    //endregion

    //region Setters
    public void setId(int id) {
        Id = id;
    }

    public void setTime(Timestamp time) {
        Time = time;
    }

    public void setPressure(String pressure) {
        Pressure = pressure;
    }

    public void setHbHt(String hbHt) {
        HbHt = hbHt;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setReactions(String reactions) {
        Reactions = reactions;
    }

    public void setId_user(int id_user) {
        Id_user = id_user;
    }

    public void setId_donor(int id_donor) {
        Id_donor = id_donor;
    }
    //endregion
}
