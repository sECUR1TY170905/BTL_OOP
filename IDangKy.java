package model.interfaces;

import model.DangKy;
import model.MonHoc;
import model.SinhVien;

public interface IDangKy {
    void dangKyMonHoc(SinhVien sv, MonHoc mh) throws Exception;
    boolean kiemTraDaDangKy(SinhVien sv, MonHoc mh);
    int demSoMonDaDangKy(SinhVien sv);
}
