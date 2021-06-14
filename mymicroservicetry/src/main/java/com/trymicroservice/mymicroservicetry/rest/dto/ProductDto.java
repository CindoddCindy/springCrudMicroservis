package com.trymicroservice.mymicroservicetry.rest.dto;

import com.trymicroservice.mymicroservicetry.rest.entities.Product;
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
            this.id = id;
            this.productName = productName;
            this.productQuantity = productQuantity;
            this.productCode = productCode;

        }

    }
}
