package controller;

import model.DangKy;
import model.MonHoc;
import model.SinhVien;
import model.manager.DangKyManager;
import model.manager.MonHocManager;
import model.manager.SinhVienManager;

import java.util.List;

public class DangKyController {
    private DangKyManager dangKyManager;
    private SinhVienManager sinhVienManager;
    private MonHocManager monHocManager;

    public DangKyController(DangKyManager dangKyManager, SinhVienManager sinhVienManager, MonHocManager monHocManager) {
        this.dangKyManager = dangKyManager;
        this.sinhVienManager = sinhVienManager;
        this.monHocManager = monHocManager;
    }

    public void dangKyMonHoc(int maSV, int maMH) throws Exception {
        SinhVien sv = sinhVienManager.timSinhVienTheoMa(maSV);
        MonHoc mh = monHocManager.timMonHocTheoMa(maMH);

        if (sv == null) {
            throw new Exception("Không tìm thấy sinh viên!");
        }

        if (mh == null) {
            throw new Exception("Không tìm thấy môn học!");
        }

        dangKyManager.dangKyMonHoc(sv, mh);
    }

    public String layDanhSachDangKy() {
        List<DangKy> danhSach = dangKyManager.layDanhSach();
        StringBuilder sb = new StringBuilder();

        sb.append("DANH SÁCH ĐĂNG KÝ\n");
        sb.append("=".repeat(120)).append("\n\n");

        if (danhSach.isEmpty()) {
            sb.append("Chưa có dữ liệu đăng ký.\n");
        } else {
            for (int i = 0; i < danhSach.size(); i++) {
                sb.append(String.format("%3d. %s\n", i + 1, danhSach.get(i)));
            }
        }

        return sb.toString();
    }

    public List<SinhVien> layDanhSachSinhVien() {
        return sinhVienManager.layDanhSach();
    }

    public List<MonHoc> layDanhSachMonHoc() {
        return monHocManager.layDanhSach();
    }
}
