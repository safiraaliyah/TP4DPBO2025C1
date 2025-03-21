import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Menu extends JFrame {
    public static void main(String[] args) {
        // Buat object window
        Menu window = new Menu();

        // Atur ukuran window
        window.setSize(600, 560); // Lebar diperbesar agar muat
        // Letakkan window di tengah layar
        window.setLocationRelativeTo(null);
        // Isi window
        window.setContentPane(window.mainPanel);
        // Ubah warna background
        window.getContentPane().setBackground(Color.white);
        // Tampilkan window
        window.setVisible(true);
        // Agar program ikut berhenti saat window diclose
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Index baris yang diklik
    private int selectedIndex = -1;

    // List untuk menampung semua mahasiswa
    private ArrayList<Mahasiswa> listMahasiswa;

    // Komponen GUI
    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JButton deleteButton;
    private JComboBox jenisKelaminComboBox;
    private JComboBox programStudiComboBox;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;
    private JLabel programStudiLabel;

    // Constructor
    public Menu() {
        // Inisialisasi listMahasiswa
        listMahasiswa = new ArrayList<>();

        // Isi listMahasiswa
        populateList();

        // Inisialisasi komponen-komponen UI
        initializeComponents();

        // Isi tabel mahasiswa
        mahasiswaTable.setModel(setTable());

        // Ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // Atur isi combo box jenis kelamin
        String[] jenisKelaminData = {"", "Laki-laki", "Perempuan"};
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel(jenisKelaminData));

        // Atur isi combo box program studi
        String[] programStudiData = {"", "Teknik Informatika", "Sistem Informasi", "Teknik Komputer", "Manajemen Informatika"};
        programStudiComboBox.setModel(new DefaultComboBoxModel(programStudiData));

        // Sembunyikan button delete
        deleteButton.setVisible(false);

        // Saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });

        // Saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0) {
                    deleteData();
                }
            }
        });

        // Saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        // Saat salah satu baris tabel ditekan
        mahasiswaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = mahasiswaTable.getSelectedRow();

                // Simpan value textfield dan combo box
                String selectedNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();
                String selectedNama = mahasiswaTable.getModel().getValueAt(selectedIndex, 2).toString();
                String selectedJenisKelamin = mahasiswaTable.getModel().getValueAt(selectedIndex, 3).toString();
                String selectedProgramStudi = mahasiswaTable.getModel().getValueAt(selectedIndex, 4).toString();

                // Ubah isi textfield dan combo box
                nimField.setText(selectedNim);
                namaField.setText(selectedNama);
                jenisKelaminComboBox.setSelectedItem(selectedJenisKelamin);
                programStudiComboBox.setSelectedItem(selectedProgramStudi);

                // Ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");
                // Tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    // Metode untuk inisialisasi komponen GUI
    private void initializeComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        // Inisialisasi semua komponen
        titleLabel = new JLabel("Data Mahasiswa");
        nimLabel = new JLabel("NIM");
        namaLabel = new JLabel("Nama");
        jenisKelaminLabel = new JLabel("Jenis Kelamin");
        programStudiLabel = new JLabel("Program Studi");

        nimField = new JTextField(20);
        namaField = new JTextField(20);
        jenisKelaminComboBox = new JComboBox();
        programStudiComboBox = new JComboBox();

        addUpdateButton = new JButton("Add");
        cancelButton = new JButton("Cancel");
        deleteButton = new JButton("Delete");

        mahasiswaTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(mahasiswaTable);

        // Mengatur layout komponen
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Judul
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        mainPanel.add(titleLabel, gbc);

        // NIM
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(nimLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(nimField, gbc);

        // Tombol Add/Update di sebelah kanan NIM
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(addUpdateButton, gbc);

        // Nama
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(namaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(namaField, gbc);

        // Tombol Cancel di sebelah kanan Nama
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(cancelButton, gbc);

        // Jenis Kelamin
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(jenisKelaminLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(jenisKelaminComboBox, gbc);

        // Tombol Delete di sebelah kanan Jenis Kelamin
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(deleteButton, gbc);

        // Program Studi
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(programStudiLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(programStudiComboBox, gbc);

        // Table
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        mainPanel.add(scrollPane, gbc);
    }

    // Metode untuk mengisi tabel
    public final DefaultTableModel setTable() {
        // Tentukan kolom tabel
        Object[] column = {"No", "NIM", "Nama", "Jenis Kelamin", "Program Studi"};

        // Buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel temp = new DefaultTableModel(null, column);

        // Isi tabel dengan listMahasiswa
        for (int i = 0; i < listMahasiswa.size(); i++) {
            Object[] row = new Object[5];
            row[0] = i + 1;
            row[1] = listMahasiswa.get(i).getNim();
            row[2] = listMahasiswa.get(i).getNama();
            row[3] = listMahasiswa.get(i).getJenisKelamin();
            row[4] = listMahasiswa.get(i).getProgramStudi();

            temp.addRow(row);
        }

        return temp;
    }

    // Metode untuk menambahkan data
    public void insertData() {
        // Ambil value dari textfield dan combo box
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String programStudi = programStudiComboBox.getSelectedItem().toString();

        // Validasi data
        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || programStudi.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Tambahkan data ke dalam list
        listMahasiswa.add(new Mahasiswa(nim, nama, jenisKelamin, programStudi));

        // Update tabel
        mahasiswaTable.setModel(setTable());

        // Bersihkan form
        clearForm();

        // Feedback
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
    }

    // Metode untuk mengupdate data
    public void updateData() {
        // Ambil data dari form
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String programStudi = programStudiComboBox.getSelectedItem().toString();

        // Validasi data
        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || programStudi.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Ubah data mahasiswa di list
        listMahasiswa.get(selectedIndex).setNim(nim);
        listMahasiswa.get(selectedIndex).setNama(nama);
        listMahasiswa.get(selectedIndex).setJenisKelamin(jenisKelamin);
        listMahasiswa.get(selectedIndex).setProgramStudi(programStudi);

        // Update tabel
        mahasiswaTable.setModel(setTable());

        // Bersihkan form
        clearForm();

        // Feedback
        JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
    }

    // Metode untuk menghapus data
    public void deleteData() {
        // Konfirmasi hapus data
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // Hapus data dari list
            listMahasiswa.remove(selectedIndex);

            // Update tabel
            mahasiswaTable.setModel(setTable());

            // Bersihkan form
            clearForm();

            // Feedback
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
        }
    }

    // Metode untuk membersihkan form
    public void clearForm() {
        // Kosongkan semua textfield dan combo box
        nimField.setText("");
        namaField.setText("");
        jenisKelaminComboBox.setSelectedItem("");
        programStudiComboBox.setSelectedItem("");

        // Ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");
        // Sembunyikan button delete
        deleteButton.setVisible(false);
        // Reset selectedIndex
        selectedIndex = -1;
    }

    // Metode untuk mengisi list dengan data dummy
    private void populateList() {
        listMahasiswa.add(new Mahasiswa("2203999", "Amelia Zalfa Julianti", "Perempuan", "Teknik Informatika"));
        listMahasiswa.add(new Mahasiswa("2202292", "Muhammad Iqbal Fadhilah", "Laki-laki", "Sistem Informasi"));
        listMahasiswa.add(new Mahasiswa("2202346", "Muhammad Rifky Afandi", "Laki-laki", "Teknik Komputer"));
        listMahasiswa.add(new Mahasiswa("2210239", "Muhammad Hanif Abdillah", "Laki-laki", "Manajemen Informatika"));
        listMahasiswa.add(new Mahasiswa("2202046", "Nurainun", "Perempuan", "Teknik Informatika"));
        listMahasiswa.add(new Mahasiswa("2205101", "Kelvin Julian Putra", "Laki-laki", "Sistem Informasi"));
        listMahasiswa.add(new Mahasiswa("2200163", "Rifanny Lysara Annastasya", "Perempuan", "Teknik Komputer"));
        listMahasiswa.add(new Mahasiswa("2202869", "Revana Faliha Salma", "Perempuan", "Manajemen Informatika"));
        listMahasiswa.add(new Mahasiswa("2209489", "Rakha Dhifiargo Hariadi", "Laki-laki", "Teknik Informatika"));
        listMahasiswa.add(new Mahasiswa("2203142", "Roshan Syalwan Nurilham", "Laki-laki", "Sistem Informasi"));
        listMahasiswa.add(new Mahasiswa("2200311", "Raden Rahman Ismail", "Laki-laki", "Teknik Komputer"));
        listMahasiswa.add(new Mahasiswa("2200978", "Ratu Syahirah Khairunnisa", "Perempuan", "Manajemen Informatika"));
        listMahasiswa.add(new Mahasiswa("2204509", "Muhammad Fahreza Fauzan", "Laki-laki", "Teknik Informatika"));
        listMahasiswa.add(new Mahasiswa("2205027", "Muhammad Rizki Revandi", "Laki-laki", "Sistem Informasi"));
        listMahasiswa.add(new Mahasiswa("2203484", "Arya Aydin Margono", "Laki-laki", "Teknik Komputer"));
        listMahasiswa.add(new Mahasiswa("2200481", "Marvel Ravindra Dioputra", "Laki-laki", "Manajemen Informatika"));
        listMahasiswa.add(new Mahasiswa("2209889", "Muhammad Fadlul Hafiizh", "Laki-laki", "Teknik Informatika"));
        listMahasiswa.add(new Mahasiswa("2206697", "Rifa Sania", "Perempuan", "Sistem Informasi"));
        listMahasiswa.add(new Mahasiswa("2207260", "Imam Chalish Rafidhul Haque", "Laki-laki", "Teknik Komputer"));
        listMahasiswa.add(new Mahasiswa("2204343", "Meiva Labibah Putri", "Perempuan", "Manajemen Informatika"));
    }
}