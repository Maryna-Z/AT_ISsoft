import java.util.Objects;

public class Product {
    private String name;
    private int rating;
    private double price;
    private Category category;
    private int quantity;

    public  Product(){};

    public Product(Product product) {
        this.name = product.name;
        this.rating = product.rating;
        this.price = product.price;
        this.category = product.category;
        this.quantity = product.quantity;
    }

    public static class Builder{
        private Product newProduct;

        public Builder() {newProduct = new Product();}

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

        public Builder withCategory(Category category){
            newProduct.category = category;
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

    public Category getCategory() {
        return category;
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
                Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rating, price, category);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name: " + name + '\'' +
                ", rate: " + rating +
                ", price: " + price +
                ", category: " + category.getName() +
                ", quantity: " + quantity +
                "}";
    }

}
