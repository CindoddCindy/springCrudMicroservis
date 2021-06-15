package com.trymicroservice.mymicroservicetry.rest.services;


import com.trymicroservice.mymicroservicetry.rest.dto.request.MyProductDto;
import com.trymicroservice.mymicroservicetry.rest.entities.Product;
import com.trymicroservice.mymicroservicetry.rest.entities.User;
import com.trymicroservice.mymicroservicetry.rest.exception.InvalidUserDataException;
import com.trymicroservice.mymicroservicetry.rest.repositories.ProductRepository;
import com.trymicroservice.mymicroservicetry.rest.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {
    /*

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;


        @Transactional
        public Product insertProduct(MyProductDto myProductDto){

            Optional<User> optionalUser = userRepository.findById(myProductDto.getUser().getId());

            if (!optionalUser.isPresent()){
                throw new InvalidUserDataException("Product Data data cannot be null");
            }

            myProductDto.setUser(optionalUser.get());
            Product product = new Product();
            product.setProductName(myProductDto.getProductName());
            product.setProductQuantity(myProductDto.getProductQuantity());
            product.setProductCode(myProductDto.getProductCode());

            Product productCreated = productRepository.save(product);
            productCreated = productRepository.save(productCreated);

            log.info(String.format("Product %s has been created.", productCreated.getId()));
            return productCreated;

        }


  @Transactional
    public Product createProduct(MyProductDto myProductDto,Long userId
                                 ) {
            Product product = new Product();
        return userRepository.findById(userId).map(user -> {
            myProductDto.setUser(user);

            return productRepository.save(product);
        }).orElseThrow(() -> new InvalidUserDataException("UserId " + userId + " not found"));
    }

     */

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Product> getAllProduct() {

        return productRepository.findAll();
    }

    public Product addProduct(MyProductDto myProductDto) {

        User user = userRepository.findById(myProductDto.getUser().getId()).orElse(null);
        if (null == user) {
            user = new User();
        }

        Product product = new Product();
        product.setProductName(myProductDto.getProductName());
        product.setProductQuantity(myProductDto.getProductQuantity());
        product.setProductCode(myProductDto.getProductCode());


       // User userCreated = userRepository.save(user);
       // userCreated = userRepository.save(userCreated);

        user.setUsername(myProductDto.getUser().getUsername());
        myProductDto.setUser(user);


        return productRepository.save(product);
    }

    public Product editProduct(Product entity) {

        return productRepository.save(entity);
    }

    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }
}
