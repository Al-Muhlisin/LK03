public class ElectronicProduct extends Product {
    private String warrantyPeriod;

    public ElectronicProduct() {}

    public ElectronicProduct(String productId, String name, double price, int stockQuantity, String warrantyPeriod) {
        super(productId, name, price, stockQuantity);
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public double calculateDiscount() {
        double discountPercent = 0.05;
        if (getPrice() > 500000) {
            discountPercent += 0.02; 
        }
        return getPrice() * discountPercent;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
    @Override
    public void getProductInfo() {
        super.getProductInfo();
        System.out.printf("   Detail Elektronik -> Garansi: %s%n", warrantyPeriod);
    }
}