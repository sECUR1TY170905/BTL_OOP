package model.interfaces;

import model.DangKy;
import java.util.List;
import java.util.Map;

public interface ILopHoc {
    Map<String, List<DangKy>> lapDanhSachLopHoc(List<DangKy> danhSachDangKy);
}
