public abstract class Product {
    private String productId;
    private String name;
    private double price;
    private int stockQuantity;

    public Product() {}

    public Product(String productId, String name, double price, int stockQuantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public abstract double calculateDiscount();
    
    public void updateStock(int quantity) {
        this.stockQuantity += quantity;
    }
    public void updateStock(int quantity, String reason) {
        this.stockQuantity += quantity;
        System.out.println("Stok diperbarui karena " + reason);
    }
    
    public String getName() {
         return name; 
    }
    public String getProductId() {
        return productId;
    }
    public double getPrice() { 
        return price; 
    }
    public int getStockQuantity() { 
        return stockQuantity; 
    }
    
    public void getProductInfo() {
        System.out.printf("ID: %s | Nama: %s | Harga: %.2f | Stok: %d%n", 
                          productId, name, price, stockQuantity);
    }
}