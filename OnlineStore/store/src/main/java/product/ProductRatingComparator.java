package product;

import product.Product;

import java.util.Comparator;

public class ProductRatingComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        if (o1.getRating() > o2.getRating())
            return 1;
        else if(o1.getRating() < o2.getRating())
            return -1;
        else
            return 0;
    }
}
