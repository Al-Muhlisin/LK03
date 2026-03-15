public class ClothingProduct extends Product {
    private String size;
    private String brand;

    public ClothingProduct() {}

    public ClothingProduct(String productId, String name, double price, int stockQuantity, String size, String brand) {
        super(productId, name, price, stockQuantity);
        this.size = size;
        this.brand = brand;
    }

    @Override
    public double calculateDiscount() {
        if ("L".equalsIgnoreCase(size) || "XL".equalsIgnoreCase(size)) {
            return getPrice() * 0.15;
        } else {
            return 0.0;

        }
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public void getProductInfo() {
        super.getProductInfo();
        System.out.printf("   Detail Pakaian -> Size: %s | Brand: %s%n", size, brand);
    }
}
