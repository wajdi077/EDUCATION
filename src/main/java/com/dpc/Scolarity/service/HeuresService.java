package com.dpc.Scolarity.service;

import com.dpc.Scolarity.Domain.*;
import com.dpc.Scolarity.Dto.HeuresDTO;;

/**
 * Service Interface for managing Heures.
 */
public interface HeuresService {

 
    HeuresDTO save(HeuresDTO heures);

 
    HeuresDTO findOne(Long id);

 
    void delete(Long id);
}
