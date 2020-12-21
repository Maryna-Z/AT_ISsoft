import product.Product;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Store {

    List<Product> products;

    public Store(ArrayList<Product> products){
        this.products = products;
    }

    public List<Product> newSortProduct(String categoryName, String fieldName, String sortOrder){
        List<Product> sortedList =  new ArrayList<>();
        switch (fieldName.toLowerCase()) {
            case "name":
                if (sortOrder.toLowerCase().equals("asc")) {
                    sortedList = getProductByCategory(categoryName).stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
                } else if (sortOrder.toLowerCase().equals("desc")) {
                    Comparator<Product> productNameComparatorDesc = Comparator.comparing(Product::getName, (s1, s2) -> {
                        return s2.compareTo(s1);
                    });
                    sortedList = getProductByCategory(categoryName).stream().sorted(productNameComparatorDesc).collect(Collectors.toList());
                }
                break;
            case "price":
                if (sortOrder.toLowerCase().equals("asc")) {
                    sortedList = getProductByCategory(categoryName).stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
                } else if (sortOrder.toLowerCase().equals("desc")) {
                    Comparator<Product> productPriceComparatorDesc = Comparator.comparing(Product::getPrice, (s1, s2) -> {
                        return s2.compareTo(s1);
                    });
                    sortedList = getProductByCategory(categoryName).stream().sorted(productPriceComparatorDesc).collect(Collectors.toList());
                }
                break;
            case "rating":
                if (sortOrder.toLowerCase().equals("asc")) {
                    sortedList = getProductByCategory(categoryName).stream().sorted(Comparator.comparing(Product::getRating)).collect(Collectors.toList());
                } else if (sortOrder.toLowerCase().equals("desc")) {
                    Comparator<Product> productRatingComparatorDesc = Comparator.comparing(Product::getRating, (s1, s2) -> {
                        return s2.compareTo(s1);
                    });
                    sortedList = getProductByCategory(categoryName).stream().sorted(productRatingComparatorDesc).collect(Collectors.toList());
                }
                break;
        }
        return sortedList;
    }

    public List<Product> getProductByCategory(String categoryName){
        List<Product> productsByCategory = products.stream().filter(p -> categoryName.equals(p.getCategory().getName()))
                .collect(Collectors.toList());
        return productsByCategory;
    }
}
