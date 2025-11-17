package view;

import controller.SinhVienController;

import javax.swing.*;
import java.awt.*;

public class PanelSinhVien extends JPanel {
    private SinhVienController controller;

    private JTextField txtHoTen, txtDiaChi, txtSoDT;
    private JTextArea txtAreaDanhSach;
    private JButton btnThem, btnHienThi;

    public PanelSinhVien(SinhVienController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel nhập liệu
        JPanel panelInput = new JPanel(new GridBagLayout());
        panelInput.setBorder(BorderFactory.createTitledBorder("Thông tin Sinh viên"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Họ tên
        gbc.gridx = 0; gbc.gridy = 0;
        panelInput.add(new JLabel("Họ và tên:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtHoTen = new JTextField(30);
        panelInput.add(txtHoTen, gbc);

        // Địa chỉ
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        panelInput.add(new JLabel("Địa chỉ:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtDiaChi = new JTextField(30);
        panelInput.add(txtDiaChi, gbc);

        // Số điện thoại
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        panelInput.add(new JLabel("Số điện thoại:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtSoDT = new JTextField(30);
        panelInput.add(txtSoDT, gbc);

        // Buttons
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnThem = new JButton("Thêm Sinh viên");
        btnHienThi = new JButton("Hiển thị Danh sách");
        panelButtons.add(btnThem);
        panelButtons.add(btnHienThi);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panelInput.add(panelButtons, gbc);

        add(panelInput, BorderLayout.NORTH);

        // TextArea hiển thị danh sách
        txtAreaDanhSach = new JTextArea();
        txtAreaDanhSach.setEditable(false);
        txtAreaDanhSach.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtAreaDanhSach);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách Sinh viên"));
        add(scrollPane, BorderLayout.CENTER);

        // Event Listeners
        btnThem.addActionListener(e -> themSinhVien());
        btnHienThi.addActionListener(e -> hienThiDanhSach());

        // Hiển thị danh sách ban đầu
        hienThiDanhSach();
    }

    private void themSinhVien() {
        try {
            String hoTen = txtHoTen.getText().trim();
            String diaChi = txtDiaChi.getText().trim();
            String soDT = txtSoDT.getText().trim();

            if (hoTen.isEmpty() || soDT.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            controller.themSinhVien(hoTen, diaChi, soDT);

            JOptionPane.showMessageDialog(this, "Thêm sinh viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            // Reset form
            txtHoTen.setText("");
            txtDiaChi.setText("");
            txtSoDT.setText("");

            hienThiDanhSach();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hienThiDanhSach() {
        txtAreaDanhSach.setText(controller.layDanhSachSinhVien());
    }
}
