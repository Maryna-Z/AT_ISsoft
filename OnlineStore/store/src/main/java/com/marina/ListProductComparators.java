package com.marina;

import com.marina.dao.domain.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ListProductComparators {
    Map<String, String> sortConditions;

    public ListProductComparators(Map<String, String> sortConditions) {
        this.sortConditions = sortConditions;
    }

    public List<Comparator<Product>> getComparatorList(Map<String, String> sortConditions) {
        List<Comparator<Product>> comparatorList = new ArrayList<>();
        for (Map.Entry<String, String> item : sortConditions.entrySet()) {
            String key = item.getKey().trim().toLowerCase();
            String value = item.getValue().trim().toLowerCase();
            switch (key) {
                case "name":
                    if (value.equals("asc")) {
                        comparatorList.add(Comparator.comparing(Product::getName));
                    } else if (value.equals("desc")) {
                        Comparator<Product> productNameComparatorDesc = Comparator.comparing(Product::getName, (s1, s2) -> {
                            return s2.compareTo(s1);
                        });
                        comparatorList.add(productNameComparatorDesc);
                    }
                    break;
                case "price":
                    if (value.equals("asc")) {
                        comparatorList.add(Comparator.comparing(Product::getPrice));
                    } else if (value.equals("desc")) {
                        Comparator<Product> productPriceComparatorDesc = Comparator.comparing(Product::getPrice, (s1, s2) -> {
                            return s2.compareTo(s1);
                        });
                        comparatorList.add(productPriceComparatorDesc);
                    }
                case "rating":
                    if (value.equals("asc")) {
                        comparatorList.add(Comparator.comparing(Product::getRating));
                    } else if (value.equals("desc")) {
                        Comparator<Product> productRatingComparatorDesc = Comparator.comparing(Product::getRating, (s1, s2) -> {
                            return s2.compareTo(s1);
                        });
                        comparatorList.add(productRatingComparatorDesc);
                    }
            }
        }
        return comparatorList;
    }
}
