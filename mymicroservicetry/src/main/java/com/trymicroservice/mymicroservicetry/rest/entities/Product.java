package com.trymicroservice.mymicroservicetry.rest.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name="id")
    private Long id;

    @Column(name="product_name", nullable = false)
    private String productName;

    @Column(name="product_quantity", nullable = false)
    private String productQuantity;

    @Column(name="product_code", nullable = false)
    private int productCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User users;


}
