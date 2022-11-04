package com.example.sqlitesinhvien.Adapter;

import android.widget.ImageView;

public class Sinhvien {
   String msv,ten;
   int nam;
   int imageView;

    public Sinhvien(int imageView,String msv, String ten, int nam) {
        this.imageView=imageView;
        this.msv = msv;
        this.ten = ten;
        this.nam = nam;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int  imageView) {
        this.imageView = imageView;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    @Override
    public String toString() {
        return "Sinhvien{" +
                "msv='" + msv + '\'' +
                ", ten='" + ten + '\'' +
                ", nam=" + nam +
                ", imageView=" + imageView +
                '}';
    }
}
