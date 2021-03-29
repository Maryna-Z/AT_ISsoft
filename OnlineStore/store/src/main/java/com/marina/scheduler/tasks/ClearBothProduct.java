package com.marina.scheduler.tasks;

import com.marina.domain.ProductObj;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ClearBothProduct implements Runnable{
    List<ProductObj> bothProduct;

    @Override
    public void run() {
      bothProduct.clear();
    }
}
