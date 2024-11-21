# E-Surat 📄  

**E-Surat** adalah aplikasi manajemen dokumen berbasis Java yang dirancang untuk mempermudah pengelolaan surat antara mahasiswa dan dosen. Aplikasi ini menawarkan fitur-fitur seperti pengiriman surat, tagging, dan pengelolaan status surat untuk meningkatkan efisiensi dan transparansi.

---

## 📋 Fitur Utama  
- **Pengiriman Surat**  
  Mahasiswa dapat mengirim berbagai jenis surat, seperti surat konsul, surat maaf, atau surat pertemuan.  
- **Tagging Surat**  
  Menandai surat dengan tag seperti *Penting* atau *Tidak Penting* untuk membantu dosen memprioritaskan peninjauan.  
- **Pengaturan Status Surat**  
  Mengubah status surat menjadi *Pending*, *Diterima*, atau *Ditolak*.  
- **Melihat Surat Bertipe PDF**
  Memungkinkan User untuk membuka file PDF pada surat yang dikirim, di netbeans menurut saya ini sangat sulit diterapkan, jadi saya membuat ini menjadi fitur program kami.

---

## 📖 Panduan Penggunaan  

Aplikasi **E-Surat** dirancang untuk mempermudah pengelolaan surat elektronik antara mahasiswa dan dosen. Berikut adalah panduan langkah demi langkah untuk menggunakan aplikasi ini.

### 1. Login  
Untuk menggunakan aplikasi, pengguna harus login terlebih dahulu dengan kredensial yang sesuai.

- **Login Mahasiswa**:  
  Mahasiswa akan diminta untuk memasukkan **NIM** dan **Password** yang telah terdaftar dalam sistem.

- **Login Dosen**:  
  Dosen harus memasukkan **NIP** dan **Password** yang sesuai untuk mengakses aplikasi dan melihat surat yang diterima.

Jika kredensial yang dimasukkan benar, pengguna akan diarahkan ke menu utama sesuai dengan peran (mahasiswa atau dosen).

### 2. Pengelolaan Surat  

#### 2.1. Mahasiswa
- **Kirim Surat**:  
  Mahasiswa dapat mengirimkan surat ke dosen dengan memilih jenis surat yang diinginkan (misalnya: Surat Konsul, Surat Maaf, atau Surat Pertemuan).  
  Setelah memilih jenis surat, mahasiswa dapat mengisi **isi surat**, memilih **dosen** yang akan menerima surat, dan meng-upload **file PDF** surat.  
  Tekan tombol **Submit** untuk mengirimkan surat.

- **Lihat Surat**:  
  Mahasiswa dapat melihat status surat yang telah dikirim (misalnya: diterima, ditolak, atau masih pending).  
  Mahasiswa juga dapat mengedit surat yang sudah dikirim dengan memilih surat, kemudian mengubah isi atau file surat yang telah diunggah.  

- **Tagging Surat**:  
  Mahasiswa dapat memberi tag pada surat untuk menandainya sebagai **Penting** atau **Tidak Penting**.  
  Pilih surat yang ingin diberi tag, kemudian klik tombol **Penting** atau **Tidak Penting** di menu yang tersedia.

#### 2.2. Dosen
- **Lihat Surat**:  
  Dosen dapat melihat surat yang diterima dari mahasiswa di dalam menu **Lihat Surat**.  
  Dosen dapat melihat isi surat dan memutuskan apakah surat diterima atau ditolak.

- **Terima atau Tolak Surat**:  
  Dosen dapat memilih untuk **Menerima** atau **Menolak** surat dari mahasiswa.  
  Jika surat diterima, status surat akan diperbarui menjadi **Diterima**. Jika ditolak, status surat akan diperbarui menjadi **Ditolak**.

### 3. Logout  
Setelah selesai menggunakan aplikasi, pastikan untuk keluar (logout) dari aplikasi agar data tetap aman dan terjaga.  
Cukup klik tombol **Logout** yang tersedia di menu utama.

---

## 🖥️ Teknologi yang Digunakan  
- **Java Swing**: Untuk pengembangan antarmuka pengguna.  
- **MySQL**: Database untuk menyimpan data surat.  
- **MV Pattern**: Struktur kode yang memisahkan logika frontend dan backend 
