package com.trymicroservice.mymicroservicetry.rest.dto;

import com.trymicroservice.mymicroservicetry.rest.entities.Product;
import com.trymicroservice.mymicroservicetry.rest.entities.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDto implements Serializable {

    private Long id;

    private String productName;

    private String productQuantity;

    private int productCode;

    public ProductDto(Product product) {

            if (product != null) {
                this.id = product.getId();
                this.productName = product.getProductName();
                this.productQuantity=product.getProductQuantity();
                this.productCode = product.getProductCode();

        }
    }
}
