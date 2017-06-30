package com.example.poul.bloodykeras.Model;


import java.sql.Timestamp;

public class Transfusion {

    private  int id;
    private  int IdBag;
    private int IdUser;
    private int IdApply;
    private Timestamp Date;
//region Constructors
    public Transfusion(int id, int idBag, int idUser, int idApply, Timestamp date) {
        this.id = id;
        IdBag = idBag;
        IdUser = idUser;
        IdApply = idApply;
        Date = date;
    }

    public Transfusion() {
    }
//endregion

    //region Getters
    public int getId() {
        return id;
    }

    public int getIdBag() {
        return IdBag;
    }

    public int getIdUser() {
        return IdUser;
    }

    public int getIdApply() {
        return IdApply;
    }

    public Timestamp getDate() {
        return Date;
    }

    //endregion

    //region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setIdBag(int idBag) {
        IdBag = idBag;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
    }

    public void setIdApply(int idApply) {
        IdApply = idApply;
    }

    public void setDate(Timestamp date) {
        Date = date;
    }

    //endregion
}
