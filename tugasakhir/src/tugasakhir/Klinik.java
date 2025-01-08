package tugasakhir;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class Klinik {
    String nama;
    String alamat;
    int nomor;
    int kodeLayanan;
    int jenisPelanggan;
    int jumlahKunjungan;
    public static String BOLD = "\u001B[1m";
    public static String RESET = "\u001B[0m";

    public Klinik(String nama, String alamat, int nomor, int kodeLayanan, int jenisPelanggan, int jumlahKunjungan) {
        this.nama = nama;
        this.alamat = alamat;
        this.nomor = nomor;
        this.kodeLayanan = kodeLayanan;
        this.jenisPelanggan = jenisPelanggan;
        this.jumlahKunjungan = jumlahKunjungan;
    }

    static ArrayList <Klinik> pelangganData = new ArrayList<>();
    static boolean isRunning = true;
    static Scanner input = new Scanner(System.in);

   static void showMenu() throws IOException {
        System.out.println("\n\n\n\n======================================");
        System.out.println("   MENU KLINIK PERAWATAN KECANTIKAN \n\t  FIFI SKIN CLINIC");
        System.out.println("======================================");
        System.out.println("[1] Tampilkan Data Pelanggan");
        System.out.println("[2] Tambah Data Pelanggan");
        System.out.println("[3] Ubah Data Pelanggan");
        System.out.println("[4] Hapus Data Pelanggan");
        System.out.println("[5] Cetak Struk Pelanggan"); 
        System.out.println("[6] Selesai");
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
                cetakStrukPelanggan(); // Panggil metode untuk mencetak struk
                break;
            case 6:
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
        int subtotalHalaman = 0;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd MMMM yyyy ", new Locale("id", "ID"));
        String formattedDate = now.format(formatter);
        
        System.out.println("\nLaporan Layanan FIFI SKIN CLINIC");
        System.out.printf("%-150s %-2s\n", "Per"+formattedDate, "Hal " + page);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|    |                 |                |                |                |                 |                  |                |                |                   |");
        System.out.println("| No | Nama Pelanggan  | Alamat         | Jenis Layanan  | Harga Layanan  | Jenis Pelanggan | Jumlah Kunjungan | Total Harga    | Diskon         | Total Pembayaran  |");
        System.out.println("|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|");

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

            int totalharga = hargaLayanan * pelanggan.jumlahKunjungan;
            int diskon = pelanggan.jenisPelanggan == 2 ? 10 * hargaLayanan / 100  : 0;
            int totalPembayaran = totalharga - diskon;
            subtotalHalaman += totalPembayaran;
            System.out.printf("| %-2s | %-15s | %-14s | %-14s | %-14s | %-15s | %-16s | %-14s | %-14s | %-17s |\n",
                    (i + 1), pelanggan.nama, pelanggan.alamat, layanan, "Rp. "+hargaLayanan, 
                    pelanggan.jenisPelanggan == 1 ? "Reguler" : "VIP", pelanggan.jumlahKunjungan,"Rp. "+totalharga,"Rp. "+diskon, "Rp. "+totalPembayaran);
        }

        System.out.println("|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.printf("| %-2s | %-15s | %-14s | %-14s | %-14s | %-15s | %-16s | %-14s | %-14s | %-17s |\n", "", "Subtotal Hal " + page, "", "","", "", "","","","Rp. "+ subtotalHalaman);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        grandTotal += subtotalHalaman;

        if (page < totalPages) {
            System.out.print("\nTekan 'n' untuk halaman berikutnya atau 'q' untuk keluar: ");
        } else {
            System.out.printf("| %-2s | %-15s | %-14s | %-14s | %-14s | %-15s | %-16s | %-14s | %-14s | %-17s |\n", "", "Grand Total ", "", "", "", "", "","","","Rp. "+ grandTotal);
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("\n                                                                         * Laporan Sudah Habis  *                                                          ");
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
   
    static void cetakStruk(Klinik pelanggan) {
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
    int totalharga = hargaLayanan * pelanggan.jumlahKunjungan;
    int diskon = pelanggan.jenisPelanggan == 2 ? 10 * hargaLayanan / 100  : 0;
    int totalPembayaran = totalharga - diskon;
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("id", "ID"));
    String formattedDate = now.format(formatter);
    System.out.println(BOLD +"\n\n\n\t      FIFI SKIN CLINIC\n    Jl. Paesan Utara, Kec. Kedungwuni,\n    Kabupaten Pekalongan, Jawa Tengah"+RESET);
            System.out.println( "==========================================");
        System.out.println(BOLD + "        *** KWITANSI PEMBAYARAN ***        " + RESET);
            System.out.println( "==========================================" );
            System.out.printf( "%-15s : %s\n","Tanggal",formattedDate );
            System.out.printf("%-15s : %s\n", "Nama", pelanggan.nama);
            System.out.printf("%-15s : %s\n", "Alamat", pelanggan.alamat);
            System.out.printf("%-15s : %s\n", "Jenis Pelanggan", pelanggan.jenisPelanggan == 1 ? "Reguler" : "VIP");
            System.out.println( "==========================================" );
            System.out.printf("%-5s %-5s %-6s\n",layanan,"Rp. "+hargaLayanan+"x"+pelanggan.jumlahKunjungan," Rp. "+totalharga);
            System.out.println( "------------------------------------------" );
            System.out.printf("%-25s : Rp. %d\n", "             Subtotal", totalharga);
            System.out.printf("%-25s : Rp. %d\n", "             Diskon VIP", diskon);
            System.out.printf("%-25s : Rp. %d\n", "             Total", totalPembayaran);
            System.out.println("==========================================");
            System.out.println(BOLD + "     Terima Kasih atas Kunjungan Anda    " + RESET);
}

static void tambahData() throws IOException {
    String nama;
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
        
        Klinik pelangganBaru = new Klinik(nama, alamat, nomor, kodeLayanan, jenisPelanggan, jumlahKunjungan);
        pelangganData.add(pelangganBaru);
        
       
        
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
    static void cetakStrukPelanggan() {
        showAll();
        System.out.print("Pilih nomor pelanggan untuk mencetak struk: ");
        int indexPelanggan = input.nextInt() - 1;

        if (indexPelanggan >= 0 && indexPelanggan < pelangganData.size()) {
            Klinik pelanggan = pelangganData.get(indexPelanggan);
            cetakStruk(pelanggan); 
        } else {
            System.out.println("Nomor pelanggan tidak valid.");
        }
    }
    
    public static void main(String[] args) throws IOException {
        do {
            showMenu();
        } while (isRunning); }}