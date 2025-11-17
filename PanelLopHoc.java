package view;

import controller.LopHocController;
import model.DangKy;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class PanelLopHoc extends JPanel {
    private LopHocController controller;

    private JTextArea txtAreaDanhSach;
    private JButton btnLapLop;

    public PanelLopHoc(LopHocController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel button
        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelButton.setBorder(BorderFactory.createTitledBorder("Lập Danh sách Lớp học"));

        btnLapLop = new JButton("Lập Lớp học");
        panelButton.add(btnLapLop);

        JLabel lblInfo = new JLabel("(Mỗi lớp tối đa 30 sinh viên, sắp xếp theo thời gian đăng ký)");
        lblInfo.setFont(new Font("Arial", Font.ITALIC, 11));
        panelButton.add(lblInfo);

        add(panelButton, BorderLayout.NORTH);

        // TextArea hiển thị danh sách
        txtAreaDanhSach = new JTextArea();
        txtAreaDanhSach.setEditable(false);
        txtAreaDanhSach.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtAreaDanhSach);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách Lớp học"));
        add(scrollPane, BorderLayout.CENTER);

        // Event Listener
        btnLapLop.addActionListener(e -> lapLopHoc());
    }

    private void lapLopHoc() {
        Map<String, List<DangKy>> danhSachLopHoc = controller.lapDanhSachLopHoc();

        StringBuilder sb = new StringBuilder();
        sb.append("DANH SÁCH LỚP HỌC\n");
        sb.append("=".repeat(120)).append("\n\n");

        if (danhSachLopHoc.isEmpty()) {
            sb.append("Chưa có dữ liệu đăng ký để lập lớp.\n");
        } else {
            for (Map.Entry<String, List<DangKy>> entry : danhSachLopHoc.entrySet()) {
                sb.append("━".repeat(120)).append("\n");
                sb.append(String.format("LỚP: %s (Số sinh viên: %d)\n", entry.getKey(), entry.getValue().size()));
                sb.append("━".repeat(120)).append("\n");

                for (int i = 0; i < entry.getValue().size(); i++) {
                    DangKy dk = entry.getValue().get(i);
                    sb.append(String.format("  %2d. %s\n", i + 1, dk));
                }
                sb.append("\n");
            }
        }

        txtAreaDanhSach.setText(sb.toString());
        txtAreaDanhSach.setCaretPosition(0);

        if (!danhSachLopHoc.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                String.format("Đã lập thành công %d lớp học!", danhSachLopHoc.size()),
                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
