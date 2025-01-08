package tugasakhir;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Klinik {
    String nama;
    String alamat;
    int nomor;
    int kodeLayanan;
    int jenisPelanggan;
    int jumlahKunjungan;

    public Klinik(String nama, String alamat, int nomor, int kodeLayanan, int jenisPelanggan, int jumlahKunjungan) {
        this.nama = nama;
        this.alamat = alamat;
        this.nomor = nomor;
        this.kodeLayanan = kodeLayanan;
        this.jenisPelanggan = jenisPelanggan;
        this.jumlahKunjungan = jumlahKunjungan;
    }

    static ArrayList <Klinik> pelangganData = new ArrayList<>();
     static boolean running = true;
    static boolean isRunning = true;
    static Scanner input = new Scanner(System.in);

    static void showMenu() throws IOException {
        System.out.println("========= MENU KLINIK PERAWATAN KECANTIKAN FIFI SKIN CLINIC=========");
        System.out.println("[1] Tampilkan Data Pelanggan");
        System.out.println("[2] Tambah Data Pelanggan");
        System.out.println("[3] Ubah Data Pelanggan");
        System.out.println("[4] Hapus Data Pelanggan");
        System.out.println("[5] Selesai");
        System.out.print("PILIH MENU > ");

        int selectedMenu = Integer.parseInt(input.next());

        switch (selectedMenu) {
            case 1:
                showAll();
                break;
            case 2:
                tambahData();
                break;
            case 3:
                editData();
                break;
            case 4:
                deleteData();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Pilihan Salah!");
        }
    }

   static void showAll() {
    int page = 1;
    int recordsPerPage = 3;
    int totalPages = (int) Math.ceil((double) pelangganData.size() / recordsPerPage);
    int grandTotal = 0;

    if (pelangganData.isEmpty()) {
        System.out.println("\nBelum Ada Data\n");
        return;
    }

    while (true) {
        int subtotal = 0;
        int subtotalHalaman = 0;

        System.out.println("\nLaporan Layanan FIFI SKIN CLINIC");
        System.out.printf("%-100s %-2s\n", "Per 1 Januari 2025", "Hal " + page);
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|    |                 |                |                |                |                 |                  |                   |");
        System.out.println("| No | Nama Pelanggan  | Alamat         | Jenis Layanan  | Harga Layanan  | Jenis Pelanggan | Jumlah Kunjungan | Total Pembayaran  |");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

        int start = (page - 1) * recordsPerPage;
        int end = Math.min(start + recordsPerPage, pelangganData.size());

        for (int i = start; i < end; i++) {
            Klinik pelanggan = pelangganData.get(i);
            String layanan;
            int hargaLayanan;

            switch (pelanggan.kodeLayanan) {
                case 1:
                    hargaLayanan = 600000;
                    layanan = "Mediflex";
                    break;
                case 2:
                    hargaLayanan = 500000;
                    layanan = "Hifu";
                    break;
                case 3:
                    hargaLayanan = 500000;
                    layanan = "Laser Flex";
                    break;
                case 4:
                    hargaLayanan = 2000000;
                    layanan = "Skin Booster";
                    break;
                default:
                    hargaLayanan = 0;
                    layanan = "Tidak Valid";
                    break;
            }

            int diskon = pelanggan.jenisPelanggan == 2 ? 10 * hargaLayanan / 100 : 0;
            int totalPembayaran = (hargaLayanan - diskon) * pelanggan.jumlahKunjungan;
            subtotal += hargaLayanan;
            subtotalHalaman += totalPembayaran;
            System.out.printf("| %-2s | %-15s | %-14s | %-14s | %-14s | %-15s | %-16s | %-17s |\n",
                    (i + 1), pelanggan.nama, pelanggan.alamat, layanan, hargaLayanan, 
                    pelanggan.jenisPelanggan == 1 ? "Reguler" : "VIP", pelanggan.jumlahKunjungan, totalPembayaran);
        }

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-2s | %-15s | %-14s | %-14s | %-14s | %-15s | %-16s | %-17s |\n", "", "Subtotal Hal " + page, "", "", subtotal, "", "", subtotalHalaman);
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        grandTotal += subtotalHalaman;

        if (page < totalPages) {
            System.out.print("\nTekan 'n' untuk halaman berikutnya atau 'q' untuk keluar: ");
        } else {
            System.out.printf("| %-2s | %-15s | %-14s | %-14s | %-14s | %-15s | %-16s | %-17s |\n", "", "Grand Total ", "", "", "", "", "", grandTotal);
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("\n                                                      * Laporan Sudah Habis  *                                                          ");
            System.out.print("\nTekan 'q' untuk keluar: ");
        }

        String jawab = input.next();

        if (jawab.equalsIgnoreCase("n") && page < totalPages) {
            page++;
        } else if (jawab.equalsIgnoreCase("q")) {
            break;
        } else {
            System.out.println("Input tidak valid. Silakan coba lagi.");
        }
    }
}
    static void tambahData() throws IOException {
    String nama ;
    String alamat;
    int nomor;
    int kodeLayanan;
    int jenisPelanggan;
    int jumlahKunjungan;
            while (true) {
            System.out.println("==========================================");
            System.out.print("Nomor Pelanggan                     : ");
            nomor = input.nextInt();
            System.out.print("Nama Pelanggan                      : ");
            nama = input.next();            
            System.out.print("Alamat Pelanggan                    : ");
            alamat = input.next();            
            System.out.println("1.Mediflex\n2.Hifu\n3.Laser Flex\n4.Skin Booster");
            System.out.print("Kode Layanan [1 .. 4]               : ");
            kodeLayanan = input.nextInt();
            System.out.print("Jenis Pelanggan [1=Reguler, 2=VIP]  : ");
            jenisPelanggan = input.nextInt();
            System.out.print("Jumlah Kunjungan [1 .. 10]          : ");
            jumlahKunjungan = input.nextInt();
            System.out.println("==========================================");
            pelangganData.add(new Klinik(nama, alamat, nomor, kodeLayanan, jenisPelanggan, jumlahKunjungan));  
            
            System.out.print("Tambah data lagi? (y/t) > ");
            String jawab = input.next();
            if (jawab.equalsIgnoreCase("t")) {
                break;
            }
        }
    }
    static void editData() throws IOException {
        showAll();
        System.out.print("Pilih nomor pelanggan: ");
        int indexPelanggan = input.nextInt() - 1;
        System.out.print("Nomor Pelanggan Baru : ");
        int nomor = input.nextInt();
        System.out.print("Nama Pelanggan Baru  : ");
        String nama = input.next();        
        System.out.print("Alamat Pelanggan Baru: ");
        String alamat = input.next();
        System.out.print("Kode Layanan [1 .. 4]: ");
        int kodeLayanan = input.nextInt();
        System.out.println("1.Mediflex\n2.Hifu\n3.Laser Flex\n4.Skin Booster");
        System.out.print("Jenis Pelanggan [1=Reguler, 2=VIP]: ");
        int jenisPelanggan = input.nextInt();
        System.out.print("Jumlah Kunjungan [1 .. 10]: ");
        int jumlahKunjungan = input.nextInt();

        pelangganData.set(indexPelanggan, new Klinik(nama, alamat, nomor, kodeLayanan, jenisPelanggan, jumlahKunjungan));
    }
    static void deleteData() throws IOException {
        showAll();
        System.out.print("Pilih Nomor Data: ");
        int indexData = input.nextInt() - 1;
        pelangganData.remove(indexData);
    }
    public static void main(String[] args) throws IOException {
        do {
            showMenu();
        } while (isRunning); }}