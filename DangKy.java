package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DangKy implements Serializable {
    private static final long serialVersionUID = 1L;

    private SinhVien sinhVien;
    private MonHoc monHoc;
    private LocalDateTime thoiGianDangKy;

    public DangKy(SinhVien sinhVien, MonHoc monHoc) {
        this.sinhVien = sinhVien;
        this.monHoc = monHoc;
        this.thoiGianDangKy = LocalDateTime.now();
    }

    public DangKy(SinhVien sinhVien, MonHoc monHoc, LocalDateTime thoiGianDangKy) {
        this.sinhVien = sinhVien;
        this.monHoc = monHoc;
        this.thoiGianDangKy = thoiGianDangKy;
    }

    // Getters and Setters
    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    public LocalDateTime getThoiGianDangKy() {
        return thoiGianDangKy;
    }

    public void setThoiGianDangKy(LocalDateTime thoiGianDangKy) {
        this.thoiGianDangKy = thoiGianDangKy;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("MSSV: %05d | Sinh viên: %-30s | Mã MH: %03d | Môn học: %-30s | Thời gian: %s",
            sinhVien.getMaSV(), sinhVien.getHoTen(),
            monHoc.getMaMon(), monHoc.getTenMon(),
            thoiGianDangKy.format(formatter));
    }
}
