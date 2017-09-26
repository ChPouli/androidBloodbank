package com.example.poul.bloodykeras.Service;

import com.example.poul.bloodykeras.Model.Application;
import com.example.poul.bloodykeras.Model.Bloodbag;
import com.example.poul.bloodykeras.Model.Donor;
import com.example.poul.bloodykeras.Model.Patient;
import com.example.poul.bloodykeras.Model.SessionM;
import com.example.poul.bloodykeras.Model.User;

import java.util.Date;
import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by poul on 7/3/2017.
 */
public interface APIService {
    @GET("/patients.php")
    Observable<List<Patient>> getPatients();

    @FormUrlEncoded
    @POST("/SearchUser.php")
    Observable<List<User>> authenticate(@Field("Username") String Username, @Field("Password") String Pass);

    @FormUrlEncoded
    @POST("/AddPatient.php")
    Observable<Void>   addPatient(@Field("Lname") String Lname, @Field("Fname") String Fname ,
                     @Field("Fathername") String Fathername , @Field("BirthYear") int BirthYear,
                     @Field("Phone") int Phone, @Field("Address") String Address,
                     @Field("AT") String AT, @Field("Clinic") String Clinic,
                     @Field("Diagnosis") String Diagnosis , @Field("Bloodtype") String Bloodtype,
                     @Field("Rh") String Rh, @Field("Fenotypos") String Fenotypos, @Field("Antisomata") String Antisomata) ;

    @FormUrlEncoded
    @POST("/SearchPatient.php")
    Observable<List<Patient>> searchPatient(@Field("AT") String AT);

    @FormUrlEncoded
    @POST("/AddDonor.php")
    Observable<Void>  addDonor(@Field("Lname") String Lname, @Field("Fname") String Fname ,
                               @Field("FatherName") String FatherName , @Field("BirthYear") String BirthYear,
                               @Field("Phone") String Phone, @Field("Address") String Address,
                               @Field("AT") String AT, @Field("Occupation") String Occupation,
                               @Field("BirthPlace") String BirthPlace , @Field("BloodType") String BloodType,
                               @Field("Rh") String Rh );
    @FormUrlEncoded
    @POST("/SearchDonor.php")
    Observable<List<Donor>> searchDonor(@Field("AT") String AT);

    @FormUrlEncoded
    @POST("/AddSession.php")
    Observable<Void> addSession(@Field("Pressure") String Pressure,@Field("HbHt") String HbHt,@Field("Status") String Status,
                                @Field("Reactions") String Reactions,@Field("Id_user") int Id_user,
                                @Field("Id_donor") int Id_donor);
    @FormUrlEncoded
    @POST("/SessionGamietai.php")
    Observable<List<SessionM>> getSessionId(@Field("Id_donor") int Id_donor);

    @FormUrlEncoded
    @POST("/UpdateBag.php")
    Observable<Void> updateBag(@Field("Kind") String Kind, @Field("Bloodtype") String Bloodtype,
                               @Field("Rh") String Rh, @Field("Checked") String Checked,
                               @Field("TagRfid") String TagRfid, @Field("available") String available);

    @FormUrlEncoded
    @POST("/AddNewBag.php")
    Observable<Void> addNewBag(@Field("Bagtype") String Bagtype,@Field("Anticoagulant") String Anticoagulant,
                               @Field("Volume") String Volume, @Field("IdSession") int IdSession,
                               @Field("TagRfid") String TagRfid);

    @FormUrlEncoded
    @POST("/AddNewApplication.php")
    Observable<Void> addNewApplication(@Field("IdPatient") int IdPatient,@Field("Quantity") String Quantity,
                                       @Field("Clinic") String Clinic, @Field("EidosParagogou") String EidosParagogou
            ,@Field("Priority") String Priority,
                                       @Field("TransfusionDate") String TransfusionDate,@Field("ABO") String ABO,
                                       @Field("Rh") String Rh);


    @GET("/GetAllBloodbags.php")
    Observable<List<Bloodbag>> getAllBloodbags();

    @GET("/availableBags.php")
    Observable<List<Bloodbag>> gamhsou();



    @GET("/PendingApplications.php")
    Observable<List<Application>> getAllApplications();

    @FormUrlEncoded
    @POST("/TransfusionExecution.php")
    Observable<Void> ExecTrans(@Field("TagRfid") String TagRfid);


}

