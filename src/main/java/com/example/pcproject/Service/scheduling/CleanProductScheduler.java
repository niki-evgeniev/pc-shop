package com.example.pcproject.Service.scheduling;


import com.example.pcproject.Service.ProductService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CleanProductScheduler {

    private final ProductService productService;

    public CleanProductScheduler(ProductService productService) {
        this.productService = productService;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void cleanUp(){
        productService.cleanExpiredProduct();
        System.out.println("CLEAR PRODUCT THAT HAVE MORE THEN 30 DAYS");
    }
}
