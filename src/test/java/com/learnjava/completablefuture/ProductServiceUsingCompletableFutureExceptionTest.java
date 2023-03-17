package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceUsingCompletableFutureExceptionTest {

    @Mock
    private ProductInfoService productInfoService;

    @Mock
    private ReviewService reviewService;

    @Mock
    InventoryService inventoryService;

    @InjectMocks
    ProductServiceUsingCompletableFuture productServiceUsingCompletableFuture;

    @Test
    void testRetrieveProductDetailsWithInventory() {
        //given
        String productId = "ABC123";
        when(productInfoService.retrieveProductInfo(any())).thenCallRealMethod();
        when(reviewService.retrieveReviews(any())).thenThrow(new RuntimeException("Exception Occured"));
        when(inventoryService.addInventory(any())).thenCallRealMethod();

        //when
         Product prodcut = productServiceUsingCompletableFuture.retrieveProductDetailsWithInventory(productId);

        //then
        assertNotNull(prodcut);
        assertTrue(prodcut.getProductInfo().getProductOptions().size() > 0);
        prodcut.getProductInfo()
                .getProductOptions()
                .forEach(productOption -> {
                    assertNotNull(productOption.getInventory());
                });
        assertNotNull(prodcut.getReview());
        assertEquals(0, prodcut.getReview().getNoOfReviews());
    }


    void testRetrieveProductDetailsWithInventory_productSeriveError() {
        //given
        String productId = "ABC123";
        when(productInfoService.retrieveProductInfo(any())).thenThrow(new RuntimeException("Exception Occured"));
        when(reviewService.retrieveReviews(any())).thenCallRealMethod();
        //when(inventoryService.addInventory(any())).thenCallRealMethod();

        //when
        Product prodcut = productServiceUsingCompletableFuture.retrieveProductDetailsWithInventory(productId);

        //then
        assertThrows(RuntimeException.class, ()->productServiceUsingCompletableFuture.retrieveProductDetailsWithInventory(any()));
    }
}