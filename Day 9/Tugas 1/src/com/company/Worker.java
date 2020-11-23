package com.company;

abstract class Worker {
    String nama;
    int idKaryawan = 0;
    int tunjanganPulsa = 100000;
    int absensi = 5;

    abstract void setNama(String newValue);
    abstract String getNama();
    abstract void setId(int newValue);
    abstract int getId();
    abstract int getAbsen();
    abstract void Absen();
    abstract int getTunjanganPulsa();
}

