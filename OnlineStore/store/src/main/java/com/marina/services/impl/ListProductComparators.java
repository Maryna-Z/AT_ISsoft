package com.marina.services.impl;

import com.marina.domain.ProductObj;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ListProductComparators {
    Map<String, String> sortConditions;

    public ListProductComparators(Map<String, String> sortConditions) {
        this.sortConditions = sortConditions;
    }

    public List<Comparator<ProductObj>> getComparatorList(Map<String, String> sortConditions) {
        List<Comparator<ProductObj>> comparatorList = new ArrayList<>();
        for (Map.Entry<String, String> item : sortConditions.entrySet()) {
            String key = item.getKey().trim().toLowerCase();
            String value = item.getValue().trim().toLowerCase();
            switch (key) {
                case "name":
                    if (value.equals("asc")) {
                        comparatorList.add(Comparator.comparing(ProductObj::getName));
                    } else if (value.equals("desc")) {
                        Comparator<ProductObj> productNameComparatorDesc = Comparator
                                .comparing(ProductObj::getName, (s1, s2) -> {
                            return s2.compareTo(s1);
                        });
                        comparatorList.add(productNameComparatorDesc);
                    }
                    break;
                case "price":
                    if (value.equals("asc")) {
                        comparatorList.add(Comparator.comparing(ProductObj::getPrice));
                    } else if (value.equals("desc")) {
                        Comparator<ProductObj> productPriceComparatorDesc = Comparator
                                .comparing(ProductObj::getPrice, (s1, s2) -> {
                            return s2.compareTo(s1);
                        });
                        comparatorList.add(productPriceComparatorDesc);
                    }
                case "rating":
                    if (value.equals("asc")) {
                        comparatorList.add(Comparator.comparing(ProductObj::getRating));
                    } else if (value.equals("desc")) {
                        Comparator<ProductObj> productRatingComparatorDesc = Comparator
                                .comparing(ProductObj::getRating, (s1, s2) -> {
                            return s2.compareTo(s1);
                        });
                        comparatorList.add(productRatingComparatorDesc);
                    }
            }
        }
        return comparatorList;
    }
}
