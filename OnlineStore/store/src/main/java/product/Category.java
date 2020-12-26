package product;

public abstract class Category {
    private String categoryName;

    public Category(){}

    public String getName1() {
        return categoryName;
    }

    public void setName(String categoryName) {
        this.categoryName = categoryName;
    }
}
