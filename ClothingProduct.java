public class ClothingProduct extends Product {
    String size;
    String brand;

    @Override
    public double calculateDiscount() {
        if (size.equals("L") || size.equals("XL")) {
            return getPrice() * 0.15;
        } else {
            return 0.0;

        }
    }
}
