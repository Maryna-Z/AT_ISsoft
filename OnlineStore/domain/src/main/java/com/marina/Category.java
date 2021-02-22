package com.marina;

public class Category {
    private int categoryID;
    public String categoryName;

    public Category(){}

    public Category(String categoryName) {
        this.categoryName =  categoryName;
    }

    public  Category(int categoryID,String categoryName){
        this.categoryID = categoryID;
        this.categoryName = categoryName;

    }

    public String getName() {
        return categoryName;
    }

    public int getCategoryID(){
        return categoryID;
    }

    public void setName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryID: " + categoryID + "\n" +
                "categoryName: " + categoryName + "\n" +
                "}";
    }
}