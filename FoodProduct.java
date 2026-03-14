public class FoodProduct extends Product {
    private String expiryDate;

    public FoodProduct() {}

    public FoodProduct(String productId, String name, double price, int stockQuantity, String expiryDate) {
        super(productId, name, price, stockQuantity);
        this.expiryDate = expiryDate;
    }

    @Override
    public double calculateDiscount() {
        if (getStockQuantity() > 10) {
            return getPrice() * 0.1; // 10% discount based on price
        } else {
            return 0.0; // no discount
        }
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}