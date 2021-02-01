package other;

import product.Category;

public class ProductDB {
    private int productID;
    private String name;
    private int rating;
    private double price;
    private Category category;
    private int quantity;

    public ProductDB(int productID, String name, int rating, double price, Category category, int quantity) {
        this.productID = productID;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }


}
