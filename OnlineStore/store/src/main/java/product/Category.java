package product;

public abstract class Category {
    private String categoryName;

    public Category(){}

    public String getName() {
        return categoryName;
    }

    public void setName(String categoryName) {
        this.categoryName = categoryName;
    }
}
