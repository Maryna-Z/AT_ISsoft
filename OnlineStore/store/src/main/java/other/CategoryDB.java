package other;

public class CategoryDB {
    int categoryID;
    String categoryName;

    public CategoryDB(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "other.CategoryDB{" + "\n" +
                "categoryID: " + categoryID + "\n" +
                "categoryName: " + categoryName + "\n" +
                "}";
    }
}
