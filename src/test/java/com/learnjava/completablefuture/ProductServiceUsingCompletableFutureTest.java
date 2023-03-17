package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.domain.Review;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceUsingCompletableFutureTest {

    private ProductInfoService productInfoService = new ProductInfoService();
    private ReviewService reviewService = new ReviewService();
    private InventoryService inventoryService =new InventoryService();
    ProductServiceUsingCompletableFuture psucm = new ProductServiceUsingCompletableFuture(productInfoService,reviewService,inventoryService);

    @Test
    void retrieveProductDetails() {
        //given
        String productId = "ABC123";
        //when

        Product product = psucm.retrieveProductDetails(productId);

        //then
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);

    }
    @Test
    void retrieveProductDetails_approach2() throws ExecutionException, InterruptedException {
        //given
        String productId = "ABC123";
        startTimer();

        //when
        CompletableFuture<Product> cfProduct = psucm.retrieveProductDetails_approach2(productId);

        //then
        cfProduct
                .thenAccept((product -> {
                    assertNotNull(product);
                    assertTrue(product.getProductInfo().getProductOptions().size() > 0);
                    assertNotNull(product.getReview());
                }))
                .join();

        timeTaken();
    }

    @Test
    void retrieveProductDetailsWithInventory() {
        //given
        String productId = "ABC123";

        //when
        Product cfProduct = psucm.retrieveProductDetailsWithInventory(productId);

        //then
        assertNotNull(cfProduct);
        assertTrue(cfProduct.getProductInfo().getProductOptions().size() > 0);
        cfProduct.getProductInfo()
                        .getProductOptions()
                                .forEach(productOption -> {
                                    assertNotNull(productOption.getInventory());
                                });
    }

    @Test
    void retrieveProductDetailsWithInventory_approach2() {
        //given
        String productId = "ABC123";

        //when
        Product cfProduct = psucm.retrieveProductDetailsWithInventory(productId);

        //then
        assertNotNull(cfProduct);
        assertTrue(cfProduct.getProductInfo().getProductOptions().size() > 0);
        cfProduct.getProductInfo()
                .getProductOptions()
                .forEach(productOption -> {
                    assertNotNull(productOption.getInventory());
                });
    }

    @Test
    void testRetrieveProductDetailsWithInventory() {

    }
}