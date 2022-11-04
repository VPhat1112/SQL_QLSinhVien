package com.example.sqlitesinhvien.Adapter;

import android.widget.ImageView;

public class Sinhvien {
   String msv245,ten245;
   int nam245;
   int imageView245;

    public Sinhvien(int imageView,String msv, String ten, int nam) {
        this.imageView245=imageView;
        this.msv245 = msv;
        this.ten245 = ten;
        this.nam245 = nam;
    }

    public int getImageView() {
        return imageView245;
    }

    public void setImageView(int  imageView) {
        this.imageView245 = imageView;
    }

    public String getMsv() {
        return msv245;
    }

    public void setMsv(String msv) {
        this.msv245 = msv;
    }

    public String getTen() {
        return ten245;
    }

    public void setTen(String ten) {
        this.ten245 = ten;
    }

    public int getNam() {
        return nam245;
    }

    public void setNam(int nam) {
        this.nam245 = nam;
    }

    @Override
    public String toString() {
        return "Sinhvien{" +
                "msv='" + msv245 + '\'' +
                ", ten='" + ten245 + '\'' +
                ", nam=" + nam245 +
                ", imageView=" + imageView245 +
                '}';
    }
}
