package com.trymicroservice.mymicroservicetry.rest.services.serviceImpl;

import com.trymicroservice.mymicroservicetry.rest.dto.AuserDTO;
import com.trymicroservice.mymicroservicetry.rest.dto.ProductDto;
import com.trymicroservice.mymicroservicetry.rest.entities.Product;
import com.trymicroservice.mymicroservicetry.rest.entities.User;
import com.trymicroservice.mymicroservicetry.rest.exception.InvalidProductDataException;
import com.trymicroservice.mymicroservicetry.rest.exception.InvalidUserDataException;
import com.trymicroservice.mymicroservicetry.rest.repositories.UserRepository;
import com.trymicroservice.mymicroservicetry.rest.services.AuserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuserServiceImpl implements AuserService {

    private final UserRepository userRepository;
    //private final WarehouseDtoValidator warehouseValidator;

    @Autowired
    public AuserServiceImpl(
            UserRepository userRepository
            ) {
        this.userRepository = userRepository;

    }

    @Override
    public AuserDTO findById(Long id) {
        User user = this.userRepository
                .findById(id)
                .orElseThrow(() -> new InvalidProductDataException("Not Found" + id));

        return new AuserDTO(user);
    }

    @Override
    public List<AuserDTO> findAll() {
        return this.userRepository
                .findAll()
                .stream()
                .map(user -> new AuserDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public AuserDTO save(AuserDTO auserDTO) {

        User user = this.dtoToEntity(auserDTO);
        User savedUser = this.userRepository.save(user);
        return new AuserDTO(savedUser);
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    private User dtoToEntity(AuserDTO auserDTO) {
        User user = new User();
        List<Product> products = new ArrayList<>();

        BeanUtils.copyProperties(auserDTO, user, "product");

        List<ProductDto> productDtos = auserDTO.getProductDtoList();
        if(productDtos!= null && productDtos.size() > 0) {
            productDtos.forEach(productDto -> {
                Product product = new Product();
                BeanUtils.copyProperties(productDto, product);
                product.setUser(user);
                products.add(product);
            });
        }
        user.setProducts(products);
        return user;
    }
}
