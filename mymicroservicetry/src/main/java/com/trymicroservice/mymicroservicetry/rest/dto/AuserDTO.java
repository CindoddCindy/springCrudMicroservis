package com.trymicroservice.mymicroservicetry.rest.dto;

import com.trymicroservice.mymicroservicetry.rest.entities.Product;
import com.trymicroservice.mymicroservicetry.rest.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class AuserDTO {

    private Long id;
    private String username;

    private String password;

    private String name;

    private String email;


    private List<ProductDto> productDtoList = new ArrayList<>();

    public AuserDTO(User user) {
        BeanUtils.copyProperties(user, this, "products");

        List<Product> products = user.getProducts();
        if(products != null && products.size() > 0) {
            products.forEach(product -> {
                ProductDto productDto = new ProductDto(product);
                this.productDtoList.add(productDto);
            });
        }
    }
}
