import java.util.ArrayList;

public class Transaction {
    private static int transactionCounter = 1;
    private final String transactionId;
    private final ArrayList<Product> items;
    private final ArrayList<Integer> quantities;

    public Transaction() {
        this.transactionId = "TRX-" + transactionCounter++;
        this.items = new ArrayList<>();
        this.quantities = new ArrayList<>();
    }

    public void addItem(Product item) {
        addItem(item, 1);
    }

    public void addItem(Product item, int quantity) {
        if (quantity <= 0) return;

        int existingIndex = findItemIndex(item.getProductId());
        if (existingIndex != -1) {
            quantities.set(existingIndex, quantities.get(existingIndex) + quantity);
        } else {
            items.add(item);
            quantities.add(quantity);
        }
    }

    public int getItemCount() {
        return items.size();
    }

    public double calculateSubtotal() {
        double subtotal = 0.0;
        for (int i = 0; i < items.size(); i++) {
            subtotal += items.get(i).getPrice() * quantities.get(i);
        }
        return subtotal;
    }

    public double calculateTotalDiscount() {
        double totalDiscount = 0.0;
        for (int i = 0; i < items.size(); i++) {
            totalDiscount += items.get(i).calculateDiscount() * quantities.get(i);
        }
        return totalDiscount;
    }

    public double calculateTotalPay() {
        double totalPay = 0.0;
        for (int i = 0; i < items.size(); i++) {
            double price = items.get(i).getPrice();
            double discount = items.get(i).calculateDiscount();
            double finalPrice = price - discount;
            if (finalPrice < 0) {
                finalPrice = 0;
            }
            totalPay += finalPrice * quantities.get(i);
        }
        return totalPay;
    }

    public boolean processCheckout() {
        if (getItemCount() == 0) {
            return false;
        }

        for (int i = 0; i < items.size(); i++) {
            if (quantities.get(i) > items.get(i).getStockQuantity()) {
                System.out.println("Stok tidak mencukupi untuk produk: " + items.get(i).getName());
                return false;
            }
        }

        for (int i = 0; i < items.size(); i++) {
            items.get(i).updateStock(-quantities.get(i), "Transaksi " + transactionId);
        }

        return true;
    }

    public double processSale() {
        return calculateTotalPay();
    }

    public void printReceipt() {
        System.out.println("\n===== STRUK PEMBELIAN =====");
        System.out.println("ID Transaksi     : " + transactionId);

        for (int i = 0; i < items.size(); i++) {
            Product item = items.get(i);
            int qty = quantities.get(i);
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
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProductId().equalsIgnoreCase(productId)) {
                return i;
            }
        }
        return -1;
    }
}
