package controller;

import model.SinhVien;
import model.manager.SinhVienManager;

import java.util.List;

public class SinhVienController {
    private SinhVienManager sinhVienManager;

    public SinhVienController(SinhVienManager sinhVienManager) {
        this.sinhVienManager = sinhVienManager;
    }

    public void themSinhVien(String hoTen, String diaChi, String soDT) throws Exception {
        SinhVien sinhVien = new SinhVien(hoTen, diaChi, soDT);
        sinhVienManager.them(sinhVien);
    }

    public String layDanhSachSinhVien() {
        List<SinhVien> danhSach = sinhVienManager.layDanhSach();
        StringBuilder sb = new StringBuilder();

        sb.append("DANH SÁCH SINH VIÊN\n");
        sb.append("=".repeat(120)).append("\n\n");

        if (danhSach.isEmpty()) {
            sb.append("Chưa có sinh viên nào.\n");
        } else {
            for (int i = 0; i < danhSach.size(); i++) {
                sb.append(String.format("%3d. %s\n", i + 1, danhSach.get(i)));
            }
        }

        return sb.toString();
    }

    public List<SinhVien> getDanhSachSinhVien() {
        return sinhVienManager.layDanhSach();
    }
}
