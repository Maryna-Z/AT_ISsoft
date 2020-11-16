import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

public class Store {

    ArrayList<Product> products;
    public Store(ArrayList<Product> products){
        this.products = products;
    }

    public ArrayList<Product> sortByNameAsc(){
        ProductNameComparator nameComparator = new ProductNameComparator();
        ArrayList<Product> sortedByName = (ArrayList<Product>) products.stream()
                .sorted(nameComparator).collect(Collectors.toList());
        return sortedByName;
    }

    public ArrayList<Product> sortByNameDec(){
        ProductNameComparator nameComparator = new ProductNameComparator();
        ArrayList<Product> sortedByName = (ArrayList<Product>) products.stream().distinct()
                .sorted(nameComparator).collect(Collectors.toList());
        return sortedByName;
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

    public ArrayList<Product>sortProduct(Map<String, String> map, String sortingKey){
        ArrayList<Product> sortedListOfProducts = null;
            String value = map.get(sortingKey).trim().toLowerCase();
            switch (sortingKey){
                case "name":
                    if (value.equals("asc")) {
                        sortedListOfProducts = sortByNameAsc();
                    }else if (value.equals("desc")){
                        sortedListOfProducts = sortByNameDec();
                    }
                    break;
                case "price":
                    if (value.equals("asc")) {
                        sortedListOfProducts = sortByPriceAscending();
                    }else if (value.equals("desc")){
                        sortedListOfProducts = sortByPriceDescending();
                    }
                    break;
                case "rating":
                    if (value.equals("asc")) {
                        sortedListOfProducts = sortByRatingAscending();
                    }else if (value.equals("desc")){
                        sortedListOfProducts = sortByRatingDescending();
                    }
                    break;
            }

        return sortedListOfProducts;
    }

    public ArrayList<Product>multipleSort(Map<String, String> map){
        ArrayList<Product> sortedListOfProducts = null;
        for(Map.Entry<String, String> item : map.entrySet()){
            String key = item.getKey().trim().toLowerCase();
            String value = item.getValue().trim().toLowerCase();
            switch (key){
                case "name":
                    if (value.equals("asc")) {
                        sortedListOfProducts = sortByNameAsc();
                    }else if (value.equals("desc")){
                        sortedListOfProducts = sortByNameDec();
                    }
                    break;
                case "price":
                    if (value.equals("asc")) {
                        sortedListOfProducts = sortByPriceAscending();
                    }else if (value.equals("desc")){
                        sortedListOfProducts = sortByPriceDescending();
                    }
                    break;
                case "rating":
                    if (value.equals("asc")) {
                        sortedListOfProducts = sortByRatingAscending();
                    }else if (value.equals("desc")){
                        sortedListOfProducts = sortByRatingDescending();
                    }
                    break;
            }
        }
        return sortedListOfProducts;
    }
}
