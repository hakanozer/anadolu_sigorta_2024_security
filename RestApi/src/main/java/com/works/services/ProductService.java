package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ResponseEntity addProduct(Product product) {
        productRepository.save(product);
        if (product.getPid() == null) {
            return new ResponseEntity(product, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(product, HttpStatus.OK);
    }

    public ResponseEntity allProduct() {
        List<Product> productList = productRepository.findAll();
        return new ResponseEntity(productList, HttpStatus.OK);
    }

}
