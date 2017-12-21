package com.example.poul.bloodykeras.Model;

/**
 * Created by poul on 7/3/2017.
 */
public class Patient {
    //region Properties
    private int Id;
    private String Lname;
    private String Fname;
    private String Fathername;
    private String   BirthYear;
    private String  Phone;
    private String Address;
    private String AT;
    private String Clinic;
    private String Diagnosis;
    private String Bloodtype;
    private String Rh;







    //endregion

    //region Constructors

    public Patient(int id, String lname, String fname, String fathername, String birthYear, String phone, String address, String AT, String clinic, String diagnosis, String bloodtype, String rh ) {
        Id = id;
        Lname = lname;
        Fname = fname;
        Fathername = fathername;
        BirthYear = birthYear;
        Phone = phone;
        Address = address;
        this.AT = AT;
        Clinic = clinic;
        Diagnosis = diagnosis;
        Bloodtype = bloodtype;
        Rh = rh;

    }

    //endregion

    //region Getters/Setters

    public String getLname() {
        return Lname;
    }

    public int getId() {
        return Id;
    }

    public String getFname() {
        return Fname;
    }

    public String getFathername() {
        return Fathername;
    }

    public String getBirthYear() {
        return BirthYear;
    }

    public String getAddress() {
        return Address;
    }

    public String getPhone() {
        return Phone;
    }

    public String getAT() {
        return AT;
    }

    public String getClinic() {
        return Clinic;
    }

    public String getDiagnosis() {
        return Diagnosis;
    }

    public String getBloodtype() {
        return Bloodtype;
    }

    public String getRh() {
        return Rh;
    }



    public void setId(int id) {
        Id = id;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public void setFathername(String fathername) {
        Fathername = fathername;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public void setBirthYear(String birthYear) {
        BirthYear = birthYear;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setClinic(String clinic) {
        Clinic = clinic;
    }

    public void setAT(String AT) {
        this.AT = AT;
    }

    public void setDiagnosis(String diagnosis) {
        Diagnosis = diagnosis;
    }

    public void setBloodtype(String bloodtype) {
        Bloodtype = bloodtype;
    }

    public void setRh(String rh) {
        Rh = rh;
    }


//endregion
}

