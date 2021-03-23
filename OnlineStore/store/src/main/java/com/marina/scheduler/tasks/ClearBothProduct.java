package com.marina.scheduler.tasks;

import com.marina.entities.Product;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ClearBothProduct implements Runnable{
    List<Product> bothProduct;

    @Override
    public void run() {
      bothProduct.clear();
    }
}
