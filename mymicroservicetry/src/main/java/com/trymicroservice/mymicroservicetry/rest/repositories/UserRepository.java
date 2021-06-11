package com.trymicroservice.mymicroservicetry.rest.repositories;

import com.trymicroservice.mymicroservicetry.rest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}