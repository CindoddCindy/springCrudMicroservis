package com.trymicroservice.mymicroservicetry.rest.controller;

import com.trymicroservice.mymicroservicetry.rest.dto.ProductDto;
import com.trymicroservice.mymicroservicetry.rest.dto.UserDTO;
import com.trymicroservice.mymicroservicetry.rest.dto.request.MyProductDto;
import com.trymicroservice.mymicroservicetry.rest.dto.request.MyUserDto;
import com.trymicroservice.mymicroservicetry.rest.entities.Product;
import com.trymicroservice.mymicroservicetry.rest.services.ProductService;
import com.trymicroservice.mymicroservicetry.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products-api")
public class ProductController {

/*
    @Autowired
    private ProductService productService;

    @PostMapping("/get-products")
    public ResponseEntity<List<Product>> getAllProduct() {

        List<Product> products = productService.getAllProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
/*
    @PostMapping("/product")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody MyProductDto myProductDto) {

        MyProductDto prd = productService.addProduct(myProductDto);
        return new ResponseEntity<>(prd, HttpStatus.OK);
    }

 */
/*
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody MyProductDto myProductDto) {
        return new ResponseEntity(new ProductDto(productService.addProduct(myProductDto)), null, HttpStatus.CREATED);
    }

    @PostMapping("/product/edit")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {

        Product prd = productService.editProduct(product);
        return new ResponseEntity<>(prd, HttpStatus.OK);
    }

    @PostMapping("/product/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam(name = "productId") Long productId) {

        productService.deleteProduct(productId);
        return new ResponseEntity<>("Product with ID :" + productId+ " deleted successfully", HttpStatus.OK);
    }

*/




 private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public ProductDto findById(@PathVariable Long id) {
        return this.productService.findById(id);
    }

    @GetMapping
    public List<ProductDto> findAll() {
        return this.productService.findAll();
    }

    @PostMapping
    public ProductDto save(@RequestBody ProductDto productDto) {
        productDto.setId(null);
        return this.productService.save(shelfDto);
    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto productDto) {
        return this.productService.save(productDto);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        this.productService.deleteById(id);
    }


}
