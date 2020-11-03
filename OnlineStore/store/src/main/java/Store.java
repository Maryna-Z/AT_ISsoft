import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

public class Store {

    ArrayList<Product> products;
    public Store(ArrayList<Product> products){
        this.products = products;
    }

    public ArrayList<Product> checkCategory(Category category){
        ArrayList<Product> clonedProducts = products.stream().map(Product::new).collect(toCollection(ArrayList::new));
        for (int i = 0; i < clonedProducts.size(); i++){
            String clonedCategoryName = null;
            String paramCategoryName = null;
            Category clonedCategory = clonedProducts.get(i).getCategory();
            Category paramCategory = category;
            try {
                Field clonedFieldCategoryName = clonedCategory.getClass().getDeclaredField("categoryName");
                clonedFieldCategoryName.setAccessible(true);
                clonedCategoryName = (String) clonedFieldCategoryName.get(clonedCategory);

                Field paramFieldCategoryName = paramCategory.getClass().getDeclaredField("categoryName");
                paramFieldCategoryName.setAccessible(true);
                paramCategoryName = (String) paramFieldCategoryName.get(paramCategory);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if (clonedCategoryName != paramCategoryName ) {
                clonedProducts.remove(i);
                i--;
            }
        }
        return clonedProducts;
    }

    public ArrayList<Product> uncheckCategory(Category category){
        ArrayList<Product> clonedProducts = products.stream().map(Product::new).collect(toCollection(ArrayList::new));
        for (int i = 0; i < clonedProducts.size(); i++){
            String clonedCategoryName = null;
            String paramCategoryName = null;
            Category clonedCategory = clonedProducts.get(i).getCategory();
            Category paramCategory = category;
            try {
                Field clonedFieldCategoryName = clonedCategory.getClass().getDeclaredField("categoryName");
                clonedFieldCategoryName.setAccessible(true);
                clonedCategoryName = (String) clonedFieldCategoryName.get(clonedCategory);

                Field paramFieldCategoryName = paramCategory.getClass().getDeclaredField("categoryName");
                paramFieldCategoryName.setAccessible(true);
                paramCategoryName = (String) paramFieldCategoryName.get(paramCategory);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if (clonedCategoryName == paramCategoryName ) {
                clonedProducts.remove(i);
                i--;
            }
        }
        return clonedProducts;
    }

   public ArrayList<Product> sortByPriceAscending(){
        ProductPriceComparator priceComparator = new ProductPriceComparator();
        ArrayList<Product> sortedByPrice = (ArrayList<Product>) products.stream()
                .sorted(priceComparator).collect(Collectors.toList());
        return sortedByPrice;
    }

    public ArrayList<Product> sortByPriceDescending(){
        ProductPriceComparator priceComparator = new ProductPriceComparator();
        ArrayList<Product> sortedByPrice = (ArrayList<Product>) products.stream()
                .distinct().sorted(priceComparator).collect(Collectors.toList());
        return sortedByPrice;
    }

    public ArrayList<Product> sortByRatingAscending(){
        ProductRatingComparator ratingComparator = new ProductRatingComparator();
        ArrayList<Product> sortedByPrice = (ArrayList<Product>) products.stream()
                .sorted(ratingComparator).collect(Collectors.toList());
        return sortedByPrice;
    }

    public ArrayList<Product> sortByRatingDescending(){
        ProductRatingComparator ratingComparator = new ProductRatingComparator();
        ArrayList<Product> sortedByPrice = (ArrayList<Product>) products.stream()
                .distinct().sorted(ratingComparator).collect(Collectors.toList());
        return sortedByPrice;
    }

}
