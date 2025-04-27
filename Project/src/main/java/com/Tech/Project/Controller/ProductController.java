package com.Tech.Project.Controller;

import com.Tech.Project.Dto.ResponseDto;
import com.Tech.Project.Entity.Product;
import com.Tech.Project.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public ResponseDto createProduct(@RequestBody Product product) {
        return this.productService.createProduct(product);
    }

    @GetMapping("/retrive-product")
    public ResponseDto retriveProduct() {
        return this.productService.retriveProduct();
    }

    @GetMapping("/retrive-product/{id}")
    public ResponseDto retriveProduct(@PathVariable String id) {
        return this.productService.retriveProductById(id);
    }
}
