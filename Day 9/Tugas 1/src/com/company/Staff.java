package com.company;
import java.lang.reflect.Array;
import java.util.ArrayList;
import org.json.simple.JSONArray;
class Staff extends Worker {
    int tunjanganMakan = 0;
    int gapok = 4500000;
    ArrayList email = new ArrayList<>();

    Staff(int id, String nama, ArrayList email){
        this.idKaryawan = id;
        this.nama = nama;
        this.email = email;
        this.totalTunjanganMakan();
    }
    Staff(){
        super();
    }
    int getTunjanganMakan(){
        return this.tunjanganMakan;
    }
    void totalTunjanganMakan(){
        this.tunjanganMakan = this.absensi * 20000;
    }
    void Absen(){
        this.absensi += 1;
    }
    public void setNama (String newValue){
        this. nama = newValue;
    }
    public void setId(int newValue){
        this.idKaryawan = newValue;
    }
    public int getId (){
        return this.idKaryawan;
    }
    public String getNama (){
        return this.nama;
    }
    public int getAbsen(){
        return this.absensi;
    }
    public int getTunjanganPulsa (){
        return this.tunjanganPulsa;
    }
    public int getGapok (){
        return this.tunjanganMakan;
    }
    public ArrayList<String> getEmail(){return this.email;}
}
