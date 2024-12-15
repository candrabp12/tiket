import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class i {

    // Menggunakan ArrayList untuk daftar konser
    static ArrayList<String> concertNames = new ArrayList<>();
    static ArrayList<Integer> availableTickets = new ArrayList<>();
    static ArrayList<Integer> ticketPrices = new ArrayList<>();

    // Daftar tiket yang dipesan
    static ArrayList<String> bookedConcerts = new ArrayList<>();
    static ArrayList<Integer> bookedTickets = new ArrayList<>();
    static ArrayList<Integer> totalPrices = new ArrayList<>();

    // File untuk menyimpan database pemesanan tiket
    private static final String DATABASE_FILE = "bookingDatabase.txt";
    private static final String ADMIN_PIN = "1234"; // PIN untuk akses menu admin

    public static void main(String[] args) {
        initializeConcerts(); // Inisialisasi data konser
        Scanner scanner = new Scanner(System.in);
        System.out.println("=======================================================");
        System.out.println("===  Selamat Datang Di Ulang Tahun Ke-10 Unesa 5     ===");
        System.out.println("=   Dengan pertunjukan konser yang sangat menarik     =");
        System.out.println("= Diantaranya: Bernadya, Dewa19, Luckyjuicy, Mahalini =");
        System.out.println("=======================================================");

        System.out.println("=======            NAMA PEMBUAT                ======");
        System.out.println("= -Anton                                            =");
        System.out.println("= -Amarrazan                                        =");
        System.out.println("= -Bagas                                            =");
        System.out.println("= -Bagus                                            =");
        System.out.println("= -Bintang                                          =");
        System.out.println("= -Dedi                                             =");
        System.out.println("= -Desta                                            =");
        System.out.println("= -Fandy                                            =");

        while (true) { // Program terus berjalan
            displayMainMenu(); // Menampilkan menu utama
            int menuChoice = tryCatchInput(scanner);
            switch (menuChoice) {
                case 1: // Lihat daftar konser
                    displayConcerts();
                    break;
                case 2: // Pesan tiket
                    bookTickets(scanner);
                    break;
                case 3: // Lihat tiket yang sudah dipesan
                    displayBookedTickets();
                    break;
                case 4: // Menu Admin
                    adminMenu(scanner);
                    break;
                case 5: // Keluar dan beralih ke menu utama
                    System.out.println("\n==================================================");
                    System.out.println("terimakasih telah memesan Tiket  dan sampai jumpa...");
                    System.out.println("==================================================\n");
                    break;
                default:
                    System.out.println("Menu yang anda pilih salah. Silakan coba lagi.");
            }
        }
    }

    // Inisialisasi daftar konser menggunakan ArrayList
    private static void initializeConcerts() {
        concertNames.add("Konser Panggung A");
        concertNames.add("Konser Panggung B");
        concertNames.add("Konser Panggung C");

        availableTickets.add(50);
        availableTickets.add(75);
        availableTickets.add(100);

        ticketPrices.add(500000);
        ticketPrices.add(750000);
        ticketPrices.add(1000000);
    }

    // Menampilkan menu utama
    private static void displayMainMenu() {
        System.out.println("\n===================");
        System.out.println("     Menu Utama:     ");
        System.out.println("=====================");
        System.out.println("1. Lihat Daftar Konser");
        System.out.println("2. Pesan Tiket");
        System.out.println("3. Lihat Tiket yang Dipesan");
        System.out.println("4. Dasboard Admin");
        System.out.println("5. Keluar");
        System.out.print("Pilih menu: ");
    }

    // Menampilkan daftar konser
    private static void displayConcerts() {
        System.out.println("\n============");
        System.out.println("Daftar Konser:");
        System.out.println("==============");
        //ARITMATIKA,REASIONAL
        for (int i = 0; i < concertNames.size(); i++) {
            System.out.println((i + 1) + ". " + concertNames.get(i) +
                    " (Tiket tersedia: " + availableTickets.get(i) +
                    ", Harga: Rp" + ticketPrices.get(i) + ")");
        }
    }

    // Memesan tiket
    private static void bookTickets(Scanner scanner) {
        while (true) {
            displayConcerts(); // Menampilkan daftar konser
            System.out.println("\nMasukkan nama Anda (atau ketik 'keluar' untuk kembali ke menu utama): ");
            scanner.nextLine(); // Menghapus newline dari input sebelumnya
            // RELASIONAL
            String customerName = scanner.nextLine();
            if (customerName.equalsIgnoreCase("keluar")) {
                System.out.println("Pemesanan dibatalkan. Kembali ke menu utama.");
                return; // Kembali ke menu utama
            }

            System.out.println("\nPilih konser dengan memasukkan nomor (atau 0 untuk keluar): ");
            int choice = tryCatchInput(scanner);
            
            //RELASIONAL
            if (choice == 0) {
                System.out.println("Pemesanan dibatalkan. Kembali ke menu utama.");
                return; // Kembali ke menu utama
            }
            //LOGIKA
            if (choice < 1 || choice > concertNames.size()) {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                continue;
            } else {
                System.out.println("Pilihan valid. Memproses pemesanan...");
            }
            //ARITMETIKA ,METHOD CALL OPERTOR,PENUGASAN
            int concertIndex = choice - 1;
            System.out.println("Anda memilih: " + concertNames.get(concertIndex));
            System.out.println("Berapa tiket yang ingin Anda pesan?");
            int ticketCount = tryCatchInput(scanner);

            //RELASIONAL
            if (ticketCount <= 0) {
                System.out.println("Jumlah tiket harus lebih dari 0. Silakan coba lagi.");
                continue;
            }

            //RELASIONAL
            if (ticketCount > availableTickets.get(concertIndex)) {
                System.out.println("Maaf, tiket yang tersedia tidak mencukupi. Coba jumlah lebih kecil.");
                continue;
            }
            // MEMPROSE PEMESANAN
            // OPERTOR ARITMETIKA
            int totalPrice = ticketCount * ticketPrices.get(concertIndex);
            availableTickets.set(concertIndex, availableTickets.get(concertIndex) - ticketCount);

            // Menyimpan detail pemesanan ke dalam file (database)
            saveBookingToFile(customerName, concertNames.get(concertIndex), ticketCount, totalPrice);

            bookedConcerts.add(concertNames.get(concertIndex));
            bookedTickets.add(ticketCount);
            totalPrices.add(totalPrice);
            // OPERATOR PENUGASAN
            System.out.println("=================================================================================");
            System.out.println("Pemesanan berhasil! Anda memesan " +
                    ticketCount + " tiket untuk " + concertNames.get(concertIndex) +
                    ". Total harga: Rp" + totalPrice);
            System.out.println("=================================================================================");
            break; // Keluar dari loop setelah pemesanan berhasil
        }
    }

    // Menampilkan tiket yang telah dipesan

    //FUNGSI DAN STRING
    private static void displayBookedTickets() {
        if (bookedConcerts.isEmpty()) {
            System.out.println("\nAnda belum memesan tiket apa pun.");
            return;
        }

        System.out.println("\n===========================");
        System.out.println("Tiket yang Telah Dipesan:");
        System.out.println("===========================");
        // OPERATOR ARITMETIKA,RELASIONAL
        for (int i = 0; i < bookedConcerts.size(); i++) {
            System.out.println((i + 1) + ". Konser: " + bookedConcerts.get(i) +
                    " | Jumlah Tiket: " + bookedTickets.get(i) +
                    " | Total Harga: Rp" + totalPrices.get(i));
        }
    }
    // Menyimpan pemesanan ke dalam file
    private static void saveBookingToFile(String customerName, String concertName, int ticketCount, int totalPrice) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE_FILE, true));
            writer.write("Nama Pembeli: " + customerName + " | Konser: " + concertName +
                    " | Jumlah Tiket: " + ticketCount + " | Total Harga: Rp" + totalPrice);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error menulis ke database: " + e.getMessage());
        }
    }

    // Menu Admin untuk melihat semua pemesanan
    private static void adminMenu(Scanner scanner) {
        System.out.println("\nMasukkan PIN untuk akses menu Admin: ");
        scanner.nextLine(); // Membersihkan input newline
        String enteredPin = scanner.nextLine(); // Membaca input PIN dari user

        if (enteredPin.equals(ADMIN_PIN)) {
            System.out.println("\n===========================");
            System.out.println("Semua Pemesanan Tiket:");
            System.out.println("===========================");
            try {
                BufferedReader reader = new BufferedReader(new FileReader(DATABASE_FILE));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                reader.close();
            } catch (IOException e) {
                System.out.println("Error membaca database: " + e.getMessage());
            }
        } else {
            System.out.println("\nPIN salah. Akses ditolak.");
        }
    }

    // Rekursi untuk menangani input angka yang salah
    // TRYCATCHINPUT
    private static int tryCatchInput(Scanner scanner) {
        try {
            int input = scanner.nextInt();
            System.out.println(input >= 0 ? "Input valid." : "input tidak negatif.");// TERNARY
            return input;
        } catch (Exception e) {
            scanner.next(); // Membersihkan input yang salah
            System.out.println("Input tidak valid. Masukkan angka: ");
            return tryCatchInput(scanner); // Memanggil kembali fungsi untuk mencoba lagi
        }
    }
}
