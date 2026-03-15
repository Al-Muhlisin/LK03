import java.util.Scanner;

public class ShopManagementSystem {
    private static final int MAX_PRODUCTS = 100;
    private static final Product[] products = new Product[MAX_PRODUCTS];
    private static int productCount = 0;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            seedInitialData();

            boolean running = true;
            while (running) {
                printMenu();
                int choice = inputInt(scanner, "Pilih menu: ");

                switch (choice) {
                    case 1 -> addProduct(scanner);
                    case 2 -> showAllProducts();
                    case 3 -> updateProductStock(scanner);
                    case 4 -> processTransaction(scanner);
                    case 0 -> {
                        running = false;
                        System.out.println("Terima kasih telah menggunakan sistem Filkom Mart.");
                    }
                    default -> System.out.println("Menu tidak tersedia.");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== FILKOM MART - SHOP MANAGEMENT SYSTEM =====");
        System.out.println("1. Tambah Produk");
        System.out.println("2. Tampilkan Semua Produk");
        System.out.println("3. Update Stok Produk");
        System.out.println("4. Simulasi Transaksi Penjualan");
        System.out.println("0. Keluar");
    }

    private static void addProduct(Scanner scanner) {
        if (productCount >= MAX_PRODUCTS) {
            System.out.println("Kapasitas produk penuh.");
            return;
        }

        System.out.println("\n--- Tambah Produk ---");
        System.out.println("1. Food Product");
        System.out.println("2. Electronic Product");
        System.out.println("3. Clothing Product");

        int type = inputInt(scanner, "Pilih jenis produk: ");

        String id = inputString(scanner, "Masukkan ID produk: ");
        if (findProductIndexById(id) != -1) {
            System.out.println("ID produk sudah digunakan.");
            return;
        }

        String name = inputString(scanner, "Masukkan nama produk: ");
        double price = inputDouble(scanner, "Masukkan harga produk: ");
        int stock = inputInt(scanner, "Masukkan stok awal: ");

        Product newProduct;
        switch (type) {
            case 1 -> {
                String expiryDate = inputString(scanner, "Masukkan tanggal kedaluwarsa: ");
                newProduct = new FoodProduct(id, name, price, stock, expiryDate);
            }
            case 2 -> {
                String warrantyPeriod = inputString(scanner, "Masukkan masa garansi: ");
                newProduct = new ElectronicProduct(id, name, price, stock, warrantyPeriod);
            }
            case 3 -> {
                String size = inputString(scanner, "Masukkan ukuran (S/M/L/XL): ");
                String brand = inputString(scanner, "Masukkan merek: ");
                newProduct = new ClothingProduct(id, name, price, stock, size, brand);
            }
            default -> {
                System.out.println("Jenis produk tidak valid.");
                return;
            }
        }

        products[productCount] = newProduct;
        productCount++;
        System.out.println("Produk berhasil ditambahkan.");
    }

    private static void showAllProducts() {
        System.out.println("\n--- Daftar Produk ---");
        if (productCount == 0) {
            System.out.println("Belum ada produk.");
            return;
        }

        for (int i = 0; i < productCount; i++) {
            Product product = products[i];
            System.out.printf("%d. ", i + 1);
            product.getProductInfo();
            System.out.printf("   Diskon per item: %.2f%n", product.calculateDiscount());

            if (product instanceof FoodProduct food) {
                System.out.println("   Expired Date: " + food.getExpiryDate());
            } else if (product instanceof ElectronicProduct electronic) {
                System.out.println("   Garansi: " + electronic.getWarrantyPeriod());
            }
        }
    }

    private static void updateProductStock(Scanner scanner) {
        System.out.println("\n--- Update Stok Produk ---");
        String id = inputString(scanner, "Masukkan ID produk: ");
        int index = findProductIndexById(id);

        if (index == -1) {
            System.out.println("Produk tidak ditemukan.");
            return;
        }

        Product product = products[index];
        int quantity = inputInt(scanner, "Masukkan jumlah perubahan stok (+/-): ");

        if (product.getStockQuantity() + quantity < 0) {
            System.out.println("Stok tidak boleh negatif.");
            return;
        }

        String useReason = inputString(scanner, "Tambahkan alasan? (y/n): ");
        if (useReason.equalsIgnoreCase("y")) {
            product.updateStock(quantity, inputString("Alasan: "));
        } else {
            product.updateStock(quantity);
        }
    }

    private static void processTransaction(Scanner scanner) {
        System.out.println("\n--- Simulasi Transaksi ---");
        if (productCount == 0) {
            System.out.println("Stok produk kosong.");
            return;
        }

        Transaction transaction = new Transaction();

        while (true) {
            String id = inputString(scanner, "Masukkan ID produk: ");
            int index = findProductIndexById(id);

            if (index == -1) {
                System.out.println("Produk tidak ditemukan.");
            } else {
                Product p = products[index];
                int qty = inputInt(scanner, "Jumlah beli: ");

                if (qty > 0 && qty <= p.getStockQuantity()) {
                    // Mendukung Overloading addItem
                    if (qty == 1) transaction.addItem(p);
                    else transaction.addItem(p, qty);
                    System.out.println("Berhasil ditambah ke keranjang.");
                } else {
                    System.out.println("Gagal. Stok tidak cukup atau input salah.");
                }
            }

            if (!inputString(scanner, "Tambah lagi? (y/n): ").equalsIgnoreCase("y")) break;
        }

        if (transaction.getItemCount() > 0 && transaction.processCheckout()) {
            System.out.printf("Total Bayar: %.2f%n", transaction.processSale());
            transaction.printReceipt();
        }
    }

    private static int findProductIndexById(String id) {
        for (int i = 0; i < productCount; i++) {
            if (products[i].getProductId().equalsIgnoreCase(id)) return i;
        }
        return -1;
    }

    private static int inputInt(Scanner s, String p) {
        System.out.print(p);
        try { return Integer.parseInt(s.nextLine()); } catch (Exception e) { return 0; }
    }
    private static double inputDouble(Scanner s, String p) {
        System.out.print(p);
        try { return Double.parseDouble(s.nextLine()); } catch (Exception e) { return 0; }
    }
    private static String inputString(Scanner s, String p) {
        System.out.print(p);
        return s.nextLine();
    }
    private static String inputString(String p) {
        System.out.print(p);
        return new Scanner(System.in).nextLine();
    }

    private static void seedInitialData() {
        products[productCount++] = new FoodProduct("F001", "Susu UHT", 18000, 20, "12/09/2026");
        products[productCount++] = new ElectronicProduct("E001", "Kipas Angin", 550000, 8, "12 bulan");
        products[productCount++] = new ClothingProduct("C001", "Kaos Polos", 120000, 15, "L", "Filkom Wear");
    }
}
