package com.codeflex.springboot.model;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
public class Staff extends Worker {
    private static final AtomicLong counter = new AtomicLong();
    private int tunjanganMakan = 50000;
    private ArrayList<String> email = new ArrayList<>();
    public void setAbsensi(int absensi){
        this.absensi = absensi;
    }
    public int getAbsensi(){
        return absensi;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Long getidKaryawan(){
        return this.idKaryawan;
    }
    public void setidKaryawan(){
        this.idKaryawan = idKaryawan;
    }

    public int getGapok(){
        return gapok;
    }

    public void setGapok(int gapok){
        this.gapok = gapok;
    }

    public int getTunjanganPulsa(){
        return tunjanganPulsa;
    }

    public void setTunjanganPulsa(int tunjanganPulsa){
        this.tunjanganPulsa = tunjanganPulsa;
    }



}