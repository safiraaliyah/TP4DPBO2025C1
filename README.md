# TP4DPBO2025C1
## JANJI

---

## Desain Program

### 1. **Struktur Kelas**
Program terdiri dari dua kelas utama:
- **`Menu`**: Kelas ini bertanggung jawab untuk menangani tampilan GUI dan logika aplikasi.
- **`Mahasiswa`**: Kelas ini merepresentasikan entitas mahasiswa dengan atribut-atribut seperti NIM, nama, jenis kelamin, dan program studi.

### 2. **Komponen GUI**
Program menggunakan komponen-komponen Swing untuk membangun antarmuka pengguna. Berikut adalah komponen utama yang digunakan:
- **`JTextField`**: Digunakan untuk input NIM dan Nama.
- **`JComboBox`**: Digunakan untuk memilih Jenis Kelamin dan Program Studi.
- **`JTable`**: Digunakan untuk menampilkan data mahasiswa dalam bentuk tabel.
- **`JButton`**: Digunakan untuk operasi Add/Update, Cancel, dan Delete.
- **`JLabel`**: Digunakan untuk menampilkan teks seperti judul dan label input.
- **`JPanel`**: Digunakan sebagai container untuk mengatur tata letak komponen GUI.

### 3. **Tata Letak (Layout)**
Program menggunakan `GridBagLayout` untuk mengatur tata letak komponen GUI. `GridBagLayout` dipilih karena fleksibilitasnya dalam mengatur posisi dan ukuran komponen. Berikut adalah penjelasan tata letak:
- **Judul**: Ditempatkan di bagian atas dengan lebar penuh (span 3 kolom).
- **Input NIM**: Label "NIM" ditempatkan di kolom pertama, field input di kolom kedua, dan tombol "Add/Update" di kolom ketiga.
- **Input Nama**: Label "Nama" ditempatkan di kolom pertama, field input di kolom kedua, dan tombol "Cancel" di kolom ketiga.
- **Input Jenis Kelamin**: Label "Jenis Kelamin" ditempatkan di kolom pertama, combo box di kolom kedua, dan tombol "Delete" di kolom ketiga.
- **Input Program Studi**: Label "Program Studi" ditempatkan di kolom pertama, combo box di kolom kedua.
- **Tabel Mahasiswa**: Ditempatkan di bawah form input dengan lebar penuh (span 3 kolom).

---

### Alur Program `Menu.java`

1. **Inisialisasi Data dan GUI**:
   - Saat program dijalankan, konstruktor `Menu()` dipanggil. Di dalam konstruktor ini, list `listMahasiswa` diinisialisasi dan diisi dengan data dummy menggunakan metode `populateList()`.
   - Komponen GUI seperti `JTextField`, `JComboBox`, `JButton`, dan `JTable` diinisialisasi dan diatur tata letaknya menggunakan `GridBagLayout`.
   - Tabel mahasiswa diisi dengan data dari `listMahasiswa` menggunakan metode `setTable()`.

2. **Menambahkan Data Mahasiswa**:
   - Pengguna mengisi form dengan NIM, Nama, Jenis Kelamin, dan Program Studi.
   - Saat tombol "Add" ditekan, metode `insertData()` dipanggil.
     - Metode ini mengambil nilai dari `nimField`, `namaField`, `jenisKelaminComboBox`, dan `programStudiComboBox`.
     - Jika semua field terisi, data mahasiswa baru ditambahkan ke `listMahasiswa`.
     - Tabel diperbarui dengan memanggil `setTable()`.
     - Form dibersihkan menggunakan `clearForm()`.
     - Pesan sukses ditampilkan menggunakan `JOptionPane`.

3. **Mengupdate Data Mahasiswa**:
   - Pengguna memilih baris di tabel untuk mengedit data mahasiswa.
   - Data dari baris yang dipilih dimuat ke dalam form.
   - Tombol "Add" berubah menjadi "Update".
   - Saat tombol "Update" ditekan, metode `updateData()` dipanggil.
     - Metode ini mengambil nilai dari form dan memvalidasi apakah semua field terisi.
     - Data mahasiswa di `listMahasiswa` diperbarui berdasarkan `selectedIndex`.
     - Tabel diperbarui dengan memanggil `setTable()`.
     - Form dibersihkan menggunakan `clearForm()`.
     - Pesan sukses ditampilkan menggunakan `JOptionPane`.

4. **Menghapus Data Mahasiswa**:
   - Pengguna memilih baris di tabel untuk menghapus data mahasiswa.
   - Saat tombol "Delete" ditekan, metode `deleteData()` dipanggil.
     - Metode ini menampilkan dialog konfirmasi menggunakan `JOptionPane`.
     - Jika pengguna memilih "Yes", data mahasiswa dihapus dari `listMahasiswa` berdasarkan `selectedIndex`.
     - Tabel diperbarui dengan memanggil `setTable()`.
     - Form dibersihkan menggunakan `clearForm()`.
     - Pesan sukses ditampilkan menggunakan `JOptionPane`.

5. **Membersihkan Form**:
   - Saat tombol "Cancel" ditekan, metode `clearForm()` dipanggil.
     - Metode ini mengosongkan semua field di form (`nimField`, `namaField`, `jenisKelaminComboBox`, dan `programStudiComboBox`).
     - Tombol "Update" dikembalikan ke "Add".
     - Tombol "Delete" disembunyikan.
     - `selectedIndex` direset ke `-1`.

6. **Mengisi Tabel dengan Data**:
   - Metode `setTable()` digunakan untuk mengisi tabel dengan data dari `listMahasiswa`.
     - Kolom tabel diatur dengan nama "No", "NIM", "Nama", "Jenis Kelamin", dan "Program Studi".
     - Data dari `listMahasiswa` diiterasi dan dimasukkan ke dalam tabel.

7. **Menangani Interaksi Pengguna**:
   - Saat pengguna mengklik baris di tabel, metode `mousePressed()` dipanggil.
     - Data dari baris yang dipilih dimuat ke dalam form.
     - Tombol "Add" berubah menjadi "Update".
     - Tombol "Delete" ditampilkan.

---

### Penjelasan Metode Utama

1. **`insertData()`**:
   - Digunakan untuk menambahkan data mahasiswa baru ke dalam `listMahasiswa`.
   - Memvalidasi apakah semua field terisi sebelum menambahkan data.
   - Memperbarui tabel dan membersihkan form setelah data ditambahkan.

2. **`updateData()`**:
   - Digunakan untuk memperbarui data mahasiswa yang sudah ada di `listMahasiswa`.
   - Memvalidasi apakah semua field terisi sebelum memperbarui data.
   - Memperbarui tabel dan membersihkan form setelah data diperbarui.

3. **`deleteData()`**:
   - Digunakan untuk menghapus data mahasiswa dari `listMahasiswa`.
   - Menampilkan dialog konfirmasi sebelum menghapus data.
   - Memperbarui tabel dan membersihkan form setelah data dihapus.

4. **`clearForm()`**:
   - Digunakan untuk mengosongkan semua field di form dan mengembalikan tombol ke keadaan awal.

5. **`setTable()`**:
   - Digunakan untuk mengisi tabel dengan data dari `listMahasiswa`.
   - Mengembalikan `DefaultTableModel` yang berisi data mahasiswa.

6. **`populateList()`**:
   - Digunakan untuk mengisi `listMahasiswa` dengan data dummy saat program pertama kali dijalankan.

---

### Dokumentasi Program

