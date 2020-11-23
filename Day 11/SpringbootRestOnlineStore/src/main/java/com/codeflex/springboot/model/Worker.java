package com.codeflex.springboot.model;

public abstract class Worker {
    Long idKaryawan;
    String name="";
    int gapok=1000000;
    int absensi=10;
    int tunjanganPulsa=100000;

    public abstract String getName();
    public abstract Long getidKaryawan();
    public abstract int getAbsensi();
}
