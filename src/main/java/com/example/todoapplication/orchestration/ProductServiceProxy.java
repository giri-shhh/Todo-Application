package com.example.todoapplication.orchestration;

import com.example.todoapplication.model.Product;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name= "product-service")
@RibbonClient(name= "product-service")
public interface ProductServiceProxy {
    @GetMapping("/products")
    ResponseEntity<List<Product>> getProducts();

}
