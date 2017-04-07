package com.example.poul.bloodykeras.Model;

/**
 * Created by poul on 9/3/2017.
 */
public class User {
    //region Properties
    private int Id;
    private String Lname;
    private String Fname;
    private String Username;
    private int Password;
//endregion

    //region constructors

    public User(int id, String lname, String fname, String username, int password) {
        Id = id;
        Lname = lname;
        Fname = fname;
        Username = username;
        Password = password;
    }

    public User() {
    }
  //endregion


    //region getters
    public int getId() {
        return Id;
    }

    public String getLname() {
        return Lname;
    }

    public String getFname() {
        return Fname;
    }

    public String getUsername() {
        return Username;
    }

    public int getPassword() {
        return Password;
    }

    //endregion


    //region setters
    public void setLname(String lname) {
        Lname = lname;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(int password) {
        Password = password;
    }

    //endregion
}
