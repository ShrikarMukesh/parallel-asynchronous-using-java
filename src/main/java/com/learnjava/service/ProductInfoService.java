package com.learnjava.service;

import com.learnjava.domain.ProductInfo;
import com.learnjava.domain.ProductOption;

import java.util.List;

import static com.learnjava.util.CommonUtil.delay;

public class ProductInfoService {

    public ProductInfo retrieveProductInfo(String productId) {
        delay(1000);
        List<ProductOption> productOptions = List.of(
                new ProductOption(1, "64GB", "Black", 699.99),
                new ProductOption(2, "128GB", "Black", 749.99),
                new ProductOption(3, "8GB", "White", 800),
                new ProductOption(4, "266GB", "Blue", 1127)
        );
        return ProductInfo.builder()
                .productId(productId)
                .productOptions(productOptions)
                .build();
    }
}
