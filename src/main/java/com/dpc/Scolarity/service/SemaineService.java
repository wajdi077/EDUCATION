package com.dpc.Scolarity.service;

import com.dpc.Scolarity.Dto.SemaineDTO;

public interface SemaineService {

 
    SemaineDTO save(SemaineDTO semaineDTO);


    SemaineDTO findOne(Long id);

    void delete(Long id);
}
