# Aplikasi Data Siswa

## A. Frame: App
- Title: "Aplikasi Data Siswa"  
- Size: 700 x 500  
- Layout: BorderLayout  
  - BorderLayout adalah layout manager yang membagi tampilan menjadi 5 area: NORTH, SOUTH, EAST, WEST, dan CENTER.  
    - NORTH → Judul
    - WEST → Form input
    - CENTER → Tabel
    - SOUTH → Tombol

- setDefaultCloseOperation(EXIT_ON_CLOSE) → Menutup aplikasi saat window ditutup  
- setLocationRelativeTo(null) → Menampilkan window di tengah layar  
- getContentPane().setBackground(...) → Mengatur warna background utama  

---

## B. Komponen

### 1. Judul & Form Input
- Container: JPanel panelInput  
- Layout: GridLayout (10, 25, 1, 1)  
  - Digunakan untuk menyusun label dan field input dalam grid 10 x 25, dengan jarak tiap selnya sebanyak 1 satuan.

- Isi Komponen:
  - JLabel "NIS"  
    - Label untuk input nomor induk siswa  
  - JTextField tfNis  
    - Input untuk memasukkan NIS  

  - JLabel "Nama"  
    - Label untuk nama siswa  
  - JTextField tfNama  
    - Input untuk memasukkan nama siswa  

  - JLabel "Alamat"  
    - Label untuk alamat siswa  
  - JTextField tfAlamat  
    - Input untuk memasukkan alamat siswa  

- Fungsi Utama:
  - Tempat pengguna memasukkan data siswa sebelum diproses  

---

### 2. Tabel Data
- Komponen: JTable table  
- Model: DefaultTableModel  
- Kolom:
  - NIS → Menyimpan nomor induk siswa  
  - Nama → Menyimpan nama siswa  
  - Alamat → Menyimpan alamat siswa  

- Container: JScrollPane  
  - Agar tabel bisa di-scroll jika data banyak  

- Event Listener:
  - MouseListener (klik baris tabel)
    - Saat baris diklik, data otomatis dimasukkan ke field input (tfNis, tfNama, tfAlamat)  
    - Memudahkan proses update dan delete  

---

### 3. Button
- Container: JPanel panelBtn  

- Daftar Button & Fungsi:
  - JButton btnTambah  
    - Fungsi: Menambahkan data siswa baru  
    - Event: ActionListener → memanggil tambahData()  

  - JButton btnUpdate  
    - Fungsi: Mengupdate data siswa yang dipilih dari tabel  
    - Event: ActionListener → memanggil updateData()  

  - JButton btnDelete  
    - Fungsi: Menghapus data siswa yang dipilih  
    - Event: ActionListener → memanggil deleteData()  

  - JButton btnClear  
    - Fungsi: Mengosongkan semua field input  
    - Event: ActionListener → memanggil clearForm()  

- Styling:
  - Setiap tombol memiliki warna berbeda untuk membedakan fungsi:
    - Tambah → Hijau  
    - Update → Oranye  
    - Delete → Merah  
    - Clear → Biru  

---

## C. Variabel Global

1. JTextField tfNis, tfNama, tfAlamat  
   - Menyimpan input dari user  

2. JTable table  
   - Menampilkan data siswa  

3. DefaultTableModel model  
   - Menyimpan dan mengelola data pada tabel  

4. ArrayList<String[]> dataList  
   - Menyimpan seluruh data siswa di memori  

5. String fileName  
   - Nama file penyimpanan (siswa.csv)  

---

## D. Method

### 1. void loadData()
Method untuk membaca data dari file siswa.csv.

Proses:
- Mengosongkan dataList  
- Reset tabel (model.setRowCount(0))  
- Membuat file jika belum ada  
- Membaca file dengan BufferedReader  
- Parsing data (split dengan koma)  
- Menambahkan ke dataList dan tabel  

---

### 2. void saveData()
Method untuk menyimpan data ke file.

Proses:
- Menggunakan BufferedWriter  
- Menulis data dalam format CSV  
- Setiap baris mewakili satu siswa  

---

### 3. void tambahData()
Method untuk menambahkan data baru.

Proses:
- Ambil input dari form  
- Validasi field tidak boleh kosong  
- Validasi NIS tidak duplikat  
- Tambahkan ke dataList  
- Simpan ke file  
- Reload data  
- Clear form  

---

### 4. void updateData()
Method untuk memperbarui data.

Proses:
- Cek apakah ada baris yang dipilih  
- Ambil input terbaru  
- Update data di dataList  
- Simpan dan reload  
- Clear form  

---

### 5. void deleteData()
Method untuk menghapus data.

Proses:
- Cek baris yang dipilih  
- Hapus dari dataList  
- Simpan dan reload  
- Clear form  

---

### 6. void clearForm()
Method untuk reset input:
- Mengosongkan tfNis, tfNama, tfAlamat  

---

## E. Event Handling

1. ActionListener (Button)
   - btnTambah → tambahData()  
   - btnUpdate → updateData()  
   - btnDelete → deleteData()  
   - btnClear → clearForm()  

2. MouseListener (Table)
   - Klik baris tabel → isi form otomatis  

---
