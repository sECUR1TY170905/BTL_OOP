package view;

import model.DangKy;
import model.manager.DangKyManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelSapXep extends JPanel {
    private DangKyManager dangKyManager;

    private JTextArea txtAreaDanhSach;
    private JButton btnSapXepTen, btnSapXepThoiGian;

    public PanelSapXep(DangKyManager dangKyManager) {
        this.dangKyManager = dangKyManager;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel buttons
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelButtons.setBorder(BorderFactory.createTitledBorder("Sắp xếp Danh sách Đăng ký"));

        btnSapXepTen = new JButton("Sắp xếp theo Tên Sinh viên");
        btnSapXepThoiGian = new JButton("Sắp xếp theo Thời gian Đăng ký");

        panelButtons.add(btnSapXepTen);
        panelButtons.add(btnSapXepThoiGian);

        add(panelButtons, BorderLayout.NORTH);

        // TextArea hiển thị danh sách
        txtAreaDanhSach = new JTextArea();
        txtAreaDanhSach.setEditable(false);
        txtAreaDanhSach.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtAreaDanhSach);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách Đăng ký (Đã sắp xếp)"));
        add(scrollPane, BorderLayout.CENTER);

        // Event Listeners
        btnSapXepTen.addActionListener(e -> sapXepTheoTen());
        btnSapXepThoiGian.addActionListener(e -> sapXepTheoThoiGian());

        // Hiển thị danh sách ban đầu
        hienThiDanhSach(dangKyManager.layDanhSach());
    }

    private void sapXepTheoTen() {
        List<DangKy> danhSach = dangKyManager.layDanhSach();
        dangKyManager.sapXepTheoTenSinhVien(danhSach);
        hienThiDanhSach(danhSach);
        JOptionPane.showMessageDialog(this, "Đã sắp xếp theo tên sinh viên!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void sapXepTheoThoiGian() {
        List<DangKy> danhSach = dangKyManager.layDanhSach();
        dangKyManager.sapXepTheoThoiGianDangKy(danhSach);
        hienThiDanhSach(danhSach);
        JOptionPane.showMessageDialog(this, "Đã sắp xếp theo thời gian đăng ký!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void hienThiDanhSach(List<DangKy> danhSach) {
        StringBuilder sb = new StringBuilder();
        sb.append("DANH SÁCH ĐĂNG KÝ\n");
        sb.append("=".repeat(120)).append("\n\n");

        if (danhSach.isEmpty()) {
            sb.append("Chưa có dữ liệu đăng ký.\n");
        } else {
            for (int i = 0; i < danhSach.size(); i++) {
                sb.append(String.format("%3d. %s\n", i + 1, danhSach.get(i)));
            }
        }

        txtAreaDanhSach.setText(sb.toString());
        txtAreaDanhSach.setCaretPosition(0);
    }
}
