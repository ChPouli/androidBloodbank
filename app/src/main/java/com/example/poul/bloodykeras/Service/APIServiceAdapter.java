package com.example.poul.bloodykeras.Service;

import com.example.poul.bloodykeras.Model.Bloodbag;
import com.example.poul.bloodykeras.Model.Donor;
import com.example.poul.bloodykeras.Model.Patient;
import com.example.poul.bloodykeras.Model.SessionM;
import com.example.poul.bloodykeras.Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by poul on 8/3/2017.
 */
public class APIServiceAdapter {
    //Region properties
    public static final String BASE_URL = "http://192.168.1.254:8888";
    private Retrofit retrofit;
    private Gson gson = new GsonBuilder().setLenient().create();
    //endregion


    private static APIServiceAdapter ourInstance = null;

    public static APIServiceAdapter getInstance() {
        if(ourInstance == null)
            ourInstance = new APIServiceAdapter();
        return ourInstance;
    }

    private APIServiceAdapter() {
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

   public Observable<List<Patient>> getPatients() {
       return retrofit.create(APIService.class)
               .getPatients().subscribeOn(Schedulers.newThread())
               .observeOn(AndroidSchedulers.mainThread());
   }

    public Observable<List<Bloodbag>> getAllBloodbags() {
        return retrofit.create(APIService.class)
                .getAllBloodbags().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<List<User>> authenticate(String Username, String Password) {
        return retrofit.create(APIService.class)
                .authenticate(Username, Password).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Void> addPatient(String lname, String fname, String fathername, int birthYear,
                                       int phone, String address, String AT, String clinic,
                                       String diagnosis, String bloodtype, String rh, String fenotypos,
                                       String antisomata) {

        return retrofit.create(APIService.class)
                .addPatient(lname, fname, fathername, birthYear, phone,
                        address, AT, clinic, diagnosis, bloodtype, rh, fenotypos, antisomata)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Donor>> searchDonor( String AT) {
        return retrofit.create(APIService.class)
                .searchDonor(AT)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Patient>> searchPatient( String AT) {
        return retrofit.create(APIService.class)
                .searchPatient(AT)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Void> addDonor(String lname, String fname, String fathername, int birthYear,
                                     int phone, String address, String AT, String occupation,
                                     String birthplace, String bloodtype, String rh) {

        return retrofit.create(APIService.class)
                        .addDonor(lname, fname, fathername, birthYear, phone, address, AT, occupation, birthplace, bloodtype, rh)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<Void> addSession(String pressure,String hbht,String status,String reactions,int id_user,int id_donor){

        return retrofit.create(APIService.class)
                        .addSession(pressure, hbht, status, reactions, id_user, id_donor)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<SessionM>> getSessionId(int iddonor){
        return retrofit.create(APIService.class)
                        .getSessionId(iddonor)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Void> updateBag(String kind,String bloodtype,String rh,String checked, String tagrfid,String available){

        return retrofit.create(APIService.class)
                        .updateBag(kind, bloodtype, rh, checked, tagrfid, available)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());

    }


    public Observable<Void> addNewBag(String bagtype,String anticoagulant, String volume, int idSession, String tagRfid){

        return retrofit.create(APIService.class)
                        .addNewBag(bagtype,anticoagulant,volume,idSession,tagRfid)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());
    }

}
