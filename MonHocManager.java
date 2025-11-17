package model.manager;

import model.MonHoc;
import model.FileController;
import model.interfaces.IManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MonHocManager implements IManager<MonHoc> {
    private List<MonHoc> danhSachMonHoc;
    private static final String FILE_NAME = "MH.DAT";

    public MonHocManager() {
        this.danhSachMonHoc = new ArrayList<>();
        try {
            docFile(FILE_NAME);
        } catch (Exception e) {
            // File chưa tồn tại, khởi tạo danh sách rỗng
        }
    }

    @Override
    public void them(MonHoc monHoc) throws Exception {
        if (monHoc == null) {
            throw new IllegalArgumentException("Môn học không được null");
        }
        if (monHoc.getTenMon() == null || monHoc.getTenMon().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên môn học không được để trống");
        }
        if (monHoc.getTongSoTiet() <= 0) {
            throw new IllegalArgumentException("Tổng số tiết phải lớn hơn 0");
        }
        danhSachMonHoc.add(monHoc);
        luuFile(FILE_NAME);
    }

    @Override
    public List<MonHoc> layDanhSach() {
        return new ArrayList<>(danhSachMonHoc);
    }

    @Override
    public void luuFile(String fileName) throws Exception {
        try {
            FileController.luuFile(fileName, danhSachMonHoc);
        } catch (IOException e) {
            throw new Exception("Lỗi khi lưu file: " + e.getMessage());
        }
    }

    @Override
    public void docFile(String fileName) throws Exception {
        try {
            List<MonHoc> list = FileController.docFile(fileName);
            if (list != null) {
                this.danhSachMonHoc = list;
                // Cập nhật counter để tránh trùng mã
                if (!danhSachMonHoc.isEmpty()) {
                    int maxMa = danhSachMonHoc.stream()
                        .mapToInt(MonHoc::getMaMon)
                        .max()
                        .orElse(99);
                    MonHoc.setCounter(maxMa + 1);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("Lỗi khi đọc file: " + e.getMessage());
        }
    }

    public MonHoc timMonHocTheoMa(int maMon) {
        return danhSachMonHoc.stream()
            .filter(mh -> mh.getMaMon() == maMon)
            .findFirst()
            .orElse(null);
    }
}
