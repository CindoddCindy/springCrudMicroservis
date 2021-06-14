package com.trymicroservice.mymicroservicetry.rest.repositories;

import com.trymicroservice.mymicroservicetry.rest.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
