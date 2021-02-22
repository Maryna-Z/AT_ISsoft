package com.marina;
import java.util.Objects;

public class Product {
    private int productID;
    private String name;
    private int rating;
    private double price;
    private int categoryID;
    private int quantity;

    public  Product(){};

    public Product(String name, int rating, double price, int categoryID, int quantity){
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.categoryID = categoryID;
        this.quantity = quantity;
    }

    public Product(Product product) {
        this.productID = product.productID;
        this.name = product.name;
        this.rating = product.rating;
        this.price = product.price;
        this.categoryID = product.categoryID;
        this.quantity = product.quantity;
    }

    public static class Builder{
        private Product newProduct;

        public Builder() {newProduct = new Product();}

        public Builder withProductID(int productID){
            newProduct.productID = productID;
            return this;
        }

        public Builder withName(String name){
            newProduct.name = name;
            return this;
        }

        public Builder withRating(int rating){
            newProduct.rating = rating;
            return this;
        }

        public Builder withPrice(double price){
            newProduct.price = price;
            return this;
        }

        public Builder withCategory(int categoryID){
            newProduct.categoryID = categoryID;
            return this;
        }

        public Builder withQuantity(int quantity){
            newProduct.quantity = quantity;
            return this;
        }

        public Product build(){return newProduct;}
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return rating == product.rating &&
                Double.compare(product.price, price) == 0 &&
                Objects.equals(name, product.name) &&
                categoryID == product.categoryID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rating, price, categoryID);
    }

    @Override
    public String toString() {
        return "com.marina.Product{" +
                "productID: " + productID +
                ", name: " + name +
                ", rating: " + rating +
                ", price: " + price +
                ", categoryID: " + getCategoryID() +
                ", quantity: " + quantity +
                "}";
    }

}
