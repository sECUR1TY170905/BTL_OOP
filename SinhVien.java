package model;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int counter = 10000; // Bắt đầu từ 10000 để đảm bảo 5 chữ số

    private int maSV;
    private String hoTen;
    private String diaChi;
    private String soDT;

    public SinhVien(String hoTen, String diaChi, String soDT) {
        this.maSV = counter++;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.soDT = soDT;
    }

    // Getters and Setters
    public int getMaSV() {
        return maSV;
    }

    public void setMaSV(int maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public static void setCounter(int counter) {
        SinhVien.counter = counter;
    }

    public static int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return String.format("MSSV: %05d | Họ tên: %-30s | Địa chỉ: %-30s | SĐT: %s",
            maSV, hoTen, diaChi, soDT);
    }
}
