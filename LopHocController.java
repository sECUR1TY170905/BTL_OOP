package controller;

import model.DangKy;
import model.manager.DangKyManager;
import model.manager.LopHocManager;

import java.util.List;
import java.util.Map;

public class LopHocController {
    private LopHocManager lopHocManager;
    private DangKyManager dangKyManager;

    public LopHocController(LopHocManager lopHocManager, DangKyManager dangKyManager) {
        this.lopHocManager = lopHocManager;
        this.dangKyManager = dangKyManager;
    }

    public Map<String, List<DangKy>> lapDanhSachLopHoc() {
        List<DangKy> danhSachDangKy = dangKyManager.layDanhSach();
        return lopHocManager.lapDanhSachLopHoc(danhSachDangKy);
    }
}
