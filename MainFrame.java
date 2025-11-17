package view;

import controller.*;
import model.manager.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;

    private MonHocManager monHocManager;
    private SinhVienManager sinhVienManager;
    private DangKyManager dangKyManager;
    private LopHocManager lopHocManager;

    public MainFrame() {
        // Khởi tạo Managers
        monHocManager = new MonHocManager();
        sinhVienManager = new SinhVienManager();
        dangKyManager = new DangKyManager();
        lopHocManager = new LopHocManager();

        // Cấu hình JFrame
        setTitle("HỆ THỐNG QUẢN LÝ ĐĂNG KÝ HỌC THEO TÍN CHỈ");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo TabbedPane
        tabbedPane = new JTabbedPane();

        // Tạo các Panel với Controllers
        MonHocController monHocController = new MonHocController(monHocManager);
        PanelMonHoc panelMonHoc = new PanelMonHoc(monHocController);

        SinhVienController sinhVienController = new SinhVienController(sinhVienManager);
        PanelSinhVien panelSinhVien = new PanelSinhVien(sinhVienController);

        DangKyController dangKyController = new DangKyController(dangKyManager, sinhVienManager, monHocManager);
        PanelDangKy panelDangKy = new PanelDangKy(dangKyController);

        PanelSapXep panelSapXep = new PanelSapXep(dangKyManager);

        LopHocController lopHocController = new LopHocController(lopHocManager, dangKyManager);
        PanelLopHoc panelLopHoc = new PanelLopHoc(lopHocController);

        // Thêm các tab
        tabbedPane.addTab("Quản lý Môn học", panelMonHoc);
        tabbedPane.addTab("Quản lý Sinh viên", panelSinhVien);
        tabbedPane.addTab("Đăng ký Môn học", panelDangKy);
        tabbedPane.addTab("Sắp xếp Danh sách", panelSapXep);
        tabbedPane.addTab("Lập Lớp học", panelLopHoc);

        add(tabbedPane);
    }
}
