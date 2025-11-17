package model.manager;

import model.SinhVien;
import model.FileController;
import model.interfaces.IManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SinhVienManager implements IManager<SinhVien> {
    private List<SinhVien> danhSachSinhVien;
    private static final String FILE_NAME = "SV.DAT";

    public SinhVienManager() {
        this.danhSachSinhVien = new ArrayList<>();
        try {
            docFile(FILE_NAME);
        } catch (Exception e) {
            // File chưa tồn tại, khởi tạo danh sách rỗng
        }
    }

    @Override
    public void them(SinhVien sinhVien) throws Exception {
        if (sinhVien == null) {
            throw new IllegalArgumentException("Sinh viên không được null");
        }
        if (sinhVien.getHoTen() == null || sinhVien.getHoTen().trim().isEmpty()) {
            throw new IllegalArgumentException("Họ tên không được để trống");
        }
        if (sinhVien.getSoDT() == null || sinhVien.getSoDT().trim().isEmpty()) {
            throw new IllegalArgumentException("Số điện thoại không được để trống");
        }
        danhSachSinhVien.add(sinhVien);
        luuFile(FILE_NAME);
    }

    @Override
    public List<SinhVien> layDanhSach() {
        return new ArrayList<>(danhSachSinhVien);
    }

    @Override
    public void luuFile(String fileName) throws Exception {
        try {
            FileController.luuFile(fileName, danhSachSinhVien);
        } catch (IOException e) {
            throw new Exception("Lỗi khi lưu file: " + e.getMessage());
        }
    }

    @Override
    public void docFile(String fileName) throws Exception {
        try {
            List<SinhVien> list = FileController.docFile(fileName);
            if (list != null) {
                this.danhSachSinhVien = list;
                // Cập nhật counter để tránh trùng mã
                if (!danhSachSinhVien.isEmpty()) {
                    int maxMa = danhSachSinhVien.stream()
                        .mapToInt(SinhVien::getMaSV)
                        .max()
                        .orElse(9999);
                    SinhVien.setCounter(maxMa + 1);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("Lỗi khi đọc file: " + e.getMessage());
        }
    }

    public SinhVien timSinhVienTheoMa(int maSV) {
        return danhSachSinhVien.stream()
            .filter(sv -> sv.getMaSV() == maSV)
            .findFirst()
            .orElse(null);
    }
}
