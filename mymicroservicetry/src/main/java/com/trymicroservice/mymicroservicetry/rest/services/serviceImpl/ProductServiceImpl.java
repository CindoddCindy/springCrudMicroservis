package com.trymicroservice.mymicroservicetry.rest.services.serviceImpl;

import com.trymicroservice.mymicroservicetry.rest.dto.ProductDto;
import com.trymicroservice.mymicroservicetry.rest.entities.Product;
import com.trymicroservice.mymicroservicetry.rest.entities.User;
import com.trymicroservice.mymicroservicetry.rest.exception.UserNotFoundException;
import com.trymicroservice.mymicroservicetry.rest.repositories.ProductRepository;
import com.trymicroservice.mymicroservicetry.rest.repositories.UserRepository;
import com.trymicroservice.mymicroservicetry.rest.services.ServiceProduct;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ServiceProduct {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    //private final ShelfDtoValidator shelfValidator;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepository,
            UserRepository userRepository
            ) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;

    }

    @Override
    public ProductDto findById(Long id) {
        Product product = this.productRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("Not Found"+id));

        return new ProductDto(product);
    }

    @Override
    public List<ProductDto> findAll() {
        return this.productRepository
                .findAll()
                .stream()
                .map(product -> new ProductDto(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto save(ProductDto productDto) {
       // this.shelfValidator.validate(shelfDto);
        Product product = this.dtoToEntity(productDto);
        Product savedProduct = this.productRepository.save(product);
        return new ProductDto(savedProduct);
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }

    private Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);

        Long user_id = productDto.getUser_id();
        if (user_id != null) {
            User user = this.userRepository
                    .findById(user_id)
                    .orElse(null);

            if (user != null) {
                product.setUser(user);
            }
        }
        return product;
    }
}
