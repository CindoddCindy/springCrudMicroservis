package com.trymicroservice.mymicroservicetry.rest.services;

import com.trymicroservice.mymicroservicetry.rest.dto.ProductDto;

import java.util.List;

public interface ServiceProduct {

    ProductDto findById(Long id);

    List<ProductDto> findAll();

    ProductDto save(ProductDto productDto);

    void deleteById(Long id);
}
