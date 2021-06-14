package com.trymicroservice.mymicroservicetry.rest.dto.request;

import com.trymicroservice.mymicroservicetry.rest.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyProductDto implements Serializable {

    private String productName;

    private String productQuantity;

    private int productCode;

    private User user;


}
