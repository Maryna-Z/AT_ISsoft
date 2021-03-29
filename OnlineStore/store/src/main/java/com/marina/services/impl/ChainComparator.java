package com.marina.services.impl;

import com.marina.domain.ProductObj;

import java.util.Comparator;
import java.util.List;

public class ChainComparator implements Comparator<ProductObj> {
    private List<Comparator<ProductObj>> comparatorList;

    public ChainComparator(List<Comparator<ProductObj>> comparatorList){
        this.comparatorList = comparatorList;
    }

    @Override
    public int compare (ProductObj p1, ProductObj p2) {
        int result;
        for (Comparator<ProductObj> comparator : comparatorList) {
            if ((result = comparator.compare(p1, p2)) != 0) {
                return result;
            }
        }
        return  0;
    }

}
