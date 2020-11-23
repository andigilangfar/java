package com.company;
import java.util.ArrayList;
import org.json.simple.JSONArray;

public class Manager extends Worker {
    ArrayList telp = new ArrayList();
    int tunjanganTransport = 0;
    int tunjanganEntertaint = 0;
    int gapok=6000000;

    Manager(int id, String nama, ArrayList telp){
        this.idKaryawan = id;
        this.nama = nama;
        this.telp = telp;
        totalTunjanganTransport();
        totalTunjanganEntertaint(0);
    }
    Manager(){
        super();
    }
    int getTunjanganTransport(){
        return this.tunjanganTransport;
    }
    void totalTunjanganTransport(){
        this.tunjanganTransport = this.absensi * 50000;
    }
    int getTunjanganEntertaint(){
        return this.tunjanganEntertaint;
    }
    void totalTunjanganEntertaint(int iJumlah){
        this.tunjanganEntertaint = iJumlah * 50000;
    }
    public void Absen(){
        this.absensi += 1;
    }
    public void setNama (String newValue){
        this.nama = newValue;
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
        return this.gapok;
    }
    public ArrayList<String> getTelp (){
        return this.telp;
    }
}
