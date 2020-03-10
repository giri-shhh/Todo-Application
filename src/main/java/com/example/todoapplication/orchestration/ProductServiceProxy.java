package com.example.todoapplication.orchestration;

import com.example.todoapplication.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name= "product-service", url = "localhost:8081")
public interface ProductServiceProxy {
    @GetMapping("/products")
    ResponseEntity<List<Product>> getProducts();

}
