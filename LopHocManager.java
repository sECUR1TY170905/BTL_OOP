package model.manager;

import model.DangKy;
import model.interfaces.ILopHoc;

import java.util.*;

public class LopHocManager implements ILopHoc {
    private static final int MAX_SINH_VIEN_MOT_LOP = 30;

    @Override
    public Map<String, List<DangKy>> lapDanhSachLopHoc(List<DangKy> danhSachDangKy) {
        Map<String, List<DangKy>> danhSachLopHoc = new LinkedHashMap<>();

        // Nhóm theo môn học
        Map<Integer, List<DangKy>> nhomTheoMonHoc = new HashMap<>();
        for (DangKy dk : danhSachDangKy) {
            int maMon = dk.getMonHoc().getMaMon();
            nhomTheoMonHoc.computeIfAbsent(maMon, k -> new ArrayList<>()).add(dk);
        }

        // Tạo lớp cho từng môn học
        for (Map.Entry<Integer, List<DangKy>> entry : nhomTheoMonHoc.entrySet()) {
            List<DangKy> danhSachDK = entry.getValue();

            // Sắp xếp theo thời gian đăng ký
            danhSachDK.sort(Comparator.comparing(DangKy::getThoiGianDangKy));

            // Chia thành các lớp
            int soLop = (int) Math.ceil((double) danhSachDK.size() / MAX_SINH_VIEN_MOT_LOP);

            for (int i = 0; i < soLop; i++) {
                int start = i * MAX_SINH_VIEN_MOT_LOP;
                int end = Math.min(start + MAX_SINH_VIEN_MOT_LOP, danhSachDK.size());
                List<DangKy> lopHoc = danhSachDK.subList(start, end);

                String tenMonHoc = danhSachDK.get(0).getMonHoc().getTenMon();
                int maMon = danhSachDK.get(0).getMonHoc().getMaMon();
                String tenLop = String.format("%s (Mã: %03d) - Lớp %d", tenMonHoc, maMon, i + 1);

                danhSachLopHoc.put(tenLop, new ArrayList<>(lopHoc));
            }
        }

        return danhSachLopHoc;
    }
}
