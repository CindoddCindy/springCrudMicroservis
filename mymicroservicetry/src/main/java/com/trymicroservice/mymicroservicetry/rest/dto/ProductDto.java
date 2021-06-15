package com.trymicroservice.mymicroservicetry.rest.dto;

import com.trymicroservice.mymicroservicetry.rest.entities.Product;
import com.trymicroservice.mymicroservicetry.rest.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;


@Data
@NoArgsConstructor
public class ProductDto  {

    private Long id;

    private String productName;

    private String productQuantity;


    private Long user_id;

    public ProductDto(Product product) {
        BeanUtils.copyProperties(product, this, "user_id");

         User user = product.getUser();
        if (user != null) {
            this.user_id = user.getId();
        }
    }
}
