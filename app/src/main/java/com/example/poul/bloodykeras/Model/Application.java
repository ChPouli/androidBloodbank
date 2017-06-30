package com.example.poul.bloodykeras.Model;

import java.sql.Timestamp;


public class Application {

    private int Id;
    private int IdPatient;
    private int Quantity;
    private Timestamp Date;
    private String Clinic;
    private String EidosParagogou;
    private String Status;
    private String Priority;
    private Timestamp TransfusionDate;

    //region Constructors
    public Application(int id, int idPatient, int quantity, Timestamp date, String clinic, String eidosParagogou, String status, Timestamp transfusionDate, String priority) {
        Id = id;
        IdPatient = idPatient;
        Quantity = quantity;
        Date = date;
        Clinic = clinic;
        EidosParagogou = eidosParagogou;
        Status = status;
        TransfusionDate = transfusionDate;
        Priority = priority;
    }

    public Application() {
    }
//endregion

    //region Getters-Setters
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdPatient() {
        return IdPatient;
    }

    public void setIdPatient(int idPatient) {
        IdPatient = idPatient;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Timestamp getDate() {
        return Date;
    }

    public void setDate(Timestamp date) {
        Date = date;
    }

    public String getClinic() {
        return Clinic;
    }

    public void setClinic(String clinic) {
        Clinic = clinic;
    }

    public String getEidosParagogou() {
        return EidosParagogou;
    }

    public void setEidosParagogou(String eidosParagogou) {
        EidosParagogou = eidosParagogou;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public Timestamp getTransfusionDate() {
        return TransfusionDate;
    }

    public void setTransfusionDate(Timestamp transfusionDate) {
        TransfusionDate = transfusionDate;
    }
    //endregion
}


