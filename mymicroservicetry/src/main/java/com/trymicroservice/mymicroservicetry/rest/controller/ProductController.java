package com.trymicroservice.mymicroservicetry.rest.controller;

import com.trymicroservice.mymicroservicetry.rest.dto.ProductDto;
import com.trymicroservice.mymicroservicetry.rest.dto.UserDTO;
import com.trymicroservice.mymicroservicetry.rest.dto.request.MyProductDto;
import com.trymicroservice.mymicroservicetry.rest.dto.request.MyUserDto;
import com.trymicroservice.mymicroservicetry.rest.services.ProductService;
import com.trymicroservice.mymicroservicetry.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductController {


    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody MyProductDto myProductDto) {
        return new ResponseEntity(new ProductDto(productService.insertProduct(myProductDto)), null, HttpStatus.CREATED);
    }
}
