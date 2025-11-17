package view;

import controller.DangKyController;
import model.MonHoc;
import model.SinhVien;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelDangKy extends JPanel {
    private DangKyController controller;

    private JComboBox<String> cboSinhVien;
    private JComboBox<String> cboMonHoc;
    private JTextArea txtAreaDanhSach;
    private JButton btnDangKy, btnHienThi;

    public PanelDangKy(DangKyController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel nhập liệu
        JPanel panelInput = new JPanel(new GridBagLayout());
        panelInput.setBorder(BorderFactory.createTitledBorder("Đăng ký Môn học"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Sinh viên
        gbc.gridx = 0; gbc.gridy = 0;
        panelInput.add(new JLabel("Chọn Sinh viên:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        cboSinhVien = new JComboBox<>();
        panelInput.add(cboSinhVien, gbc);

        // Môn học
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        panelInput.add(new JLabel("Chọn Môn học:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        cboMonHoc = new JComboBox<>();
        panelInput.add(cboMonHoc, gbc);

        // Buttons
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnDangKy = new JButton("Đăng ký");
        btnHienThi = new JButton("Hiển thị Danh sách");
        JButton btnTaiLai = new JButton("Tải lại Dữ liệu");
        panelButtons.add(btnDangKy);
        panelButtons.add(btnHienThi);
        panelButtons.add(btnTaiLai);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panelInput.add(panelButtons, gbc);

        add(panelInput, BorderLayout.NORTH);

        // TextArea hiển thị danh sách
        txtAreaDanhSach = new JTextArea();
        txtAreaDanhSach.setEditable(false);
        txtAreaDanhSach.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtAreaDanhSach);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách Đăng ký"));
        add(scrollPane, BorderLayout.CENTER);

        // Event Listeners
        btnDangKy.addActionListener(e -> dangKyMonHoc());
        btnHienThi.addActionListener(e -> hienThiDanhSach());
        btnTaiLai.addActionListener(e -> taiLaiDuLieu());

        // Tải dữ liệu ban đầu
        taiLaiDuLieu();
        hienThiDanhSach();
    }

    private void taiLaiDuLieu() {
        cboSinhVien.removeAllItems();
        cboMonHoc.removeAllItems();

        List<SinhVien> danhSachSV = controller.layDanhSachSinhVien();
        List<MonHoc> danhSachMH = controller.layDanhSachMonHoc();

        for (SinhVien sv : danhSachSV) {
            cboSinhVien.addItem(String.format("%05d - %s", sv.getMaSV(), sv.getHoTen()));
        }

        for (MonHoc mh : danhSachMH) {
            cboMonHoc.addItem(String.format("%03d - %s", mh.getMaMon(), mh.getTenMon()));
        }
    }

    private void dangKyMonHoc() {
        try {
            if (cboSinhVien.getSelectedItem() == null || cboMonHoc.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sinh viên và môn học!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String svStr = (String) cboSinhVien.getSelectedItem();
            String mhStr = (String) cboMonHoc.getSelectedItem();

            int maSV = Integer.parseInt(svStr.split(" - ")[0]);
            int maMH = Integer.parseInt(mhStr.split(" - ")[0]);

            controller.dangKyMonHoc(maSV, maMH);

            JOptionPane.showMessageDialog(this, "Đăng ký môn học thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            hienThiDanhSach();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hienThiDanhSach() {
        txtAreaDanhSach.setText(controller.layDanhSachDangKy());
    }
}
