package model.manager;

import model.DangKy;
import model.MonHoc;
import model.SinhVien;
import model.FileController;
import model.interfaces.IManager;
import model.interfaces.IDangKy;
import model.interfaces.ISortDangKy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DangKyManager implements IManager<DangKy>, IDangKy, ISortDangKy {
    private List<DangKy> danhSachDangKy;
    private static final String FILE_NAME = "QLDK.DAT";
    private static final int MAX_MON_HOC = 8;

    public DangKyManager() {
        this.danhSachDangKy = new ArrayList<>();
        try {
            docFile(FILE_NAME);
        } catch (Exception e) {
            // File chưa tồn tại, khởi tạo danh sách rỗng
        }
    }

    @Override
    public void them(DangKy dangKy) throws Exception {
        if (dangKy == null) {
            throw new IllegalArgumentException("Đăng ký không được null");
        }
        danhSachDangKy.add(dangKy);
        luuFile(FILE_NAME);
    }

    @Override
    public void dangKyMonHoc(SinhVien sv, MonHoc mh) throws Exception {
        // Kiểm tra đã đăng ký môn này chưa
        if (kiemTraDaDangKy(sv, mh)) {
            throw new Exception("Sinh viên đã đăng ký môn học này rồi!");
        }

        // Kiểm tra số lượng môn đã đăng ký
        if (demSoMonDaDangKy(sv) >= MAX_MON_HOC) {
            throw new Exception("Sinh viên đã đăng ký đủ " + MAX_MON_HOC + " môn học!");
        }

        DangKy dangKy = new DangKy(sv, mh);
        them(dangKy);
    }

    @Override
    public boolean kiemTraDaDangKy(SinhVien sv, MonHoc mh) {
        return danhSachDangKy.stream()
            .anyMatch(dk -> dk.getSinhVien().getMaSV() == sv.getMaSV()
                && dk.getMonHoc().getMaMon() == mh.getMaMon());
    }

    @Override
    public int demSoMonDaDangKy(SinhVien sv) {
        return (int) danhSachDangKy.stream()
            .filter(dk -> dk.getSinhVien().getMaSV() == sv.getMaSV())
            .count();
    }

    @Override
    public List<DangKy> layDanhSach() {
        return new ArrayList<>(danhSachDangKy);
    }

    @Override
    public void luuFile(String fileName) throws Exception {
        try {
            FileController.luuFile(fileName, danhSachDangKy);
        } catch (IOException e) {
            throw new Exception("Lỗi khi lưu file: " + e.getMessage());
        }
    }

    @Override
    public void docFile(String fileName) throws Exception {
        try {
            List<DangKy> list = FileController.docFile(fileName);
            if (list != null) {
                this.danhSachDangKy = list;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("Lỗi khi đọc file: " + e.getMessage());
        }
    }

    @Override
    public void sapXepTheoTenSinhVien(List<DangKy> danhSach) {
        danhSach.sort(Comparator.comparing(dk -> dk.getSinhVien().getHoTen()));
    }

    @Override
    public void sapXepTheoThoiGianDangKy(List<DangKy> danhSach) {
        danhSach.sort(Comparator.comparing(DangKy::getThoiGianDangKy));
    }

    public List<DangKy> layDanhSachDangKyTheoSinhVien(int maSV) {
        return danhSachDangKy.stream()
            .filter(dk -> dk.getSinhVien().getMaSV() == maSV)
            .toList();
    }
}
