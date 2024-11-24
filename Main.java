import java.util.ArrayList;
import java.util.Scanner;

class Menu {
    String name;
    double price;
    String category;

    Menu(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}

public class Main {
    static ArrayList<Menu> menuList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Inisialisasi menu
        initializeMenu();

        while (true) {
            System.out.println("\n==== Aplikasi Restoran ====");
            System.out.println("1. Menu Pelanggan");
            System.out.println("2. Manajemen Menu");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            switch (choice) {
                case 1 -> pelangganMenu();
                case 2 -> manageMenu();
                case 3 -> {
                    System.out.println("Terima kasih telah menggunakan aplikasi restoran!");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // Inisialisasi menu default
    static void initializeMenu() {
        menuList.add(new Menu("Nasi Goreng", 25000, "Makanan"));
        menuList.add(new Menu("Ayam Goreng", 30000, "Makanan"));
        menuList.add(new Menu("Mie Goreng", 20000, "Makanan"));
        menuList.add(new Menu("Sate Ayam", 35000, "Makanan"));
        menuList.add(new Menu("Es Teh", 5000, "Minuman"));
        menuList.add(new Menu("Es Jeruk", 7000, "Minuman"));
        menuList.add(new Menu("Kopi Hitam", 10000, "Minuman"));
        menuList.add(new Menu("Teh Hangat", 5000, "Minuman"));
    }

    // Menu pelanggan
    static void pelangganMenu() {
        ArrayList<Menu> pesanan = new ArrayList<>();
        while (true) {
            System.out.println("\n==== Menu Pelanggan ====");
            tampilkanMenu();
            System.out.println("Ketik 'selesai' untuk menyelesaikan pemesanan.");
            System.out.print("Pilih menu: ");
            String pilihan = scanner.nextLine();

            if (pilihan.equalsIgnoreCase("selesai")) {
                break;
            }

            Menu item = cariMenu(pilihan);
            if (item != null) {
                pesanan.add(item);
                System.out.println(item.name + " telah ditambahkan ke pesanan.");
            } else {
                System.out.println("Menu tidak ditemukan. Silakan coba lagi.");
            }
        }

        if (!pesanan.isEmpty()) {
            prosesPesanan(pesanan);
        } else {
            System.out.println("Pesanan kosong.");
        }
    }

    // Menampilkan menu restoran
    static void tampilkanMenu() {
        System.out.println("=== Makanan ===");
        for (Menu item : menuList) {
            if (item.category.equals("Makanan")) {
                System.out.println(item.name + " - Rp " + item.price);
            }
        }
        System.out.println("\n=== Minuman ===");
        for (Menu item : menuList) {
            if (item.category.equals("Minuman")) {
                System.out.println(item.name + " - Rp " + item.price);
            }
        }
    }

    // Mencari menu berdasarkan nama
    static Menu cariMenu(String name) {
        for (Menu item : menuList) {
            if (item.name.equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    // Proses pesanan
    static void prosesPesanan(ArrayList<Menu> pesanan) {
        double total = 0;
        System.out.println("\n==== Struk Pesanan ====");
        for (Menu item : pesanan) {
            System.out.println(item.name + " - Rp " + item.price);
            total += item.price;
        }

        double pajak = total * 0.10;
        double biayaPelayanan = 20000;
        total += pajak + biayaPelayanan;

        // Diskon
        if (total > 100000) {
            System.out.println("Diskon 10% diterapkan!");
            total *= 0.90;
        }

        System.out.println("Pajak (10%): Rp " + pajak);
        System.out.println("Biaya Pelayanan: Rp " + biayaPelayanan);
        System.out.println("Total Bayar: Rp " + total);
    }

    // Manajemen menu
    static void manageMenu() {
        while (true) {
            System.out.println("\n==== Manajemen Menu ====");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Ubah Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali");
            System.out.print("Pilih menu: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> tambahMenu();
                case 2 -> ubahMenu();
                case 3 -> hapusMenu();
                case 4 -> {
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // Menambahkan menu baru
    static void tambahMenu() {
        System.out.print("Masukkan nama menu: ");
        String name = scanner.nextLine();
        System.out.print("Masukkan harga menu: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Masukkan kategori (Makanan/Minuman): ");
        String category = scanner.nextLine();

        menuList.add(new Menu(name, price, category));
        System.out.println("Menu berhasil ditambahkan!");
    }

    // Mengubah harga menu
    static void ubahMenu() {
        tampilkanMenu();
        System.out.print("Masukkan nama menu yang ingin diubah: ");
        String name = scanner.nextLine();
        Menu item = cariMenu(name);

        if (item != null) {
            System.out.print("Masukkan harga baru: ");
            item.price = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Harga berhasil diubah!");
        } else {
            System.out.println("Menu tidak ditemukan.");
        }
    }

    // Menghapus menu
    static void hapusMenu() {
        tampilkanMenu();
        System.out.print("Masukkan nama menu yang ingin dihapus: ");
        String name = scanner.nextLine();
        Menu item = cariMenu(name);

        if (item != null) {
            System.out.print("Yakin ingin menghapus? (Ya/Tidak): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("Ya")) {
                menuList.remove(item);
                System.out.println("Menu berhasil dihapus!");
            } else {
                System.out.println("Penghapusan dibatalkan.");
            }
        } else {
            System.out.println("Menu tidak ditemukan.");
        }
    }
}
