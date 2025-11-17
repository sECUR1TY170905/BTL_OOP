package view;

import controller.MonHocController;
import model.MonHoc;

import javax.swing.*;
import java.awt.*;

public class PanelMonHoc extends JPanel {
    private MonHocController controller;

    private JTextField txtTenMon, txtSoTiet;
    private JComboBox<MonHoc.LoaiMonHoc> cboLoaiMonHoc;
    private JTextArea txtAreaDanhSach;
    private JButton btnThem, btnHienThi;

    public PanelMonHoc(MonHocController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel nhập liệu
        JPanel panelInput = new JPanel(new GridBagLayout());
        panelInput.setBorder(BorderFactory.createTitledBorder("Thông tin Môn học"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Tên môn
        gbc.gridx = 0; gbc.gridy = 0;
        panelInput.add(new JLabel("Tên môn học:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtTenMon = new JTextField(30);
        panelInput.add(txtTenMon, gbc);

        // Số tiết
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        panelInput.add(new JLabel("Tổng số tiết:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtSoTiet = new JTextField(30);
        panelInput.add(txtSoTiet, gbc);

        // Loại môn học
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        panelInput.add(new JLabel("Loại môn học:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        cboLoaiMonHoc = new JComboBox<>(MonHoc.LoaiMonHoc.values());
        panelInput.add(cboLoaiMonHoc, gbc);

        // Buttons
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnThem = new JButton("Thêm Môn học");
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
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách Môn học"));
        add(scrollPane, BorderLayout.CENTER);

        // Event Listeners
        btnThem.addActionListener(e -> themMonHoc());
        btnHienThi.addActionListener(e -> hienThiDanhSach());

        // Hiển thị danh sách ban đầu
        hienThiDanhSach();
    }

    private void themMonHoc() {
        try {
            String tenMon = txtTenMon.getText().trim();
            String soTietStr = txtSoTiet.getText().trim();

            if (tenMon.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên môn học!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int soTiet = Integer.parseInt(soTietStr);
            MonHoc.LoaiMonHoc loai = (MonHoc.LoaiMonHoc) cboLoaiMonHoc.getSelectedItem();

            controller.themMonHoc(tenMon, soTiet, loai);

            JOptionPane.showMessageDialog(this, "Thêm môn học thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            // Reset form
            txtTenMon.setText("");
            txtSoTiet.setText("");
            cboLoaiMonHoc.setSelectedIndex(0);

            hienThiDanhSach();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Số tiết phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hienThiDanhSach() {
        txtAreaDanhSach.setText(controller.layDanhSachMonHoc());
    }
}
