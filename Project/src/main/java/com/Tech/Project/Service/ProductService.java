package com.Tech.Project.Service;

import com.Tech.Project.Dto.ResponseDto;
import com.Tech.Project.Entity.Product;
import com.Tech.Project.Repository.ProductRepository;
import com.Tech.Project.Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ResponseDto createProduct(Product product) {
        return new ResponseDto(Constants.CREATED, this.productRepository.save(product), HttpStatus.CREATED.value());
    }

    public ResponseDto retriveProduct() {
        return new ResponseDto(Constants.RETRIEVED,this.productRepository.findAll(),HttpStatus.OK.value());
    }

    public ResponseDto retriveProductById(String id) {
        return new ResponseDto(Constants.RETRIEVED,this.productRepository.findById(id),HttpStatus.OK.value());
    }
}
