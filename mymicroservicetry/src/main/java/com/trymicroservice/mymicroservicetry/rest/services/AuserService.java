package com.trymicroservice.mymicroservicetry.rest.services;

import com.trymicroservice.mymicroservicetry.rest.dto.AuserDTO;

import java.util.List;

public interface AuserService {

    AuserDTO findById(Long id);

    List<AuserDTO> findAll();

    AuserDTO save(AuserDTO auserDTO);

    void deleteById(Long id);
}
