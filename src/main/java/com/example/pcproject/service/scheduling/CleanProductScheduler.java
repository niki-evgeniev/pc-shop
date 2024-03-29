package com.example.pcproject.service.scheduling;


import com.example.pcproject.service.ProductService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CleanProductScheduler {

    private final ProductService productService;

    public CleanProductScheduler(ProductService productService) {
        this.productService = productService;
    }

    //    every 6h  to check
    @Scheduled(cron = "0 0 */6 * * *")
//    @Scheduled(cron = "*/10 * * * * *")
    public void cleanUp() {
        productService.cleanExpiredProduct();
        System.out.println("CLEAR PRODUCT THAT HAVE MORE THEN 30 DAYS");
    }
}
