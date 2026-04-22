import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class App extends JFrame {

    JTextField tfNis, tfNama, tfAlamat;
    JTable table;
    DefaultTableModel model;

    java.util.List<String[]> dataList = new ArrayList<>();
    String fileName = "siswa.csv";

    public App() {
        //LAYOUT
        setTitle("Aplikasi Data Siswa");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(230, 240, 255));

        //JUDUL
        JLabel title = new JLabel("DATA SISWA", JLabel.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        //FORM INPUT
        JPanel panelInput = new JPanel(new GridLayout(10, 25, 1, 1));
        panelInput.setBackground(Color.WHITE);
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Data"));

        panelInput.add(new JLabel("NIS"));
        tfNis = new JTextField(5);
        panelInput.add(tfNis);

        panelInput.add(new JLabel("Nama"));
        tfNama = new JTextField(15);
        panelInput.add(tfNama);

        panelInput.add(new JLabel("Alamat"));
        tfAlamat = new JTextField(20);
        panelInput.add(tfAlamat);

        add(panelInput, BorderLayout.WEST);

        //TABEL
        model = new DefaultTableModel(new String[]{"NIS", "Nama", "Alamat"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        //BUTTON
        JPanel panelBtn = new JPanel();

        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnClear = new JButton("Clear");

        panelBtn.add(btnTambah);
        panelBtn.add(btnUpdate);
        panelBtn.add(btnDelete);
        panelBtn.add(btnClear);

        btnTambah.setBackground(new Color(76, 175, 80));
        btnTambah.setForeground(Color.WHITE);
        btnTambah.setFocusPainted(false);
        btnUpdate.setBackground(new Color(255, 152, 0));
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFocusPainted(false);
        btnDelete.setBackground(new Color(244, 67, 54));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFocusPainted(false);
        btnClear.setBackground(new Color(33, 150, 243));
        btnClear.setForeground(Color.WHITE);
        btnClear.setFocusPainted(false);


        add(panelBtn, BorderLayout.SOUTH);

        //LOAD DATA
        loadData();

        //EVENT (listener button & table)
        btnTambah.addActionListener(e -> tambahData());
        btnUpdate.addActionListener(e -> updateData());
        btnDelete.addActionListener(e -> deleteData());
        btnClear.addActionListener(e -> clearForm());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                tfNis.setText(model.getValueAt(row, 0).toString());
                tfNama.setText(model.getValueAt(row, 1).toString());
                tfAlamat.setText(model.getValueAt(row, 2).toString());
            }
        });
    }

    //LOAD DATA (METHOD)
    void loadData() {
        dataList.clear();
        model.setRowCount(0);

        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                dataList.add(data);
                model.addRow(data);
            }
            br.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error load data!");
        }
    }

    //SAVE FILE
    void saveData() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            for (String[] d : dataList) {
                bw.write(d[0] + "," + d[1] + "," + d[2]);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error save data!");
        }
    }

    //TAMBAH SISWA
    void tambahData() {
        String nis = tfNis.getText();
        String nama = tfNama.getText();
        String alamat = tfAlamat.getText();

        if (nis.isEmpty() || nama.isEmpty() || alamat.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
            return;
        }

        for (String[] d : dataList) {
            if (d[0].equals(nis)) {
                JOptionPane.showMessageDialog(this, "NIS sudah ada!");
                return;
            }
        }

        dataList.add(new String[]{nis, nama, alamat});
        saveData();
        loadData();
        clearForm();
    }

    //UPDATE DATA
    void updateData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data dulu!");
            return;
        }

        String nis = tfNis.getText();
        String nama = tfNama.getText();
        String alamat = tfAlamat.getText();

        dataList.set(row, new String[]{nis, nama, alamat});
        saveData();
        loadData();
        clearForm();
    }

    // HAPUS DATA
    void deleteData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data dulu!");
            return;
        }

        dataList.remove(row);
        saveData();
        loadData();
        clearForm();
    }

    //CLEAR FORM
    void clearForm() {
        tfNis.setText("");
        tfNama.setText("");
        tfAlamat.setText("");
    }

    public static void main(String[] args) {
        new App().setVisible(true);
    }
}