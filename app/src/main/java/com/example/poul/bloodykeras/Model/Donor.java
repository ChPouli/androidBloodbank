package com.example.poul.bloodykeras.Model;

/**
 * Created by poul on 15/3/2017.
 */
public class Donor {

    //region Properties
    private int Id;
    private String Lname;
    private String Fname;
    private String FatherName;
    private String   BirthYear;
    private String  Phone;
    private String Address;
    private String AT;
    private String Occupation;
    private String BirthPlace;
    private String BloodType;
    private String Rh;
//endregion

    //region Constructors
    public Donor(int id, String lname, String fname, String fatherName, String birthYear, String phone, String address, String AT, String occupation, String birthPlace, String bloodtype, String rh) {
        Id = id;
        Lname = lname;
        Fname = fname;
        FatherName = fatherName;
        BirthYear = birthYear;
        Phone = phone;
        Address = address;
        this.AT = AT;
        Occupation = occupation;
        BirthPlace = birthPlace;
        BloodType = bloodtype;
        Rh = rh;
    }

    public Donor() {
    }

    //endregion

    //region Getters

    public int getId() {
        return Id;
    }

    public String getLname() {
        return Lname;
    }

    public String getFname() {
        return Fname;
    }

    public String getFatherName() {
        return FatherName;
    }

    public String getBirthYear() {
        return BirthYear;
    }

    public String getPhone() {
        return Phone;
    }

    public String getAddress() {
        return Address;
    }

    public String getOccupation() {
        return Occupation;
    }

    public String getAT() {
        return AT;
    }

    public String getBirthPlace() {
        return BirthPlace;
    }

    public String getBloodtype() {
        return BloodType;
    }

    public String getRh() {
        return Rh;
    }

    //endregion

    //region Setters
    public void setId(int id) {
        Id = id;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public void setBirthYear(String birthYear) {
        BirthYear = birthYear;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setAT(String AT) {
        this.AT = AT;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public void setBloodtype(String bloodtype) {
        BloodType = bloodtype;
    }

    public void setBirthPlace(String birthPlace) {
        BirthPlace = birthPlace;
    }

    public void setRh(String rh) {
        Rh = rh;
    }

    //endregion
}
