package com.dpc.Scolarity.service;

import com.dpc.Scolarity.Dto.SalleDTO;

public interface SalleService {

  
    SalleDTO save(SalleDTO salleDTO);

    
    SalleDTO findOne(Long id);


    void delete(Long id);
}
