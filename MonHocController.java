package controller;

import model.MonHoc;
import model.manager.MonHocManager;

import java.util.List;

public class MonHocController {
    private MonHocManager monHocManager;

    public MonHocController(MonHocManager monHocManager) {
        this.monHocManager = monHocManager;
    }

    public void themMonHoc(String tenMon, int tongSoTiet, MonHoc.LoaiMonHoc loaiMonHoc) throws Exception {
        MonHoc monHoc = new MonHoc(tenMon, tongSoTiet, loaiMonHoc);
        monHocManager.them(monHoc);
    }

    public String layDanhSachMonHoc() {
        List<MonHoc> danhSach = monHocManager.layDanhSach();
        StringBuilder sb = new StringBuilder();

        sb.append("DANH SÁCH MÔN HỌC\n");
        sb.append("=".repeat(120)).append("\n\n");

        if (danhSach.isEmpty()) {
            sb.append("Chưa có môn học nào.\n");
        } else {
            for (int i = 0; i < danhSach.size(); i++) {
                sb.append(String.format("%3d. %s\n", i + 1, danhSach.get(i)));
            }
        }

        return sb.toString();
    }

    public List<MonHoc> getDanhSachMonHoc() {
        return monHocManager.layDanhSach();
    }
}
