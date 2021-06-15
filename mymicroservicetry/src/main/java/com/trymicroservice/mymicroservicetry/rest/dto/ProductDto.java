package com.trymicroservice.mymicroservicetry.rest.dto;

import com.trymicroservice.mymicroservicetry.rest.entities.Product;
import com.trymicroservice.mymicroservicetry.rest.entities.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Data
public class ProductDto implements Serializable {

    private Long id;

    private String productName;

    private String productQuantity;


    private Long userId;

    public ProductDto(Product product) {
        BeanUtils.copyProperties(product, this, "userId");

         User user = product.getUser();
        if (user != null) {
            this.userId = user.getId();
        }
    }
}
