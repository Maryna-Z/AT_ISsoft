import java.util.ArrayList;
import static java.util.stream.Collectors.toCollection;

public class Store {

    ArrayList<Product> products;
    public Store(ArrayList<Product> products){
        this.products = products;
    }

    public ArrayList<Product> checkCategory(Category category){
        ArrayList<Product> clonedProducts = products.stream().map(Product::new).collect(toCollection(ArrayList::new));
        for (int i = 0; i < clonedProducts.size(); i++){
            if (clonedProducts.get(i).getCategory() != category) {
                clonedProducts.remove(i);
                i--;
            }
        }
        return clonedProducts;
    }

    public ArrayList<Product> uncheckCategory(Category category){
        ArrayList<Product> clonedProducts = products.stream().map(Product::new).collect(toCollection(ArrayList::new));
        for (int i = 0; i < clonedProducts.size(); i++){
            if (clonedProducts.get(i).getCategory() == category) {
                clonedProducts.remove(i);
                i--;
            }
        }
        return clonedProducts;
    }

    

}
