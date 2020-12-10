package com.example.seminarkp1.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Pegawai implements Serializable{

    private String nama;
    private String merk;
    private String harga;
    private String key;
    private String mulai;
    private String akhir;

    public Pegawai(){

    }

    public String getAkhir() {
        return akhir;
    }

    public void setAkhir(String akhir) {
        this.akhir = akhir;
    }

    public String getMulai() {
        return mulai;
    }

    public void setMulai(String mulai) {
        this.mulai = mulai;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    @Override
    public String toString() {
        return " "+nama+"\n" +
                " "+merk +"\n" +
                " "+harga+"\n" +
                " "+mulai+"\n" +
                " "+akhir;

    }

    public Pegawai(String nm, String mrk, String hrg,String mul,String akh){
        nama = nm;
        merk = mrk;
        harga = hrg;
        mulai=mul;
        akhir=akh;

    }
}
