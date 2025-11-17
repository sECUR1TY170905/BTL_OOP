package model;

import java.io.Serializable;

public class MonHoc implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int counter = 100; // Bắt đầu từ 100 để đảm bảo 3 chữ số

    private int maMon;
    private String tenMon;
    private int tongSoTiet;
    private LoaiMonHoc loaiMonHoc;

    public enum LoaiMonHoc {
        DAI_CUONG("Đại cương"),
        CO_SO_NGANH("Cơ sở ngành"),
        CHUYEN_NGANH_BAT_BUOC("Chuyên ngành bắt buộc"),
        CHUYEN_NGANH_TU_CHON("Chuyên ngành tự chọn");

        private String tenLoai;

        LoaiMonHoc(String tenLoai) {
            this.tenLoai = tenLoai;
        }

        public String getTenLoai() {
            return tenLoai;
        }

        @Override
        public String toString() {
            return tenLoai;
        }
    }

    public MonHoc(String tenMon, int tongSoTiet, LoaiMonHoc loaiMonHoc) {
        this.maMon = counter++;
        this.tenMon = tenMon;
        this.tongSoTiet = tongSoTiet;
        this.loaiMonHoc = loaiMonHoc;
    }

    // Getters and Setters
    public int getMaMon() {
        return maMon;
    }

    public void setMaMon(int maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getTongSoTiet() {
        return tongSoTiet;
    }

    public void setTongSoTiet(int tongSoTiet) {
        this.tongSoTiet = tongSoTiet;
    }

    public LoaiMonHoc getLoaiMonHoc() {
        return loaiMonHoc;
    }

    public void setLoaiMonHoc(LoaiMonHoc loaiMonHoc) {
        this.loaiMonHoc = loaiMonHoc;
    }

    public static void setCounter(int counter) {
        MonHoc.counter = counter;
    }

    public static int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return String.format("Mã: %03d | Tên: %-30s | Số tiết: %-3d | Loại: %s",
            maMon, tenMon, tongSoTiet, loaiMonHoc.getTenLoai());
    }
}
