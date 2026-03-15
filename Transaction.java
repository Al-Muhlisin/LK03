public class Transaction {
    private static int transactionCounter = 1;

    private final String transactionId;
    private final Product[] items;
    private final int[] quantities;
    private int itemCount;

    public Transaction() {
        this("TRX-" + transactionCounter++, 50);
    }

    public Transaction(String transactionId, int maxItems) {
        this.transactionId = transactionId;
        this.items = new Product[maxItems];
        this.quantities = new int[maxItems];
        this.itemCount = 0;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void addItem(Product item) {
        addItem(item, 1);
    }

    public void addItem(Product item, int quantity) {
        if (item == null || quantity <= 0) {
            return;
        }

        int existingIndex = findItemIndex(item.getProductId());
        if (existingIndex != -1) {
            quantities[existingIndex] += quantity;
            return;
        }

        if (itemCount >= items.length) {
            System.out.println("Transaksi penuh. Tidak bisa menambah item lagi.");
            return;
        }

        items[itemCount] = item;
        quantities[itemCount] = quantity;
        itemCount++;
    }

    public double calculateSubtotal() {
        double subtotal = 0.0;
        for (int i = 0; i < itemCount; i++) {
            subtotal += items[i].getPrice() * quantities[i];
        }
        return subtotal;
    }

    public double calculateTotalDiscount() {
        double totalDiscount = 0.0;
        for (int i = 0; i < itemCount; i++) {
            totalDiscount += items[i].calculateDiscount() * quantities[i];
        }
        return totalDiscount;
    }

    public double calculateTotalPay() {
        double totalPay = 0.0;
        for (int i = 0; i < itemCount; i++) {
            double finalPrice = items[i].getPrice() - items[i].calculateDiscount();
            if (finalPrice < 0) {
                finalPrice = 0;
            }
            totalPay += finalPrice * quantities[i];
        }
        return totalPay;
    }

    public boolean processCheckout() {
        if (itemCount == 0) {
            return false;
        }

        for (int i = 0; i < itemCount; i++) {
            if (quantities[i] > items[i].getStockQuantity()) {
                System.out.println("Stok tidak mencukupi untuk produk: " + items[i].getName());
                return false;
            }
        }

        for (int i = 0; i < itemCount; i++) {
            items[i].updateStock(-quantities[i], "Transaksi " + transactionId);
        }

        return true;
    }

    public void printReceipt() {
        System.out.println("\n===== STRUK PEMBELIAN =====");
        System.out.println("ID Transaksi     : " + transactionId);

        for (int i = 0; i < itemCount; i++) {
            Product item = items[i];
            int qty = quantities[i];
            double price = item.getPrice();
            double discount = item.calculateDiscount();
            double finalPrice = price - discount;
            if (finalPrice < 0) {
                finalPrice = 0;
            }

            System.out.println("- " + item.getName());
            System.out.println("  Qty            : " + qty);
            System.out.printf("  Harga/item     : %.2f%n", price);
            System.out.printf("  Diskon/item    : %.2f%n", discount);
            System.out.printf("  Total item     : %.2f%n", finalPrice * qty);
            System.out.println("  Sisa stok      : " + item.getStockQuantity());
        }

        System.out.printf("Subtotal         : %.2f%n", calculateSubtotal());
        System.out.printf("Total Diskon     : %.2f%n", calculateTotalDiscount());
        System.out.printf("Total Bayar      : %.2f%n", calculateTotalPay());
    }

    private int findItemIndex(String productId) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].getProductId().equalsIgnoreCase(productId)) {
                return i;
            }
        }
        return -1;
    }
}
