package com.marina.services.impl;

import com.marina.entities.Product;

import java.util.Comparator;
import java.util.List;

public class ChainComparator implements Comparator<Product> {
    private List<Comparator<Product>> comparatorList;

    public ChainComparator(List<Comparator<Product>> comparatorList){
        this.comparatorList = comparatorList;
    }

    @Override
    public int compare (Product p1, Product p2) {
        int result;
        for (Comparator<Product> comparator : comparatorList) {
            if ((result = comparator.compare(p1, p2)) != 0) {
                return result;
            }
        }
        return  0;
    }

}
