Program KlinikKecantikan

// Deklarasi Variabel Global
array pelangganData
boolean programBerjalan = true

// Struktur Data Pelanggan
class Pelanggan
    string nama
    string alamat
    integer nomor
    integer kodeLayanan
    integer jenisPelanggan
    integer jumlahKunjungan

// Program Utama
Prosedur Main()
    Selama programBerjalan
        TampilMenu()
    
Prosedur TampilMenu()
    Tampilkan "MENU KLINIK PERAWATAN KECANTIKAN FIFI SKIN CLINIC"
    Tampilkan "[1] Tampilkan Data Pelanggan"
    Tampilkan "[2] Tambah Data Pelanggan"
    Tampilkan "[3] Ubah Data Pelanggan"
    Tampilkan "[4] Hapus Data Pelanggan"
    Tampilkan "[5] Selesai"
    Input pilihan
    
    Jika pilihan = 1 maka
        TampilkanData()
    Jika pilihan = 2 maka
        TambahData()
    Jika pilihan = 3 maka
        UbahData()
    Jika pilihan = 4 maka
        HapusData()
    Jika pilihan = 5 maka
        programBerjalan = false
    
Prosedur TambahData()
    Input nomor
    Input nama
    Input alamat
    Tampilkan "Pilih Layanan:"
    Tampilkan "1. Mediflex"
    Tampilkan "2. Hifu"
    Tampilkan "3. Laser Flex"
    Tampilkan "4. Skin Booster"
    Input kodeLayanan
    Input jenisPelanggan
    Input jumlahKunjungan
    
    Tambahkan ke pelangganData
    
Prosedur TampilkanData()
    Untuk setiap halaman
        Tampilkan header laporan
        Untuk setiap 3 data dalam pelangganData
            Hitung hargaLayanan berdasar kodeLayanan
            Hitung diskon jika pelanggan VIP
            Hitung totalPembayaran
            Tampilkan data pelanggan
        Tampilkan subtotal halaman
        Tanya lanjut ke halaman berikutnya
        
Prosedur UbahData()
    TampilkanData()
    Input nomorPelanggan
    Input data baru
    Update pelangganData
    
Prosedur HapusData()
    TampilkanData()
    Input nomorPelanggan
    Hapus dari pelangganData
